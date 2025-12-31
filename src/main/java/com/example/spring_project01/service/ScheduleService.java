package com.example.spring_project01.service;

import com.example.spring_project01.dto.request.ScheduleCreateRequest;
import com.example.spring_project01.dto.response.ScheduleCreateResponse;
import com.example.spring_project01.entity.Schedule;
import com.example.spring_project01.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleCreateResponse createSchedule(ScheduleCreateRequest request) {

        // Request DTO -> Entity 생성
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getAuthor(),
                request.getPassword()
        );

        // DB 저장
        Schedule savedSchedule = scheduleRepository.save(schedule);

        // Entity -> Response DTO 변환
        return new ScheduleCreateResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getAuthor(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }
}
