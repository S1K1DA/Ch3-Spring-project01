package com.example.spring_project01.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ScheduleDetailResponse {

    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private List<CommentResponse> comments;

    public ScheduleDetailResponse(Long id, String title, String content, String author, LocalDateTime createdAt, LocalDateTime modifiedAt, List<CommentResponse> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
    }
}
