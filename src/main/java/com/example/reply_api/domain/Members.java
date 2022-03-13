package com.example.reply_api.domain;

import java.time.LocalDateTime;
import java.util.function.Supplier;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.reply_api.domain.global.BaseEntity;
import com.example.reply_api.domain.global.BaseFunction;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Members")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Members extends BaseEntity implements BaseFunction<Members> {

    @Id
    @GeneratedValue
    @Column(name = "membersId")
    private Long membersId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "hireDate")
    private String hireDate;

    // public Long getMemberId() {
    // return memberId;
    // }
    // public void setMemberId(Long memberId) {
    // this.memberId = memberId;
    // }
    // public String getUsername() {
    // return username;
    // }
    // public void setUsername(String username) {
    // this.username = username;
    // }
    // public String getHireDate() {
    // return hireDate;
    // }
    // public void setHireDate(String hireDate) {
    // this.hireDate = hireDate;
    // }

    public Members(Long createUserId, LocalDateTime createDateTime, char useYn, char delYn, Long membersId,
            String username, String hireDate) {
        super(createUserId, createDateTime, useYn, delYn);
        this.membersId = membersId;
        this.username = username;
        this.hireDate = hireDate;
    }

    @Override
    public Supplier<Members> identity() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Members clone(Members e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Members destory(Members e) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean validate(Members e) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void doSynchronizerRelationData() {
        // TODO Auto-generated method stub

    }

}
