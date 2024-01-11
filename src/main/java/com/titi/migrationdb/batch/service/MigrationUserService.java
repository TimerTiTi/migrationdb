package com.titi.migrationdb.batch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.titi.migrationdb.infra.db.titi.destination.repository.UserDestinationRepository;
import com.titi.migrationdb.infra.db.titi.entity.User;
import com.titi.migrationdb.infra.db.titi.source.repository.UserSourceRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MigrationUserService {

	private final UserSourceRepository userSourceRepository;
	private final UserDestinationRepository userDestinationRepository;

	@Transactional
	public Page<User> migrateInChunks(int pageNumber, int chunkSize) {
		Page<User> taskPage = userSourceRepository.findAll(PageRequest.of(pageNumber, chunkSize));
		userDestinationRepository.saveAll(taskPage.getContent());
		log.info("[migrateInChunks] Migration was successful. chunk size: {}. page: {}.",
			taskPage.getNumberOfElements(), taskPage.getNumber());
		return taskPage;
	}

}
