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
   /* When the onclick method of gotoScouting is called it opens the Scouting.class*/

    public void gotoScouting(View view){
        Intent intent = new Intent(this, Scouting.class);
        startActivity(intent);
    }
    /*When the onclick method of gotoPitScouting is called it opens the pit_scouting.class*/
    public void gotoPitScouting(View view){
        Intent intent = new Intent(this, pit_scouting.class);
        startActivity(intent);
    }

}
