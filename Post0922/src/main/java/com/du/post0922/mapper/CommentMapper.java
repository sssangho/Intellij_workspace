package com.du.post0922.mapper;

import com.du.post0922.domain.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from comment where post_id = #{postId} order by created_at asc")
    List<Comment> findByPostId(Long postId);

    @Insert("insert into comment(post_id, writer, content) values (#{postId}, #{writer}, #{content})")
    void insert(Comment comment);
}
