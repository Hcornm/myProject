package com.example.reply_api.controller;

import com.example.reply_api.model.MembersDto;
import com.example.reply_api.service.MembersService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("members")
public class MembersController {

    @NonNull
    private final MembersService membersService;

    @GetMapping("/login")
    @CrossOrigin(origins = "*")
    public MembersDto.membersInfo membersInfo(@ModelAttribute MembersDto.membersInfoParam membersInfoParam) {
        return membersService.membersInfo(membersInfoParam);
    }

}
