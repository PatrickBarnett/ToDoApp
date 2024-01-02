package com.example.todoapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

public class ToDoListSelectDialogFragment extends Dialog {

    ToDoItem toDoItem;
    DBHelper helpDb;
    Activity createNewActivity;

    public ToDoListSelectDialogFragment(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public  void setUpdateActivity(Activity createNewActivity){
        this.createNewActivity = createNewActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Button closeThis = (Button)findViewById(R.id.closeDialogButton);
        closeThis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                dismiss();
                cancel();
            }

        });
        Button updateThis = (Button)findViewById(R.id.updateTaskButton);
        updateThis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                helpDb.updateTask(toDoItem);
                dismiss();
                Intent updatePage = new Intent(createNewActivity, UpdateToDoItemActivity.class);
                updatePage.putExtra("toDoItem", toDoItem);
                createNewActivity.startActivity(updatePage);

            }

        });
        Button deleteThis = (Button)findViewById(R.id.deleteTaskButton);
        deleteThis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                helpDb.deleteTask(toDoItem);
                dismiss();
            }

        });

    }

    public void setDBHelper(DBHelper helpDb){
        this.helpDb = helpDb;
    }

    public void setItem(ToDoItem toDoItem){
        this.toDoItem = toDoItem;

    }


}
