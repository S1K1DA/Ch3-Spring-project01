package com.example.spring_project01.repository;

import com.example.spring_project01.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 특정 일정의 댓글 개수 조회
    long countByScheduleId(Long scheduleId);
}
