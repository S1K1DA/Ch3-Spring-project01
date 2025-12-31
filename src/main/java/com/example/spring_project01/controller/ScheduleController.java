package com.example.spring_project01.controller;

import com.example.spring_project01.dto.request.ScheduleCreateRequest;
import com.example.spring_project01.dto.response.ScheduleCreateResponse;
import com.example.spring_project01.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ScheduleCreateResponse createSchedule(@RequestBody ScheduleCreateRequest request) {
        return scheduleService.createSchedule(request);
    }
}
