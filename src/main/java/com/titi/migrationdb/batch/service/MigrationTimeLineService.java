package com.titi.migrationdb.batch.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.titi.migrationdb.infra.db.local.entity.Daily;
import com.titi.migrationdb.infra.db.local.entity.TimeLine;
import com.titi.migrationdb.infra.db.local.entity.User;
import com.titi.migrationdb.infra.db.local.repository.TimeLineRepository;
import com.titi.migrationdb.infra.db.titi.entity.TiTiTimeLine;
import com.titi.migrationdb.infra.db.titi.repository.TimeLineTiTiRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class MigrationTimeLineService {

	private final TimeLineTiTiRepository timeLineTiTiRepository;
	private final TimeLineRepository timeLineRepository;

	@Transactional
	public Page<TiTiTimeLine> migrateInChunks(int pageNumber, int chunkSize) {
		Page<TiTiTimeLine> taskPage = timeLineTiTiRepository.findAll(PageRequest.of(pageNumber, chunkSize));
		timeLineRepository.saveAll(taskPage.getContent().stream()
			.map(timeLine -> TimeLine.builder()
				.id(timeLine.getId())
				.user(User.builder().id(timeLine.getUser().getId()).build())
				.daily(Daily.builder().id(timeLine.getDaily().getId()).build())
				.time0(timeLine.getTime0())
				.time1(timeLine.getTime1())
				.time2(timeLine.getTime2())
				.time3(timeLine.getTime3())
				.time4(timeLine.getTime4())
				.time5(timeLine.getTime5())
				.time6(timeLine.getTime6())
				.time7(timeLine.getTime7())
				.time8(timeLine.getTime8())
				.time9(timeLine.getTime9())
				.time10(timeLine.getTime10())
				.time11(timeLine.getTime11())
				.time12(timeLine.getTime12())
				.time13(timeLine.getTime13())
				.time14(timeLine.getTime14())
				.time15(timeLine.getTime15())
				.time16(timeLine.getTime16())
				.time17(timeLine.getTime17())
				.time18(timeLine.getTime18())
				.time19(timeLine.getTime19())
				.time20(timeLine.getTime20())
				.time21(timeLine.getTime21())
				.time22(timeLine.getTime22())
				.time23(timeLine.getTime23())
				.createdAt(timeLine.getCreatedAt())
				.updatedAt(timeLine.getUpdatedAt())
				.build()
			)
			.collect(Collectors.toList()));
		log.info("[migrateInChunks] Migration was successful. chunk size: {}. page: {}.",
			taskPage.getNumberOfElements(), taskPage.getNumber());
		return taskPage;
	}

}
