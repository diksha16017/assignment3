package com.example.ajay.datahandle;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.FileOutputStream;


public class MainActivity extends ActionBarActivity {

    private boolean mIsBackButtonPressed;
    private static final int SPLASH_DURATION = 3000; //
    private Handler myhandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File (sdCard.getAbsolutePath() +"/MyToDo");
        directory.mkdirs();
        my_internal_file();
        myhandler = new Handler();
        //used to start an activity after some duration of time
        // run a thread to start the home screen
        myhandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                finish();

                if (!mIsBackButtonPressed) {
                    // start the home activity
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    MainActivity.this.startActivity(intent);
                }

            }

        }, SPLASH_DURATION);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed()
    {
        mIsBackButtonPressed = true;
        super.onBackPressed();
    }

    /*
    * function made to write at internal location of app
    * show working of internal file part
    * */
    public void my_internal_file()
    {
        try
        {

            String filename = "my_internal_file";
            String mystring = "App will help you to keep a tap on special events of yours. You can store, update or delete them according to your wish. It will help you to save your to-do list in form of file. After an event app will automatically come to first page. All TO-DO files will be stored in an explicitly created folder at external memory for your easy reference..  ";
            FileOutputStream outputStream;
            //file written in private mode so that other apps cannot access it...
            //it will be applicable in current app only and will be deleted as soon as app is deleted
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(mystring.getBytes());
            outputStream.close();



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
