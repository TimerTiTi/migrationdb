package com.titi.migrationdb.batch.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.titi.migrationdb.infra.db.local.entity.User;
import com.titi.migrationdb.infra.db.local.repository.UserRepository;
import com.titi.migrationdb.infra.db.titi.entity.TiTiUser;
import com.titi.migrationdb.infra.db.titi.repository.UserTiTiRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MigrationUserService {

	private final UserTiTiRepository userTiTiRepository;
	private final UserRepository userRepository;

	@Transactional
	public Page<TiTiUser> migrateInChunks(int pageNumber, int chunkSize) {
		Page<TiTiUser> taskPage = userTiTiRepository.findAll(PageRequest.of(pageNumber, chunkSize));
		userRepository.saveAll(taskPage.getContent().stream()
			.map(user -> User.builder()
				.id(user.getId())
				.username(user.getUsername())
				.password(user.getPassword())
				.email(user.getEmail())
				.createdAt(user.getCreatedAt())
				.updatedAt(user.getUpdatedAt())
				.build()
			)
			.collect(Collectors.toList()));
		log.info("[migrateInChunks] Migration was successful. chunk size: {}. page: {}.",
			taskPage.getNumberOfElements(), taskPage.getNumber());
		return taskPage;
	}

}
