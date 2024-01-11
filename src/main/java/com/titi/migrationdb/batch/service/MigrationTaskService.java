package com.titi.migrationdb.batch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.titi.migrationdb.infra.db.titi.destination.repository.TaskDestinationRepository;
import com.titi.migrationdb.infra.db.titi.entity.Task;
import com.titi.migrationdb.infra.db.titi.source.repository.TaskSourceRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MigrationTaskService {

	private final TaskSourceRepository taskSourceRepository;
	private final TaskDestinationRepository taskDestinationRepository;

	@Transactional
	public Page<Task> migrateInChunks(int pageNumber, int chunkSize) {
		Page<Task> taskPage = taskSourceRepository.findAll(PageRequest.of(pageNumber, chunkSize));
		taskDestinationRepository.saveAll(taskPage.getContent());
		log.info("[migrateInChunks] Migration was successful. chunk size: {}. page: {}.",
			taskPage.getNumberOfElements(), taskPage.getNumber());
		return taskPage;
	}

}
