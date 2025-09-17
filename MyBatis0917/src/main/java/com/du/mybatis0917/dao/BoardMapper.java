package com.du.mybatis0917.dao;

import com.du.mybatis0917.model.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Select("SELECT * FROM board ORDER BY id DESC")
    List<Board> findAll();

    @Select("SELECT * FROM board WHERE id = #{id}")
    Board findById(int id);

    @Insert("INSERT INTO board (title, content) VALUES (#{title}, #{content})")
    void insert(Board board);

    @Update("UPDATE board SET title = #{title}, content = #{content} WHERE id = #{id}")
    void update(Board board);

    @Delete("DELETE FROM board WHERE id = #{id}")
    void delete(int id);
}
