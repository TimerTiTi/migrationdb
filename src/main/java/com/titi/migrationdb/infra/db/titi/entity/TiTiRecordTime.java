package com.titi.migrationdb.infra.db.titi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "recordTimes")
@Table(name = "recordTimes")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TiTiRecordTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private TiTiUser user;

	@Column(nullable = false)
	private Integer settedTimerTime;

	@Column(nullable = false)
	private Integer settedGoalTime;

	@Column(nullable = false)
	private Integer savedSumTime;

	@Column(nullable = false)
	private Integer savedTimerTime;

	@Column(nullable = false)
	private Integer savedStopwatchTime;

	@Column(nullable = false)
	private Integer savedGoalTime;

	@Column(nullable = false)
	private Integer recordingMode;

	@Column(nullable = false)
	private String recordTask;

	@Column(nullable = false)
	private Integer recordTaskFromTime;

	@Column(nullable = false)
	private Boolean recording;

	@Column(nullable = false)
	private LocalDateTime recordStartAt;

	@Column(columnDefinition = "JSON", nullable = false)
	private String recordStartTimeline;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime updatedAt;

}
