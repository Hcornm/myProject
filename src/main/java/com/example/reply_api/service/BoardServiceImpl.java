package com.example.reply_api.service;

import java.util.List;

import javax.transaction.Transactional;

import com.example.reply_api.domain.Board;
import com.example.reply_api.model.BoardDto;
import com.example.reply_api.repository.BoardRepositoryManager;
import com.example.reply_api.repository.BoardRepositorySupport;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    @NonNull
    private final BoardRepositorySupport boardRepositorySupport;

    @NonNull
    private final BoardRepositoryManager boardRepositoryManager;

    @Override
    public List<BoardDto.boardList> boardList(BoardDto.boardListParam boardListParam) {
        return boardRepositorySupport.boardList(boardListParam);
    }

    @Override
    public BoardDto.boardInsert insert(BoardDto.boardInsertParam boardInsertParam) {
        return boardRepositoryManager.insert(boardInsertParam);
    }

    @Override
    public BoardDto.boardInfo boardInfo(BoardDto.boardInfoParam boardInfoParam) {
        final Long boardId = boardInfoParam.getBoardId();
        final Board board = boardRepositorySupport.findByBoard(boardId);
        return new BoardDto.boardInfo(board);
    }

}
