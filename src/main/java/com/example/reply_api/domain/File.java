package com.example.reply_api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 파일 엔티티
 */
@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class File {

    @Id
    @GeneratedValue
    @Column(name = "fileID")
    private Long id;

    // 실제 파일네임
    @Column(nullable = false)
    private String originFileName;

    // 서버에 저장되는 파일명
    @Column(nullable = false)
    private String fileName;

    // 서버에 저장되는 파일 경로
    @Column(nullable = false)
    private String filePath;

    @Column
    @ColumnDefault("'N'")
    private char delYn = 'N';

    @Builder
    public File(Long id, String originFileName, String fileName, String filePath) {
        this.id = id;
        this.originFileName = originFileName;
        this.fileName = fileName;
        this.filePath = filePath;
    }

    // public File toEntity() {
    // File build = File.builder()
    // .id(id)
    // .originFileName(originFileName)
    // .fileName(fileName)
    // .filePath(filePath)
    // .delYn(delYn)
    // .build();
    // return build;
    // }
}
