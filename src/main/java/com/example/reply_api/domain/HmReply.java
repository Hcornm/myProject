package com.example.reply_api.domain;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.reply_api.domain.global.BaseEntity;
import com.example.reply_api.domain.global.BaseFunction;
import com.example.reply_api.model.HmReplyDto.hmReplyUpdateParam;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class HmReply extends BaseEntity implements BaseFunction<HmReply> {

    @Id
    @GeneratedValue
    @Column(name = "hmReplyId")
    private Long hmReplyId;

    // 게시글id
    @Column(name = "boardId")
    private Long boardId;

    // 댓글 내용
    @Column(name = "content")
    private String content;

    // 작성자
    @Column(name = "writer")
    private String writer;

    // 작성일
    @Column(name = "writeDate")
    private LocalDateTime writeDate;

    @Column(name = "sort")
    private Long sort;

    @Column(name = "isParent")
    private boolean isParent;

    // css 상에 이 댓글이 몇번째 뎁스인지 알기 위해 추가
    @Column(name = "depth")
    private Long depth;

    // 상위 댓글
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private HmReply parentHmReply;

    // 자식 댓글들
    @OneToMany(mappedBy = "parentHmReply")
    private List<HmReply> childrenHmReply = new ArrayList<>();

    public HmReply(Long createUserId, LocalDateTime createDateTime, char useYn, char delYn,
            Long hmReplyId, Long boardId, String content, String writer, LocalDateTime writeDate, Long sort,
            boolean isParent,
            HmReply parentHmReply, Long depth, List<HmReply> childrenHmReply) {
        super(createUserId, createDateTime, useYn, delYn);
        this.hmReplyId = hmReplyId;
        this.boardId = boardId;
        this.content = content;
        this.writer = writer;
        this.writeDate = writeDate;
        this.sort = sort;
        this.isParent = isParent;
        this.depth = depth;
        this.parentHmReply = parentHmReply;
        this.childrenHmReply = childrenHmReply;
    }

    @Override
    public Supplier<HmReply> identity() {
        // TODO Auto-generated method stub
        // ::는 메서드 레퍼런스로 클래스명 뒤에 ::를 두개 찍고 클래스에 적용할 메서드를 붙여준다.
        // HmReply라는 클래스를 레퍼런스해서 HmReply 인스턴스를 새로 생성
        return HmReply::new;
    }

    // 서플라이어는 인자값을 받지 않는다.
    // Supplier<HmReply> identity = () -> new HmReply();

    @Override
    public HmReply clone(HmReply e) {

        return new HmReply(e);
    }

    @Override
    public HmReply destory(HmReply e) {
        final HmReply hmReply = this.clone(e);

        // if (Optional.ofNullable(hmReply.childrenHmReply).isPresent()) {
        if (hmReply.childrenHmReply.size() != 0) {
            // 존재
            hmReply.delYn = 'Y';
        } else {
            hmReply.delYn = 'Y';
            hmReply.useYn = 'N';
        }
        return hmReply;
    }

    @Override
    public boolean validate(HmReply e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void doSynchronizerRelationData() {
        // TODO Auto-generated method stub

    }

    @Builder(access = AccessLevel.PROTECTED)
    protected HmReply(Long hmReplyId, Long boardId, String content, String writer,
            Long writeDate, Long sort, boolean isParent, HmReply parentHmReply, Long depth,
            List<HmReply> childrenHmReply, Long createUserId) {
        this.hmReplyId = hmReplyId;
        this.boardId = boardId;
        this.content = content;
        this.writer = writer;
        this.writeDate = epochToTimeConvert(writeDate).orElseGet(() -> null);
        this.sort = sort;
        this.isParent = isParent;
        this.parentHmReply = parentHmReply;
        this.depth = depth;
        this.childrenHmReply = childrenHmReply;
        this.createUserId = createUserId;
    }

    /**
     * 생성자
     * 
     * @param content
     * @param sort
     * @param boardId
     * @param writeDate
     * @param writer
     * @param parentReply
     * @param createUserId
     * @return
     */
    public static HmReply doCreate(String content, Long sort, Long boardId, String writer, Long writeDate,
            Boolean isParent, Long depth, HmReply parentReply, Long createUserId) {
        return HmReply.builder().boardId(boardId).content(content).sort(sort).parentHmReply(parentReply).writer(writer)
                .writeDate(writeDate).isParent(isParent).depth(depth).parentHmReply(parentReply)
                .createUserId(createUserId).build();
    }

    // /**
    // * 댓글 등록용 생성자
    // *
    // * @param boardInsertParam
    // */
    // public HmReply(HmReplyDto.hmReplyInsertparam hmReplyInsertparam) {
    // this.boardId = hmReplyInsertparam.getBoardId();
    // this.content = hmReplyInsertparam.getContent();
    // this.writer = hmReplyInsertparam.getWriter();

    // this.writeDate =
    // epochToTimeConvert(hmReplyInsertparam.getWriteDate()).orElseGet(() -> null);

    // this.parentHmReply
    // this.childrenHmReply

    // }

    // public static HmReply doCreate(HmReplyDto.hmReplyInsertparam
    // hmReplyInsertparam) {
    // return new HmReply(hmReplyInsertparam);
    // }

    // time 컨버트
    // 추후 글로벌 메소드로 다시 정의 해야함
    public static Optional<LocalDateTime> epochToTimeConvert(Long epochTime) {
        return Optional.of(Instant.ofEpochMilli(epochTime).atZone(ZoneId.of("Asia/Seoul")).toLocalDateTime());
    }

    // 삭제
    public Supplier<HmReply> doRemove() {
        final HmReply cloneReply = this.clone(this);
        return () -> cloneReply.destory(cloneReply);
    }

    // 클론용
    protected HmReply(HmReply r) {
        super(r.createUserId, r.createDateTime, r.useYn, r.delYn);
        this.boardId = r.boardId;
        this.childrenHmReply = r.childrenHmReply;
        this.content = r.content;
        this.hmReplyId = r.hmReplyId;
        this.isParent = r.isParent;
        this.parentHmReply = r.parentHmReply;
        this.sort = r.sort;
        this.writeDate = r.writeDate;
        this.writer = r.writer;
        this.depth = r.depth;
    }

    /**
     * 댓글 수정
     * 
     * @param hmReplyUpdateParam
     */
    public void doUpdate(hmReplyUpdateParam hmReplyUpdateParam) {
        this.content = hmReplyUpdateParam.getContent();
        this.writer = hmReplyUpdateParam.getWriter();
        this.writeDate = epochToTimeConvert(hmReplyUpdateParam.getWriteDate()).orElseGet(() -> null);
    }
}
