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
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by User on 4/15/2017.
 */

public class FavouriteActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor favoritesCursor;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        // call the method to start the database  and add events to it
        setupFavoritesListView();

        // bottom bar Navigation functionality going to Activates if u click on different icons

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        Intent intent0 = new Intent(FavouriteActivity.this, MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_events:
                        Intent intent1 = new Intent(FavouriteActivity.this, EventActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_fav:
                        break;

                    case R.id.ic_attraction:
                        Intent intent3 = new Intent(FavouriteActivity.this, AttracrtionActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.map:
                        Intent intent4 = new Intent(FavouriteActivity.this, MapsMarkerActivity.class);
                        startActivity(intent4);
                        break;
                }

                return false;
            }
        });
    }


    @Override
    public void onContentChanged() {
        super.onContentChanged();

        View emptypic = findViewById(R.id.emptypic);
        ListView list = (ListView) findViewById(R.id.list_favorites);

        list.setEmptyView(emptypic);

        emptypic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(FavouriteActivity.this, EventActivity.class);
                startActivity(intent1);
            }
        });
    }


    private void setupFavoritesListView() {

        //Populate the list_favorites ListView from a cursor
        ListView listFavorites = (ListView) findViewById(R.id.list_favorites);
        try{
            SQLiteOpenHelper starbuzzDatabaseHelper = new MyDatabaseHelper(this);
            db = starbuzzDatabaseHelper.getReadableDatabase();

            favoritesCursor = db.query("Event ",
                    new String[] { "_id", "NAME","DESCRIPTION", "IMAGE_RESOURCE_ID", "DATE"},
                    "FAVORITE = 1",
                    null, null, null, null);
            CursorAdapter favoriteAdapter =
                    new SimpleCursorAdapter(FavouriteActivity.this,
                            //set my own design for the evlist
                            R.layout.list2,
                            favoritesCursor,
                            new String[]{"NAME","DESCRIPTION", "IMAGE_RESOURCE_ID", "DATE"},
                            new int[]{R.id.title, R.id.information ,R.id.picture ,R.id.dateinfo2 }, 0);

            listFavorites.setAdapter(favoriteAdapter);
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        //Navigate to EventInfoActivity if a drink is clicked
        listFavorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View v, int position, long id) {
                Intent intent = new Intent(FavouriteActivity.this, EventInfoActivity.class);
                intent.putExtra(EventInfoActivity.EXTRA_EventID, (int) id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Cursor newCursor = db.query("Event",
                new String[] { "_id", "NAME","DESCRIPTION", "IMAGE_RESOURCE_ID", "DATE"}, "FAVORITE = 1",
                null, null, null, null);
        ListView listFavorites = (ListView) findViewById(R.id.list_favorites);
        CursorAdapter adapter = (CursorAdapter) listFavorites.getAdapter();
        adapter.changeCursor(newCursor);
        favoritesCursor = newCursor;
    }

    //Close the cursor and database in the onDestroy() method
    @Override
    public void onDestroy(){
        super.onDestroy();
        favoritesCursor.close();
        db.close();
    }
}