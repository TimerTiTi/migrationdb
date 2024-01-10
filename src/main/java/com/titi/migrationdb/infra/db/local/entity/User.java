package com.titi.migrationdb.infra.db.local.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "users")
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	private Integer id;

	@Column(length = 45, unique = true, nullable = false)
	private String username;

	@Column(length = 128, nullable = false)
	private String password;

	@Column(length = 128, nullable = false)
	private String email;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime updatedAt;

}
