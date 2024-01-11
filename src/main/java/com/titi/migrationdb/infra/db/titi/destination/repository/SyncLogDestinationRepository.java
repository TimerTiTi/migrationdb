package com.titi.migrationdb.infra.db.titi.destination.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titi.migrationdb.infra.db.titi.entity.SyncLog;

public interface SyncLogDestinationRepository extends JpaRepository<SyncLog, Integer> {

}
