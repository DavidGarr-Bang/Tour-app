package com.example.test;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsMarkerActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private SQLiteDatabase db;
    private Cursor cursor;
    double lon = 51.506816;
    double lat = -0.301267;
    ListView listevents;

    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_maps);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        SQLiteOpenHelper myDatabaseHelper = new MyDatabaseHelper(this);
        listevents = (ListView) findViewById(R.id.list_map);


        //listevents.setRotation(-90);

        try {

            db = myDatabaseHelper.getReadableDatabase();
            cursor = db.query("PLACE Order BY RANDOM()",
                    new String[]{"_id","NAME","DESCRIPTION", "IMAGE_RESOURCE_ID", "TUBE"},
                    null, null, null, null, null);
            SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    //set design for my own attraction list
                    R.layout.loclist_map,
                    cursor,
                    new String[]{"NAME","DESCRIPTION","IMAGE_RESOURCE_ID","TUBE"},
                    new int[]{R.id.item ,R.id.textView1, R.id.icon,R.id.tubeinfo},
                    0);

            listevents.setAdapter(listAdapter);
          //  listevents.setRotation(90);

        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        //Create the listener for list view
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> listPlace,
                                            View itemView,
                                            int position,
                                            long id) {

                        //raw Query this is to get longitude of the attraction of the attraction dependeing on what the user on and put it inside the marker.
                        Cursor lancur = db.rawQuery("select lon from place where _id = " +id ,null, null);
                        lancur.moveToFirst();
                        lon = lancur.getDouble(0);

                        //raw Query this is to get latitude of the attraction of the attraction dependeing on what the user on and put it inside the marker.

                        Cursor latcur = db.rawQuery("select lan from place where _id = " +id ,null, null);
                        latcur.moveToFirst();
                        lat = latcur.getDouble(0);

                        //raw Query this is to get name of the attraction of the attraction dependeing on what the user on and put it inside the marker.

                        Cursor namecur = db.rawQuery("select name from place where _id = " +id ,null, null);
                        namecur.moveToFirst();
                        final String name = namecur.getString(0);

                        // call the map fragment and make new object then update the camera with new location and add new marker with name that the user has clicked on
                        mapFragment.getMapAsync(new OnMapReadyCallback() {
                                @Override
                                public void onMapReady(GoogleMap googleMap) {

                                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lon, lat), 18));

                                    googleMap.addMarker(new MarkerOptions()
                                            .position(new LatLng(lon,lat))
                                            .title(name)
                                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.new_mark))
                                    );
                                }
                            });
                        }

                };


        // call the click listener
        listevents.setOnItemClickListener(itemClickListener);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(4);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        Intent intent0 = new Intent(MapsMarkerActivity.this, MainActivity.class);
                        startActivity(intent0);
                        break;

                    case R.id.ic_events:
                        Intent intent1 = new Intent(MapsMarkerActivity.this, EventActivity.class);
                        startActivity(intent1);
                        break;

                    case R.id.ic_fav:
                        Intent intent2 = new Intent(MapsMarkerActivity.this, FavouriteActivity.class);
                        startActivity(intent2);
                        break;

                    case R.id.ic_attraction:
                        Intent intent3 = new Intent(MapsMarkerActivity.this, AttracrtionActivity.class);
                        startActivity(intent3);
                        break;

                    case R.id.map:
                        break;
                }
                return false;
            }});}


    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in London,
        // and move the map's camera to the same location.
        LatLng london = new LatLng(51.496246, -0.120236);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(london)      // Sets the center of the map to Mountain View
                .zoom(10)                   // Sets the zoom
                .build();                   // Creates a CameraPosition from the builder

        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void  byeList(View v){

        listevents.setVisibility(View.INVISIBLE);

        Button open = (Button)findViewById(R.id.close);

        open.setVisibility(View.INVISIBLE); //To set visible

        Button close = (Button)findViewById(R.id.open);

        close.setVisibility(View.VISIBLE); //To set visible

    }



    public void  hiList(View v){

        listevents.setVisibility(View.VISIBLE);

        Button resetButton = (Button)findViewById(R.id.open);

        resetButton.setVisibility(View.INVISIBLE); //To set visible

        Button open = (Button)findViewById(R.id.close);

        open.setVisibility(View.VISIBLE); //To set visible

    }

    }