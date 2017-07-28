package com.team5690.frc.frczero;


import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.WindowManager;


import android.widget.EditText;







public class Home extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    EditText Number;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
//    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        Number = (EditText) findViewById(R.id.Number);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Name)) {
            Number.setText(sharedpreferences.getString(Name, ""));
        }

    }

    public void Save(View view) {
        String n = Number.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Name, n);
        editor.commit();
    }

//    public void clear(View view) {
//        Number = (EditText) findViewById(R.id.Number);
//        Number.setText("");
//
//    }
//
//    public void Get(View view) {
//        Number = (EditText) findViewById(R.id.Number);
//        sharedpreferences = getSharedPreferences(mypreference,
//                Context.MODE_PRIVATE);
//
//        if (sharedpreferences.contains(Name)) {
//            Number.setText(sharedpreferences.getString(Name, ""));
//        }
//    }


//
//    public void pewpew(View view) {
//        Intent intent = new Intent(this, Home.class);
//        EditText editText = (EditText) findViewById(R.id.sendScouterNum);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }

   /* When the onclick method of gotoScouting is called it opens the Scouting.class*/

    public void gotoScouting(View view) {
        Intent intent = new Intent(this, Scouting.class);
        startActivity(intent);
//        intent = new Intent(this, Scouting.class);
//        EditText editText = (EditText) findViewById(R.id.Number);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
    }

    /*When the onclick method of gotoPitScouting is called it opens the pit_scouting.class*/
    public void gotoPitScouting(View view) {
        Intent intent = new Intent(this, pit_scouting.class);
        startActivity(intent);
//        intent = new Intent(this, pit_scouting.class);
//        EditText editText = (EditText) findViewById(R.id.Number);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);

    }
}













