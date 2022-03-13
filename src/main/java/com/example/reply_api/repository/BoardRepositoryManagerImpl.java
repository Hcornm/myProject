package com.example.reply_api.repository;

import javax.persistence.EntityManager;

import com.example.reply_api.domain.Board;
import com.example.reply_api.model.BoardDto;

import org.springframework.stereotype.Repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BoardRepositoryManagerImpl implements BoardRepositoryManager {

    @NonNull
    private final EntityManager entityManager;

    // public BoardRepositoryManagerImpl(@NonNull EntityManager entityManager) {
    // this.entityManager = entityManager;
    // }

    @Override
    public BoardDto.boardInsert insert(BoardDto.boardInsertParam boardInsertParam) {
        final Board boardInsert = boardInsertParam.insertBoard().get();

        entityManager.persist(boardInsert);

        return new BoardDto.boardInsert(boardInsert.getBoardId());
    }

}
