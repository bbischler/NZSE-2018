package de.h_da.fbi.hartle.nzse.android;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        RxView.clicks(fab)
                .map(ignored -> fab)

                // Disable button on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(view -> {
                    view.setEnabled(false);
                    view.setAlpha(0.3f);
                })

                // Show a snackbar
                .doOnNext(view ->
                        Snackbar.make(view, "Disabling button for 5 seconds...", Snackbar.LENGTH_LONG)
                                .setAction("Action", null)
                                .show())

                // Wait for 5 seconds on IO
                .observeOn(Schedulers.io())
                .delay(5, TimeUnit.SECONDS)

                // Enable button on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(view -> {
                    view.setAlpha(1.0f);
                    view.setEnabled(true);
                })
                .subscribe();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
