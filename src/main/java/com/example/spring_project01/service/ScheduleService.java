package com.example.spring_project01.service;

import com.example.spring_project01.dto.request.ScheduleCreateRequest;
import com.example.spring_project01.dto.response.ScheduleCreateResponse;
import com.example.spring_project01.dto.response.ScheduleResponse;
import com.example.spring_project01.entity.Schedule;
import com.example.spring_project01.repository.ScheduleRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public List<ScheduleResponse> getSchedules(String author) {

        List<Schedule> schedules;

        if(author == null || author.isBlank()) { // author가 없으면 전체 조회
            schedules = scheduleRepository.findAllByOrderByModifiedAtDesc();
        } else { // author가 있으면 작성자 조회
            schedules = scheduleRepository.findByAuthorOrderByModifiedAtDesc(author);
        }
        // Entity -> Response DTO 변환
        return schedules.stream()
                .map(schedule -> new ScheduleResponse(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getAuthor(),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ScheduleResponse getSchedule(Long id) {
        // 일정 ID로 단건 조회 (존재하지 않으면 예외 발생)
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));

        // Entity -> Response DTO 변환
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
}
