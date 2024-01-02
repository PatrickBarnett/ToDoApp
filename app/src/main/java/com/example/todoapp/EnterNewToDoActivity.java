package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.util.Date;

public class EnterNewToDoActivity extends AppCompatActivity {

    private String dateFormat;

    DBHelper toDoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toDoDB = new DBHelper(this);
        setContentView(R.layout.activity_enter_new_to_do);
        setTitle("Enter in a new Task");
    }
    //sends user back to main menu
    public void goToMainMenu(View v) {
        Intent goToMainMenu = new Intent(this, MainActivity.class);
        startActivity(goToMainMenu);

    }
    // Sends the user back to look at already added tasks
    public void lookAtTasks(View v){
        Intent lookAtList = new Intent(this, ToDoListActivity.class);
        startActivity(lookAtList);
    }


    // insert information into the database that the user has submitted
    public void insertDB(View v){

        EditText toDoItem = (EditText)findViewById(R.id.toDoItem);
        EditText priority = (EditText)findViewById(R.id.priority);
        EditText dueDate = (EditText)findViewById(R.id.dueDate);

        int selectedStatus = ((RadioGroup)findViewById(R.id.statusUpdate)).getCheckedRadioButtonId();
        RadioGroup status = (RadioGroup)findViewById(R.id.statusUpdate);
        String statusStr = ((RadioButton)findViewById(selectedStatus)).getText().toString();

        //Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        //Formatting the obtained date
        String formattedDate = formatter.format(new Date());
        ToDoItem newTask = new ToDoItem(-1, formattedDate,
                toDoItem.getText().toString(),
                priority.getText().toString(),
                dueDate.getText().toString(),
                statusStr);

        ValidateMemes validator = new ValidateMemes();
        boolean formChecker = validator.validateToDoItem(newTask);
        if (!formChecker) {
            toDoItem.setError(validator.getToDoItemError());
            priority.setError(validator.getPriorityError());
            dueDate.setError(validator.getDueDateError());
            return;
        }


        toDoDB.insertTask(newTask);

        //sets the text fields back to nothing
        dueDate.setText("");
        priority.setText("");
        toDoItem.setText("");
    }
}