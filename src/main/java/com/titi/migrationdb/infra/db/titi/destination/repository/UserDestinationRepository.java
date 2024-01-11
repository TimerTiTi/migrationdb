package com.titi.migrationdb.infra.db.titi.destination.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titi.migrationdb.infra.db.titi.entity.User;

public interface UserDestinationRepository extends JpaRepository<User, Integer> {

}
