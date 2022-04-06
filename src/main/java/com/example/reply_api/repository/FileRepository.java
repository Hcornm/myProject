package com.example.reply_api.repository;

import com.example.reply_api.domain.File;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {

}
