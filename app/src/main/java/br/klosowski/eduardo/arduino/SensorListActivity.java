package br.klosowski.eduardo.arduino;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class SensorListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.sensors);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new SensorItemRecyclerAdapter(this, getSensorList()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.add:
                Intent intent = new Intent(SensorListActivity.this, SensorFormActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<SensorItem> getSensorList() {
        List<SensorItem> list = new ArrayList<>();

        ArduinoItem a = new ArduinoItem();
        a.setName("Arduino");

        SensorItem s = new SensorItem();
        s.setId(1);
        s.setName("Sensor 1");
        s.setArduino(a);
        s.setType(ArduinoType.Digital);
        s.setPort(0);
        list.add(s);

        s = new SensorItem();
        s.setId(2);
        s.setName("Sensor 2");
        s.setArduino(a);
        s.setType(ArduinoType.Digital);
        s.setPort(0);
        list.add(s);

        return list;
    }
}
