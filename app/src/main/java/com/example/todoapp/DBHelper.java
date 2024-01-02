package com.example.todoapp;

        import java.util.ArrayList;
        import java.util.Date;
        import java.util.HashMap;
        import java.util.Hashtable;
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.DatabaseUtils;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.database.sqlite.SQLiteDatabase;

//Making the Database helper class
public class DBHelper extends SQLiteOpenHelper {

    //Sets variables
    public static final String DATABASE_NAME = "ToDoAppDB.db";
    public static final String TODOITEM_TABLE_NAME = "ToDoItem";
    public static final String TODOITEM_COLUMN_ID = "id";
    public static final String TODOITEM_COLUMN_DATE_CREATED = "dateMade";
    public static final String TODOITEM_COLUMN_DESCRIPTION = "item";
    public static final String TODOITEM_COLUMN_PRIORITY = "priority";
    public static final String TODOITEM_COLUMN_DUE_DATE = "dueDate";
    public static final String TODOITEM_COLUMN_STATUS = "status";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    //will override the oncreate method with a new one
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table ToDoItem " +
                        "(id integer primary key, dateMade text,item text,priority text,dueDate text,status text)"
        );
    }

    //
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS ToDoItem");
        onCreate(db);
    }

    //This will instert the user information into the database
    public boolean insertTask (ToDoItem insertItem){
       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues contentValues = new ContentValues();
       contentValues.put("dateMade", insertItem.getDateMade());
       contentValues.put("item", insertItem.getItem());
       contentValues.put("priority", insertItem.getPriority());
       contentValues.put("dueDate", insertItem.getDueDate());
       contentValues.put("status", insertItem.getStatus());
       db.insert("ToDoItem", null, contentValues);
       return true;
    }

    //
    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    //No sorting, just all items
    public ArrayList<ToDoItem> getAllItems(){
        return getAllItems("select * from toDoItem");
    }

    //sorted by priority
    public  ArrayList<ToDoItem> getAllItemsByPriority(){
        return  getAllItems("select * from toDoItem order by priority asc");
    }

    //sorted by date made
    public  ArrayList<ToDoItem> getAllItemsByDateMade(){
        return  getAllItems("select * from toDoItem order by dateMade asc");
    }

    //sorted by due date
    public  ArrayList<ToDoItem> getAllItemsByDueDate(){
        return  getAllItems("select * from toDoItem order by dueDate asc");
    }

    //sorted by status
    public  ArrayList<ToDoItem> getAllItemsByStatus(){
        return  getAllItems("select * from toDoItem order by status asc");
    }

    //Gets all the information from the database for the listview
    public ArrayList<ToDoItem> getAllItems(String query) {
        ArrayList<ToDoItem> array_list = new ArrayList<ToDoItem>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( query, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            int idIndex = res.getColumnIndex(TODOITEM_COLUMN_ID);
            int descriptionIndex = res.getColumnIndex(TODOITEM_COLUMN_DESCRIPTION);
            int dateMadeIndex = res.getColumnIndex(TODOITEM_COLUMN_DATE_CREATED);
            int dueDateIndex = res.getColumnIndex(TODOITEM_COLUMN_DUE_DATE);
            int priorityIndex = res.getColumnIndex(TODOITEM_COLUMN_PRIORITY);
            int statusIndex = res.getColumnIndex(TODOITEM_COLUMN_STATUS);
            int id = res.getInt(idIndex);
            String description = res.getString(descriptionIndex);
            String dateMade = res.getString(dateMadeIndex);
            String dueDate = res.getString(dueDateIndex);
            String priority = res.getString(priorityIndex);
            String status = res.getString(statusIndex);

            ToDoItem obj = new ToDoItem(id, dateMade, description, priority, dueDate, status);
            array_list.add(obj);

            res.moveToNext();
        }
        return array_list;
    }


        // This will update the list item with new information from the user
        public boolean updateTask (ToDoItem toDoItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("item", toDoItem.getItem());
        contentValues.put("priority", toDoItem.getPriority());
        contentValues.put("dueDate", toDoItem.getDueDate());
        contentValues.put("status", toDoItem.getStatus());
        db.update("ToDoItem", contentValues, "id = ? ", new String[] { Integer.toString(toDoItem.getId()) } );
        return true;
    }

    // this will delete the task from the list
    public Integer deleteTask (ToDoItem toDoItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("ToDoItem", "id = ? ", new String[] { Integer.toString(toDoItem.getId()) });
    }


}