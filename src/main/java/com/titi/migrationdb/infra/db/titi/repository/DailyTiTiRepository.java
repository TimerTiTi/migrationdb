package com.titi.migrationdb.infra.db.titi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titi.migrationdb.infra.db.titi.entity.TiTiDaily;

public interface DailyTiTiRepository extends JpaRepository<TiTiDaily, Integer> {

}
