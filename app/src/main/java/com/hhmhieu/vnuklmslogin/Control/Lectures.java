package com.hhmhieu.vnuklmslogin.Control;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hhmhieu.vnuklmslogin.Data;
import com.hhmhieu.vnuklmslogin.Model.Course;
import com.hhmhieu.vnuklmslogin.R;

import java.util.ArrayList;

public class Lectures extends AppCompatActivity {

    ListView listViewCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ls_lectures);

        ArrayList<String> courseNameArrayList = new ArrayList<>();
        for (Course course : Data.currentCourseArrayList) {
            courseNameArrayList.add(course.getNameCourse());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, courseNameArrayList);

        listViewCourses = (ListView) findViewById(R.id.ls_lecture);
        listViewCourses.setAdapter(adapter);
        listViewCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                FirebaseDatabase.getInstance().getReference().child("Courses")
                        .child("course" + Data.currentCourseArrayList.get(position).getIdCourse())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                Data.currentCourse = Data.currentCourseArrayList.get(position);
                                if (Data.currentUser.getAccess() == 0) {
                                    FirebaseDatabase
                                            .getInstance()
                                            .getReference()
                                            .child("Student").child("student " + Data.currentUser.getId())
                                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    Intent intent = new Intent(Lectures.this, StudentDocSharing.class);
                                                    startActivity(intent);
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {
                                                }
                                            });

                                } else {
                                    FirebaseDatabase
                                            .getInstance()
                                            .getReference()
                                            .child("Teachers")
                                            .child("teacher" + Data.currentUser.getId())
                                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    Intent intent = new Intent(Lectures.this, DocSharing.class);
                                                    startActivity(intent);
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {
                                                }
                                            });
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
        });
    }
}
