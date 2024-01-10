package com.titi.migrationdb.infra.db.titi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titi.migrationdb.infra.db.titi.entity.TiTiSyncLog;

public interface SyncLogTiTiRepository extends JpaRepository<TiTiSyncLog, Integer> {

}
