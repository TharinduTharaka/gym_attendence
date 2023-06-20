package com.example.gym_attendence.model;

import java.util.Comparator;

public class AttendanceComparatorModel implements Comparator<AttendanceModel> {

    @Override
    public int compare(AttendanceModel o1, AttendanceModel o2) {
        if (o1.getDate().equals(o2.getDate())) {
            System.out.println(" This line of code is not executing ");
            return 1;
        } else {
            System.out.println(" This line of code is executing ");
            return 0;
        }

    }
}
