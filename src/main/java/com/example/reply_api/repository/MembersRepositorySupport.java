package com.example.reply_api.repository;

import java.util.List;
import java.util.Optional;

import com.example.reply_api.domain.Members;
import com.example.reply_api.model.MembersDto;

public interface MembersRepositorySupport {

    List<MembersDto.membersList> membersList();

    Optional<Members> findByIdAndPw(MembersDto.membersInfoParam membersInfoParam);
}
