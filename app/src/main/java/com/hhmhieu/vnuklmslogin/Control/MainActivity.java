package com.hhmhieu.vnuklmslogin.Control;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hhmhieu.vnuklmslogin.Data;
import com.hhmhieu.vnuklmslogin.GeneratingDummyData;
import com.hhmhieu.vnuklmslogin.Model.Student;
import com.hhmhieu.vnuklmslogin.Model.Teacher;
import com.hhmhieu.vnuklmslogin.Model.User;
import com.hhmhieu.vnuklmslogin.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button login;
    EditText edt_username;
    EditText edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //GeneratingDummyData generatingDummyData = new GeneratingDummyData();
        //generatingDummyData.generate(10, 20);

        edt_username = (EditText) findViewById(R.id.username);
        edt_password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.btn_login);
        login.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login: {
                FirebaseDatabase
                        .getInstance()
                        .getReference()
                        .child("Users")
                        .child(edt_username.getText().toString() + "-" + edt_password.getText().toString())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    Data.currentUser = dataSnapshot.getValue(User.class);

                                    if (Data.currentUser.getAccess() == 0) {  // student
                                        FirebaseDatabase.getInstance().getReference()
                                                .child("Student").child("student " + Data.currentUser.getId())
                                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        Data.currentStudent = dataSnapshot.getValue(Student.class);
                                                        Toast.makeText(getApplicationContext(), "Login with student account", Toast.LENGTH_LONG).show();
                                                        Intent intent = new Intent(MainActivity.this, StudentHP.class);
                                                        startActivity(intent);
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });

                                        Toast.makeText(getApplicationContext(), "Login with student account", Toast.LENGTH_LONG).show();

                                    } else { // teacher
                                        FirebaseDatabase
                                                .getInstance()
                                                .getReference()
                                                .child("Teachers")
                                                .child("teacher" + Data.currentUser.getId())
                                                .addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        Data.currentTeacher = dataSnapshot.getValue(Teacher.class);
                                                        Toast.makeText(getApplicationContext(), "Login with lecture account", Toast.LENGTH_LONG).show();
                                                        Intent intent = new Intent(MainActivity.this, LecturerHP.class);
                                                        startActivity(intent);
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "That bai", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
            break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        edt_username.setText("");
        edt_password.setText("");
    }
}
