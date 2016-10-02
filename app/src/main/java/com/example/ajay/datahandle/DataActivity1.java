package com.example.ajay.datahandle;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class DataActivity1 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_activity1);
        getSupportActionBar().hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_data_activity1, menu);
        return true;
    }

    /*
    * function made to move to add events activity page
    * */
    public void onAddEvent(View view)
    {
        Intent intent = new Intent(DataActivity1.this,AddSpecialEvent.class);
        startActivity(intent);
    }


    /*
    * function made to move to update events activity page
    * */
    public void onUpdateEvent(View view)
    {
        Intent intent = new Intent(DataActivity1.this,UpdateSpecialEvent.class);
        startActivity(intent);
    }


    /*
    * function made to move to remove events activity page
    * */
    public void onRemoveEvent(View view)
    {
        Intent intent = new Intent(DataActivity1.this,RemoveSpecialEvent.class);
        startActivity(intent);
    }


    /*
    * function made to move to show all events activity page
    * */
    public void onShowAllEvent(View view)
    {
        Intent intent = new Intent(DataActivity1.this,ShowAllSpecialDates.class);
        startActivity(intent);
    }


    /*
    * function made to move to add to-do activity page
    * */
    public void onToDoList(View view)
    {
        Intent intent = new Intent(DataActivity1.this,AddTODOList.class);
        startActivity(intent);
    }

    /*
    * function made to move to read me activity page
    * */
    public void onReadMeClick(View view)
    {
        Intent intent = new Intent(DataActivity1.this,ReadMeActivity.class);
        startActivity(intent);

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
