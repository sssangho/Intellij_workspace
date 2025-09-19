package com.du.post1.mapper;

import com.du.post1.domain.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    @Select("select * from post order by id desc")
    List<Post> findAll();

    @Select("select * from post where id = #{id}")
    Post findById(Long id);

    @Insert("insert into post(title, content, writer) values(#{title}, #{content}, #{writer})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Post post);

    @Update("update post set title = #{title}, content = #{content}, writer = #{writer} where id =  #{id}")
    void update(Post post);

    @Delete("delete from post where id = #{id}")
    void delete(Long id);

    @Select("select * from post order by id desc LIMIT #{limit} OFFSET #{offset}")
    List<Post> findPage(@Param("offset") int offset, @Param("limit") int limit);

    @Select("select count(*) from post")
    int count();

}
