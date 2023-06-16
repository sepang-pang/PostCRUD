package com.crudpost.posts.Dto;

import lombok.Getter;

public class PostBooleanDto {
    @Getter
    private Boolean success;

    public PostBooleanDto (Boolean success) {
        this.success = success;
    }
}
