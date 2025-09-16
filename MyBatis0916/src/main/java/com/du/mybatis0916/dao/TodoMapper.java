package com.du.mybatis0916.dao;

import com.du.mybatis0916.model.Todo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TodoMapper {

    @Select("select * from todos")
    List<Todo> findAll();

    @Insert("Insert into todos (title) values (#{title})")
    void add(String title);

    @Delete("delete from todos where id = #{id}")
    void delete(int id);

    @Update("update todos set completed = not completed where id  = #{id}")
    void toggleCompleted(int id);
}
