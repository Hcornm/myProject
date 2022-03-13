package com.example.reply_api.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.reply_api.domain.Members;
import com.example.reply_api.model.MembersDto;
import com.example.reply_api.repository.MembersRepositorySupport;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MembersServiceImpl implements MembersService {

    @NonNull
    private final MembersRepositorySupport membersRepositorySupport;

    @Override
    public List<MembersDto.membersList> membersList() {
        return membersRepositorySupport.membersList();
    }

    @Override
    public MembersDto.membersInfo membersInfo(MembersDto.membersInfoParam membersInfoParam) {
        final Optional<Members> memberOptional = membersRepositorySupport.findByIdAndPw(membersInfoParam);
        final Members members = memberOptional.orElseThrow(() -> new IllegalArgumentException("계정 정보가 없습니다"));

        // if(!memberOptional.isPresent()) {
        // throw new IllegalArgumentException("계정 정보가 없습니다");
        // }

        return new MembersDto.membersInfo(members);
    }
}
