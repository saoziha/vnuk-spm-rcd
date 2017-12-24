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
import com.hhmhieu.vnuklmslogin.Model.Deadline;
import com.hhmhieu.vnuklmslogin.R;

public class DocSharing extends AppCompatActivity {
    Button Syllabus, LectureNotes, btnDeadline, btnNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecturer_doc_sharing);
        Syllabus = (Button) findViewById(R.id.btn_syllabus_upload);
        LectureNotes = (Button) findViewById(R.id.btn_lecture_note_upload);
        btnDeadline = (Button) findViewById(R.id.btn_deadline1);
        btnNotification = (Button) findViewById(R.id.btn_new_notice);

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
                                    Intent intent = new Intent(DocSharing.this, SyllabusesView.class);
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
                                    Intent intent = new Intent(DocSharing.this, SyllabusesSharing.class);
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
                                    Intent intent = new Intent(DocSharing.this, LectureNotesView.class);
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
                                    Intent intent = new Intent(DocSharing.this, LectureNotesSharing.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });
                }

            }
        });
        btnDeadline.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
            FirebaseDatabase
                    .getInstance()
                    .getReference()
                    .child("Deadlines")
                    .child("course" + Data.currentCourse.getIdCourse())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Deadline newDeadline;
            Data.currentDeadlineArrayList.clear();

            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                newDeadline = ds.getValue(Deadline.class);
                Data.currentDeadlineArrayList.add(newDeadline);
        }

            Intent intent = new Intent(DocSharing.this, DeadlineManage.class);
            startActivity(intent);


        }


        @Override public void onCancelled(DatabaseError databaseError) {

        }
        });

        }

        });

        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DocSharing.this, NotificationCreating.class);
                startActivity(intent);
            }
        });
    }

    ;
}




