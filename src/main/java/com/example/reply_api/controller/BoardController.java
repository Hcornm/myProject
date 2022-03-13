package com.example.reply_api.controller;

import java.util.List;

import com.example.reply_api.model.BoardDto;
import com.example.reply_api.service.BoardService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
