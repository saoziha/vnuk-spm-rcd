package com.hhmhieu.vnuklmslogin;

import com.hhmhieu.vnuklmslogin.Model.Course;
import com.hhmhieu.vnuklmslogin.Model.Deadline;
import com.hhmhieu.vnuklmslogin.Model.Student;
import com.hhmhieu.vnuklmslogin.Model.Teacher;
import com.hhmhieu.vnuklmslogin.Model.User;

import java.util.ArrayList;

/**
 * Created by Tran Viet Thanh on 11/2/2017.
 */

public class Data {
    public static User currentUser = new User();
    public static Teacher currentTeacher = new Teacher();
    public static Student currentStudent = new Student();
    public static ArrayList<Course> currentCourseArrayList = new ArrayList<Course>();
    public static ArrayList<Deadline> currentDeadlineArrayList = new ArrayList<Deadline>();
    public static Course currentCourse = new Course();

    public static void clearData() {
        currentUser = null;
        currentCourseArrayList.clear();
        currentDeadlineArrayList.clear();
    }
}
