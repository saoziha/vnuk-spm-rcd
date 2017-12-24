package com.hhmhieu.vnuklmslogin.Model;

import java.util.ArrayList;

// ~/Student/student[idUser]

public class Student {
    private int idUser;
    private String name;
    private ArrayList<Integer> idCourseArrayList;

    public Student() {
        this.idCourseArrayList = new ArrayList<>();
    }

    public Student(int idUser, String name, ArrayList<Integer> idCourseArrayList) {
        this.idUser = idUser;
        this.name = name;
        this.idCourseArrayList = idCourseArrayList;

    }

    public int getId() {
        return idUser;
    }

    public void setId(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getIdCourseArrayList() {
        return idCourseArrayList;
    }

    public void setIdCourseArrayList(ArrayList<Integer> idCourseArrayList) {
        this.idCourseArrayList = idCourseArrayList;
    }
}
