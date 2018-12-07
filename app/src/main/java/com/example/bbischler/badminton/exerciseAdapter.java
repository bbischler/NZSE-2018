package com.example.bbischler.badminton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bbischler.badminton.Exercise;
import com.example.bbischler.badminton.R;

import java.util.List;

public class exerciseAdapter extends
        RecyclerView.Adapter<exerciseAdapter.MyViewHolder> {

    private List<Exercise> list;

    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView time;
        public TextView desc;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.textView_exerciseName);
            time = (TextView) view.findViewById(R.id.textView_exerciseTime);
            desc = (TextView) view.findViewById(R.id.textView_exerciseDesc);

        }
    }

    public exerciseAdapter(List<Exercise> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Exercise e = list.get(position);
        holder.name.setText(e.getName());
        holder.time.setText(e.getDuration());
        holder.desc.setText(e.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise,parent, false);
        return new MyViewHolder(v);
    }
}