package com.example.reply_api.repository;

import javax.persistence.EntityManager;

import com.example.reply_api.domain.HmReply;
import com.example.reply_api.model.HmReplyDto;

import org.springframework.stereotype.Repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HmReplyRepositoryManagerlmpl implements HmReplyRepositoryManager {

    @NonNull
    private final EntityManager entityManager;

    @Override
    public HmReplyDto.hmReplyInsert hmReplyInsert(HmReplyDto.hmReplyInsertParam hmReplyInsertParam,
            HmReply parentHmReply) {
        HmReply hmReply = hmReplyInsertParam.insertHmReply(parentHmReply);

        entityManager.persist(hmReply);
        return new HmReplyDto.hmReplyInsert(hmReply.getHmReplyId());
    }

    @Override
    public HmReplyDto.hmReplyDelete hmReplyDelete(HmReply hmReply) {
        // 서플라이어를 사용할려면 get()해줘야함
        final HmReply hmReplyDelete = entityManager.merge(hmReply.doRemove().get());
        return new HmReplyDto.hmReplyDelete(hmReplyDelete);
    }

    @Override
    public HmReplyDto.hmReplyUpdate hmReplyUpdate(HmReplyDto.hmReplyUpdateParam hmReplyUpdateParam, HmReply hmReply) {
        hmReplyUpdateParam.updateHmReply().accept(hmReply);
        return new HmReplyDto.hmReplyUpdate(hmReply);
    }

    // @Override
    // public HmReply hmReplyInsert(HmReplyDto.hmReplyInsertParam
    // hmReplyInsertParam,HmReply parentHmReply) {

    // HmReply hmReply = hmReplyInsertParam.insertHmReply(parentHmReply);

    // entityManager.persist(hmReply);

    // return hmReply;
    // }

    // MyReply myReply = insertParam.toEnityMyReply(parentReply);

    // entityManager.persist(myReply);

    // return myReply;

}