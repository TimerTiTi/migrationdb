package com.titi.migrationdb.batch.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.titi.migrationdb.infra.db.local.entity.Daily;
import com.titi.migrationdb.infra.db.local.entity.Task;
import com.titi.migrationdb.infra.db.local.entity.User;
import com.titi.migrationdb.infra.db.local.repository.TaskRepository;
import com.titi.migrationdb.infra.db.titi.entity.TiTiTask;
import com.titi.migrationdb.infra.db.titi.repository.TaskTiTiRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MigrationTaskService {

	private final TaskTiTiRepository taskTiTiRepository;
	private final TaskRepository taskRepository;

	@Transactional
	public Page<TiTiTask> migrateInChunks(int pageNumber, int chunkSize) {
		Page<TiTiTask> taskPage = taskTiTiRepository.findAll(PageRequest.of(pageNumber, chunkSize));
		taskRepository.saveAll(taskPage.getContent().stream()
			.map(task -> Task.builder()
				.id(task.getId())
				.user(User.builder().id(task.getUser().getId()).build())
				.daily(Daily.builder().id(task.getDaily().getId()).build())
				.taskName(task.getTaskName())
				.taskTime(task.getTaskTime())
				.createdAt(task.getCreatedAt())
				.updatedAt(task.getUpdatedAt())
				.build()
			)
			.collect(Collectors.toList()));
		log.info("[migrateInChunks] Migration was successful. chunk size: {}. page: {}.",
			taskPage.getNumberOfElements(), taskPage.getNumber());
		return taskPage;
	}

}
