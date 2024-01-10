package com.titi.migrationdb.batch.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.titi.migrationdb.infra.db.local.entity.Daily;
import com.titi.migrationdb.infra.db.local.entity.User;
import com.titi.migrationdb.infra.db.local.repository.DailyRepository;
import com.titi.migrationdb.infra.db.titi.entity.TiTiDaily;
import com.titi.migrationdb.infra.db.titi.repository.DailyTiTiRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MigrationDailyService {

	private final DailyTiTiRepository dailyTiTiRepository;
	private final DailyRepository dailyRepository;

	@Transactional
	public Page<TiTiDaily> migrateInChunks(int pageNumber, int chunkSize) {
		Page<TiTiDaily> taskPage = dailyTiTiRepository.findAll(PageRequest.of(pageNumber, chunkSize));
		dailyRepository.saveAll(taskPage.getContent().stream()
			.map(daily -> Daily.builder()
				.id(daily.getId())
				.user(User.builder().id(daily.getUser().getId()).build())
				.day(daily.getDay())
				.maxTime(daily.getMaxTime())
				.timeline(daily.getTimeline())
				.tasks(daily.getTasks())
				.taskHistorys(daily.getTaskHistorys())
				.status(daily.getStatus())
				.createdAt(daily.getCreatedAt())
				.updatedAt(daily.getUpdatedAt())
				.build()
			)
			.collect(Collectors.toList()));
		log.info("[migrateInChunks] Migration was successful. chunk size: {}. page: {}.",
			taskPage.getNumberOfElements(), taskPage.getNumber());
		return taskPage;
	}

}
