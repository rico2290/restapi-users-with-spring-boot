package com.users.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.users.restapi.models.File;

public interface FileRepository extends JpaRepository<File, Long> {
	File findById(long id);
}
