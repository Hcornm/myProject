package com.example.reply_api.service;

import java.util.List;

import com.example.reply_api.model.HmReplyDto;
import com.example.reply_api.model.HmReplyDto.hmReplyDelete;
import com.example.reply_api.model.HmReplyDto.hmReplyInsert;
import com.example.reply_api.model.HmReplyDto.hmReplyTest;
import com.example.reply_api.model.HmReplyDto.hmReplyUpdate;

public interface HmReplyService {

    List<HmReplyDto.hmReply> hmReplyList(HmReplyDto.hmReplyListParam hmReplyListParam);

    hmReplyTest hmReplyTest(HmReplyDto.hmReplyListParam hmReplyListParam);

    hmReplyInsert hmReplyInsert(HmReplyDto.hmReplyInsertParam hmReplyInsertParam);

    hmReplyDelete hmReplyDelete(Long hmReplyId);

    hmReplyUpdate hmReplyUpdate(HmReplyDto.hmReplyUpdateParam hmReplyUpdateParam);

}
