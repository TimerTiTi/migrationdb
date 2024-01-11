package com.titi.migrationdb.batch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.titi.migrationdb.infra.db.titi.destination.repository.SyncLogDestinationRepository;
import com.titi.migrationdb.infra.db.titi.entity.SyncLog;
import com.titi.migrationdb.infra.db.titi.source.repository.SyncLogSourceRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MigrationSyncLogService {

	private final SyncLogSourceRepository syncLogSourceRepository;
	private final SyncLogDestinationRepository syncLogDestinationRepository;

	@Transactional
	public Page<SyncLog> migrateInChunks(int pageNumber, int chunkSize) {
		Page<SyncLog> taskPage = syncLogSourceRepository.findAll(PageRequest.of(pageNumber, chunkSize));
		syncLogDestinationRepository.saveAll(taskPage.getContent());
		log.info("[migrateInChunks] Migration was successful. chunk size: {}. page: {}.",
			taskPage.getNumberOfElements(), taskPage.getNumber());
		return taskPage;
	}

}
