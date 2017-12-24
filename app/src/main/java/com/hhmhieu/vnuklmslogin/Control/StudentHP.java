package com.hhmhieu.vnuklmslogin.Control;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hhmhieu.vnuklmslogin.Data;
import com.hhmhieu.vnuklmslogin.Model.Course;
import com.hhmhieu.vnuklmslogin.R;

public class StudentHP extends AppCompatActivity implements View.OnClickListener {
    Button btnCourse, btnNotification, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_homepage);

        btnCourse = (Button) findViewById(R.id.btn_std_ls_lecture);
        btnCourse.setOnClickListener(this);
        btnNotification = (Button) findViewById(R.id.btn_std_notices);
        btnNotification.setOnClickListener(this);
        logout = (Button) findViewById(R.id.btn_std_logout);
        logout.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_std_logout: {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(StudentHP.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(StudentHP.this);
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
            case R.id.btn_std_notices: {
                notificationIntent();
            }
            break;

            case R.id.btn_std_ls_lecture: {
                loadAllCoursesOfCurrentStudent();
            }
            break;
        }
    }

    public void notificationIntent() {
        Intent intent = new Intent(StudentHP.this, Notification.class);
        startActivity(intent);
    }

    public void loadAllCoursesOfCurrentStudent() {
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

                            for (Integer idCourse : Data.currentStudent.getIdCourseArrayList()) {
                                if (newCourse.getIdCourse() == idCourse) {
                                    Data.currentCourseArrayList.add(newCourse);
                                    break;
                                }
                            }

                        }

                        Intent intent = new Intent(StudentHP.this, Lectures.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}