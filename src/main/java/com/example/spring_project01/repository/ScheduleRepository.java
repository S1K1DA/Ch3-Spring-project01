package com.example.spring_project01.repository;

import com.example.spring_project01.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    // 전체 일정 조회
    List<Schedule> findAllByOrderByModifiedAtDesc();

    // 작성자 기준 일정 조회
    List<Schedule> findByAuthorOrderByModifiedAtDesc(String author);
}
