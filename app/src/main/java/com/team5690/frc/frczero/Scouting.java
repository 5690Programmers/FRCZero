package com.team5690.frc.frczero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Scouting extends AppCompatActivity {

    Button pGears, mGears, pFuel, mFuel;
    TextView tGears, vGears, vFuel, tFuel;
    int GearCounter;
    int FuelCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting);
        GearCounter = 0;
        pGears = (Button) findViewById(R.id.pGears);
        mGears = (Button) findViewById(R.id.mGears);
        vGears = (TextView) findViewById(R.id.vGears);
        tGears = (TextView) findViewById(R.id.tGears);

        FuelCounter = 0;
        pFuel = (Button) findViewById(R.id.pFuel);
        mFuel = (Button) findViewById(R.id.mFuel);
        vFuel = (TextView) findViewById(R.id.vFuel);
        tFuel = (TextView) findViewById(R.id.tFuel);

        pGears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GearCounter = GearCounter + 1;
                vGears.setText(Integer.toString(GearCounter));

            }
        });
        mGears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GearCounter = GearCounter - 1;
                vGears.setText(Integer.toString(GearCounter));
            }
        });

        pFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FuelCounter = FuelCounter + 1;
                vFuel.setText(Integer.toString(FuelCounter));

            }
        });
        mFuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FuelCounter = FuelCounter - 1;
                vFuel.setText(Integer.toString(FuelCounter));
            }
        });
    }
}
