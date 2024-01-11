package com.titi.migrationdb.batch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.titi.migrationdb.infra.db.titi.destination.repository.DailyDestinationRepository;
import com.titi.migrationdb.infra.db.titi.entity.Daily;
import com.titi.migrationdb.infra.db.titi.source.repository.DailySourceRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MigrationDailyService {

	private final DailySourceRepository dailySourceRepository;
	private final DailyDestinationRepository dailyDestinationRepository;

	@Transactional
	public Page<Daily> migrateInChunks(int pageNumber, int chunkSize) {
		Page<Daily> taskPage = dailySourceRepository.findAll(PageRequest.of(pageNumber, chunkSize));
		dailyDestinationRepository.saveAll(taskPage.getContent());
		log.info("[migrateInChunks] Migration was successful. chunk size: {}. page: {}.",
			taskPage.getNumberOfElements(), taskPage.getNumber());
		return taskPage;
	}

}
