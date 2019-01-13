package com.example.bbischler.badminton.Details;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bbischler.badminton.Model.Exercise;
import com.example.bbischler.badminton.Model.TrainingExercise;
import com.example.bbischler.badminton.R;
import com.example.bbischler.badminton.Service.IBadmintonServiceInterface;
import com.example.bbischler.badminton.Service.MockBadmintonService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements ItemMoveCallback.ItemTouchHelperContract {

    private ArrayList<TrainingExercise> data;
    int trainingID;
    IBadmintonServiceInterface service;

    private final StartDragListener mStartDragListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout viewForeground;
        private TextView name;
        private TextView time;
        private TextView desc;
        ImageView imageView;
        View rowView;

        public MyViewHolder(View itemView) {
            super(itemView);

            viewForeground = itemView.findViewById(R.id.viewForeground);
            rowView = itemView;
            name = itemView.findViewById(R.id.textView_exerciseName);
            time = itemView.findViewById(R.id.textView_exerciseTime);
            desc = itemView.findViewById(R.id.textView_exerciseDesc);
            imageView = itemView.findViewById(R.id.handle);
        }
    }

    public RecyclerViewAdapter(ArrayList<TrainingExercise> data, StartDragListener startDragListener, int trainingid) {
        mStartDragListener = startDragListener;
        this.data = data;
        this.trainingID = trainingid;
        service = new MockBadmintonService();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        TrainingExercise exercise = data.get(position);

        holder.name.setText(exercise.getExercise().getName());
        holder.time.setText(""+exercise.getDurationMinutes());
        holder.desc.setText(exercise.getExercise().getDescription());

        holder.imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() ==
                        MotionEvent.ACTION_DOWN) {
                    mStartDragListener.requestDrag(holder);
                }
                return false;
            }
        });
    }

    public void removeItem(int position) {
        data.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(TrainingExercise item, int position) {
        data.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public void onRowMoved(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(data, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(data, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onRowSelected(MyViewHolder myViewHolder) {
        myViewHolder.rowView.setBackgroundColor(Color.GRAY);

    }

    @Override
    public void onRowClear(MyViewHolder myViewHolder) {
        myViewHolder.rowView.setBackgroundColor(Color.WHITE);
        //service.setExercisesForTraining(trainingID, data);
    }
}