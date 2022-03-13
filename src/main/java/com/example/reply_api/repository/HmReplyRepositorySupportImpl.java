package com.example.reply_api.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.reply_api.domain.HmReply;
import com.example.reply_api.domain.QHmReply;
import com.example.reply_api.model.HmReplyDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class HmReplyRepositorySupportImpl extends QuerydslRepositorySupport implements HmReplyRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public HmReplyRepositorySupportImpl(EntityManager entityManager) {
        super(HmReply.class);
        super.setEntityManager(entityManager);
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    // fetchJoin = 연관관계에 있는 entity만큼 추가로 쿼리가 실행되는 것을
    // 방지하고 연관관계에 있는 데이터를 조인해서 가져오는 전략
    @Override
    public List<HmReplyDto.hmReply> hmReplyList(HmReplyDto.hmReplyListParam hmReplyListParam) {
        final QHmReply hmReply = QHmReply.hmReply;

        final BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(hmReply.delYn.eq('N'));
        booleanBuilder.and(hmReply.useYn.eq('Y'));

        return jpaQueryFactory.select(Projections.constructor(HmReplyDto.hmReply.class, hmReply))
                .from(hmReply)
                .leftJoin(hmReply.parentHmReply)
                .fetchJoin()
                .where(booleanBuilder)
                .orderBy(hmReply.parentHmReply.hmReplyId.asc().nullsFirst(), hmReply.writeDate.asc()).fetch();
        // null은 맨 앞에 정렬
    }

    @Override
    public List<HmReplyDto.list> hmReplyTestList(Long boardId) {
        final QHmReply hmReply = QHmReply.hmReply;
        final BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(hmReply.isParent.eq(true));
        booleanBuilder.and(hmReply.delYn.eq('N'));
        booleanBuilder.and(hmReply.useYn.eq('Y'));
        booleanBuilder.and(hmReply.boardId.eq(boardId));

        return jpaQueryFactory.select(Projections.constructor(HmReplyDto.list.class, hmReply))
                .from(hmReply)
                .where(booleanBuilder)
                .orderBy(hmReply.sort.asc())
                .fetch();
    }

    @Override
    public HmReply findById(Long parentReplyId) {
        final QHmReply hmReply = QHmReply.hmReply;
        final BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(hmReply.useYn.eq('Y'));
        booleanBuilder.and(hmReply.delYn.eq('N'));
        booleanBuilder.and(hmReply.hmReplyId.eq(parentReplyId));

        return jpaQueryFactory.select(hmReply)
                .from(hmReply)
                .where(booleanBuilder)
                .fetchFirst();
    }

    // @Override
    // public Optional<HmReply> findById(Long parentReplyId) {
    // final QHmReply hmReply = QHmReply.hmReply;
    // final BooleanBuilder booleanBuilder = new BooleanBuilder();

    // booleanBuilder.and(hmReply.useYn.eq('Y'));
    // booleanBuilder.and(hmReply.delYn.eq('N'));
    // booleanBuilder.and(hmReply.parentHmReply.hmReplyId.eq(parentReplyId));

    // return Optional.ofNullable(
    // jpaQueryFactory.select(hmReply)
    // .from(hmReply)
    // .where(booleanBuilder)
    // .fetchFirst()
    // );
    // }

    // @Override
    // public Optional<HmReply> findById(Long parentReplyId) {
    // final QHmReply hmReply = QHmReply.hmReply;
    // final BooleanBuilder booleanBuilder = new BooleanBuilder();

    // booleanBuilder.and(hmReply.delYn.eq('N'));
    // booleanBuilder.and(hmReply.useYn.eq('Y'));
    // booleanBuilder.and(hmReply.parentHmReply.hmReplyId.eq(parentReplyId));

    // }

}
