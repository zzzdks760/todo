package com.example.blog.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class TodoForm {

    @NotEmpty(message = "내용은 필수입니다.")

    private String item;

    private String date;

    private boolean completed;

    private String time;
}
