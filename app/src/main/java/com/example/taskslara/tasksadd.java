package com.example.taskslara;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class tasksadd extends AppCompatActivity {
    private Task taskclass;
    private ListView listView;

    private ArrayAdapter<String> taskAdapter;
    private ArrayList<String> tasks;
    private Button Add;
    private CheckBox checkBox;

    public static final String DATA = "DATA";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtasks);

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String retrievedTaskString = sharedPreferences.getString(DATA, "");

        listView = findViewById(R.id.listview);
        Add = findViewById(R.id.Addbut);
        checkBox = findViewById(R.id.checkBoxtassk);
        setupSharedPrefs();

        if (!retrievedTaskString.isEmpty()) {
            Gson gson = new Gson();
            Task retrievedTask = gson.fromJson(retrievedTaskString, Task.class);
            taskAdapter.add(retrievedTask.getName());
        }


        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addtask(view);
            }
        });
        tasks = new ArrayList<>();
        taskAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        listView.setAdapter(taskAdapter);
        
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
             Context context = getApplicationContext();
             Toast.makeText(context,"It's Removed",Toast.LENGTH_SHORT).show();
            tasks.remove(position);
            taskAdapter.notifyDataSetChanged();
            return;
            }
        });

    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void addtask(View view) {
        int id = listView.getId();
        EditText value = findViewById(R.id.taskvalue);
        String stringValue = value.getText().toString();
        Task task = new Task(stringValue, listView.getId());

        if (!stringValue.equals(null)) {
            Gson gson = new Gson();
            String existingTasksString = prefs.getString(DATA, "");
            ArrayList<Task> existingTasks = new ArrayList<>();

            if (!existingTasksString.isEmpty()) {
                // Parse the existing tasks from JSON string
                existingTasks = gson.fromJson(existingTasksString, new TypeToken<ArrayList<Task>>() {
                }.getType());
            }

            existingTasks.add(task);
            String updatedTasksString = gson.toJson(existingTasks);
            editor.putString(DATA, updatedTasksString);
            editor.apply();
            taskAdapter.add(stringValue);
            value.setText("");
            Toast.makeText(getApplicationContext(), "Add Task!!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Please Enter Your Task !!", Toast.LENGTH_SHORT).show();
        }
    }
}