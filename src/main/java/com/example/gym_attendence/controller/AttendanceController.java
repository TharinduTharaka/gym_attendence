package com.example.gym_attendence.controller;


import com.example.gym_attendence.response.Response;
import com.example.gym_attendence.service.impl.AttendanceManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gym-attendance/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {


    @Autowired
    private AttendanceManagerService attendanceManagerService;
    @GetMapping("/get-all-attendance/{emp_id}")
    public Response getAllAttendance(@PathVariable String emp_id) {
        return attendanceManagerService.getAllAttendance(emp_id);
    }


}
