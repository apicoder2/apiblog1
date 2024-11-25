package com.blogpractice.service;

import com.blogpractice.payload.PostDto;
import java.util.List;

public interface PostService {

PostDto createPost(PostDto postDto);
void deletePost(Long id);
List<PostDto> getAllPosts(int pageNo,int pageSize, String sortBy, String sortDir);
PostDto updatePost(Long postId, PostDto postDto);
}
