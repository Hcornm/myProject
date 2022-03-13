package com.example.reply_api.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.example.reply_api.domain.HmReply;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class HmReplyDto {

    // 댓글 리스트 반환 객체
    @Getter
    @Setter
    public static class hmReply {

        // replyId
        private Long hmReplyId;
        // boardId
        private Long boardId;
        // 내용
        private String content;
        // 작성자
        private String writer;
        // 작성일
        private LocalDateTime writeDate;
        // 상위 댓글
        private HmReply parentHmReply;
        // 순번
        private Long sort;
        // 사용 여부 (추후 삭제 플레그 때 사용)
        private Character useYn;
        // 뎁스
        private Long depth;
        // 대댓글 (자식 댓글)
        private List<HmReply> childrenHmReply = new ArrayList<>();

        public hmReply(HmReply hmReply) {
            this.hmReplyId = hmReply.getHmReplyId();
            this.boardId = hmReply.getBoardId();
            this.content = hmReply.getContent();
            this.writer = hmReply.getWriter();
            this.writeDate = hmReply.getWriteDate();
            this.parentHmReply = hmReply.getParentHmReply();
            this.useYn = hmReply.getUseYn();
            this.depth = hmReply.getDepth();
            // this.childrenHmReply = hmReply.getChildrenHmReply();
            this.childrenHmReply = hmReply.getChildrenHmReply()
                    .stream()
                    .filter(x -> x.getDelYn() == 'N' && x.getUseYn() == 'Y')
                    .collect(Collectors.toList());
        }
    }

    /**
     * 댓글 조회 파라미터
     */
    @Getter
    @Setter
    public static class hmReplyListParam {
        // 게시글 id
        private Long boardId;
    }

    @Getter
    @Setter
    public static class hmReplyTest {
        private List<HmReplyDto.list> hmReplyList = new ArrayList<>();

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class list
            implements parentDataModelingInterface<HmReply, HmReplyDto.list>, dataModelingInterface<HmReply> {
        // 자식댓글
        private List<HmReplyDto.list> childReply = new ArrayList<>();

        //
        private Long createUserId;

        // replyId
        private Long hmReplyId;
        // 게시글di
        private Long boardId;
        // 내용
        private String content;
        // 작성자
        private String writer;
        // 작성일
        private LocalDateTime writeDate;
        // 부모여부
        // private boolean isParent;
        // 상위 댓글
        private HmReply parentHmReply;
        // 순번
        private Long sort;
        // 뎁스 (css 처리를 위해 필요해서 추가)
        private Long depth;

        public list(HmReply t) {
            this.dataModeling(t);
        }

        @Override
        public list parentDataModeling(HmReply t) {
            this.dataModeling(t);
            return this;
        }

        @Override
        public void dataModeling(HmReply t) {
            this.hmReplyId = t.getHmReplyId();
            this.createUserId = t.getCreateUserId();
            this.boardId = t.getBoardId();
            this.content = t.getContent();
            this.writer = t.getWriter();
            this.writeDate = t.getWriteDate();
            this.parentHmReply = t.getParentHmReply();
            this.sort = t.getSort();
            this.depth = t.getDepth();
            // 자식 댓글
            this.childReply = t.getChildrenHmReply()
                    .stream()
                    .filter(x -> x.getDelYn() == 'N' && x.getUseYn() == 'Y')
                    // 정렬할때 주로 Comparator를 사용
                    // HmReply클래스의(인스턴스) sort사용
                    .sorted(Comparator.comparing(HmReply::getSort))
                    // 자기자신을 (객체) 넘기고 자기자신을 리턴받음
                    .map(x -> new HmReplyDto.list().parentDataModeling(x))
                    .collect(Collectors.toList());
        }

    }

    /**
     * 등록
     */
    @Getter
    @Setter
    public static class hmReplyInsert {
        //
        private Long replyId;

        public hmReplyInsert(Long replyId) {
            this.replyId = replyId;
        }

    }

    /**
     * 등록 파라미터
     */
    @Getter
    @Setter
    public static class hmReplyInsertParam {
        // 게시글 id
        private Long boardId;

        // 내용
        private String content;

        // 작성일
        private Long writeDate;

        // 작성자
        private String writer;

        // 부모 댓글 id
        private Long parentReplyId;

        // 순번
        private Long sort;

        // 최상위 여부 (isParent 가 true이면 최상위 댓글)
        private Boolean isParent;

        // 뎁스
        private Long depth;

        private Long createUserId;

        public HmReply insertHmReply(HmReply parentReply) {
            return HmReply.doCreate(this.content, this.sort, this.boardId, this.writer, this.writeDate, this.isParent,
                    this.depth, parentReply, this.createUserId);
        }

    }

    /**
     * 댓글 삭제 반환 객체
     */
    @Getter
    @Setter
    @NoArgsConstructor
    public static class hmReplyDelete {
        private Long hmReplyId;

        public hmReplyDelete(HmReply r) {
            this.hmReplyId = r.getHmReplyId();
        }
    }

    /**
     * 댓글 수정 반환 객체
     */
    @Getter
    @Setter
    @NoArgsConstructor
    public static class hmReplyUpdate {
        // 게시글 id
        private Long boardId;

        // 내용
        private String content;

        // 작성일
        private LocalDateTime writeDate;

        // 작성자
        private String writer;

        private Long sort;

        private Boolean isParent;

        private Long depth;

        public hmReplyUpdate(HmReply r) {
            this.boardId = r.getBoardId();
            this.content = r.getContent();
            this.writeDate = r.getWriteDate();
            this.writer = r.getWriter();
            this.sort = r.getSort();
            this.isParent = r.isParent();
            this.depth = r.getDepth();
        }
    }

    /**
     * 댓글 수정 파라미터
     */
    @Getter
    @Setter
    public static class hmReplyUpdateParam {
        // replyId
        private Long hmReplyId;
        // 내용
        private String content;

        // 작성일
        private Long writeDate;

        // 작성자
        private String writer;

        public Consumer<HmReply> updateHmReply() {
            return hmReply -> hmReply.doUpdate(this);
        }

    }

}
