package com.titi.migrationdb.infra.db.titi.source.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.titi.migrationdb.infra.db.titi.entity.User;

public interface UserSourceRepository extends JpaRepository<User, Integer> {

}
