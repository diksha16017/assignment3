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


public class RemoveSpecialEvent extends ActionBarActivity {

    private AutoCompleteTextView autocompletetextviewSelectEvent;
    private EditText dayOf;
    private EditText dayOn;
    private String[] arr = { "Birthday", "Anniversary", "Examination","ResultDate","Festivals","FamilyEvents","Personal","Other" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_remove_special_event);
            getSupportActionBar().hide();
            autocompletetextviewSelectEvent = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextViewSelectEventRemove);
            ArrayAdapter adapter = new ArrayAdapter (this,android.R.layout.select_dialog_item, arr);
            autocompletetextviewSelectEvent.setThreshold(1);
            autocompletetextviewSelectEvent.setAdapter(adapter);
            dayOf = (EditText) findViewById(R.id.removeSpecialDayOf);
            dayOn = (EditText) findViewById(R.id.removeSpecialDayOn);
        }
        catch (Exception e)
        {

        }

    }

    public void onRemoveEventClick(View view)
    {
        SpecialEvent se = new SpecialEvent();
        DBclass db = new DBclass(this);

        //checking emptiness of the feilds
        String eventType = autocompletetextviewSelectEvent.getText().toString();
        String eventOf = dayOf.getText().toString();
        String eventOn = dayOn.getText().toString();
        if((eventType.equalsIgnoreCase(""))||(eventOn.equalsIgnoreCase(""))||(eventOf.equalsIgnoreCase("")))
        {
            Toast.makeText(RemoveSpecialEvent.this, "" + "Empty fields not allowed .... ", Toast.LENGTH_LONG).show();
        }
        else
        {
            //calling database function to remove particular event from the database
            se.setEventType(eventType);
            se.setEventOf(eventOf);
            se.setEventOn(eventOn);
            int num = db.deleteRow(se);

            if(num>0) {
                Toast.makeText(RemoveSpecialEvent.this, "" + "Event deleted successfully .... ", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(RemoveSpecialEvent.this, "" + "no such event present .... ", Toast.LENGTH_LONG).show();
            }

            dayOf.setText("");
            dayOn.setText("");
            int DELAY = 2000;
            // function made to get to first page after completion of activity
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(RemoveSpecialEvent.this, DataActivity1.class);
                    startActivity(intent);
                }
            }, DELAY);


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_remove_special_event, menu);
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
