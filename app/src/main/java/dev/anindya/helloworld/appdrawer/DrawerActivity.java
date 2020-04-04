package dev.anindya.helloworld.appdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dev.anindya.helloworld.R;

public class DrawerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        final RecyclerView recyclerView = findViewById(R.id.icon_list);
        final RecyclerView.Adapter viewAdapter = new IconViewAdapter(this);
        recyclerView.setAdapter(viewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {
            Intent intent = new Intent();
            intent.setClass(this, DrawerActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
