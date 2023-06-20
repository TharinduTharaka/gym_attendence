package com.example.gym_attendence.model;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceModel {

    private int id;
    private String empCode;
    private  String date;
    private String time;

//    private String workDuration;
}
