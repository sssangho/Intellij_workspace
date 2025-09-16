package com.du.mybatis0916.dao;

import com.du.mybatis0916.model.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Select("select * from board order by id desc")
    List<Board> findAll();

    @Select("select * from board where id = #{id}")
    Board findById(int id);

    @Insert("insert into board(title, content) values (#{title}, #{content})")
    void insert(Board board);

    @Update("update board set title = #{title}, content = #{content} where id = #{id}")
    void update(Board board);

    @Delete("delete from board where id = #{id}")
    void delete(int id);
}
