package com.hhmhieu.vnuklmslogin;

import com.google.firebase.database.FirebaseDatabase;
import com.hhmhieu.vnuklmslogin.Model.Announcement;
import com.hhmhieu.vnuklmslogin.Model.Course;
import com.hhmhieu.vnuklmslogin.Model.Deadline;
import com.hhmhieu.vnuklmslogin.Model.LectureNote;
import com.hhmhieu.vnuklmslogin.Model.Student;
import com.hhmhieu.vnuklmslogin.Model.Syllabus;
import com.hhmhieu.vnuklmslogin.Model.Teacher;
import com.hhmhieu.vnuklmslogin.Model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by Tran Viet Thanh on 11/2/2017.
 */

public class GeneratingDummyData {

    private int numberOfCourses, numberOfUsers;

    public GeneratingDummyData() {
    }

    public void generate(int numberOfCourses, int numberOfUsers) {
        generateCourseArrayList(numberOfCourses);
        generateUserArrayList(numberOfUsers);
    }


    // generate courseArrayList
    public ArrayList<Course> generateCourseArrayList(int numberOfCourses) {
        this.numberOfCourses = numberOfCourses;

        ArrayList<Course> courseArrayList = new ArrayList<>();
        for (int i = 0; i < numberOfCourses; i++) {
            courseArrayList.add(generateSingleCourse(i));
        }

        return courseArrayList;
    }

    public Course generateSingleCourse(int idCourse) {
        Course newCourse = new Course();
        newCourse.setIdCourse(idCourse);
        newCourse.setNameCourse("course " + idCourse);

        newCourse.setNumberOfDeadlines((new Random()).nextInt(10));
        for (int i = 0; i < newCourse.getNumberOfDeadlines(); i++) {
            generateSingleDeadline(i, idCourse);
        }

        newCourse.setNumberOfLectureNotes((new Random()).nextInt(10));
        for (int i = 0; i < newCourse.getNumberOfLectureNotes(); i++) {
            generateSingleLectureNote(i, idCourse);
        }

        newCourse.setNumberOfAnnouncements((new Random()).nextInt(7));
        for (int i = 0; i < newCourse.getNumberOfAnnouncements(); i++) {
            generateSingleAnnouncement(i, idCourse);

        }

        generateSyllabus(idCourse);

        FirebaseDatabase.getInstance().getReference().child("Courses").child("course" + idCourse).setValue(newCourse);

        return newCourse;
    }

    public Deadline generateSingleDeadline(int idDeadline, int idCourse) {
        Deadline newDeadline = new Deadline();
        newDeadline.setIdDeadline(idDeadline);
        newDeadline.setIdCourse(idCourse);
        newDeadline.setName("deadline " + idDeadline);
        newDeadline.setTime(new Date());
        newDeadline.setDescription("description of deadline " + idDeadline);

        FirebaseDatabase.getInstance().getReference().child("Deadlines").child("course" + idCourse).child("deadline" + idDeadline).setValue(newDeadline);

        return newDeadline;
    }

    public Announcement generateSingleAnnouncement(int idAnnouncement, int idCourse) {
        Announcement newAnnouncement = new Announcement();
        newAnnouncement.setIdAnnouncement(idAnnouncement);
        newAnnouncement.setIdCourse(idCourse);
        newAnnouncement.setAnnouncementName("announcement " + idAnnouncement);
        newAnnouncement.setDescription("description of announcement" + idAnnouncement);

        FirebaseDatabase.getInstance().getReference().child("Announcement").child("course" + idCourse).child("announcement" + idAnnouncement).setValue(newAnnouncement);

        return newAnnouncement;
    }

    public LectureNote generateSingleLectureNote(int idLectureNote, int idCourse) {
        LectureNote newLectureNote = new LectureNote();
        newLectureNote.setIdLectureNote(idLectureNote);
        newLectureNote.setIdCourse(idCourse);
        newLectureNote.setName("LectureNote " + idLectureNote);
        newLectureNote.setDate(new Date());
        newLectureNote.setLink("Link " + idLectureNote);
        newLectureNote.setDescription("Description " + idLectureNote);

        FirebaseDatabase.getInstance().getReference().child("LectureNotes").child("course" + idCourse).child("lectureNote" + idLectureNote).setValue(newLectureNote);

        return newLectureNote;
    }

    public Syllabus generateSyllabus(int idCourse) {
        Syllabus newSyllabus = new Syllabus();
        newSyllabus.setIdCourse(idCourse);
        newSyllabus.setLink("Link " + idCourse);
        newSyllabus.setDescription("Description " + idCourse);

        FirebaseDatabase.getInstance().getReference().child("Syllabuses").child("course" + idCourse).child("syllabus").setValue(newSyllabus);

        return newSyllabus;
    }

    public ArrayList<User> generateUserArrayList(int numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
        ArrayList<User> userArrayList = new ArrayList<>();

        for (int i = 0; i < numberOfUsers; i++) {
            userArrayList.add(generateSingleUser(i));
        }

        return userArrayList;
    }

    public User generateSingleUser(int idUser) {
        User newUser = new User();
        newUser.setId(idUser);
        newUser.setUserName("rcd" + idUser);
        newUser.setPassWord("rcd" + idUser);
        newUser.setAccess((new Random()).nextInt(2));

        if (newUser.getAccess() == 0) {
            generateSingleStudent(idUser);

        } else {
            generateSingleTeacher(idUser);
        }

        FirebaseDatabase.getInstance().getReference().child("Users").child(newUser.getUserName() + "-" + newUser.getPassWord()).setValue(newUser);

        return newUser;
    }

    public Teacher generateSingleTeacher(int idUser) {
        Teacher newTeacher = new Teacher();
        newTeacher.setId(idUser);
        newTeacher.setName("teacher " + idUser);

        int randomNumberOfCourses = (new Random()).nextInt(numberOfCourses);

        for (int i = 0; i < randomNumberOfCourses; i++) {
            newTeacher.getIdCourseArrayList().add(i);
        }

        FirebaseDatabase.getInstance().getReference().child("Teachers").child("teacher" + idUser).setValue(newTeacher);

        return newTeacher;
    }

    public Student generateSingleStudent(int idUser) {
        Student newStudent = new Student();
        newStudent.setId(idUser);
        newStudent.setName("Student " + idUser);

        int randomNumberOfCourses = (new Random()).nextInt(numberOfCourses);

        for (int i = 0; i < randomNumberOfCourses; i++) {
            newStudent.getIdCourseArrayList().add(i);
        }
        FirebaseDatabase.getInstance().getReference().child("Student").child("student " + idUser).setValue(newStudent);

        return newStudent;
    }
}

