package com.du.post0924.mapper;

import com.du.post0924.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {
    //    @Select("SELECT * FROM post ORDER BY id DESC")
//    List<Post> findAll();
//
//    @Select("SELECT * FROM post WHERE id = #{id}")
//    Post findById(Long id);
//
//    @Insert("INSERT INTO post(title, content, writer) VALUES(#{title}, #{content}, #{writer})")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    void insert(Post post);
//
//    @Update("UPDATE post SET title = #{title}, content = #{content}, writer = #{writer} WHERE id = #{id}")
//    void update(Post post);
//
//    @Delete("DELETE FROM post WHERE id = #{id}")
//    void delete(Long id);
//
//    @Select("SELECT * FROM post ORDER BY id DESC LIMIT #{limit} OFFSET #{offset}")
//    List<Post> findPage(@Param("offset") int offset, @Param("limit") int limit);
//
//    @Select("SELECT COUNT(*) FROM post")
//    int count();
    List<Post> findAll();
    Post findById(Long id);
    void insert(Post post);
    void update(Post post);
    void delete(Long id);
    List<Post> findPage(@Param("offset") int offset, @Param("limit") int limit);
    int count();
}
