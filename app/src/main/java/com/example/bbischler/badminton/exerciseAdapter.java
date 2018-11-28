
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


public class exerciseAdapter extends ArrayAdapter<Exercise> {

    private Context mContext;
    private List<Exercise> exerciseList = new ArrayList<>();

    public exerciseAdapter(@NonNull Context context, @LayoutRes ArrayList<Exercise> list) {
        super(context, 0, list);
        mContext = context;
        exerciseList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.traininglist, parent, false);

        Exercise currentExercise = exerciseList.get(position);


        TextView name = (TextView) listItem.findViewById(R.id.textView_exerciseName);
        name.setText(currentExercise.getName());

        TextView time = (TextView) listItem.findViewById(R.id.textView_exerciseTime);
        time.setText(currentExercise.getDuration());

        TextView desc = (TextView) listItem.findViewById(R.id.textView_exerciseDesc);
        desc.setText(currentExercise.getDescription());

        return listItem;
    }
}