package com.du.post0922.service;

import com.du.post0922.domain.Comment;
import com.du.post0922.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentMapper.findByPostId(postId);
    }

    public void addComment(Comment comment) {
        commentMapper.insert(comment);
    }
}
