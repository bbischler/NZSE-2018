
package com.example.bbischler.badminton.Main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bbischler.badminton.Model.Training;
import com.example.bbischler.badminton.R;

import java.util.ArrayList;
import java.util.List;


public class trainingAdapter extends ArrayAdapter<Training> {

    private Context mContext;
    private List<Training> trainingListList = new ArrayList<>();

    public trainingAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Training> list) {
        super(context, 0, list);
        mContext = context;
        trainingListList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.training_item, parent, false);

        Training currentTraining = trainingListList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentTraining.getName());

        TextView datumMonth = (TextView) listItem.findViewById(R.id.textView_dateMonth);
        datumMonth.setText(currentTraining.getDatumMonth());


        TextView datumYear = (TextView) listItem.findViewById(R.id.textView_dateYear);
        datumYear.setText(currentTraining.getDatumYear());


        TextView time = (TextView) listItem.findViewById(R.id.textView_time);
        time.setText(currentTraining.getTime());

        ImageView acceptImage = (ImageView) listItem.findViewById(R.id.imageView_accept);
        ImageView declineImage = (ImageView) listItem.findViewById(R.id.imageView_decline);

        TextView indicator = (TextView) listItem.findViewById(R.id.indicator);

        int setColor = Color.WHITE;

        if (currentTraining.isCancelled()) {
            setColor = Color.DKGRAY;
        } else {
            switch (currentTraining.getAcceptState()) {
                case Accepted:
                    acceptImage.setVisibility(View.VISIBLE);
                    declineImage.setVisibility(View.GONE);
                    indicator.setVisibility(View.VISIBLE);
                    indicator.setBackgroundColor(Color.parseColor("#b334af3a"));
//                    setColor = Color.GREEN;
                    break;
                case Cancelled:
                    acceptImage.setVisibility(View.GONE);
                    declineImage.setVisibility(View.VISIBLE);
                    indicator.setVisibility(View.VISIBLE);
                    indicator.setBackgroundColor(Color.parseColor("#bade3131"));
//                    setColor = Color.RED;
                    break;
                case Unset:
                    acceptImage.setVisibility(View.INVISIBLE);
                    declineImage.setVisibility(View.GONE);
                    indicator.setBackgroundColor(Color.parseColor("#ffffffff"));
//                    setColor = Color.WHITE;
                    break;
            }
        }

//        name.setTextColor(setColor);
//        datum.setTextColor(setColor);
//        time.setTextColor(setColor);

        return listItem;
    }
}