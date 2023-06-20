package com.example.gym_attendence.response;

import lombok.Data;

@Data
public class Response {
    private int code;
    private String msg;
    private Object data;
}
