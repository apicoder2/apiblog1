package com.blogpractice.service.impl;

import com.blogpractice.entity.Post;
import com.blogpractice.exception.ResourceNotFoundException;
import com.blogpractice.payload.PostDto;
import com.blogpractice.repository.PostRepository;
import com.blogpractice.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post savedPost = postRepo.save(post);

        PostDto dto = new PostDto();

        dto.setId(savedPost.getId());
        dto.setTitle(savedPost.getTitle());
        dto.setDescription(savedPost.getDescription());
        dto.setContent(savedPost.getContent());
        return dto;
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("post is not found with id: " + id)
        );
        postRepo.deleteById(id);
    }


    //http://localhost:8080/api/postspractice?pageNo=0&pageSize=2&sortBy=title&sortDir=desc
    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        // Debugging: Log the received parameters
        System.out.println("Service received: pageNo=" + pageNo + ", pageSize=" + pageSize + ", sortBy=" + sortBy + ", sortDir=" + sortDir);

        // Validate pageSize to ensure it's at least 1
        if (pageSize < 1) {
            throw new IllegalArgumentException("Page size must not be less than one");
        }

        // Ensure pageNo is non-negative
        if (pageNo < 0) {
            throw new IllegalArgumentException("Page number must not be less than zero");
        }

        // Prepare the sort direction based on the input
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        // Create the PageRequest with page number, size, and sort
        PageRequest pageable = PageRequest.of(pageNo, pageSize, sort);

        // Fetch the posts from the repository
        Page<Post> pagePosts = postRepo.findAll(pageable);

        // Convert the Page<Post> to a List<PostDto>
        List<Post> posts = pagePosts.getContent();
        List<PostDto> dtos = posts.stream().map(this::mapToDto).collect(Collectors.toList());

        // Return the list of PostDto objects
        return dtos;
    }



    @Override
    public PostDto updatePost(Long postId, PostDto postDto) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post Not Found with Id: " + postId)
        );
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post savedPost = postRepo.save(post);
        PostDto dto = mapToDto(savedPost);

        return dto;
    }

    PostDto mapToDto(Post post) {

        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());
        return dto;
    }


}