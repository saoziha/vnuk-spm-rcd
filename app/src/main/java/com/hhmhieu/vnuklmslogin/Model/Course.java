package com.hhmhieu.vnuklmslogin.Model;

/**
 * Created by Tran Viet Thanh on 10/23/2017.
 */

// ~/Courses/course[idCourse]

public class Course {
    private int idCourse;
    private String nameCourse;
    private int numberOfDeadlines;
    private int numberOfLectureNotes;
    private int numberOfAnnouncements;

    public Course() {
    }


    public Course(int idCourse, String nameCourse, int numberOfDeadlines, int numberOfLectureNotes, int numberOfAnnouncements) {
        this.idCourse = idCourse;
        this.nameCourse = nameCourse;
        this.numberOfDeadlines = numberOfDeadlines;
        this.numberOfLectureNotes = numberOfLectureNotes;
        this.numberOfAnnouncements = numberOfAnnouncements;

    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public int getNumberOfDeadlines() {
        return numberOfDeadlines;
    }

    public void setNumberOfDeadlines(int numberOfDeadlines) {
        this.numberOfDeadlines = numberOfDeadlines;
    }

    public int getNumberOfLectureNotes() {
        return numberOfLectureNotes;
    }

    public void setNumberOfLectureNotes(int numberOfLectureNotes) {
        this.numberOfLectureNotes = numberOfLectureNotes;
    }

    public int getNumberOfAnnouncements() {
        return numberOfAnnouncements;
    }

    public void setNumberOfAnnouncements(int numberOfAnnouncements) {
        this.numberOfAnnouncements = numberOfAnnouncements;
    }
}
