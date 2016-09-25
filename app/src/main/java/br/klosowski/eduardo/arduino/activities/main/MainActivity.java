package br.klosowski.eduardo.arduino.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.Timer;
import java.util.TimerTask;

import br.klosowski.eduardo.arduino.R;
import br.klosowski.eduardo.arduino.activities.arduino.ArduinoListActivity;
import br.klosowski.eduardo.arduino.activities.sensor.SensorListActivity;
import br.klosowski.eduardo.arduino.models.RunningSensor;
import br.klosowski.eduardo.arduino.models.RunningSensorDAO;

public class MainActivity extends AppCompatActivity {
    private static final int UPDATE_LIST = 1;

    private RunningSensorDAO rSensorDAO;
    private MainItemRecyclerAdapter adapter;
    private Timer autoUpdate;

    private void updateList() {
        adapter.setList(rSensorDAO.getAll());
        adapter.notifyDataSetChanged();
    }

    private void requestValues() {
        for (int i = adapter.getItemCount(); i-- > 0; ) {
            RunningSensor rSensor = adapter.getItem(i);
            rSensor.requestValue();
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        rSensorDAO = new RunningSensorDAO(this);

        adapter = new MainItemRecyclerAdapter(this, rSensorDAO.getAll());

        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        autoUpdate = new Timer();
        autoUpdate.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        requestValues();
                    }
                });
            }
        }, 0, 10000);
    }

    @Override
    protected void onPause() {
        autoUpdate.cancel();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_arduino:
                intent = new Intent(MainActivity.this, ArduinoListActivity.class);
                startActivityForResult(intent, UPDATE_LIST);
                return true;
            case R.id.menu_sensor:
                intent = new Intent(MainActivity.this, SensorListActivity.class);
                startActivityForResult(intent, UPDATE_LIST);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == UPDATE_LIST) {
            updateList();
        }
    }
}
