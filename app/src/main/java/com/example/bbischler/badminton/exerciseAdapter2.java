package com.example.bbischler.badminton;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class exerciseAdapter2 extends ArrayAdapter<Exercise> {

    private Context eContext;
    private List<Exercise> exerciseListList = new ArrayList<>();

    public exerciseAdapter2(@NonNull Context context, @LayoutRes ArrayList<Exercise> list) {
        super(context, 0, list);
        eContext = context;
        exerciseListList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(eContext).inflate(R.layout.exercise, parent, false);

        Exercise currentTraining = exerciseListList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.textView_exerciseName);
        name.setText(currentTraining.getName());

        TextView time = (TextView) listItem.findViewById(R.id.textView_exerciseTime);
        time.setText(currentTraining.getDuration());

        TextView desc = (TextView) listItem.findViewById(R.id.textView_exerciseDesc);
        desc.setText(currentTraining.getDescription());

        return listItem;
    }


}
