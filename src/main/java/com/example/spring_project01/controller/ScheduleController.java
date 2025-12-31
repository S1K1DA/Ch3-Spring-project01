package com.example.spring_project01.controller;

import com.example.spring_project01.dto.request.ScheduleCreateRequest;
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

    @PostMapping("/schedules")
    public ScheduleCreateResponse createSchedule(@RequestBody ScheduleCreateRequest request) {
        return scheduleService.createSchedule(request);
    }

    @GetMapping("/schedules")
    public List<ScheduleResponse> getSchedules(@RequestParam(required = false) String author) {
        return scheduleService.getSchedules(author);
    }
}
