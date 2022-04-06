package com.example.reply_api.service;

import javax.transaction.Transactional;

import com.example.reply_api.domain.File;
import com.example.reply_api.model.FileDto;
import com.example.reply_api.repository.FileRepository;

import org.springframework.stereotype.Service;

@Service
public class FileService {
    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Transactional
    public Long saveFile(FileDto fileDto) {
        return fileRepository.save(fileDto.toEntity()).getId();
    }

    @Transactional
    public FileDto getFile(Long id) {
        File file = fileRepository.findById(id).get();

        FileDto fileDto = FileDto.builder()
                .id(id)
                .originFileName(file.getOriginFileName())
                .fileName(file.getFileName())
                .filePath(file.getFilePath())
                .build();
        return fileDto;
    }
}
