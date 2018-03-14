package com.example.test;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

 //  class name and used to extend for ActionBar with the library
public class EventActivity extends AppCompatActivity {

    // Creating private Global Variable for the Database

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

     // create object from the sqllite  and name it it database helper
        SQLiteOpenHelper DatabaseHelper = new MyDatabaseHelper( this);

        ListView listEvent = (ListView) findViewById(R.id.list_evnets);

        try {

            // make data base connection and read information from table event
            db = DatabaseHelper.getReadableDatabase();
            cursor = db.query(" Event",
                    new String[]{"_id","NAME","DESCRIPTION", "IMAGE_RESOURCE_ID", "DATE"},
                    null, null, null, null, null);
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,

                    //my custom listview design design called evlist
                    R.layout.evlist,
                    cursor,
                    new String[]{"NAME","DESCRIPTION","IMAGE_RESOURCE_ID","DATE"},
                    new int[]{R.id.item ,R.id.textView1, R.id.icon,R.id.dateinfo},
                    0);

            // put items found in the retvied from database into list view Adapter
            listEvent.setAdapter(listAdapter);

        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        //Create the listener if item is clicked in list view to take the user to Event info Activity and with an intent

        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> listEvent,
                                            View itemView,
                                            int position,
                                            long id) {
                        //Pass the drink the user clicks on to EventInfoActivity
                        Intent intent = new Intent(EventActivity.this,
                                EventInfoActivity.class);

//                        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(EventActivity.this,findViewById(R.id.icon),"myImage");


                        intent.putExtra(EventInfoActivity.EXTRA_EventID, (int) id);

                        startActivity(intent);
                    //    startActivity(intent,optionsCompat.toBundle());
                    }
                };

        //Assign the listener to the evlist view

        listEvent.setOnItemClickListener(itemClickListener);

        // The bottom bar navigation with its functions

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        Intent intent0 = new Intent(EventActivity.this, MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_events:
                        break;

                    case R.id.ic_fav:
                        Intent intent2 = new Intent(EventActivity.this, FavouriteActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_attraction:
                        Intent intent3 = new Intent(EventActivity.this, AttracrtionActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.map:
                        Intent intent4 = new Intent(EventActivity.this, MapsMarkerActivity.class);
                        startActivity(intent4);
                        break;
                }

                return false;
            }
        });
    }

    // On destroy of the Activity close the cuser and the databse connection

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
