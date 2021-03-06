package com.hhmhieu.vnuklmslogin.Model;


import java.util.ArrayList;

// ~/Teachers/teacher[idUser]

public class Teacher {
    private int idUser;
    private String name;
    private ArrayList<Integer> idCourseArrayList;

    public Teacher() {
        this.idCourseArrayList = new ArrayList<>();
    }

    public Teacher(int id, String name, ArrayList<Integer> idCourseArrayList) {
        this.idUser = id;
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
