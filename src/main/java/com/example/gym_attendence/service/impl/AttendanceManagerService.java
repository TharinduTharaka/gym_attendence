package com.example.gym_attendence.service.impl;

import com.example.gym_attendence.entity.AttendanceEntity;
import com.example.gym_attendence.entity.IclockTransactionEntity;
import com.example.gym_attendence.model.AttendanceModel;
import com.example.gym_attendence.model.PunchData;
import com.example.gym_attendence.repository.IclockTransactionRepository;
import com.example.gym_attendence.response.Response;
import com.example.gym_attendence.service.AttendanceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AttendanceManagerService implements AttendanceManager {

    @Autowired
    private IclockTransactionRepository iclockTransactionRepository;


    @Override
    public Response getUserTodayAttendance(String emp_id) {

        Date todayDate = new Date();

        String punchTime = "2023-03-09";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

//        String punchTime = dateFormat.format(todayDate);

        int hr = 0, min = 0;

        List<IclockTransactionEntity> iclockTransactionEntityList = iclockTransactionRepository.findByEmpCode(emp_id);


        List<AttendanceModel> attendanceModelArrayList = new ArrayList<>();
        for (IclockTransactionEntity attendance : iclockTransactionEntityList) {

            AttendanceModel attendanceModel = new AttendanceModel();

            attendanceModel.setId(attendance.getId());
            attendanceModel.setEmpCode(attendance.getEmpCode());
//            attendanceModel.setEmpName(attendance.);
            attendanceModel.setDate(dateConvert(attendance.getPunchTime()));
            attendanceModel.setTime(timeConvert(attendance.getPunchTime()));

            if (punchTime.equalsIgnoreCase(attendanceModel.getDate())) {
                attendanceModelArrayList.add(attendanceModel);
            }

        }
        List<PunchData> punchDataList = new ArrayList<>();
        PunchData punchData = new PunchData();
        punchData.setDate(attendanceModelArrayList.get(0).getDate());
        punchData.setInTime(attendanceModelArrayList.get(0).getTime());
        punchData.setEmpNo(attendanceModelArrayList.get(0).getEmpCode());

        if (attendanceModelArrayList.size() > 1) {
            punchData.setOutTime(attendanceModelArrayList.get(attendanceModelArrayList.size() - 1).getTime());
        } else {
            punchData.setOutTime("No Time Found");
        }

        double difference = calDuration(punchData.getInTime(), punchData.getOutTime());
        double time = difference;
        hr = (int) time / 3600;
        time = (int) time % 3600;
        min = (int) time / 60;


        String timeDef = Integer.toString(hr) + " Hour " + Integer.toString(min) + " Min";

        punchData.setDuration(timeDef);
//        punchData.setEmpName(attendance);

        int status = calAttendanceStatus(hr);
        punchData.setStatus(status);

        punchDataList.add(punchData);


        Response response = new Response();
        response.setCode(200);
//        response.setData(iclockTransactionRepository.findByEmpCode(emp_id));
        response.setData(punchDataList);
//        response.setData(attendanceModelArrayList);
        response.setMsg("Get All Maintenance");

        return response;
    }


    @Override
    public Response getAllAttendance(String emp_id) {

//        Date todayDate = new Date();

        String punchTime = "2023-03-16";

//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

//        String punchTime = dateFormat.format(todayDate);


        int hr = 0, min = 0;

        List<IclockTransactionEntity> iclockTransactionEntityList = iclockTransactionRepository.findByEmpCode(emp_id);


        List<AttendanceModel> attendanceModelArrayList = new ArrayList<>();
        for (IclockTransactionEntity attendance : iclockTransactionEntityList) {

            AttendanceModel attendanceModel = new AttendanceModel();

//            attendanceModel.setId(attendance.getId());
            attendanceModel.setEmpCode(attendance.getEmpCode());
//            attendanceModel.setEmpName(attendance.);
            attendanceModel.setDate(dateConvert(attendance.getPunchTime()));
            attendanceModel.setTime(timeConvert(attendance.getPunchTime()));
//            attendanceModel.getDate();
            if (punchTime.equalsIgnoreCase(attendanceModel.getDate())) {

                attendanceModelArrayList.add(attendanceModel);
            }

        }


        List<PunchData> punchDataList = new ArrayList<>();

        PunchData punchData = new PunchData();
        punchData.setDate(attendanceModelArrayList.get(0).getDate());
        punchData.setInTime(attendanceModelArrayList.get(0).getTime());
        punchData.setEmpNo(attendanceModelArrayList.get(0).getEmpCode());

        if (attendanceModelArrayList.size() > 1) {
            punchData.setOutTime(attendanceModelArrayList.get(attendanceModelArrayList.size() - 1).getTime());
        } else {
            punchData.setOutTime("No Time Found");
        }

        double difference = calDuration(punchData.getInTime(), punchData.getOutTime());
        double time = difference;
        hr = (int) time / 3600;
        time = (int) time % 3600;
        min = (int) time / 60;


        String timeDef = Integer.toString(hr) + " Hour " + Integer.toString(min) + " Min";

        punchData.setDuration(timeDef);
//        punchData.setEmpName(attendance);

        int status = calAttendanceStatus(hr);
        punchData.setStatus(status);

        punchDataList.add(punchData);


        Response response = new Response();
        response.setCode(200);
//        response.setData(iclockTransactionRepository.findByEmpCode(emp_id));
        response.setData(punchDataList);
//        response.setData(attendanceModelArrayList);
        response.setMsg("Get All Maintenance");

        return response;
    }

//    public Map<String, List<PunchData>> filterPunchTimesByUserAndDate(List<IclockTransactionEntity> attendanceData) {
//
//        Map<String, List<PunchData>> filteredData = new HashMap<>();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
//
//        // Group attendance records by user code
//        Map<String, List<IclockTransactionEntity>> attendanceByUser = attendanceData.stream()
//                .collect(Collectors.groupingBy(IclockTransactionEntity::getEmpCode));
//
//        for (Map.Entry<String, List<IclockTransactionEntity>> userEntry : attendanceByUser.entrySet()) {
//            String userCode = userEntry.getKey();
//            List<IclockTransactionEntity> userAttendance = userEntry.getValue();
//
//            // Group attendance records by date
//            Map<LocalDate, List<IclockTransactionEntity>> attendanceByDate = userAttendance.stream()
//                    .collect(Collectors.groupingBy(a -> LocalDateTime.parse(a.getPunch_time(), formatter).toLocalDate()));
//
//            // Create a list to store punch data for each date
//            List<PunchData> punchDataList = new ArrayList<>();
//            for (Map.Entry<LocalDate, List<IclockTransactionEntity>> dateEntry : attendanceByDate.entrySet()) {
//                LocalDate date = dateEntry.getKey();
//
//                List<IclockTransactionEntity> attendanceList = dateEntry.getValue();
//
//                // Find InTime (first punch_time) and OutTime (last punch_time)
//                LocalDateTime inTime = LocalDateTime.parse(attendanceList.get(0).getPunchTime(), formatter);
//                LocalDateTime outTime = LocalDateTime.parse(attendanceList.get(attendanceList.size() - 1).getPunchTime(), formatter);
//
//                PunchData punchData = new PunchData(date, inTime, outTime);
//                punchDataList.add(punchData);
//            }
//
//            filteredData.put(userCode, punchDataList);
//        }
//
//        return filteredData;
//    }

    public void createUserTodayAttendance(String emp_id) {

    }

    public void attendanceCalculator() {

        List<IclockTransactionEntity> iclockTransactionEntityList = iclockTransactionRepository.findAll();

    }

    public String calInTime(String punch_time) {
        String inTime = "";

        return inTime;
    }

    public String calOutTime(String punch_time) {
        String inTime = "";

        return inTime;
    }


    public double calDuration(String inTime, String outTime) {

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date1;
        Date date2;
        try {
            date1 = format.parse(inTime);
            date2 = format.parse(outTime);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        double difference = (date2.getTime() - date1.getTime()) / 1000.00;


        return difference;
    }

    public int calHours(int hr) {
        int status;

        if (hr > 2) {
            status = 1;
        } else if (hr == 2) {
            status = 3;
        } else {
            status = 2;
        }

        return status;
    }

    public int calAttendanceStatus(int hr) {
        int status;

        if (hr > 2) {
            status = 1;
        } else if (hr == 2) {
            status = 3;
        } else {
            status = 2;
        }

        return status;
    }

    public String dateConvert(Date punch_time) {
        String date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        date = dateFormat.format(punch_time);

        return date;
    }

    public String timeConvert(Date punch_time) {
        String time = null;
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        time = timeFormat.format(punch_time);

        return time;
    }
}
