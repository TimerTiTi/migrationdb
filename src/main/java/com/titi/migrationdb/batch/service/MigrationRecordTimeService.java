package com.titi.migrationdb.batch.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.titi.migrationdb.infra.db.local.entity.RecordTime;
import com.titi.migrationdb.infra.db.local.entity.User;
import com.titi.migrationdb.infra.db.local.repository.RecordTimeRepository;
import com.titi.migrationdb.infra.db.titi.entity.TiTiRecordTime;
import com.titi.migrationdb.infra.db.titi.repository.RecordTimeTiTiRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MigrationRecordTimeService {

	private final RecordTimeTiTiRepository recordTimeTiTiRepository;
	private final RecordTimeRepository recordTimeRepository;

	@Transactional
	public Page<TiTiRecordTime> migrateInChunks(int pageNumber, int chunkSize) {
		Page<TiTiRecordTime> taskPage = recordTimeTiTiRepository.findAll(PageRequest.of(pageNumber, chunkSize));
		recordTimeRepository.saveAll(taskPage.getContent().stream()
			.map(recordTime -> RecordTime.builder()
				.id(recordTime.getId())
				.user(User.builder().id(recordTime.getUser().getId()).build())
				.settedTimerTime(recordTime.getSettedTimerTime())
				.settedGoalTime(recordTime.getSettedGoalTime())
				.savedSumTime(recordTime.getSavedSumTime())
				.savedTimerTime(recordTime.getSavedTimerTime())
				.savedStopwatchTime(recordTime.getSavedStopwatchTime())
				.savedGoalTime(recordTime.getSavedGoalTime())
				.recordingMode(recordTime.getRecordingMode())
				.recordTask(recordTime.getRecordTask())
				.recordTaskFromTime(recordTime.getRecordTaskFromTime())
				.recording(recordTime.getRecording())
				.recordStartAt(recordTime.getRecordStartAt())
				.recordStartTimeline(recordTime.getRecordStartTimeline())
				.createdAt(recordTime.getCreatedAt())
				.updatedAt(recordTime.getUpdatedAt())
				.build()
			)
			.collect(Collectors.toList()));
		log.info("[migrateInChunks] Migration was successful. chunk size: {}. page: {}.",
			taskPage.getNumberOfElements(), taskPage.getNumber());
		return taskPage;
	}

}
