package com.titi.migrationdb.infra.db.titi.source.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titi.migrationdb.infra.db.titi.entity.TimeLine;

public interface TimeLineSourceRepository extends JpaRepository<TimeLine, Integer> {

}
