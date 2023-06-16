package com.crudpost.posts.Controller;

import com.crudpost.posts.Dto.PostBooleanDto;
import com.crudpost.posts.Dto.PostResponseDto;
import com.crudpost.posts.Dto.PostRequestDto;
import com.crudpost.posts.entity.Post;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class PostController {

    private final Map<Long, Post> postList = new HashMap<>();

    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto postRequestDto) {
        // RequestDto -> Entity 로 변환
        Post post = new Post(postRequestDto);

        // Max ID Check
        Long maxId = postList.size() > 0 ? Collections.max(postList.keySet()) + 1 : 1;
        post.setUserId(maxId);

        // DB 저장
        postList.put(maxId, post);

        // Entity -> ResponseDto
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;

    }

    @GetMapping("/post")
    public List<PostResponseDto> getPost() {
        // Map to List
//        List<PostResponseDto> responseList = new ArrayList<>();
//
//        for (Post postItem : postList.values()) {
//            PostResponseDto postResponseDto = new PostResponseDto(postItem);
//            responseList.add(postResponseDto);
//        }

        List<PostResponseDto> responseList = postList.values().stream()
                .map(PostResponseDto::new).toList();

        return responseList;
    }

    @PutMapping("/post/{id}")
    private Long updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        // 해당 메모 존재하는지 확인
        if (postList.containsKey(id)) {
            Post post = postList.get(id);
            // 패스워드 확인
            if (post.getPassword().equals(postRequestDto.getPassword())) {
                post.update(postRequestDto);
                return post.getUserId();

            } else {
                throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
            }

        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }

    }

    @DeleteMapping("/post/{id}")
    private PostBooleanDto postBooleanDto (@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        // 해당 메모가 있는지 확인
        if (postList.containsKey(id)) {

            // password 확인
            if (postList.get(id).getPassword().equals(postRequestDto.getPassword())){
            postList.remove(id);
            return new PostBooleanDto(true);

            } else {
                throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
            }
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }
}
