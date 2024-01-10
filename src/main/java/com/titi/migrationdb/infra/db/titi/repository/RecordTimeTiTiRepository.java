package com.titi.migrationdb.infra.db.titi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titi.migrationdb.infra.db.titi.entity.TiTiRecordTime;

public interface RecordTimeTiTiRepository extends JpaRepository<TiTiRecordTime, Integer> {

}
