package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

//Validator for updating and submitting new tasks
public class UpdateToDoItemActivity extends AppCompatActivity {
    ToDoItem updateMemes;
    DBHelper toDoDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_to_do_item);
        setTitle("Update ToDo Item");
        toDoDB = new DBHelper(this);

        updateMemes = (ToDoItem)getIntent().getSerializableExtra("toDoItem");
        EditText updateTask = (EditText)findViewById(R.id.taskUpdate);
        EditText updatePriority = (EditText)findViewById(R.id.priorityUpdate);
        EditText updateDueDate = (EditText)findViewById(R.id.dueDateUpdate);
        //RadioGroup updateStatus = (RadioGroup)findViewById(R.id.statusUpdate);
        updateTask.setText(updateMemes.getItem());
        updatePriority.setText(updateMemes.getPriority());
        updateDueDate.setText(updateMemes.getDueDate());
        String status = updateMemes.getStatus();
        if (status.equals("Not Started")){
            RadioButton updateRadioButton = (RadioButton)findViewById(R.id.notStartedUpdate);
            updateRadioButton.setSelected(true);
        }
        else if( status.equals("In-Progess")){
            RadioButton updateRadioButton = (RadioButton)findViewById(R.id.inProgressUpdate);
            updateRadioButton.setSelected(true);
        }
        else if (status.equals("Done")){
            RadioButton updateRadioButton = (RadioButton)findViewById(R.id.doneUpdate);
            updateRadioButton.setSelected(true);
        }
    }

    //submits the update back to the database
    public void submitUpdate (View v){

        EditText updateTask = (EditText)findViewById(R.id.taskUpdate);
        EditText updatePriority = (EditText)findViewById(R.id.priorityUpdate);
        EditText updateDueDate = (EditText)findViewById(R.id.dueDateUpdate);
        RadioGroup updateStatus = (RadioGroup)findViewById(R.id.statusUpdate);
        updateMemes.setItem(updateTask.getText().toString());
        updateMemes.setPriority(updatePriority.getText().toString());
        updateMemes.setDueDate(updateDueDate.getText().toString());

        int selectedStatus = updateStatus.getCheckedRadioButtonId();
        String statusStr = ((RadioButton)findViewById(selectedStatus)).getText().toString();
        updateMemes.setStatus(statusStr);

        ValidateMemes validator = new ValidateMemes();
        boolean formCheck = validator.validateToDoItem(updateMemes);
        if (!formCheck){
            updateTask.setError(validator.getToDoItemError());
            updatePriority.setError(validator.getPriorityError());
            updateDueDate.setError(validator.getDueDateError());
            return;
        }
        toDoDB.updateTask(updateMemes);


        Intent toDoListIntent = new Intent(this, ToDoListActivity.class);
        startActivity(toDoListIntent);
    }


}