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
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by User on 4/15/2017.
 */

public class AttracrtionActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);

        SQLiteOpenHelper starbuzzDatabaseHelper = new MyDatabaseHelper(this);
        ListView listDrinks = (ListView) findViewById(R.id.list_drinks);



        try {

            db = starbuzzDatabaseHelper.getReadableDatabase();
            cursor = db.query("PLACE Order BY RANDOM()",
                    new String[]{"_id","NAME","DESCRIPTION", "IMAGE_RESOURCE_ID", "TUBE"},
                    null, null, null, null, null);
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    //set design for my own evlist
                    R.layout.loclist,
                    cursor,
                    new String[]{"NAME","DESCRIPTION","IMAGE_RESOURCE_ID","TUBE"},
                    new int[]{R.id.item ,R.id.textView1, R.id.icon,R.id.tubeinfo},
                    0);



            listDrinks.setAdapter(listAdapter);
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        //Create the listener
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> listDrinks,
                                            View itemView,
                                            int position,
                                            long id) {
                        //Pass the drink the user clicks on to EventInfoActivity
                        Intent intent = new Intent(AttracrtionActivity.this,
                                AttInfoActivity.class);
                        intent.putExtra(AttInfoActivity.EXTRA_LOCID, (int) id);
                        startActivity(intent);
                    }
                };

        //Assign the listener to the evlist view
        listDrinks.setOnItemClickListener(itemClickListener);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        Intent intent0 = new Intent(AttracrtionActivity.this, MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_events:
                        Intent intent1 = new Intent(AttracrtionActivity.this, EventActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_fav:
                        Intent intent2 = new Intent(AttracrtionActivity.this, FavouriteActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_attraction:

                        break;


                    case R.id.map:
                        Intent intent4 = new Intent(AttracrtionActivity.this, MapsMarkerActivity.class);
                        startActivity(intent4);
                        break;

                }


                return false;
            }
        });



    }




    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }

}
