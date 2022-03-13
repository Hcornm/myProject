package com.example.reply_api.repository;

import java.util.List;

import com.example.reply_api.domain.Board;
import com.example.reply_api.model.BoardDto;

public interface BoardRepositorySupport {

    /**
     * 게시글 불러오기
     * 
     * @return
     */
    List<BoardDto.boardList> boardList(BoardDto.boardListParam boardListParam);

    /**
     * 게시글 상세 조회
     * 
     * @param boardId
     * @return
     */
    Board findByBoard(Long boardId);
}
