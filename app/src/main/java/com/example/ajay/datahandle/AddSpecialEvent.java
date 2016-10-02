package com.example.ajay.datahandle;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;


public class AddSpecialEvent extends ActionBarActivity {

    private AutoCompleteTextView autocompletetextviewSelectEvent;
    private EditText dayOf;
    private EditText dayOn;
    private String[] arr = { "Birthday", "Anniversary", "Examination","ResultDate","Festivals","FamilyEvents","Personal","Other" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_add_special_event);
            getSupportActionBar().hide();
            autocompletetextviewSelectEvent = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextViewSelectEventAdd);
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, arr);
            autocompletetextviewSelectEvent.setThreshold(1);
            autocompletetextviewSelectEvent.setAdapter(adapter);
            dayOf = (EditText) findViewById(R.id.addSpecialDayOf);
            dayOn = (EditText) findViewById(R.id.addSpecialDayOn);
        }
        catch (Exception e)
        {

        }
    }

    /*
    * function made to get details of event which user wants to save in database
    * */
    public void onAddEventClick(View view)
    {
        SpecialEvent se = new SpecialEvent();
        DBclass db = new DBclass(this);
        String eventType = autocompletetextviewSelectEvent.getText().toString();
        String eventOf = dayOf.getText().toString();
        String eventOn = dayOn.getText().toString();
        //checking emptiness of fields..
        if((eventType.equalsIgnoreCase(""))||(eventOn.equalsIgnoreCase(""))||(eventOf.equalsIgnoreCase("")))
        {
            Toast.makeText(AddSpecialEvent.this, "" + "Empty fields not allowed .... ", Toast.LENGTH_LONG).show();
        }
        else
        {
            se.setEventType(eventType);
            se.setEventOf(eventOf);
            se.setEventOn(eventOn);
            //adding to database
            try {
                long num = db.addEvent(se);
                if (num > 0) {

                    Toast.makeText(AddSpecialEvent.this, "" + "Event added successfully .... ", Toast.LENGTH_LONG).show();

                }else
                {
                    Toast.makeText(AddSpecialEvent.this, "" + "Event user already existed .... ", Toast.LENGTH_LONG).show();
                }

                //new


            }
            catch(Exception e)
            {

            }
            finally {

                    dayOf.setText("");
                    dayOn.setText("");

                //code for returning to main activity directly
                int DELAY = 2000;

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(AddSpecialEvent.this, DataActivity1.class);
                        startActivity(intent);
                    }
                }, DELAY);

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_special_event, menu);
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
