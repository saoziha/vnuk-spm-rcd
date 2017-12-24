package com.hhmhieu.vnuklmslogin.Control;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hhmhieu.vnuklmslogin.Data;
import com.hhmhieu.vnuklmslogin.Model.Deadline;
import com.hhmhieu.vnuklmslogin.R;

import java.util.ArrayList;

public class DeadlineManage extends AppCompatActivity {
    ListView listViewDeadline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ls_deadline);

        listViewDeadline = (ListView) findViewById(R.id.ls_deadline_listView);

        ArrayList<String> deadlineNameArrayList = new ArrayList<>();
        for (Deadline deadline : Data.currentDeadlineArrayList) {
            deadlineNameArrayList.add(deadline.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, deadlineNameArrayList);
        listViewDeadline.setAdapter(adapter);

    }
}
