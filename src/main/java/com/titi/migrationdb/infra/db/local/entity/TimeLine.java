package com.titi.migrationdb.infra.db.local.entity;

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
@Entity(name = "timelines")
@Table(name = "timelines")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TimeLine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dailyId")
	private Daily daily;

	@Column(nullable = false)
	private Integer time0;

	@Column(nullable = false)
	private Integer time1;

	@Column(nullable = false)
	private Integer time2;

	@Column(nullable = false)
	private Integer time3;

	@Column(nullable = false)
	private Integer time4;

	@Column(nullable = false)
	private Integer time5;

	@Column(nullable = false)
	private Integer time6;

	@Column(nullable = false)
	private Integer time7;

	@Column(nullable = false)
	private Integer time8;

	@Column(nullable = false)
	private Integer time9;

	@Column(nullable = false)
	private Integer time10;

	@Column(nullable = false)
	private Integer time11;

	@Column(nullable = false)
	private Integer time12;

	@Column(nullable = false)
	private Integer time13;

	@Column(nullable = false)
	private Integer time14;

	@Column(nullable = false)
	private Integer time15;

	@Column(nullable = false)
	private Integer time16;

	@Column(nullable = false)
	private Integer time17;

	@Column(nullable = false)
	private Integer time18;

	@Column(nullable = false)
	private Integer time19;

	@Column(nullable = false)
	private Integer time20;

	@Column(nullable = false)
	private Integer time21;

	@Column(nullable = false)
	private Integer time22;

	@Column(nullable = false)
	private Integer time23;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime updatedAt;

}
