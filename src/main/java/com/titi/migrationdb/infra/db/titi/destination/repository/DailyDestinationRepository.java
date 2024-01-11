package com.titi.migrationdb.infra.db.titi.destination.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titi.migrationdb.infra.db.titi.entity.Daily;

public interface DailyDestinationRepository extends JpaRepository<Daily, Integer> {

}
