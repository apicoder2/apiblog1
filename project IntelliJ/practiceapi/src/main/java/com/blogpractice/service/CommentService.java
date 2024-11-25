package com.blogpractice.service;

import com.blogpractice.payload.CommentDto;

import java.util.List;

public interface CommentService {

    public CommentDto createComment(long postId, CommentDto commentDto);
    void deleteComment(long commentId);
    List<CommentDto> getCommentsByPostId(long postId);
    List<CommentDto> getAllComments();
}
