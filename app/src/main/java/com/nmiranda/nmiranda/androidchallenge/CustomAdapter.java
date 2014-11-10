package com.nmiranda.nmiranda.androidchallenge;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nahuel on 08/11/2014.
 */
public class CustomAdapter extends ArrayAdapter<Task> {

    public CustomAdapter(Context context, List<Task> values) {
        super(context, R.layout.task_item_layout, values);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater theInflater = LayoutInflater.from(getContext());
        View theView = theInflater.inflate(R.layout.task_item_layout,parent,false);

        //Create views from database
        final Task note = getItem(position);
        TextView textView = (TextView) theView.findViewById(R.id.main_listview_textview_details);
        textView.setText(note.getTask());
        switch (Integer.parseInt(note.getColor())) {
            case 0: //RED
                textView.setTextColor(Color.parseColor("#CC0303"));
                theView.setBackgroundColor(Color.parseColor("#FF9F9F"));
                break;
            case 1: //ORANGE
                textView.setTextColor(Color.parseColor("#E69138"));
                theView.setBackgroundColor(Color.parseColor("#FFEAD5"));
                break;
            case 2: //GREEN
                textView.setTextColor(Color.parseColor("#38761D"));
                theView.setBackgroundColor(Color.parseColor("#D0EBC5"));
                break;
            case 3: //BLUE
                textView.setTextColor(Color.parseColor("#0B5394"));
                theView.setBackgroundColor(Color.parseColor("#9DC2E4"));
                break;
            case 4: //PURPLE
                textView.setTextColor(Color.parseColor("#351C75"));
                theView.setBackgroundColor(Color.parseColor("#B1A2D8"));
                break;
        }

        ImageButton buttonDelete = (ImageButton) theView.findViewById(R.id.main_listview_imagebutton_delete);
        buttonDelete.setFocusable(false);

        return theView;
    }

}
