package br.klosowski.eduardo.arduino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import br.klosowski.eduardo.arduino.models.ArduinoItem;
import br.klosowski.eduardo.arduino.models.SensorItem;
import br.klosowski.eduardo.arduino.models.SensorType;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new MainItemRecyclerAdapter(this, getSensorList()));
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
                startActivity(intent);
                return true;
            case R.id.menu_sensor:
                intent = new Intent(MainActivity.this, SensorListActivity.class);
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
        s.setType(SensorType.Digital);
        s.setPort(0);
        list.add(s);

        s = new SensorItem();
        s.setId(2);
        s.setName("Sensor 2");
        s.setArduino(a);
        s.setType(SensorType.Digital);
        s.setPort(0);
        list.add(s);

        return list;
    }
}
