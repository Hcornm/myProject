package com.example.reply_api.domain;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.function.Supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.reply_api.domain.global.BaseEntity;
import com.example.reply_api.domain.global.BaseFunction;
import com.example.reply_api.model.BoardDto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity implements BaseFunction<Board> {

    @Id
    @GeneratedValue
    @Column(name = "boardId")
    private Long boardId;

    // 게시판 타입
    @Column
    private BoardType.BoardTypeEnum boardType;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "hits")
    private int hits;

    @Column(name = "writer")
    private String writer;

    @Column(name = "writeDate")
    private LocalDateTime writeDate;

    protected Board(Long createUserId, LocalDateTime createDateTime, char useYn, char delYn, Long boardId,
            BoardType.BoardTypeEnum boardType, String title, String content, int hits, String writer,
            LocalDateTime writeDate) {
        super(createUserId, createDateTime, useYn, delYn);
        this.boardId = boardId;
        this.boardType = boardType;
        this.title = title;
        this.content = content;
        this.hits = hits;
        this.writer = writer;
        this.writeDate = writeDate;

    }

    @Override
    public Supplier<Board> identity() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Board clone(Board e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Board destory(Board e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean validate(Board e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void doSynchronizerRelationData() {
        // TODO Auto-generated method stub

    }

    /**
     * 게시글 등록
     * 
     * @param boardInsertParam
     * @return
     */
    public static Board doCreate(BoardDto.boardInsertParam boardInsertParam) {
        return new Board(boardInsertParam);
    }

    /**
     * 게시글 등록용 생성자
     * 
     * @param boardInsertParam
     */
    public Board(BoardDto.boardInsertParam boardInsertParam) {
        this.content = boardInsertParam.getContent();
        this.title = boardInsertParam.getTitle();
        this.writer = boardInsertParam.getWriter();
        this.writeDate = epochToTimeConvert(boardInsertParam.getWriteDate()).orElseGet(() -> null);
        this.boardType = boardInsertParam.getBoardType();
    }

    // time 컨버트
    // 추후 글로벌 메소드로 다시 정의 해야함
    public static Optional<LocalDateTime> epochToTimeConvert(Long epochTime) {
        return Optional.of(Instant.ofEpochMilli(epochTime).atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime());
    }

}
