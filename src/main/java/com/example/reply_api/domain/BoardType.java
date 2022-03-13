package com.example.reply_api.domain;

import lombok.Getter;

public class BoardType {

    // 게시판 타입 정의
    @Getter
    public enum BoardTypeEnum {

        FREE(0, "FREE", "자유게시판"),
        QUESTION(10, "QUESTION", "질문게시판"),
        NOTICE(20, "NOTICE", "공지사항"),
        BEST(30, "BEST", "베스트"),
        ALL(100, "ALL", "전체");

        private int code;
        private String name;
        private String description;

        private BoardTypeEnum(int code, String name, String description) {
            this.code = code;
            this.name = name;
            this.description = description;
        }

    }
}
