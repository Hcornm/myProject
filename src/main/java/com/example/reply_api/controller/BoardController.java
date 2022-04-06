package com.example.reply_api.controller;

import java.io.File;
import java.util.List;

import com.example.reply_api.domain.MD5Generator;
import com.example.reply_api.model.BoardDto;
import com.example.reply_api.model.FileDto;
import com.example.reply_api.service.BoardService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {

    @NonNull
    private final BoardService boardService;

    /**
     * 게시판 리스트 조회
     * 
     * @return
     */
    @GetMapping("/boardList")
    @CrossOrigin(origins = "*")
    public List<BoardDto.boardList> boardList(@ModelAttribute BoardDto.boardListParam boardListParam) {
        return boardService.boardList(boardListParam);
    }

    /**
     * 게시판 상세 조회
     * 
     * @param boardInfoParam
     * @return
     */
    @GetMapping("/boardInfo")
    @CrossOrigin(origins = "*")
    public BoardDto.boardInfo boardInfo(@ModelAttribute BoardDto.boardInfoParam boardInfoParam) {
        return boardService.boardInfo(boardInfoParam);
    }

    /**
     * 게시판 등록
     * 
     * @param boardInsertParam
     * @return
     */
    @PostMapping("/boardInsert")
    @CrossOrigin(origins = "*")
    public BoardDto.boardInsert insert(@RequestBody BoardDto.boardInsertParam boardInsertParam) {
        return boardService.insert(boardInsertParam);
    }

    /**
     * 파일 업로드 테스트
     */
    @PostMapping("/fileUpdate")
    @CrossOrigin(origins = "*")
    public String write(@RequestParam("file") MultipartFile files) {
        try {
            String originFileName = files.getOriginalFilename();
            String fileName = new MD5Generator(originFileName).toString();
            /* 실행되는 위치의 'files' 폴더에 파일이 저장됩니다. */
            String savePath = System.getProperty("user.dir") + "\\files";
            /* 파일이 저장되는 폴더가 없으면 폴더를 생성합니다. */
            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdir();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "\\" + fileName;
            files.transferTo(new File(filePath));

            FileDto fileDto = new FileDto();
            fileDto.setOriginFileName(originFileName);
            fileDto.setFileName(fileName);
            fileDto.setFilePath(filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
