package com.example.reply_api.service;

import java.util.List;

import com.example.reply_api.model.MembersDto;

public interface MembersService {

    List<MembersDto.membersList> membersList();

    MembersDto.membersInfo membersInfo(MembersDto.membersInfoParam membersInfoParam);
}
