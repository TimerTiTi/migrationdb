package com.titi.migrationdb.infra.db.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titi.migrationdb.infra.db.local.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
