package com.example.spring_project01.dto.request;

import lombok.Getter;

@Getter
public class ScheduleCreateRequest {

    private String title;
    private String content;
    private String author;
    private String password;

}
