package br.klosowski.eduardo.arduino.activities.generics;

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
import br.klosowski.eduardo.arduino.models.ItemDAO;

public abstract class GenericFormActivity<I extends Item> extends AppCompatActivity {
    protected long id;
    private ItemDAO<I> itemDAO;

    protected EditText editName;

    abstract protected int getLayout();

    abstract protected int getActivityTitle();

    abstract protected ItemDAO<I> getItemDAO();

    abstract protected void populateElementsFromLayout();

    abstract protected void populateFields(I item);

    abstract protected I getItem();

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

        itemDAO = getItemDAO();

        Button buttonSave = (Button) findViewById(R.id.save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemDAO.save(getItem());
                setResult(RESULT_OK);
                finish();
            }
        });

        populateElementsFromLayout();

        id = getIntent().getLongExtra("id", Item.NEW_ID);
        if (id != Item.NEW_ID) {
            populateFields(itemDAO.get(id));
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
