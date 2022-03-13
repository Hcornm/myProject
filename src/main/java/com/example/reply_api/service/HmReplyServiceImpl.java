package com.example.reply_api.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.reply_api.domain.HmReply;
import com.example.reply_api.model.HmReplyDto;
import com.example.reply_api.repository.HmReplyRepository;
import com.example.reply_api.repository.HmReplyRepositoryManager;
import com.example.reply_api.repository.HmReplyRepositorySupport;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class HmReplyServiceImpl implements HmReplyService {

    @NonNull
    private final HmReplyRepositorySupport hmReplyRepositorySupport;

    @NonNull
    private final HmReplyRepositoryManager hmReplyRepositoryManager;

    @NonNull
    private final HmReplyRepository hmReplyRepository;

    @Override
    public List<HmReplyDto.hmReply> hmReplyList(HmReplyDto.hmReplyListParam hmReplyListParam) {
        // TODO Auto-generated method stub
        return hmReplyRepositorySupport.hmReplyList(hmReplyListParam);
    }

    @Override
    public HmReplyDto.hmReplyTest hmReplyTest(HmReplyDto.hmReplyListParam hmReplyListParam) {
        final HmReplyDto.hmReplyTest hmReplyTest = new HmReplyDto.hmReplyTest();

        final List<HmReplyDto.list> replyList = hmReplyRepositorySupport.hmReplyTestList(hmReplyListParam.getBoardId());

        hmReplyTest.setHmReplyList(replyList);

        return hmReplyTest;
    }

    @Override
    public HmReplyDto.hmReplyInsert hmReplyInsert(HmReplyDto.hmReplyInsertParam hmReplyInsertParam) {

        final Optional<HmReply> parentHmReplyOptional = hmReplyRepository
                .findById(hmReplyInsertParam.getParentReplyId());

        final HmReply parentHmReply;

        if (Optional.ofNullable(hmReplyInsertParam.getParentReplyId()).isPresent()) {
            parentHmReply = hmReplyRepositorySupport.findById(hmReplyInsertParam.getParentReplyId());
        } else {
            parentHmReply = parentHmReplyOptional.orElseGet(() -> hmReplyRepository.findByisParent(true).get(0));
        }

        return hmReplyRepositoryManager.hmReplyInsert(hmReplyInsertParam, parentHmReply);
    }

    @Override
    public HmReplyDto.hmReplyDelete hmReplyDelete(Long hmReplyId) {
        final HmReply hmReply = hmReplyRepositorySupport.findById(hmReplyId);

        return hmReplyRepositoryManager.hmReplyDelete(hmReply);
    }

    @Override
    public HmReplyDto.hmReplyUpdate hmReplyUpdate(HmReplyDto.hmReplyUpdateParam hmReplyUpdateParam) {
        final HmReply hmReply = hmReplyRepositorySupport.findById(hmReplyUpdateParam.getHmReplyId());
        return hmReplyRepositoryManager.hmReplyUpdate(hmReplyUpdateParam, hmReply);
    }

}
