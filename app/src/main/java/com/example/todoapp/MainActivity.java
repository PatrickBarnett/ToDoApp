package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //overrides the oncreate to make the database
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SQLiteDatabase mydatabase = openOrCreateDatabase("ToDoAppDB",MODE_PRIVATE,null);
        setContentView(R.layout.activity_main);
    }

    //Sends the user to the add a new task activity
    public void newTask(View v){
        Intent enterNewToDoActivity = new Intent(this, EnterNewToDoActivity.class);
        startActivity(enterNewToDoActivity);
    }
    ///sends the user to look at created tasks
    public void lookAtTasks(View v){
        Intent lookAtList = new Intent(this, ToDoListActivity.class);
        startActivity(lookAtList);
    }

}