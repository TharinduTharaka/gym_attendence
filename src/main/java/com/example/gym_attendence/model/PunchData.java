package com.example.gym_attendence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PunchData {


    private String date;
    private String inTime;
    private String outTime;

    private String duration;

//    public PunchData(LocalDate date, LocalDateTime inTime, LocalDateTime outTime) {
//        this.date = date;
//        this.inTime = inTime;
//        this.outTime = outTime;
//    }
//
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public LocalDateTime getInTime() {
//        return inTime;
//    }
//
//    public LocalDateTime getOutTime() {
//        return outTime;
//    }

}
