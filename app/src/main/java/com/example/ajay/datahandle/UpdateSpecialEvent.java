package com.example.ajay.datahandle;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;


public class UpdateSpecialEvent extends ActionBarActivity {

    private AutoCompleteTextView autocompletetextviewSelectEvent;
    private EditText dayOf;
    private EditText dayOn;
    private String[] arr = { "Birthday", "Anniversary", "Examination","ResultDate","Festivals","FamilyEvents","Personal","Other" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_special_event);
        getSupportActionBar().hide();
        autocompletetextviewSelectEvent = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextViewSelectEventUpdate);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, arr);
        autocompletetextviewSelectEvent.setThreshold(1);
        autocompletetextviewSelectEvent.setAdapter(adapter);
        dayOf = (EditText) findViewById(R.id.updateSpecialDayOf);
        dayOn = (EditText) findViewById(R.id.updateSpecialDayOn);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_special_event, menu);
        return true;
    }

    //function created to update particular event in the database
    public void onUpdateEventClick(View view)
    {
        DBclass db = new DBclass(this);
        SpecialEvent se = new SpecialEvent();
        String eventType = autocompletetextviewSelectEvent.getText().toString();
        String eventOf = dayOf.getText().toString();
        String eventOn = dayOn.getText().toString();
        //checking emptiness of the feilds
        if((eventType.equalsIgnoreCase(""))||(eventOn.equalsIgnoreCase(""))||(eventOf.equalsIgnoreCase("")))
        {
            Toast.makeText(UpdateSpecialEvent.this, "" + "Empty fields not allowed .... ", Toast.LENGTH_LONG).show();
        }
        else
        {
            se.setEventType(eventType);
            se.setEventOf(eventOf);
            se.setEventOn(eventOn);
            //adding to database
            int num = db.updateEventOn(se);
            if(num>0) {
                Toast.makeText(UpdateSpecialEvent.this, "" + "Event updated successfully .... ", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(UpdateSpecialEvent.this, "" + "no such event present .... ", Toast.LENGTH_LONG).show();
            }

            dayOn.setText("");
            dayOf.setText("");
            int DELAY = 2000;
//function to return to first page after completion of work
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(UpdateSpecialEvent.this, DataActivity1.class);
                    startActivity(intent);
                }
            }, DELAY);
        }
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
