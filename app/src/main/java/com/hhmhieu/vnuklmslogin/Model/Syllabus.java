package com.hhmhieu.vnuklmslogin.Model;

/**
 * Created by Tran Viet Thanh on 10/22/2017.
 */

// ~/Syllabuses/courses[idCourse]/syllabus

public class Syllabus {
    private int idCourse;
    private String link;
    private String description;

    public Syllabus() {

    }

    public Syllabus(int idCourse, String link, String description) {
        this.idCourse = idCourse;
        this.link = link;
        this.description = description;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
