package com.titi.migrationdb.batch.task;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.titi.migrationdb.batch.service.MigrationRecordTimeService;
import com.titi.migrationdb.infra.db.titi.entity.TiTiRecordTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class MigrationRecordTimeTasklet implements Tasklet {

	private final MigrationRecordTimeService migrationRecordTimeService;
	@Value("${spring.batch.chunk-size}")
	private int chunkSize;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
		log.info("[execute] Migration was started.");
		int pageNumber = 0;
		Page<TiTiRecordTime> recordTimePage;
		do {
			recordTimePage = migrationRecordTimeService.migrateInChunks(pageNumber++, chunkSize);
		} while (recordTimePage.hasNext());
		log.info("[execute] Migration was successful. total size = {}", recordTimePage.getTotalElements());
		return RepeatStatus.FINISHED;
	}

}
