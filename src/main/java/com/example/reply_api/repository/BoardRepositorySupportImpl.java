package com.example.reply_api.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.example.reply_api.domain.Board;
import com.example.reply_api.domain.QBoard;
import com.example.reply_api.domain.QHmReply;
import com.example.reply_api.model.BoardDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class BoardRepositorySupportImpl extends QuerydslRepositorySupport implements BoardRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public BoardRepositorySupportImpl(EntityManager entityManager) {
        super(Board.class);
        super.setEntityManager(entityManager);
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<BoardDto.boardList> boardList(BoardDto.boardListParam boardListParam) {
        final QBoard board = QBoard.board;
        // final QReply reply = QReply.reply;
        final QHmReply hmReply = QHmReply.hmReply;

        final BooleanBuilder booleanBuilder = new BooleanBuilder();
        final BooleanBuilder booleanBuilderForHmReply = new BooleanBuilder();

        /*
         * select bo.*, count(re.boardid)
         * from board bo
         * left join reply re
         * on bo.boardId = re.boardId
         * group by bo.boardid;
         */

        booleanBuilder.and(board.delYn.eq('N'));
        booleanBuilder.and(board.useYn.eq('Y'));
        booleanBuilder.and(board.boardType.eq(boardListParam.getBoardType()));

        booleanBuilderForHmReply.and(hmReply.delYn.eq('N'));

        // return
        // jpaQueryFactory.select(Projections.constructor(BoardDto.boardList.class,
        // board, reply.count()))
        // .from(board)
        // .leftJoin(reply)
        // .on(board.boardId.eq(reply.boardId))
        // .where(booleanBuilder)
        // .groupBy(board.boardId)
        // .orderBy(board.writeDate.desc())
        // .fetch();
        return jpaQueryFactory.select(Projections.constructor(BoardDto.boardList.class, board, hmReply.count()))
                .from(board)
                .leftJoin(hmReply)
                .on(board.boardId.eq(hmReply.boardId).and(booleanBuilderForHmReply))
                .where(booleanBuilder)
                .groupBy(board.boardId)
                .orderBy(board.writeDate.desc())
                .fetch();
    }

    @Override
    public Board findByBoard(Long boardId) {
        final QBoard board = QBoard.board;
        final BooleanBuilder booleanBuilder = new BooleanBuilder();

        booleanBuilder.and(board.delYn.eq('N'));
        booleanBuilder.and(board.useYn.eq('Y'));
        booleanBuilder.and(board.boardId.eq(boardId));

        return jpaQueryFactory.select(board)
                .from(board)
                .where(booleanBuilder)
                .fetchFirst();
    }
}
