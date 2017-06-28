package com.team5690.frc.frczero;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class pit_scouting extends AppCompatActivity {

    private  RadioGroup DriveGroup;
    private  RadioButton DriveButtons;

    private RadioGroup GearGroup;
    private RadioButton GearButtons;

    private RadioGroup FuelGroup;
    private RadioButton FuelButtons;

    private RadioGroup ClimberGroup;
    private RadioButton ClimberButtons;

    EditText vPitTeamNumber, vPitComments;
    Button SavePit;
    private String filename = "PitScouting.txt";
    private String filepath = "PitScouting";
    File myFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scouting);

        vPitComments = (EditText) findViewById(R.id.vPitComments);
        vPitTeamNumber = (EditText) findViewById(R.id.vPitTeamNumber);
        SavePit = (Button) findViewById(R.id.SavePit);
        SavePit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DriveGroup = (RadioGroup) findViewById(R.id.DriveGroup);
                GearGroup = (RadioGroup) findViewById(R.id.GearGroup);
                FuelGroup = (RadioGroup) findViewById(R.id.FuelGroup);
                ClimberGroup = (RadioGroup) findViewById(R.id.ClimberGroup);

                int selectedId = DriveGroup.getCheckedRadioButtonId();
                int selectedId2 = GearGroup.getCheckedRadioButtonId();
                int selectedId3 = FuelGroup.getCheckedRadioButtonId();
                int selectedId4 = ClimberGroup.getCheckedRadioButtonId();

                DriveButtons = (RadioButton) findViewById(selectedId);
                GearButtons = (RadioButton) findViewById(selectedId2);
                FuelButtons = (RadioButton) findViewById(selectedId3);
                ClimberButtons = (RadioButton) findViewById(selectedId4);


                try{
                    FileOutputStream fos = new FileOutputStream(myFile, true);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    String separator = System.getProperty("line.separator");

                    fos.write((vPitTeamNumber.getText().toString() + ",").getBytes());
                    fos.write((DriveButtons.getText().toString() + ",").getBytes());
                    fos.write((GearButtons.getText().toString() + ",").getBytes());
                    fos.write((FuelButtons.getText().toString() + ",").getBytes());
                    fos.write((ClimberButtons.getText().toString() + ",").getBytes());
                    fos.write(vPitComments.getText().toString().getBytes());
                    osw.append("");
                    osw.append(separator);
                    osw.flush();
                    osw.close();
                    fos.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(pit_scouting.this, ("File Saved..."), Toast.LENGTH_LONG).show();
            }
        });

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            SavePit.setEnabled(false);
        } else {
            myFile = new File(getExternalFilesDir(filepath), filename);
        }


    }

    private static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    private static boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {
            return true;
        }
        return false;
    }
}



