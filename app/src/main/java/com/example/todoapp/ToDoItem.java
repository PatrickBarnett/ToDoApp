package com.example.todoapp;

import java.io.Serializable;
import java.util.Date;



public class ToDoItem implements Serializable {
    String item, priority, dueDate, status, dateMade;
    int id;

    public String getDateMade() {
        return dateMade;
    }



    public void setDateMade(String dateMade) {
        this.dateMade = dateMade;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return dueDate;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ToDoItem (int id, String dateMade, String item, String priority, String dueDate, String status){
        this.id = id;
        this.dateMade = dateMade;
        this.item = item;
        this.priority = priority;
        this.dueDate = dueDate;
        this.status = status;
    }
}
