package com.example.ajay.datahandle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {

    private EditText username;
    private EditText password;
    SharedPreferences sp;
    String mypref="mypref";
    String Name="namekey";
    String Password="passkey";
    private static final String user_name= "diksha";
    private static final String pass_word = "diksha";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        username = (EditText)findViewById(R.id.usernameEditText);
        password = (EditText)findViewById(R.id.passwordEditText);
        //showing the use of shared preferances.
        //if already there then it will be showing users already using username and password
        sp=getSharedPreferences(mypref, Context.MODE_PRIVATE);
        if(sp.contains(Name)){
            username.setText(sp.getString(Name, ""));
        }
        if(sp.contains(Password)){
            password.setText(sp.getString(Password, ""));
        }

    }

    /*
    * function for login if correct credentials then move else ask for new credentials
    * */
    public void onSubmitClick(View view)
    {
        String user = username.getText().toString();
        String pass = password.getText().toString();
        SharedPreferences.Editor editor= sp.edit();
        editor.putString(Name, user);
        editor.putString(Password, pass);
        editor.commit();
        if((user.equalsIgnoreCase(user_name)) && (pass.equalsIgnoreCase(pass_word)))
        {
            Intent intent = new Intent(LoginActivity.this,DataActivity1.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(LoginActivity.this, "" + "Incorrect username or password .... ", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
