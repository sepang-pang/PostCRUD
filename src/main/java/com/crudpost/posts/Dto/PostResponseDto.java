package com.crudpost.posts.Dto;

import com.crudpost.posts.entity.Post;
import lombok.Getter;

@Getter
public class PostResponseDto {

    private Long userId;
    private String userName;
    private String contents;



    public PostResponseDto(Post post) {

        this.userName = post.getUserName();
        this.contents = post.getContents();
        this.userId = post.getUserId();
    }
}
