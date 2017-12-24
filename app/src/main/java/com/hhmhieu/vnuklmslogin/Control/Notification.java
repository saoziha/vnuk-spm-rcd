package com.hhmhieu.vnuklmslogin.Control;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hhmhieu.vnuklmslogin.Data;
import com.hhmhieu.vnuklmslogin.Model.Announcement;
import com.hhmhieu.vnuklmslogin.R;

import java.util.ArrayList;

public class Notification extends AppCompatActivity {

    ListView lvNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ls_notices);

        lvNotification = (ListView) findViewById(R.id.ls_notices);

        final ArrayList<String> NotificationArrayList = new ArrayList<String>();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, NotificationArrayList);

        lvNotification.setAdapter(adapter);

        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("Announcement")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int idCourse;
                        String nameCoures;
                        boolean check;
                        Announcement newAnnouncement;

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            nameCoures = ds.getKey();

                            nameCoures = nameCoures.substring(6);

                            idCourse = Integer.parseInt(nameCoures);

                            check = false;

                            if (Data.currentUser.getAccess() == 0) {
                                for (Integer integer : Data.currentStudent.getIdCourseArrayList()) {
                                    if (integer == idCourse) {
                                        check = true;
                                        break;
                                    }
                                }
                            } else {
                                for (Integer integer : Data.currentTeacher.getIdCourseArrayList()) {
                                    if (integer == idCourse) {
                                        check = true;
                                        break;
                                    }
                                }
                            }

                            if (check == true) {
                                for (DataSnapshot childrenDs : ds.getChildren()) {

                                    newAnnouncement = childrenDs.getValue(Announcement.class);

                                    NotificationArrayList.add(newAnnouncement.getAnnouncementName() + " - course " + newAnnouncement.getIdCourse());
                                }
                            }
                        }

                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}
