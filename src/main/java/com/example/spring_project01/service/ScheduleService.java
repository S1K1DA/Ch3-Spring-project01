package com.example.spring_project01.service;

import com.example.spring_project01.dto.request.ScheduleCreateRequest;
import com.example.spring_project01.dto.request.ScheduleDeleteRequest;
import com.example.spring_project01.dto.request.ScheduleUpdateRequest;
import com.example.spring_project01.dto.response.CommentResponse;
import com.example.spring_project01.dto.response.ScheduleCreateResponse;
import com.example.spring_project01.dto.response.ScheduleDetailResponse;
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
        Schedule schedule = findScheduleById(id);

        // Entity -> Response DTO 변환
        return toResponse(schedule);
    }

    @Transactional
    public ScheduleResponse updateSchedule(Long id, ScheduleUpdateRequest request) {
        // 일정 조회
        Schedule schedule = findScheduleById(id);

        // 비밀번호 검증
        if(!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }
        // 일정 수정
        schedule.update(request.getTitle(), request.getAuthor());

        return toResponse(schedule);
    }

    @Transactional
    public void deleteSchedule(Long id, ScheduleDeleteRequest request) {
        Schedule schedule = findScheduleById(id);

        if(!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        // 일정 삭제
        scheduleRepository.delete(schedule);
    }

    // 단건조회 (댓글 포함)
    @Transactional(readOnly = true)
    public ScheduleDetailResponse getScheduleDetail(Long id) {

        Schedule schedule = findScheduleById(id);

        List<CommentResponse> comments = schedule.getComments().stream()
                .map(comment -> new CommentResponse(
                        comment.getId(),
                        comment.getContent(),
                        comment.getAuthor(),
                        comment.getCreatedAt(),
                        comment.getModifiedAt()
                ))
                .toList();

        return new ScheduleDetailResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                comments
        );
    }

    // Service 공통 조회 및 응답 변환 로직 분리
    private ScheduleResponse toResponse(Schedule schedule) {
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }
    private Schedule findScheduleById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));
    }

}
