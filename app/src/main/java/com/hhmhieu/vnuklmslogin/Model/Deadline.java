package com.hhmhieu.vnuklmslogin.Model;

import java.util.Date;

/**
 * Created by Tran Viet Thanh on 10/22/2017.
 */

// ~/Deadlines/Course[idCourse]/deadline[idDeadline]

public class Deadline {
    private int idDeadline;
    private String name;
    private int idCourse;
    private Date time;
    private String description;

    public Deadline() {
    }

    public Deadline(int idDeadline, String name, int idCourse, Date time, String description) {
        this.idDeadline = idDeadline;
        this.name = name;
        this.idCourse = idCourse;
        this.time = time;
        this.description = description;
    }

    public int getIdDeadline() {
        return idDeadline;
    }

    public void setIdDeadline(int idDeadline) {
        this.idDeadline = idDeadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
