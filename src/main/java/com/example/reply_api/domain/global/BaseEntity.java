package com.example.reply_api.domain.global;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@MappedSuperclass
@NoArgsConstructor
public class BaseEntity {

    @Column
    protected Long createUserId;

    @Column
    @CreationTimestamp
    protected LocalDateTime createDateTime;

    @Column(nullable = false)
    @ColumnDefault("'Y'")
    protected char useYn = 'Y';

    @Column(nullable = false)
    @ColumnDefault("'N'")
    protected char delYn = 'N';

    /**
     * 
     * @param createUserId
     */
    protected BaseEntity(Long createUserId) {
        this.createUserId = createUserId;
        this.useYn = 'Y';
        this.delYn = 'N';
    }

    /**
     * 
     * @param createUserId
     * @param creaDateTime
     * @param useYn
     * @param delYn
     */
    protected BaseEntity(Long createUserId, LocalDateTime createDateTime, char useYn, char delYn) {
        this.createUserId = createUserId;
        this.createDateTime = createDateTime;
        this.useYn = useYn;
        this.delYn = delYn;
    }

}
