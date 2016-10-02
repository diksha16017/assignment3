package com.example.ajay.datahandle;

import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


public class AddTODOList extends ActionBarActivity {

    private EditText todolistadd;
    private EditText todolistTitleadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todolist);
        getSupportActionBar().hide();
        todolistadd = (EditText)findViewById(R.id.ToDoEditText);
        todolistTitleadd = (EditText)findViewById(R.id.ToDoTitleEditText);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_todolist, menu);
        return true;
    }

    /*
    * function made to get to-do list of user that will be stored as a file on external location
    * file will be saved on a newly created folder by app itself
    * user can view all to-do list as these will be available in text format
    * */
    public void onAddToDoClick(View view)
    {
        try {
            String filename = todolistTitleadd.getText().toString();
            String mytodo = todolistadd.getText().toString();
            File sdCard = Environment.getExternalStorageDirectory();
            //getting path of newly created folder
            File directory = new File(sdCard.getAbsolutePath() + "/MyToDo");
            File fileee = new File(directory, filename + ".txt");
            FileOutputStream fOut = new FileOutputStream(fileee);
            OutputStreamWriter osw = new OutputStreamWriter(fOut);
            osw.write(mytodo);
            osw.flush();
            osw.close();
            //file is written
            Toast.makeText(AddTODOList.this, "TO-DO File created successfully!", Toast.LENGTH_SHORT).show();

            //new

        }
        catch(Exception e)
        {

        }
        finally {

            todolistadd.setText("");
            todolistTitleadd.setText("");
            int DELAY = 2000;
            //code for directly returning to main activity after completion of work
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(AddTODOList.this, DataActivity1.class);
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
