package com.nmiranda.nmiranda.androidchallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;


public class MainActivity extends Activity {

    private TasksDataSource datasource;
    static final int CREATE_NEW_TASK = 1;
    static final String TASK_KEY = "Task";
    static final String TASK_COLOR = "Color";
    ListView mTasks;
    ImageButton mEdit;
    CustomAdapter mAdapter;
    List<Task> mValues;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //prepare database entries to be adapted to listview
        datasource = new TasksDataSource(this);
        datasource.open();
        mValues = datasource.getAllTasks();
        mAdapter = new CustomAdapter(this,mValues);
        mTasks = (ListView) findViewById(R.id.main_listview_tasks);
        mTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task task = mValues.get(i);
                mAdapter.remove(task);
                datasource.open();
                datasource.deleteTask(task);
                mAdapter.notifyDataSetChanged();
                datasource.close();
            }
        });
        mTasks.setAdapter(mAdapter);

        //prepare Edit button to lauch activity for result
        mEdit = (ImageButton) findViewById(R.id.main_imagebutton_edit);
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),EditActivity.class);
                startActivityForResult(intent, CREATE_NEW_TASK);
            }
        });


        datasource.close();

    }

    @Override
    protected void onResume() {
        datasource = new TasksDataSource(this);
        datasource.open();
        mValues = datasource.getAllTasks();
        mAdapter = new CustomAdapter(this,mValues);
        mTasks = (ListView) findViewById(R.id.main_listview_tasks);
        mTasks.setAdapter(mAdapter);
        datasource.close();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CREATE_NEW_TASK) {
            if (resultCode == RESULT_OK) {
                String message = data.getStringExtra(TASK_KEY);
                String color = data.getStringExtra(TASK_COLOR);
                datasource.open();
                Task task = datasource.createTask(message, color);
                mAdapter.notifyDataSetChanged();
                datasource.close();
            } else {
                Toast.makeText(this,getString(R.string.warning_task_not_saved),Toast.LENGTH_LONG).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
