package com.example.bbischler.badminton.Details;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.example.bbischler.badminton.Model.Exercise;
import com.example.bbischler.badminton.Model.Training;
import com.example.bbischler.badminton.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailedTrainingActivity extends AppCompatActivity implements StartDragListener, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    Training training;
    RecyclerViewAdapter mAdapter;
    ItemTouchHelper touchHelper;
    TextView trainingTime;
    TextView trainingDate;
    TextView trainingDesc;
    private RecyclerView recyclerview;
    CoordinatorLayout rootlayout;
    List<Exercise> excersises = new ArrayList<>();
    String description = "Lorem ipsum dolor sit amet, conset etur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam";
    String descriptionExercise = "Lorem ipsum dolor sit amet, conset etur sadipscing elitr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_training);
        Bundle b = getIntent().getExtras();
        int trainingID = -1; // or other values
        if (b != null)
            trainingID = b.getInt("trainingID");
        rootlayout = findViewById(R.id.root);
        training = new Training(trainingID, "testTraining", new Date(), new Date(), new Date(), description, 14);
        recyclerview = (RecyclerView) findViewById(R.id.exerciseList);
        mAdapter = new RecyclerViewAdapter(excersises, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerview.setAdapter(mAdapter);

//        Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        Mock Exercises
        for (int i = 0; i < 8; i++) {
            excersises.add(new Exercise(i, "Übung Nr. " + i, 10 + i, descriptionExercise));
        }
        training.setExcersises(excersises);

        trainingTime = (TextView) findViewById(R.id.textView_time);
        trainingTime.setText(training.getTime());
        trainingDate = (TextView) findViewById(R.id.textView_date);
        trainingDate.setText(training.getDatum());
        trainingDesc = (TextView) findViewById(R.id.textView_desc);
        trainingDesc.setText(training.getDescription());
        TextView numberStudents = (TextView) findViewById(R.id.textView_numberStudents);
        numberStudents.setText(training.getStudentsNumber());


        ItemTouchHelper.Callback callback =
                new ItemMoveCallback(mAdapter);
        touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerview);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerview);

        recyclerview.setAdapter(mAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(llm);
        recyclerview.setNestedScrollingEnabled(false);
        recyclerview.setFocusable(false);

        setupSwipeToDelete();

    }

    private void setupSwipeToDelete() {
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // Row is swiped from recycler view
                // remove it from adapter
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                // view the background view
            }
        };
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerview);
    }

    @Override
    public void requestDrag(RecyclerView.ViewHolder viewHolder) {
        touchHelper.startDrag(viewHolder);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof RecyclerViewAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String name = excersises.get(viewHolder.getAdapterPosition()).getName();

            // backup of removed item for undo purpose
            final Exercise deletedItem = excersises.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            mAdapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(rootlayout, name + " entfernt!", Snackbar.LENGTH_LONG);
            snackbar.setAction("Wiederherstellen", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    mAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.parseColor("#ffff00"));
            snackbar.show();
        }
    }
}
