package com.example.test;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AttInfoActivity extends AppCompatActivity {
    public static final String EXTRA_LOCID = "locId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.att_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Get the event from the intent
        int eventId = (Integer)getIntent().getExtras().get(EXTRA_LOCID);

        //Create a cursor
        SQLiteOpenHelper starbuzzDatabaseHelper = new MyDatabaseHelper(this);
        try {
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query ("PLACE",
                    //              0
                    /// positions in string     1                  2             3          4          5        6           7         8
                    new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID" , "TUBE" ,"LOCATION", "adPRICE" ,"INFORMATION","OPENTIME","SCEIMAGE","WEB","chPRICE" , },
                    "_id = ?",
                    new String[] {Integer.toString(eventId)},
                    null, null,null);
            //Move to the first record in the Cursor
            if (cursor.moveToFirst()) {
                //Get the event details from the cursor
                String nameText = cursor.getString(0);

                String descriptionText = cursor.getString(1);

                int photoId = cursor.getInt(2);

                String dateText = cursor.getString(3);

                String locationText = cursor.getString(4);

                String priceText = cursor.getString(5);

                String openTimeText = cursor.getString(7);

                int scepic = cursor.getInt(8);

                final String web = cursor.getString(9);

                String chpriceText = cursor.getString(10);

                //Populate the event name
                TextView name = (TextView)findViewById(R.id.name);
                name.setText(nameText);

                //Populate the event description
                final TextView description = (TextView)findViewById(R.id.info);
                description.setText(descriptionText);


                final TextView  btShowmore=(TextView) findViewById(R.id.btShowmore);

                btShowmore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (btShowmore.getText().toString().equalsIgnoreCase("More..."))
                        {
                            description.setMaxLines(Integer.MAX_VALUE);//your TextView
                            btShowmore.setText("Showless");
                        }
                        else
                        {
                            description.setMaxLines(7);//your TextView
                            btShowmore.setText("More...");
                        }
                    }
                });


                //Populate the drink image
                ImageView photo = (ImageView)findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                Button web_info = (Button)findViewById(R.id.book_info);
                web_info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goToUrl(web);
                    }
                });

                //Populate the date
                TextView opentime  = (TextView)findViewById(R.id.opentimes);
                opentime.setText(openTimeText);

                //Populate the date
                TextView date  = (TextView)findViewById(R.id.tube);
                date.setText(dateText);

                //Populate the location
                TextView location  = (TextView)findViewById(R.id.location);
                location.setText(locationText);

                //Populate the price
                TextView adprice  = (TextView)findViewById(R.id.price);
                adprice.setText(priceText);

                //Populate the price
                TextView chprice  = (TextView)findViewById(R.id.chprice);
                chprice.setText(chpriceText);

                //Populate the drink image
                ImageView sen = (ImageView)findViewById(R.id.image2);
                sen.setImageResource(scepic);


            }
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }




    public void goToUrl(String url){
        Uri uri = Uri.parse(url);
        Intent i = new Intent(Intent.ACTION_VIEW, uri);
        Intent chooser = Intent.createChooser(i, "Choose Application ...");
        startActivity(chooser);
    }



}
