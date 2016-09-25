package br.klosowski.eduardo.arduino.activities.generics;

import android.content.Context;
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

import java.util.List;

import br.klosowski.eduardo.arduino.R;
import br.klosowski.eduardo.arduino.models.Item;
import br.klosowski.eduardo.arduino.models.ItemDAO;

public abstract class GenericListActivity<I extends Item> extends AppCompatActivity {
    private static final int UPDATE_LIST = 1;

    private ItemDAO<I> itemDAO;
    private GenericItemRecyclerAdapter<I> adapter;

    public GenericListActivity() {
        itemDAO = getItemDAO();
    }

    abstract protected int getActivityTitle();

    abstract protected Class getFormActivityClass();

    abstract protected GenericItemRecyclerAdapter<I> getRecyclerAdapter(Context context,
                                                                        List<I> items);

    abstract protected ItemDAO<I> getItemDAO();

    private void updateList() {
        adapter.setList(itemDAO.getAll());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getActivityTitle());
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        adapter = getRecyclerAdapter(this, itemDAO.getAll());
        adapter.setOnItemEdited(new GenericItemRecyclerAdapter.OnItemEditedListener() {
            @Override
            public void onItemEdited(Item item) {
                Intent intent = new Intent(GenericListActivity.this, getFormActivityClass());
                intent.putExtra("id", item.getId());
                startActivityForResult(intent, UPDATE_LIST);
            }
        });
        adapter.setOnItemDeleted(new GenericItemRecyclerAdapter.OnItemDeletedListener() {
            @Override
            public void onItemDeleted(Item item) {
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
                Intent intent = new Intent(GenericListActivity.this, getFormActivityClass());
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
}
