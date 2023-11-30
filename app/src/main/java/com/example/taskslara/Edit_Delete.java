package com.example.taskslara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Edit_Delete extends AppCompatActivity {
    public static final String NAme = "NAme";
    public static final String ID = "ID";
    public static final String state = "State";
    public static final String FLAG = "FLAG";
    private boolean flag = false;
    EditText NameText;
    EditText IDText;
    EditText StateText;
    Button Edit;
    Button Delete;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete);
        setupSharedPrefs();
        setupView();
        checkPrefs();

        Intent intent = getIntent();
        String selectedTask = intent.getStringExtra("selectedTask");

// Use the selectedTask as needed in your Edit_Delete activity

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = IDText.getText().toString();
                String name = NameText.getText().toString();
                String State = StateText.getText().toString();

                if (!flag) {
                    editor.putString(NAme, name);
                    editor.putString(ID, id);
                    editor.putString(state, State);

                    editor.putBoolean(FLAG, true);
                    editor.commit();
                }
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = "";
                String name ="";
                String State ="";
                if (flag) {
                    editor.putString(NAme, name);
                    editor.putString(ID, id);
                    editor.putString(state, State);
                    editor.putBoolean(FLAG, false);
                    editor.commit();
                }
            }
        });
    }

    private void checkPrefs() {
        flag = prefs.getBoolean(FLAG, false);

        if (flag) {
            String name = prefs.getString(NAme, "");
            String id = prefs.getString(ID, "");
            String States = prefs.getString(state, "");
            NameText.setText(name);
            IDText.setText(id);
            StateText.setText(States);
        }
    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void setupView() {
        NameText = findViewById(R.id.nametask);
        IDText = findViewById(R.id.idtask);
        StateText = findViewById(R.id.stattask);
    }

    }
