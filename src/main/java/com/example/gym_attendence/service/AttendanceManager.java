package com.example.gym_attendence.service;


import com.example.gym_attendence.repository.IclockTransactionRepository;
import com.example.gym_attendence.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface AttendanceManager  {


    Response getUserTodayAttendance(String empID);
    Response getAllAttendance(String empID);
}
