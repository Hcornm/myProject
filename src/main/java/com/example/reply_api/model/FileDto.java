package com.example.reply_api.model;

import com.example.reply_api.domain.File;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FileDto {

    private Long id;
    private String originFileName;
    private String fileName;
    private String filePath;
    private char delYn;

    public File toEntity() {
        File build = File.builder()
                .id(id)
                .originFileName(originFileName)
                .fileName(fileName)
                .filePath(filePath)
                .build();
        return build;
    }

    @Builder
    public FileDto(Long id, String originFileName, String fileName, String filePath) {
        this.id = id;
        this.originFileName = originFileName;
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
