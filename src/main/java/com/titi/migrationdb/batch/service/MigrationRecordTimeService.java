package com.titi.migrationdb.batch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.titi.migrationdb.infra.db.titi.destination.repository.RecordTimeDestinationRepository;
import com.titi.migrationdb.infra.db.titi.entity.RecordTime;
import com.titi.migrationdb.infra.db.titi.source.repository.RecordTimeSourceRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MigrationRecordTimeService {

	private final RecordTimeSourceRepository recordTimeSourceRepository;
	private final RecordTimeDestinationRepository recordTimeDestinationRepository;

	@Transactional
	public Page<RecordTime> migrateInChunks(int pageNumber, int chunkSize) {
		Page<RecordTime> taskPage = recordTimeSourceRepository.findAll(PageRequest.of(pageNumber, chunkSize));
		recordTimeDestinationRepository.saveAll(taskPage.getContent());
		log.info("[migrateInChunks] Migration was successful. chunk size: {}. page: {}.",
			taskPage.getNumberOfElements(), taskPage.getNumber());
		return taskPage;
	}

}
