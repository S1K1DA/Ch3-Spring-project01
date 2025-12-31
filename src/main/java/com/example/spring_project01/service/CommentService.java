package com.example.spring_project01.service;

import com.example.spring_project01.dto.request.CommentCreateRequest;
import com.example.spring_project01.dto.response.CommentCreateResponse;
import com.example.spring_project01.entity.Comment;
import com.example.spring_project01.entity.Schedule;
import com.example.spring_project01.repository.CommentRepository;
import com.example.spring_project01.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentCreateResponse createComment(Long scheduleId, CommentCreateRequest request) {

        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));

        // 댓글 개수 제한(10개)
        long count = commentRepository.countByScheduleId(scheduleId);
        if(count >= 10) {
            throw new IllegalStateException("더이상 댓글을 작성할수 없습니다.(최대10개)");
        }

        // 댓글 생성
        Comment comment = new Comment(
                request.getContent(),
                request.getAuthor(),
                request.getPassword(),
                schedule
        );

        Comment saved = commentRepository.save(comment);

        // Response DTO 반환
        return new CommentCreateResponse(
                saved.getId(),
                saved.getContent(),
                saved.getAuthor(),
                saved.getCreatedAt(),
                saved.getModifiedAt()
        );
    }
}
