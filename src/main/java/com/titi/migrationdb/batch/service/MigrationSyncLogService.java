package com.titi.migrationdb.batch.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.titi.migrationdb.infra.db.local.entity.SyncLog;
import com.titi.migrationdb.infra.db.local.entity.User;
import com.titi.migrationdb.infra.db.local.repository.SyncLogRepository;
import com.titi.migrationdb.infra.db.titi.entity.TiTiSyncLog;
import com.titi.migrationdb.infra.db.titi.repository.SyncLogTiTiRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MigrationSyncLogService {

	private final SyncLogTiTiRepository syncLogTiTiRepository;
	private final SyncLogRepository syncLogRepository;

	@Transactional
	public Page<TiTiSyncLog> migrateInChunks(int pageNumber, int chunkSize) {
		Page<TiTiSyncLog> taskPage = syncLogTiTiRepository.findAll(PageRequest.of(pageNumber, chunkSize));
		syncLogRepository.saveAll(taskPage.getContent().stream()
			.map(syncLog -> SyncLog.builder()
				.id(syncLog.getId())
				.user(User.builder().id(syncLog.getUser().getId()).build())
				.dailysCount(syncLog.getDailysCount())
				.uploadCount(syncLog.getUploadCount())
				.createdAt(syncLog.getCreatedAt())
				.updatedAt(syncLog.getUpdatedAt())
				.build()
			)
			.collect(Collectors.toList()));
		log.info("[migrateInChunks] Migration was successful. chunk size: {}. page: {}.",
			taskPage.getNumberOfElements(), taskPage.getNumber());
		return taskPage;
	}

}
