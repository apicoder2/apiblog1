package com.blogpractice.controller;

import com.blogpractice.payload.CommentDto;
import com.blogpractice.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestParam("postId") long postId,
                                                    @RequestBody CommentDto commentDto)
    {
        CommentDto dto = commentService.createComment(postId,commentDto);
   return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long commendId)
    {
        commentService.deleteComment(commendId);
        return new ResponseEntity<>("comment is deleted !!",HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable long postId){

        List<CommentDto> commentDtos = commentService.getCommentsByPostId(postId);
   return new ResponseEntity<>(commentDtos,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments(){

        List<CommentDto> commentDtos = commentService.getAllComments();
        return new ResponseEntity<>(commentDtos,HttpStatus.OK);
    }

}
