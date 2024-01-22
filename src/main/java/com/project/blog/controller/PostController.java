package com.project.blog.controller;

import com.project.blog.dto.PostDTO;
import com.project.blog.model.Post;
import com.project.blog.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@RestController()
@RequestMapping("/api/posts/")
public class PostController {

    private final PostService postService;
    @PostMapping
    public ResponseEntity<Map<String,String>> createPost(@RequestBody PostDTO postDTO){
        postService.createPost(postDTO);
        Map<String,String> response = new HashMap<>();
        response.put("message","requested post is created");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostDTO> getSinglePost(@PathVariable @RequestBody Long id){
        return new ResponseEntity<>(postService.getSinglePost(id),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDTO>> showAllPosts(){
        return new ResponseEntity<>(postService.showAllPosts(),HttpStatus.OK);
    }

}
