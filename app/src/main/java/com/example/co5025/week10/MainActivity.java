package com.example.co5025.week10;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Variables
    EditText editUsername;
    EditText editPassword;
    Button butLogin;
    TextView errorMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //MediaPlayer for background music I guess since its within onCreate
        MediaPlayer player;
        AssetFileDescriptor afd;
        try {
            afd = getAssets().openFd("anuda-day.mp3");
            player = new MediaPlayer();
            player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            player.setLooping(false);
            player.prepare();
            player.start();
        }catch (IOException e){
            e.printStackTrace();
        }

        //onCreate Variables
        butLogin = (Button) findViewById(R.id.but_login);
        editUsername = (EditText) findViewById(R.id.edit_username);
        editPassword = (EditText) findViewById(R.id.edit_password);
        butLogin.setOnClickListener(this);
        editUsername.setOnClickListener(this);
        errorMessage = (TextView) findViewById(R.id.error_message);
        if (getIntent().getBooleanExtra("EXIT", false)) finish();
        //end of onCreate Variables
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onClick(View view) {
        Intent i;
        switch (view.getId()){
            case R.id.but_login:
                if (checkPassword()) {
                    i = new Intent(this, GameActivity.class);
                    startActivity(i);
                    break;
                } else {
                    errorMessage.setText(R.string.error_message_text);
                    editPassword.setText("");
                    editUsername.setText("");
                }
            case R.id.edit_username:
                if (editUsername.getText().toString().equals("Username")){
                    editUsername.setText("");
                }
            }
    }

    public boolean checkPassword(){
        if (editUsername.getText().toString().equals("test") &&
                (editPassword.getText().toString().equals("1234")))
        { return true; }
        else{ return false;}
    }
}
