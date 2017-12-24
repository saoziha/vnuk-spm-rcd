package com.hhmhieu.vnuklmslogin.Control;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.hhmhieu.vnuklmslogin.Data;
import com.hhmhieu.vnuklmslogin.Model.Announcement;
import com.hhmhieu.vnuklmslogin.R;

/**
 * Created by Tran Viet Thanh on 11/21/2017.
 */

public class NotificationCreating extends AppCompatActivity implements View.OnClickListener {
    Button submit, cancel;
    String nameHolder, descriptionHolder;
    private NotificationCompat.Builder builder;
    private

    EditText name, description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_notice);
        name = (EditText) findViewById(R.id.edt_notice_title);
        description = (EditText) findViewById(R.id.edt_notice_content);
        submit = (Button) findViewById(R.id.btn_notice_save);
        submit.setOnClickListener(this);
        cancel = (Button) findViewById(R.id.btn_notice_discard);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_notice_save: {
                Announcement announ = new Announcement();
                getDataFromEditText();

                int newRangeOfAnnouncement = Data.currentCourse.getNumberOfAnnouncements() + 1;
                Data.currentCourse.setNumberOfAnnouncements(newRangeOfAnnouncement);
                announ.setIdAnnouncement(newRangeOfAnnouncement);
                announ.setIdCourse(Data.currentCourse.getIdCourse());
                announ.setAnnouncementName(nameHolder);
                announ.setDescription(descriptionHolder);

                FirebaseDatabase.getInstance().getReference()
                        .child("Announcement")
                        .child("course" + Data.currentCourse.getIdCourse())
                        .child("announcement" + announ.getIdAnnouncement())
                        .setValue(announ);
                Toast.makeText(getApplicationContext(), "Adding announcement successful!", Toast.LENGTH_SHORT).show();
                finish();
            }
            break;

            case R.id.btn_notice_discard: {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(NotificationCreating.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(NotificationCreating.this);
                }
                builder.setTitle("Log out")
                        .setMessage("Are you sure you want to cancel this announcement?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
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

        }
    }


    public void getDataFromEditText() {
        nameHolder = name.getText().toString();
        descriptionHolder = description.getText().toString();
    }
}
