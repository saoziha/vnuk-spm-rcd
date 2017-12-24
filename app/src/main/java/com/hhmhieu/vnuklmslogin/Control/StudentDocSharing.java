package com.hhmhieu.vnuklmslogin.Control;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hhmhieu.vnuklmslogin.Data;
import com.hhmhieu.vnuklmslogin.R;

/**
 * Created by Tran Viet Thanh on 12/15/2017.
 */

public class StudentDocSharing extends AppCompatActivity {
    Button Syllabus, LectureNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_doc_sharing);
        Syllabus = (Button) findViewById(R.id.btn_student_syllabus_upload);
        LectureNotes = (Button) findViewById(R.id.btn_student_lecture_note_upload);

        Syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Data.currentUser.getAccess() == 0) {
                    FirebaseDatabase
                            .getInstance()
                            .getReference()
                            .child("Student").child("student " + Data.currentUser.getId())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Intent intent = new Intent(StudentDocSharing.this, SyllabusesView.class);
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
                                    Intent intent = new Intent(StudentDocSharing.this, SyllabusesSharing.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });
                }

            }
        });

        LectureNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Data.currentUser.getAccess() == 0) {
                    FirebaseDatabase.getInstance().getReference()
                            .child("Student").child("student " + Data.currentUser.getId())
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Intent intent = new Intent(StudentDocSharing.this, LectureNotesView.class);
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
                                    Intent intent = new Intent(StudentDocSharing.this, LectureNotesSharing.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });
                }

            }
        });
    }
}
