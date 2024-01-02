package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ToDoListActivity extends AppCompatActivity {

    DBHelper toDoDB;


    // Overrides oncreate to save the instance information for the database
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity testActivity = this;
        toDoDB = new DBHelper(this);


        setContentView(R.layout.activity_to_do_list);
        setTitle("ToDo List");

        ArrayList<ToDoItem> array_list = toDoDB.getAllItems();
        ToDoArrayAdapter arrayAdapter=new ToDoArrayAdapter(this, array_list);
        ListView obj = (ListView)findViewById(R.id.toDoItemList);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ToDoItem toDoItem = (ToDoItem)obj.getItemAtPosition(i);
                ToDoListSelectDialogFragment dialogMemes = new ToDoListSelectDialogFragment(ToDoListActivity.this, android.R.style.Theme_Black_NoTitleBar);
                dialogMemes.setItem(toDoItem);
                dialogMemes.setDBHelper(toDoDB);
                dialogMemes.setUpdateActivity((Activity)testActivity);
                dialogMemes.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100,0,0,0)));
                dialogMemes.setContentView(R.layout.to_do_list_dialog);
                dialogMemes.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        sortByDateMade(view);
                    }
                });
                dialogMemes.setCancelable(true);
                dialogMemes.setCanceledOnTouchOutside(true);
                dialogMemes.show();
            }
        });
    }


    //Sorts the List by Priority
    public void sortByPriority(View v){
        ArrayList<ToDoItem> array_list = toDoDB.getAllItemsByPriority();
        ToDoArrayAdapter arrayAdapter=new ToDoArrayAdapter(this, array_list);
        ListView obj = (ListView)findViewById(R.id.toDoItemList);
        obj.setAdapter(arrayAdapter);
    }

    //Sorts the list by Due Date
    public void sortByDueDate(View v){
        ArrayList<ToDoItem> array_list = toDoDB.getAllItemsByDueDate();
        ToDoArrayAdapter arrayAdapter=new ToDoArrayAdapter(this, array_list);
        ListView obj = (ListView)findViewById(R.id.toDoItemList);
        obj.setAdapter(arrayAdapter);
    }

    //Sorts the list by Date made
    public void sortByDateMade(View v){
        ArrayList<ToDoItem> array_list = toDoDB.getAllItemsByDateMade();
        ToDoArrayAdapter arrayAdapter=new ToDoArrayAdapter(this, array_list);
        ListView obj = (ListView)findViewById(R.id.toDoItemList);
        obj.setAdapter(arrayAdapter);
    }

    //Sorts by status
    public void sortByStatus(View v){
        ArrayList<ToDoItem> array_list = toDoDB.getAllItemsByStatus();
        ToDoArrayAdapter arrayAdapter=new ToDoArrayAdapter(this, array_list);
        ListView obj = (ListView)findViewById(R.id.toDoItemList);
        obj.setAdapter(arrayAdapter);
    }


    //Sens the user back to the main menu
    public void goToMainMenu(View v) {
        Intent goToMainMenu = new Intent(this, MainActivity.class);
        startActivity(goToMainMenu);

    }

    //sends the user back to add a new task
    public void newTask(View v){
        Intent enterNewToDoActivity = new Intent(this, EnterNewToDoActivity.class);
        startActivity(enterNewToDoActivity);
    }


}

