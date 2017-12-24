package com.hhmhieu.vnuklmslogin.Control;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hhmhieu.vnuklmslogin.Data;
import com.hhmhieu.vnuklmslogin.Model.Announcement;
import com.hhmhieu.vnuklmslogin.Model.Course;
import com.hhmhieu.vnuklmslogin.R;

import java.util.ArrayList;

public class LecturerHP extends AppCompatActivity implements View.OnClickListener {
    Button notification, Logout, btnCourse;

    private Context context;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder builder;
    private RemoteViews remoteViews;
    private ArrayList<Announcement> notificationArrayList;
    private boolean firstLoadNotification;
    private int notification_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecturer_hompage);

        btnCourse = (Button) findViewById(R.id.btn_ltr_ls_lecture);
        btnCourse.setOnClickListener(this);
        notification = (Button) findViewById(R.id.btn_ltr_ls_notices);
        notification.setOnClickListener(this);
        Logout = (Button) findViewById(R.id.btn_ltr_logout);
        Logout.setOnClickListener(this);

        firstLoadNotification = true;

        notificationArrayList = new ArrayList<>();

        context = this;
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(this);

        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("Announcement")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String nameCourse;
                        int idCourse;
                        boolean check;
                        boolean checkAnnouncement;
                        Announcement newAnnouncement;

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            nameCourse = ds.getKey();

                            nameCourse = nameCourse.substring(6);

                            idCourse = Integer.parseInt(nameCourse);

                            check = false;

                            for (Integer integer : Data.currentTeacher.getIdCourseArrayList()) {
                                if (idCourse == integer) {
                                    check = true;
                                    break;
                                }
                            }

                            if (check == true) {

                                if (firstLoadNotification == true) {
                                    for (DataSnapshot childrenDs : ds.getChildren()) {
                                        notificationArrayList.add(childrenDs.getValue(Announcement.class));
                                    }
                                } else {
                                    for (DataSnapshot childrenDs : ds.getChildren()) {
                                        newAnnouncement = childrenDs.getValue(Announcement.class);

                                        checkAnnouncement = false;

                                        for (Announcement announcement : notificationArrayList) {
                                            if (newAnnouncement.getIdCourse() == announcement.getIdCourse() && newAnnouncement.getIdAnnouncement() == announcement.getIdAnnouncement()) {
                                                checkAnnouncement = true;
                                                break;
                                            }
                                        }

                                        if (checkAnnouncement == false) {
                                            notification_id = (int) System.currentTimeMillis();

                                            Intent notification_intent = new Intent(context, MainActivity.class);
                                            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notification_intent, 0);

                                            NotificationCompat.Builder mBuilder =
                                                    new NotificationCompat.Builder(context)
                                                            .setSmallIcon(R.mipmap.ic_launcher_round)
                                                            .setContentTitle(newAnnouncement.getAnnouncementName())
                                                            .setContentText(newAnnouncement.getDescription())
                                                            .setContentIntent(pendingIntent);

                                            notificationManager.notify(001, mBuilder.build());

                                            Toast.makeText(getApplicationContext(), newAnnouncement.getAnnouncementName(), Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                }
                            }
                        }

                        firstLoadNotification = false;

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ltr_logout: {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(LecturerHP.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(LecturerHP.this);
                }
                builder.setTitle("Log out")
                        .setMessage("Are you sure you want to log out?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Data.clearData();
                                finish();

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

            break;
            case R.id.btn_ltr_ls_notices: {
                notificationIntent();
            }
            break;

            case R.id.btn_ltr_ls_lecture: {
                loadAllCoursesOfCurrentTeacher();
            }
            break;

        }
    }

    public void notificationIntent() {
        Intent intent = new Intent(LecturerHP.this, Notification.class);
        startActivity(intent);
    }

    public void deadlineIntent() {
        Intent intent1 = new Intent(LecturerHP.this, DeadlineManage.class);
        startActivity(intent1);
    }

    public void loadAllCoursesOfCurrentTeacher() {
        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("Courses")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Course newCourse;
                        Data.currentCourseArrayList.clear();

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            newCourse = ds.getValue(Course.class);

                            for (Integer idCourse : Data.currentTeacher.getIdCourseArrayList()) {
                                if (newCourse.getIdCourse() == idCourse) {
                                    Data.currentCourseArrayList.add(newCourse);
                                    break;
                                }
                            }

                        }

                        Intent intent = new Intent(LecturerHP.this, Lectures.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}