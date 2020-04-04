package dev.anindya.helloworld.appdrawer;

import android.os.Bundle;

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
}
