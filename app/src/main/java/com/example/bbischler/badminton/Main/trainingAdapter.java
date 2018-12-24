
package com.example.bbischler.badminton.Main;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bbischler.badminton.Model.Training;
import com.example.bbischler.badminton.R;

import java.util.ArrayList;
import java.util.List;


public class trainingAdapter extends ArrayAdapter<Training> {

    private Context mContext;
    private List<Training> trainingListList = new ArrayList<>();

    public trainingAdapter(@NonNull Context context, @LayoutRes ArrayList<Training> list) {
        super(context, 0, list);
        mContext = context;
        trainingListList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.traininglist, parent, false);

        Training currentTraining = trainingListList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentTraining.getName());

        TextView datum = (TextView) listItem.findViewById(R.id.textView_date);
        datum.setText(currentTraining.getDatum());

        TextView time = (TextView) listItem.findViewById(R.id.textView_time);
        time.setText(currentTraining.getTime());

        return listItem;
    }
}