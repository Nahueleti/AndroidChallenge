package com.nmiranda.nmiranda.androidchallenge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class EditActivity extends Activity {

    private static final int RESULT_NOT_OK = 2;
    private int CLOSE_CODE;
    public String newTask;
    public String newColor;
    public Button submitButton;
    public EditText mTaskDetails;
    public ImageButton mColorRed;
    public ImageButton mColorOrange;
    public ImageButton mColorGreen;
    public ImageButton mColorBlue;
    public ImageButton mColorPurple;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        View view = (View)findViewById(R.id.edit_view_outside);
        submitButton = (Button)findViewById(R.id.edit_button_submit);
        mTaskDetails = (EditText)findViewById(R.id.edit_edittext_newtask);
        mColorRed = (ImageButton)findViewById(R.id.edit_imagebutton_red);
        mColorOrange = (ImageButton)findViewById(R.id.edit_imagebutton_orange);
        mColorGreen = (ImageButton)findViewById(R.id.edit_imagebutton_green);
        mColorBlue = (ImageButton)findViewById(R.id.edit_imagebutton_blue);
        mColorPurple = (ImageButton)findViewById(R.id.edit_imagebutton_purple);

        mColorRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newColor = "0";
                Toast.makeText(getApplicationContext(),getString(R.string.message_red), Toast.LENGTH_SHORT).show();
            }
        });
        mColorOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newColor = "1";
                Toast.makeText(getApplicationContext(),getString(R.string.message_orange), Toast.LENGTH_SHORT).show();
            }
        });
        mColorGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newColor = "2";
                Toast.makeText(getApplicationContext(),getString(R.string.message_green), Toast.LENGTH_SHORT).show();
            }
        });
        mColorBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newColor = "3";
                Toast.makeText(getApplicationContext(),getString(R.string.message_blue), Toast.LENGTH_SHORT).show();
            }
        });
        mColorPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newColor = "4";
                Toast.makeText(getApplicationContext(),getString(R.string.message_purple), Toast.LENGTH_SHORT).show();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CLOSE_CODE = RESULT_NOT_OK;
                finish();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newTask = mTaskDetails.getText().toString();
                if (newTask.length() == 0) {
                    Toast.makeText(getApplicationContext(), getString(R.string.warning_task_empty), Toast.LENGTH_LONG).show();
                } else {
                    //If no color was picked, default to 0 (RED)
                    if (newColor == null) {
                        newColor = "0";
                    }
                    CLOSE_CODE = RESULT_OK;
                    finish();
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit, menu);
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
    public void finish() {

        if (CLOSE_CODE == RESULT_CANCELED) {
            setResult(CLOSE_CODE);
            super.finish();
        } else {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("Task",newTask);
            returnIntent.putExtra("Color",newColor);
            setResult(CLOSE_CODE,returnIntent);
            super.finish();
        }
    }


}
