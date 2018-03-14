package com.example.test;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EventInfoActivity extends AppCompatActivity {
    public static final String EXTRA_EventID = "eventId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_page);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Get the drink from the intent
        int eventid = (Integer)getIntent().getExtras().get(EXTRA_EventID);

        //Create a cursor
        SQLiteOpenHelper starbuzzDatabaseHelper = new MyDatabaseHelper(this);
        try {
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query ("Event",
                    //              0
                    /// positions in string     1                  2             3          4          5        6        7
                    new String[] {"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE", "DATE" ,"LOCATION", "ADPRICE","CHPRICE", "INFORMATION"},
                    "_id = ?",
                    new String[] {Integer.toString(eventid)},
                    null, null,null);
            //Move to the first record in the Cursor
            if (cursor.moveToFirst()) {
                //Get the drink details from the cursor
                String nameText = cursor.getString(0);

                String descriptionText = cursor.getString(1);

                int photoId = cursor.getInt(2);

                boolean isFavorite = (cursor.getInt(3) == 1);

                String dateText = cursor.getString(4);

                String Info = cursor.getString(8);

                String priceText = cursor.getString(6);

                String Childprice = cursor.getString(7);



                //Populate the drink name
                TextView name = (TextView)findViewById(R.id.name);
                name.setText(nameText);

                //Populate the drink description
                TextView description = (TextView)findViewById(R.id.description);
                description.setText(descriptionText);

                //Populate the drink image
                ImageView photo = (ImageView)findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);

                //Populate the fav checkbox
                CheckBox favorite = (CheckBox)findViewById(R.id.favorite);
                favorite.setChecked(isFavorite);



                //Populate the date
                TextView date  = (TextView)findViewById(R.id.date);
                date.setText(dateText);

                //Populate the info
                TextView info  = (TextView)findViewById(R.id.information);
                info.setText(Info);

                //Populate the  aduld price
                TextView price  = (TextView)findViewById(R.id.price_ev);
                price.setText(priceText);


               //Populate the Child price
                TextView information  = (TextView)findViewById(R.id.chprice);
                information.setText(Childprice);

            }
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //Update the database when the checkbox is clicked
    public void onFavoriteClicked(View view){
        int drinkId = (Integer) getIntent().getExtras().get(EXTRA_EventID);

        CharSequence text = "Event added to your Favourites";
        int duration = Snackbar.LENGTH_LONG;
        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator), text, duration);
        snackbar.setAction("View",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       startActivity(new Intent(EventInfoActivity.this, FavouriteActivity.class));
                    }
                });
        snackbar.show();

        new UpdateEventkTask().execute(drinkId);
    }




    public void calenderEvent(View view) {

        int drinkId = (Integer)getIntent().getExtras().get(EXTRA_EventID);

        SQLiteOpenHelper starbuzzDatabaseHelper = new MyDatabaseHelper(this);
        try {
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query ("event",
                    /// positions in string     1
                    new String[] {"NAME", "DESCRIPTION",  "DATE" ,"LOCATION" , "PRODATE"},
                    "_id = ?",
                    new String[] {Integer.toString(drinkId)},
                    null, null,null);

            //Move to the first record in the Cursor
            if (cursor.moveToFirst()) {
                //Get the drink details from the cursor
                String nameText = cursor.getString(0);

                String descriptionText = cursor.getString(1);

                String locationText = cursor.getString(3);

                long startTime = 0,endTime;

                String startDate = cursor.getString(4);


                try {
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
                    startTime=date.getTime();
                }
                catch(Exception e){ }



                Calendar calendarEvent = Calendar.getInstance();
                Intent i = new Intent(Intent.ACTION_EDIT);
                i.setType("vnd.android.cursor.item/event");
                i.putExtra("beginTime", startTime);
                i.putExtra("allDay", true);
                i.putExtra("rule", "FREQ=YEARLY");
                i.putExtra("endTime", calendarEvent.getTimeInMillis() + 60 * 60 * 1000);
                i.putExtra("title",nameText);
                i.putExtra("description", descriptionText);
                i.putExtra("eventLocation", locationText);

                startActivity(i);

            }
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    //Inner class to update the drink.
    private class UpdateEventkTask extends AsyncTask<Integer, Void, Boolean> {
        private ContentValues eventValues;

        protected void onPreExecute() {
            CheckBox favorite = (CheckBox) findViewById(R.id.favorite);
            eventValues = new ContentValues();
            eventValues.put("FAVORITE", favorite.isChecked());


        }

        protected Boolean doInBackground(Integer... event) {
            int eventid = event[0];
            SQLiteOpenHelper starbuzzDatabaseHelper = new MyDatabaseHelper(EventInfoActivity.this);
            try {
                SQLiteDatabase db = starbuzzDatabaseHelper.getWritableDatabase();
                db.update("Event", eventValues,
                        "_id = ?", new String[]{Integer.toString(eventid)});
                db.close();
                return true;

            } catch (SQLiteException e) {
                return false;
            }
        }

        protected void onPostExecute(Boolean success) {
            if (!success) {
                Toast toast = Toast.makeText(EventInfoActivity.this,
                        "Database unavailable", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
