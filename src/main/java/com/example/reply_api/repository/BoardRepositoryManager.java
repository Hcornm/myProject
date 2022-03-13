package com.example.reply_api.repository;

import com.example.reply_api.model.BoardDto;

public interface BoardRepositoryManager {

    /**
     * 게시글 작성
     * 
     * @param boardInsertParam
     * @return
     */
    BoardDto.boardInsert insert(BoardDto.boardInsertParam boardInsertParam);

}
