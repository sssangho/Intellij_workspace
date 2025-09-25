package com.du.test0925.mapper;

import com.du.test0925.domain.Todo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TodoMapper {

    @Select("SELECT * FROM todo ORDER BY completed, due_date")
    List<Todo> findAll();


    @Select("select * from todo where id = #{id}")
    Todo findById(Long id);

    @Insert("insert into todo (content, due_date, category, completed) values (#{content}, #{dueDate}, #{category}, #{completed})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Todo todo);

    @Update("update todo set content = #{content}, due_date = #{dueDate}, category = #{category}, completed = #{completed} where id = #{id}")
    void update (Todo todo);

    @Delete("Delete from todo where id = #{id}")
    void delete(Long id);

    @Update("UPDATE todo SET completed = #{completed} WHERE id = #{id}")
    void updateCompleted(@Param("id") Long id, @Param("completed") boolean completed);
}
