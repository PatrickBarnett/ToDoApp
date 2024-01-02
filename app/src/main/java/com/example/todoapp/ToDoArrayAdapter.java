package com.example.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ToDoArrayAdapter extends ArrayAdapter<ToDoItem> {
    private final Context context;
    private final ArrayList<ToDoItem> toDoArray;


    public ToDoArrayAdapter(@NonNull Context context, ArrayList<ToDoItem> toDoArray){
        super(context, 0, toDoArray);
        this.context = context;
        this.toDoArray = toDoArray;
    }

    //Populates the listview with information that the user has added
    @NonNull
    @Override
    public View getView(int position, View counterView, @NonNull ViewGroup parent) {
        View listItem = counterView;
        if(listItem == null)
            listItem = LayoutInflater.from(context).inflate(R.layout.to_do_list_view,parent,false);

        ToDoItem objItem = toDoArray.get(position);

        TextView toDoItemName = (TextView) listItem.findViewById(R.id.toDoItemName);
        toDoItemName.setText("ToDoItem: " + objItem.getItem());

        TextView toDoItemPriority = (TextView) listItem.findViewById(R.id.toDoItemPriority);
        toDoItemPriority.setText("Priority: " + objItem.getPriority());

        TextView toDoItemStatus = (TextView) listItem.findViewById(R.id.toDoItemStatus);
        toDoItemStatus.setText("Status: " + objItem.getStatus());

        TextView toDoItemDateMade = (TextView) listItem.findViewById(R.id.toDoItemDateMade);
        toDoItemDateMade.setText("Date Made: " + objItem.getDateMade());

        TextView toDoItemDueDate = (TextView) listItem.findViewById(R.id.toDoItemDueDate);
        toDoItemDueDate.setText("Due Date: " + objItem.getDueDate());

        return listItem;


    }

}
