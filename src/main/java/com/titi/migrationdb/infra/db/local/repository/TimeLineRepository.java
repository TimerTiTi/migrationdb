package com.titi.migrationdb.infra.db.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titi.migrationdb.infra.db.local.entity.TimeLine;

public interface TimeLineRepository extends JpaRepository<TimeLine, Integer> {

}
