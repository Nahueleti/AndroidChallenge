package com.nmiranda.nmiranda.androidchallenge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nahuel on 08/11/2014.
 */
public class TasksDataSource {
    // Database fields
    private SQLiteDatabase database;
    private CustomSQLiteHelper dbHelper;
    private String[] allColumns = { CustomSQLiteHelper.COLUMN_ID,
            CustomSQLiteHelper.COLUMN_TASK, CustomSQLiteHelper.COLUMN_COLOR };

    public TasksDataSource(Context context) {
        dbHelper = new CustomSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Task createTask(String task, String color) {
        ContentValues values = new ContentValues();
        values.put(CustomSQLiteHelper.COLUMN_TASK, task);
        values.put(CustomSQLiteHelper.COLUMN_COLOR, color);
        long insertId = database.insert(CustomSQLiteHelper.TABLE_TASKS, null,
                values);
        Cursor cursor = database.query(CustomSQLiteHelper.TABLE_TASKS,
                allColumns, CustomSQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Task newTask = cursorToTask(cursor);
        cursor.close();
        return newTask;
    }

    public void deleteTask(Task task) {
        long id = task.getId();

        database.delete(CustomSQLiteHelper.TABLE_TASKS, CustomSQLiteHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<Task>();

        Cursor cursor = database.query(CustomSQLiteHelper.TABLE_TASKS,
                allColumns,null,null,null,null,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Task task = cursorToTask(cursor);
            tasks.add(task);
            cursor.moveToNext();
        }
        cursor.close();
        return tasks;
    }

    private Task cursorToTask(Cursor cursor) {
        Task task = new Task();
        task.setId(cursor.getLong(0));
        task.setTask(cursor.getString(1));
        task.setColor(cursor.getString(2));
        return task;
    }
}
