package com.project.blog.service;

import com.project.blog.dto.PostDTO;
import com.project.blog.model.Post;
import com.project.blog.model.User;
import com.project.blog.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.hibernate.QueryException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final AuthService authService;

    public void createPost(PostDTO postDTO){
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        User user = authService.getCurrentUser().orElseThrow(()->
                new IllegalArgumentException("No user is authenticated when dealing with this request"));
        post.setUsername(user.getUsername());

        postRepository.save(post);
    }

    public List<PostDTO> showAllPosts(){
        List<Post> posts = postRepository.findAll();

        return posts.stream().map(this::postToDTO).collect(Collectors.toList());
    }

    public PostDTO getSinglePost(Long id){
        Post post = postRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("post you trying to retrieve doesn't exist"));

        return this.postToDTO(post);
    }

    private PostDTO postToDTO(Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setUsername(post.getUsername());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());

        return postDTO;
    }
}
