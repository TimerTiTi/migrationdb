package com.titi.migrationdb.batch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.titi.migrationdb.infra.db.titi.destination.repository.TimeLineDestinationRepository;
import com.titi.migrationdb.infra.db.titi.entity.TimeLine;
import com.titi.migrationdb.infra.db.titi.source.repository.TimeLineSourceRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MigrationTimeLineService {

	private final TimeLineSourceRepository timeLineSourceRepository;
	private final TimeLineDestinationRepository timeLineDestinationRepository;

	@Transactional
	public Page<TimeLine> migrateInChunks(int pageNumber, int chunkSize) {
		Page<TimeLine> taskPage = timeLineSourceRepository.findAll(PageRequest.of(pageNumber, chunkSize));
		timeLineDestinationRepository.saveAll(taskPage.getContent());
		log.info("[migrateInChunks] Migration was successful. chunk size: {}. page: {}.",
			taskPage.getNumberOfElements(), taskPage.getNumber());
		return taskPage;
	}

}
