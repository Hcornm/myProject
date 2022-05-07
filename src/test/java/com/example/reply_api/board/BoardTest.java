package com.example.reply_api.board;

import javax.transaction.Transactional;

import com.example.reply_api.domain.Board;
import com.example.reply_api.model.BoardDto;
import com.example.reply_api.repository.BoardRepositorySupport;
import com.example.reply_api.service.BoardService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.RequiredArgsConstructor;

@Transactional
@SpringBootTest
public class BoardTest {

    // @Autowired
    // private BoardService boardService;

    @Autowired
    private BoardRepositorySupport boardRepositorySupport;

    /**
     * 게시판 작성 테스트 코드
     */
    @Test
    void BoardTest() {

        BoardDto.boardInfoParam boardInfoParam = new BoardDto.boardInfoParam();

        final Long boardId = (long) 1;

        boardInfoParam.setBoardId(boardId);

        final Board board = boardRepositorySupport.findByBoard(boardInfoParam.getBoardId());

        System.out.println("board = " + board);

    }

}
