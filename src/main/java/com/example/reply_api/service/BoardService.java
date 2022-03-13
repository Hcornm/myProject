package com.example.reply_api.service;

import java.util.List;

import com.example.reply_api.model.BoardDto;

public interface BoardService {

    /**
     * 게시글 리스트 조회
     * 
     * @return
     */
    List<BoardDto.boardList> boardList(BoardDto.boardListParam boardListParam);

    /**
     * 게시글 등록
     * 
     * @param boardInsertParam
     * @return
     */
    BoardDto.boardInsert insert(BoardDto.boardInsertParam boardInsertParam);

    /**
     * 게시글 상세 조회
     * 
     * @param boardInfoParam
     * @return
     */
    BoardDto.boardInfo boardInfo(BoardDto.boardInfoParam boardInfoParam);
}
