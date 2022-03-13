package com.example.reply_api.model;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import com.example.reply_api.domain.Board;
import com.example.reply_api.domain.BoardType;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardDto {

    @Getter
    @Setter
    public static class boardList {

        // board Id
        private Long boardId;

        // 게시판 타입
        private BoardType.BoardTypeEnum boardType;

        // 제목
        private String title;

        // 내용
        private String content;

        // 조회수
        private int hits;

        // 댓글수
        private Long replyCount;

        // 작성자
        private String writer;

        // 작성일
        private LocalDateTime writeDate;

        public boardList(Board board, Long replyCount) {
            this.boardId = board.getBoardId();
            this.boardType = board.getBoardType();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.hits = board.getHits();
            this.replyCount = replyCount;
            this.writer = board.getWriter();
            this.writeDate = board.getWriteDate();
        }
    }

    /**
     * 게시글 등록 반환 객체
     */
    @Getter
    @Setter
    @NoArgsConstructor
    public static class boardInsert {

        private Long boardId;

        public boardInsert(Long boardId) {
            this.boardId = boardId;
        }
    }

    /**
     * 게시글 등록 파마리터
     */
    @Getter
    @Setter
    public static class boardInsertParam {

        private String title;
        private String content;
        private BoardType.BoardTypeEnum boardType;
        private String writer;
        private Long writeDate;

        // insetBoard.get()으로 사용
        public Supplier<Board> insertBoard() {
            return () -> Board.doCreate(this);
        }
    }

    /**
     * 게시글 상세 반환객체
     */
    @Getter
    @Setter
    @NoArgsConstructor
    public static class boardInfo {
        // board Id
        private Long boardId;

        // 게시판 타입
        private BoardType.BoardTypeEnum boardType;

        // 제목
        private String title;

        // 내용
        private String content;

        // 조회수
        private int hits;

        // 댓글수
        private Long replyCount;

        // 작성자
        private String writer;

        // 작성일
        private LocalDateTime writeDate;

        public boardInfo(Board board) {
            this.boardId = board.getBoardId();
            this.boardType = board.getBoardType();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.hits = board.getHits();
            this.writer = board.getWriter();
            this.writeDate = board.getWriteDate();
        }
    }

    /**
     * 게시글 상세 조회 파마리터
     */
    @Getter
    @Setter
    public static class boardInfoParam {
        // board Id
        private Long boardId;
    }

    /**
     * 게시글 리스트 조회 파라미터
     */
    @Getter
    @Setter
    public static class boardListParam {
        private BoardType.BoardTypeEnum boardType;
    }
}
