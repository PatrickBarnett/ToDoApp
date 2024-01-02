package com.example.todoapp;

import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateMemes {
    String toDoItemError;
    String priorityError;
    String dueDateError;

    public String getPriorityError() {
        return priorityError;
    }

    public String getDueDateError() {
        return dueDateError;
    }

    public ValidateMemes(){

    }

    public String getToDoItemError() {
        return toDoItemError;
    }

    public boolean validateToDoItem(ToDoItem valTask){
        boolean formChecker = true;

        //Checks user input for soemthing
        String toDoItemStr = valTask.getItem();
        if (toDoItemStr.equals("")){
            toDoItemError = "Please enter a value";
            formChecker = false;
        }


        //Checks user input To make sure it matchs the pattern, is only 1 character, and not nothing
        String priorityStr = valTask.getPriority();
        Pattern pattern = Pattern.compile("[0-9A-Z]");
        Matcher matcher = pattern.matcher(priorityStr);
        boolean matchFound = matcher.find();
        if (priorityStr.length() > 1){
            priorityError = "It can only be one character!";
            formChecker = false;
        }
        if (priorityStr.equals("") || !matchFound){
            priorityError = "You need to enter 1-9 or A-Z";
            formChecker = false;
        }

        //Checks user input for a valid date
        String dueDateStr = valTask.getDueDate();
        if (dueDateStr.equals("")){
            dueDateError = "You need to enter a date! YYYY/MM/DD ";
            formChecker = false;
        }



        return formChecker;
    }
}
