package com.kosalgeek.android.androidrecyclerviewtutorial1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        rvItem = (RecyclerView)findViewById(R.id.rvItem);
        rvItem.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);

        rvItem.setLayoutManager(manager);

        ArrayList<Item> itemList = generatedDummy();

        ItemAdapter adapter = new ItemAdapter(getApplicationContext(), itemList);

        rvItem.setAdapter(adapter);


    }

    private ArrayList<Item> generatedDummy(){
        ArrayList<Item> list = new ArrayList<>();
        for(int i = 0; i < 50; i++){
            if(i % 3 == 0){
                Item item = new Item();
                item.id = i;
                item.text = "Angkor Wat " + i;
                item.img = "https://lonelyplanetwp.imgix.net/2016/01/angkor-wat-with-water.jpg";
                list.add(item);
            }
            else if(i % 7 == 0){
                Item item = new Item();
                item.id = i;
                item.text = "Bayon " + i;
                item.img = "http://www.livingif.com/wp-content/gallery/bayon/bayon-cambodia-11.jpg";
                list.add(item);
            }
            else if(i % 11 == 0){
                Item item = new Item();
                item.id = i;
                item.text = "Something text " + i;
                item.img = "http://www.aangkortourguide.com/images/cambodia_bayon.jpg";
                list.add(item);
            }
            else{
                Item item = new Item();
                item.id = i;
                item.text = "Dummy text " + i;
                item.img = "http://www.worldwidetoursagency.com/wp-content/uploads/2016/01/phnom-penh-tour_sinhcafe-travel.jpg";
                list.add(item);
            }


        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
