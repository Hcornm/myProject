package com.example.reply_api.controller;

import java.util.List;

import com.example.reply_api.model.HmReplyDto;
import com.example.reply_api.service.HmReplyService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("hmReply")
public class HmReplyController {

    @NonNull
    private final HmReplyService hmReplyService;

    /**
     * hmreply 테스트 용
     * 
     * @param hmReplyListParam
     * @return
     */
    @GetMapping("/hmReplyList")
    @CrossOrigin(origins = "*")
    public List<HmReplyDto.hmReply> hmReplyList(@ModelAttribute HmReplyDto.hmReplyListParam hmReplyListParam) {
        return hmReplyService.hmReplyList(hmReplyListParam);
    }

    @GetMapping("/hmReplyList2")
    @CrossOrigin(origins = "*")
    public HmReplyDto.hmReplyTest hmReplyTest(@ModelAttribute HmReplyDto.hmReplyListParam hmReplyListParam) {
        return hmReplyService.hmReplyTest(hmReplyListParam);
    }

    @PostMapping("/hmReplyInsert")
    @CrossOrigin("*")
    public HmReplyDto.hmReplyInsert hmReplyInsert(@RequestBody HmReplyDto.hmReplyInsertParam hmReplyInsertParam) {
        return hmReplyService.hmReplyInsert(hmReplyInsertParam);
    }

    @DeleteMapping("/hmReplyDelete/{hmReplyId}")
    @CrossOrigin("*")
    public HmReplyDto.hmReplyDelete delete(@PathVariable("hmReplyId") Long hmReplyId) {
        return hmReplyService.hmReplyDelete(hmReplyId);
    }

    @PutMapping("/hmReplyUpdate")
    @CrossOrigin(origins = "*")
    public HmReplyDto.hmReplyUpdate hmReplyUpdate(@RequestBody HmReplyDto.hmReplyUpdateParam hmReplyUpdateParam) {
        return hmReplyService.hmReplyUpdate(hmReplyUpdateParam);
    }

}
