package br.klosowski.eduardo.arduino;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ArduinoFormActivity extends AppCompatActivity {
    private EditText editName;
    private EditText editUrl;
    private Button buttonSave;

    private long id;
    private ArduinoItem item;
    private ArduinoItemDAO itemDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arduino_form);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.arduino);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        itemDAO = new ArduinoItemDAO(this);

        editName = (EditText) findViewById(R.id.name);
        editUrl = (EditText) findViewById(R.id.url);

        buttonSave = (Button) findViewById(R.id.save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArduinoItem arduino = new ArduinoItem();
                if (id != 0) {
                    arduino.setId(id);
                }
                arduino.setName(editName.getText().toString());
                arduino.setUrl(editUrl.getText().toString());
                itemDAO.save(arduino);
                setResult(RESULT_OK);
                finish();
            }
        });

        id = getIntent().getLongExtra("id", 0);
        if (id != 0) {
            item = itemDAO.get(id);
            editName.setText(item.getName());
            editUrl.setText(item.getUrl());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
