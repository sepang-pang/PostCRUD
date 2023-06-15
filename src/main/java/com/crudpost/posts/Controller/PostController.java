package com.crudpost.posts.Controller;

import com.crudpost.posts.Dto.PostResponseDto;
import com.crudpost.posts.Dto.PostRequestDto;
import com.crudpost.posts.entity.Post;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class PostController {

    private final Map<Long, Post> postList =  new HashMap<>();
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto) {
        // RequestDto -> Entity 로 변환
        Post post = new Post(postRequestDto);

        // Max ID Check
        Long maxId = postList.size() > 0 ? Collections.max(postList.keySet()) + 1 : 1;
        post.setUserId(maxId);

        // DB 저장
        postList.put(post.getUserId(), post);

        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);

        return postResponseDto;

    }

    @GetMapping("/post")
    public List<PostResponseDto> getPost() {
        // Map to List
        List<PostResponseDto> responseList = postList.values().stream()
                .map(PostResponseDto::new).toList();

        return responseList;
    }


}
