package com.example.ajay.datahandle;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;


public class ReadMeActivity extends ActionBarActivity {

    private TextView readme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_me);
        getSupportActionBar().hide();
        readme = (TextView)findViewById(R.id.readmetextview);
        //displaying data of internal file created
        getinternaldata();

        //function made to return to main page after completion of work
        int DELAY = 4000;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ReadMeActivity.this, DataActivity1.class);
                startActivity(intent);
            }
        }, DELAY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_read_me, menu);
        return true;



    }

    void getinternaldata()
    {
        String readMeData="";
        try
        {

            //reading from the internal file created at the beginning
            String filename = "my_internal_file";
            FileInputStream inputStream;
            inputStream = openFileInput(filename);
            DataInputStream in = new DataInputStream(inputStream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                readMeData = readMeData + strLine;
            }
            in.close();

            //set the fetched text to textbox
            readme.setText(readMeData);
        }
        catch (Exception e)
        {

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
