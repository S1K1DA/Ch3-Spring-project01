package com.example.spring_project01.controller;

import com.example.spring_project01.dto.request.ScheduleCreateRequest;
import com.example.spring_project01.dto.request.ScheduleDeleteRequest;
import com.example.spring_project01.dto.request.ScheduleUpdateRequest;
import com.example.spring_project01.dto.response.ScheduleCreateResponse;
import com.example.spring_project01.dto.response.ScheduleResponse;
import com.example.spring_project01.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 추가
    @PostMapping("/schedules")
    public ScheduleCreateResponse createSchedule(@RequestBody ScheduleCreateRequest request) {
        return scheduleService.createSchedule(request);
    }

    // author로 목록조회
    @GetMapping("/schedules")
    public List<ScheduleResponse> getSchedules(@RequestParam(required = false) String author) {
        return scheduleService.getSchedules(author);
    }

    // id로 단건 조회
    @GetMapping("/schedules/{id}")
    public ScheduleResponse getSchedule(@PathVariable Long id) {
        return scheduleService.getSchedule(id);
    }

    // 일정 수정
    @PutMapping("/schedules/{id}")
    public ScheduleResponse updateSchedule(@PathVariable Long id, @RequestBody ScheduleUpdateRequest request) {
        return scheduleService.updateSchedule(id, request);
    }

    // 일정 삭제
    @DeleteMapping("/schedules/{id}")
    public String deleteSchedule(@PathVariable Long id, @RequestBody ScheduleDeleteRequest request) {
        scheduleService.deleteSchedule(id, request);
        return "삭제 완료";
    }
}
