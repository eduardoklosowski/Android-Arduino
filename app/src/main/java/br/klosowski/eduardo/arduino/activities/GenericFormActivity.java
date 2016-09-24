package br.klosowski.eduardo.arduino.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.klosowski.eduardo.arduino.R;
import br.klosowski.eduardo.arduino.models.Item;

public abstract class GenericFormActivity extends AppCompatActivity {
    protected EditText editName;

    protected long id;

    abstract protected int getLayout();

    abstract protected int getActivityTitle();

    abstract void getElementsFromLayout();

    abstract protected void save();

    abstract void loadItem(long id);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getActivityTitle());
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Button buttonSave = (Button) findViewById(R.id.save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                setResult(RESULT_OK);
                finish();
            }
        });

        getElementsFromLayout();

        long id = getIntent().getLongExtra("id", Item.NEW_ID);
        if (id != Item.NEW_ID) {
            loadItem(id);
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