package com.hhmhieu.vnuklmslogin.Model;

import java.util.Date;

/**
 * Created by Tran Viet Thanh on 10/22/2017.
 */

// ~/LectureNotes/course[idCourse]/lectureNote[idLectureNote]

public class LectureNote {
    private int idLectureNote;
    private int idCourse;
    private String name;
    private Date date;
    private String link;
    private String description;

    public LectureNote() {
    }

    public LectureNote(int idLectureNote, int idCourse, String name, Date date, String link, String description) {
        this.idLectureNote = idLectureNote;
        this.idCourse = idCourse;
        this.name = name;
        this.date = date;
        this.link = link;
        this.description = description;
    }

    public int getIdLectureNote() {
        return idLectureNote;
    }

    public void setIdLectureNote(int idLectureNote) {
        this.idLectureNote = idLectureNote;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
