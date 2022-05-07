package com.example.reply_api.board;

import javax.transaction.Transactional;

import com.example.reply_api.domain.Board;
import com.example.reply_api.domain.BoardType;
import com.example.reply_api.model.BoardDto;
import com.example.reply_api.repository.BoardRepositoryManager;
import com.example.reply_api.repository.BoardRepositorySupport;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@Transactional
@SpringBootTest
public class BoardTest {

    // @Autowired
    // private BoardService boardService;

    @Autowired
    private BoardRepositorySupport boardRepositorySupport;

    @Autowired
    private BoardRepositoryManager boardRepositoryManager;

    /**
     * 게시판 작성 테스트 코드
     */
    @Test
    void BoardInfoTest() {

        BoardDto.boardInfoParam boardInfoParam = new BoardDto.boardInfoParam();

        final Long boardId = (long) 1;

        boardInfoParam.setBoardId(boardId);

        final Board board = boardRepositorySupport.findByBoard(boardInfoParam.getBoardId());

        System.out.println("board = " + board);

    }

    @Test
    @DisplayName("게시판 작성 테스트")
    // 테스트 코드로 만든 데이터는 기본적으로 퍼시스트 돠지 않는 것 같음
    // Rollback(false) 라는 값을 주지 않으면 자동으로 Rollback(true)로 인식해서 데이터를 영속화 하지 않음
    // Rollback(false) 어노테이션을 명시해줌으로써 데이터베이스에 데이터 생성된 것 확인
    @Rollback(false)
    void BoardInsertTest() {

        // 게시글 필드 값 생성
        final String title = "테스트 코드 작성 중";
        final String content = "테스트 코드로 작성한 내용 입니다.";
        final BoardType.BoardTypeEnum boardType = BoardType.BoardTypeEnum.BEST;
        final String writer = "어드민";
        final Long writeDate = (long) 1651930100;

        // 인서트 파라미터 생성
        BoardDto.boardInsertParam insertParam = new BoardDto.boardInsertParam(
                title,
                content,
                boardType,
                writer,
                writeDate);

        // persist
        boardRepositoryManager.insert(insertParam);
    }

}
