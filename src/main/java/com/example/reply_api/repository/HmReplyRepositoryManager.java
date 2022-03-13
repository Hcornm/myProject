package com.example.reply_api.repository;

import com.example.reply_api.domain.HmReply;
import com.example.reply_api.model.HmReplyDto;
import com.example.reply_api.model.HmReplyDto.hmReplyDelete;

public interface HmReplyRepositoryManager {

    /**
     * hm reply 등록
     * 
     * @param hmReplyInsertParam
     * @param parentHmReply
     * @return
     */
    HmReplyDto.hmReplyInsert hmReplyInsert(HmReplyDto.hmReplyInsertParam hmReplyInsertParam, HmReply parentHmReply);

    /**
     * hmreply 삭제
     * 
     * @param hmReply
     * @return
     */
    hmReplyDelete hmReplyDelete(HmReply hmReply);

    /**
     * hmReply 수정
     * 
     * @param hmReplyUpdateParam
     * @param hmReply
     * @return
     */
    HmReplyDto.hmReplyUpdate hmReplyUpdate(HmReplyDto.hmReplyUpdateParam hmReplyUpdateParam, HmReply hmReply);

}
