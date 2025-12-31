package com.example.spring_project01.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentCreateResponse {

    private Long id;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentCreateResponse(Long id, String content, String author, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}

