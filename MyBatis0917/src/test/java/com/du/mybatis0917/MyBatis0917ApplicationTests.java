package com.du.mybatis0917;

import com.du.mybatis0917.dao.BoardMapper;
import com.du.mybatis0917.model.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MyBatis0917ApplicationTests {

    @Autowired
    private BoardMapper boardMapper;

    @Test
    void 내가만든처음테스트() {
        List<Board> boards = boardMapper.findAll();
        for (Board board : boards) {
            System.out.println(board);
        }
    }

    @Test
    void testInsertAndFindById() {
        // given
        Board board = new Board();
        board.setTitle("테스트 제목");
        board.setContent("테스트 내용");

        // when
        boardMapper.insert(board);
        List<Board> boards = boardMapper.findAll();
        Board saved = boards.get(0);
//        System.out.println(saved);

        // then
        assertThat(saved.getTitle()).isEqualTo("테스트 제목");
        assertThat(saved.getContent()) .isEqualTo("테스트 내용");
    }
    
    @Test
    void testUpdate() {
        Board board = new Board();
        board.setTitle("원래 제목");
        board.setContent("원래 내용");
        boardMapper.insert(board);
        
        Board saved = boardMapper.findAll().get(0);
        saved.setTitle("수정된 제목");
        saved.setContent("수정된 내용");
        
        boardMapper.update(saved);
        Board updated = boardMapper.findById(saved.getId());
        
        assertThat(updated.getTitle()).isEqualTo("수정된 제목");
        assertThat(updated.getContent()).isEqualTo("수정된 내용");
    }

    @Test
    void testDelete() {
        Board board = new Board();
        board.setTitle("삭제용 제목");
        board.setContent("삭제용 내용");
        boardMapper.insert(board);

        Board saved = boardMapper.findAll().get(0);
        boardMapper.delete(saved.getId());

        Board result = boardMapper.findById(saved.getId());
        assertThat(result).isNull();
    }

}
