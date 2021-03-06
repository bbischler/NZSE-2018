package com.example.bbischler.badminton.Details;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bbischler.badminton.Exercise.ChooseExercisePopup;
import com.example.bbischler.badminton.Exercise.NewExercisePopop;
import com.example.bbischler.badminton.Model.AcceptState;
import com.example.bbischler.badminton.Model.Training;
import com.example.bbischler.badminton.Model.TrainingExercise;
import com.example.bbischler.badminton.R;
import com.example.bbischler.badminton.Service.IBadmintonServiceInterface;
import com.example.bbischler.badminton.Service.MockBadmintonService;
import com.example.bbischler.badminton.Service.MySession;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Hashtable;

public class DetailedTrainingActivity extends AppCompatActivity implements StartDragListener, RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {
    Training training;
    RecyclerViewAdapter mAdapter;
    NestedScrollView view_exerciseList;
    ItemTouchHelper touchHelper;
    TextView trainingTime;
    TextView trainingDate;
    TextView trainingDesc;
    Button btn_Zusage;
    Button btn_Absage;
    Button btn_NeueUebung;
    private RecyclerView recyclerview;
    CoordinatorLayout rootlayout;
    //ArrayList<Exercise> excersises = new ArrayList<>();
    //    String description = "Lorem ipsum dolor sit amet, conset etur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam";
//    String descriptionExercise = "Lorem ipsum dolor sit amet, conset etur sadipscing elitr";
    IBadmintonServiceInterface service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_training);

        service = new MockBadmintonService();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle b = getIntent().getExtras();
        int trainingID = -1; // or other values
        if (b != null)
            trainingID = b.getInt("trainingID");
        rootlayout = findViewById(R.id.root);
        btn_Absage = findViewById(R.id.btn_absagen);
        btn_Zusage = findViewById(R.id.btn_zusagen);
        btn_NeueUebung = findViewById(R.id.btn_NeueUebung);
        view_exerciseList = findViewById(R.id.exerciseList_View);
        training = service.getTraining(trainingID);
        recyclerview = (RecyclerView) findViewById(R.id.exerciseList);
        mAdapter = new RecyclerViewAdapter(training.getExcersises(), this, trainingID);

        btn_Absage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_Absage_onClick(v);
            }
        });
        btn_Zusage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_Zusage_onClick(v);
            }
        });
        btn_NeueUebung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_NeueUebung_onClick(v);
            }
        });


        if (!MySession.isUserLoggedIn()) {
            view_exerciseList.setVisibility(View.GONE);
            btn_NeueUebung.setVisibility(View.GONE);
        } else {
            btn_Zusage.setVisibility(View.GONE);
        }


        if (checkState() == null) {
            btn_Zusage.setClickable(true);
            btn_Absage.setClickable(true);
        } else if (checkState() == false) {
            btn_Zusage.setClickable(true);
            btn_Absage.setClickable(false);
            btn_Absage.setTextColor(Color.parseColor("#FFB9B9B9"));
        } else {
            btn_Zusage.setClickable(false);
            btn_Absage.setClickable(true);
            btn_Zusage.setTextColor(Color.parseColor("#FFB9B9B9"));
        }


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerview.setAdapter(mAdapter);

        /*
        excersises.addAll(service.getExercises());
        training.setExcersises(excersises);
        */

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

    private Boolean checkState() {

        if(MySession.isUserLoggedIn())
            return null;
        Hashtable<String, Boolean> myCancellations;
        Gson gson = new Gson();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = pref.edit();
        String json = pref.getString("myCancellations", "");
        myCancellations = (json == "") ? new Hashtable<String, Boolean>() : gson.fromJson(json, Hashtable.class);
        String _trainingID = training.getId().toString();
        if (myCancellations.containsKey(_trainingID)) {

            if (myCancellations.get(_trainingID) == true) {
                return true;
            } else if (myCancellations.get(_trainingID) == false) {
                return false;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    private void btn_NeueUebung_onClick(View v) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Übung hinzufügen");
        // alertDialog.setMessage("Alert message to be shown");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Neu",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i =new Intent(DetailedTrainingActivity.this, NewExercisePopop.class);
                        i.putExtra("trainingID", training.getId());
                        startActivityForResult(i, 1);
                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Katalog",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(DetailedTrainingActivity.this, ChooseExercisePopup.class);
                        i.putExtra("trainingID", training.getId());
                        startActivityForResult(i, 1);
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
        System.out.println("Debug");
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    private void btn_Zusage_onClick(View v) {
        training.setNumberParticipants(training.getNumberParticipants() + 1);
        training.setAcceptState(AcceptState.Accepted);

        Hashtable<String, Boolean> myCancellations;
        Gson gson = new Gson();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = pref.edit();
        String json = pref.getString("myCancellations", "");
        myCancellations = (json == "") ? new Hashtable<String, Boolean>() : gson.fromJson(json, Hashtable.class);
        String trainingID = training.getId().toString();
        myCancellations.remove(trainingID);
        myCancellations.put(trainingID, true);

        json = gson.toJson(myCancellations, new TypeToken<HashMap<Integer, Boolean>>() {
        }.getType());
        prefsEditor.putString("myCancellations", json);
        prefsEditor.commit();


        Intent intent = new Intent();
        intent.putExtra("action", "accept");
        intent.putExtra("TrainingsId", training.getId());
        setResult(RESULT_OK, intent);
        finish();
    }

    private void btn_Absage_onClick(View v) {


        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Training wirklich absagen?");
        // alertDialog.setMessage("Alert message to be shown");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ja",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        training.setNumberParticipants(training.getNumberParticipants() - 1);

                        if (MySession.isUserLoggedIn()) {
                            service.cancelTraining(training.getId());
                            intent.putExtra("userMode", "trainer");
                        } else {
                            training.setAcceptState(AcceptState.Cancelled);
                            Hashtable<String, Boolean> myCancellations;
                            Gson gson = new Gson();

                            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
                            SharedPreferences.Editor prefsEditor = pref.edit();
                            String json = pref.getString("myCancellations", "");
                            myCancellations = (json == "") ? new Hashtable<String, Boolean>() : gson.fromJson(json, Hashtable.class);
                            String trainingID = training.getId().toString();
                            myCancellations.remove(trainingID);
                            myCancellations.put(trainingID, false);


                            json = gson.toJson(myCancellations, new TypeToken<HashMap<Integer, Boolean>>() {
                            }.getType());
                            prefsEditor.putString("myCancellations", json);
                            prefsEditor.commit();

                            intent.putExtra("userMode", "user");
                        }


                        intent.putExtra("action", "cancel");
                        intent.putExtra("TrainingsId", training.getId());
                        setResult(RESULT_OK, intent);
                        finish();

                        dialog.dismiss();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Nein",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        dialog.dismiss();
                    }
                });
        alertDialog.show();
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
        System.out.println("Debug");
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof RecyclerViewAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String name = training.getExcersises().get(viewHolder.getAdapterPosition()).getExercise().getName();

            // backup of removed item for undo purpose
            final TrainingExercise deletedItem = training.getExcersises().get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            int pos = viewHolder.getAdapterPosition();

            mAdapter.removeItem(pos);

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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
