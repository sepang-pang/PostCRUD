package com.crudpost.posts.entity;

import com.crudpost.posts.Dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Post {
    private Long userId;
    private String userName;
    private String contents;
    private Integer password;

    public Post(PostRequestDto postRequestDto) {
        this.userName = postRequestDto.getUserName();
        this.contents = postRequestDto.getContents();
        this.password = postRequestDto.getPassword();
    }

    public void update(PostRequestDto postRequestDto) {
        this.userName = postRequestDto.getUserName();
        this.contents = postRequestDto.getContents();
        this.password = postRequestDto.getPassword();
    }
}
