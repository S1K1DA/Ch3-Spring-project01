package com.example.spring_project01.controller;

import com.example.spring_project01.dto.request.CommentCreateRequest;
import com.example.spring_project01.dto.response.CommentCreateResponse;
import com.example.spring_project01.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @PostMapping("/schedules/{scheduleId}/comments")
    public CommentCreateResponse createComment(@PathVariable Long scheduleId, @RequestBody CommentCreateRequest request) {
        return commentService.createComment(scheduleId, request);
    }
}
