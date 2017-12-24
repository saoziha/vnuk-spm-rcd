package com.hhmhieu.vnuklmslogin.Model;

/**
 * Created by Tran Viet Thanh on 11/21/2017.
 */

public class Announcement {
    private int idAnnouncement;
    private String announcementName;
    private int idCourse;
    private String description;

    public Announcement() {
    }

    public Announcement(int idAnnouncement, String announcementName, int idCourse, String description) {
        this.idAnnouncement = idAnnouncement;
        this.announcementName = announcementName;
        this.idCourse = idCourse;
        this.description = description;
    }

    public int getIdAnnouncement() {
        return idAnnouncement;
    }

    public void setIdAnnouncement(int idAnnouncement) {
        this.idAnnouncement = idAnnouncement;
    }

    public String getAnnouncementName() {
        return announcementName;
    }

    public void setAnnouncementName(String announcementName) {
        this.announcementName = announcementName;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(int idCourse) {
        this.idCourse = idCourse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
