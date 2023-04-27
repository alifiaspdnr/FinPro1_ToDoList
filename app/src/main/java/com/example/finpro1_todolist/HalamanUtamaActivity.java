package com.example.finpro1_todolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class HalamanUtamaActivity extends AppCompatActivity implements View.OnClickListener {

    DbHelper dbHelper;
    ArrayAdapter<String> mAdapter;
    ListView listTask;
    Button button_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_halaman_utama);

        dbHelper = new DbHelper(this);
        listTask = findViewById(R.id.list);
        button_add = findViewById(R.id.btn_add);
        button_add.setOnClickListener(this);

        loadTaskList();
    }

    private void loadTaskList() {
        ArrayList<String> taskList = dbHelper.getTaskList();
        if(mAdapter == null) {
            mAdapter = new ArrayAdapter<String>(this, R.layout.item, R.id.item_name, taskList);
            listTask.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
    }




    public void delTask (View view) {
        View parent = (View) view.getParent();
        final TextView taskTextView = (TextView) parent.findViewById(R.id.item_name);
        Log.e("String", (String) taskTextView.getText());
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage("Delete this activity?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String task = String.valueOf(taskTextView.getText());
                        dbHelper.delTask(task);
                        loadTaskList();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add: final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add Activity")
                        .setMessage("Add your activity for today!")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String task = String.valueOf(taskEditText.getText());
                                dbHelper.addTask(task);
                                loadTaskList();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
        }
    }
}