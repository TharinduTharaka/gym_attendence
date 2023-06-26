package com.example.gym_attendence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class GymAttendenceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymAttendenceApplication.class, args);

        Timer timer = new Timer();
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR, 11);
        date.set(Calendar.MINUTE, 43);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
//        TimerTask task= new TimerTask() {
//            @Override
//            public void run() {
//
//            }
//        };
//
//        timer.scheduleAtFixedRate(task,0,);


        timer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {

                    }
                },
                date.getTime(),
                1000 * 60 * 20
        );

    }

}
