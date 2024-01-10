package com.titi.migrationdb.infra.db.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.criteria.CriteriaBuilder;

import com.titi.migrationdb.infra.db.local.entity.Daily;

public interface DailyRepository extends JpaRepository<Daily, CriteriaBuilder.In> {

}
