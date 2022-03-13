package com.example.reply_api.repository;

import java.util.List;

import com.example.reply_api.domain.HmReply;
import com.example.reply_api.model.HmReplyDto;
import com.example.reply_api.model.HmReplyDto.list;

public interface HmReplyRepositorySupport {

    /**
     * 
     * @param hmReplyListParam
     * @return
     */
    List<HmReplyDto.hmReply> hmReplyList(HmReplyDto.hmReplyListParam hmReplyListParam);

    /**
     * 
     * @param boardId
     * @return
     */
    List<list> hmReplyTestList(Long boardId);

    HmReply findById(Long parentReplyId);

    // Optional<HmReply> findById(Long parentReplyId);

}
