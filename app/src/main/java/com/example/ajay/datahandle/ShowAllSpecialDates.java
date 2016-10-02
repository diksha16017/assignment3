package com.example.ajay.datahandle;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ShowAllSpecialDates extends ActionBarActivity {

    private LinearLayout ll;
    private EditText edi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_special_dates);
        getSupportActionBar().hide();
        ll = (LinearLayout)findViewById(R.id.showAllEventsLayout1);
        //edi = (EditText)findViewById(R.id.testt);
        showdata();
        //function made to return to first page after completion of work

        int DELAY = 4000;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ShowAllSpecialDates.this, DataActivity1.class);
                startActivity(intent);
            }
        }, DELAY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_all_special_dates, menu);
        return true;
    }

    public void showdata()
    {
        DBclass db = new DBclass(this);
        try {
            db.open();
            //getting raw query for displaying all rows of database
            Cursor cur = db.getAllTitles();

            //displaying all rows to user by adding each row to textview and then adding that textview to linear layout
            while (cur.moveToNext()) {
                String valueofcol1 = cur.getString(1);
                String valueofcol2 = cur.getString(2);
                String valueofcol3 = cur.getString(3);
//
                Log.e("---****---", "***********   col 1 = " + valueofcol1);
//
                Log.e("---****---", "***********   col 2 = " + valueofcol2);

                String full = valueofcol1 + " "+valueofcol2 + " " + valueofcol3;
                ll.setOrientation(LinearLayout.VERTICAL);
                //LinearLayout layout=new LinearLayout(getBaseContext());
                //layout.setLayoutParams(new   LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                final TextView tv1=new TextView(getBaseContext());

                tv1.setVisibility(View.VISIBLE);
                tv1.setTextSize(16);
                tv1.setTextColor(Color.WHITE);

                //adding dynamically created textview to linear layout
                tv1.setText("\n\n"+full+"\n-------------------------------");
                ll.addView(tv1);



            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
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
