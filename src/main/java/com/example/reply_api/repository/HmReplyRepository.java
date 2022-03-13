package com.example.reply_api.repository;

import java.util.List;

import com.example.reply_api.domain.HmReply;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HmReplyRepository extends JpaRepository<HmReply, Long> {

    List<HmReply> findByisParent(boolean isParent);
}
