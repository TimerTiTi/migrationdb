package com.titi.migrationdb.infra.db.titi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titi.migrationdb.infra.db.titi.entity.TiTiTask;

public interface TaskTiTiRepository extends JpaRepository<TiTiTask, Integer> {

}
