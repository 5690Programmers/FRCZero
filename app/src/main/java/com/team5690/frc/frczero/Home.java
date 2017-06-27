package com.team5690.frc.frczero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }
    public void gotoScouting(View view){
        Intent intent = new Intent(this, Scouting.class);
        startActivity(intent);
    }
    public void gotoPitScouting(View view){
        Intent intent = new Intent(this, pit_scouting.class);
        startActivity(intent);
    }

}
