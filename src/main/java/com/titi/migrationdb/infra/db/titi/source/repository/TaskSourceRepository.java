package com.titi.migrationdb.infra.db.titi.source.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titi.migrationdb.infra.db.titi.entity.Task;

public interface TaskSourceRepository extends JpaRepository<Task, Integer> {

}
