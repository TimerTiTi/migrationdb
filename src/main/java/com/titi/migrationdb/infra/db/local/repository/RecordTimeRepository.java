package com.titi.migrationdb.infra.db.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titi.migrationdb.infra.db.local.entity.RecordTime;

public interface RecordTimeRepository extends JpaRepository<RecordTime, Integer> {

}
