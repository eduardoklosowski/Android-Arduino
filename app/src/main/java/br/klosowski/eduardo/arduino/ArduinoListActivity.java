package br.klosowski.eduardo.arduino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.klosowski.eduardo.arduino.activities.ArduinoFormActivity;
import br.klosowski.eduardo.arduino.models.ArduinoItem;
import br.klosowski.eduardo.arduino.models.ArduinoItemDAO;

public class ArduinoListActivity extends AppCompatActivity {
    private static final int UPDATE_LIST = 1;

    private ArduinoItemDAO itemDAO;
    private ArduinoItemRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arduino_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.arduinos);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        itemDAO = new ArduinoItemDAO(this);
        adapter = new ArduinoItemRecyclerAdapter(this, itemDAO.getAll());
        adapter.setOnItemEdited(new ArduinoItemRecyclerAdapter.OnItemEditedListener() {
            @Override
            public void onItemEdited(ArduinoItem item) {
                Intent intent = new Intent(ArduinoListActivity.this, ArduinoFormActivity.class);
                intent.putExtra("id", item.getId());
                startActivityForResult(intent, UPDATE_LIST);
            }
        });
        adapter.setOnItemDeleted(new ArduinoItemRecyclerAdapter.OnItemDeletedListener() {
            @Override
            public void onItemDeleted(ArduinoItem item) {
                updateList();
            }
        });

        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
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
                Intent intent = new Intent(ArduinoListActivity.this, ArduinoFormActivity.class);
                startActivityForResult(intent, UPDATE_LIST);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == UPDATE_LIST && resultCode == RESULT_OK) {
            updateList();
        }
    }

    private void updateList() {
        adapter.clear();
        adapter.addAll(itemDAO.getAll());
        adapter.notifyDataSetChanged();
    }
}
