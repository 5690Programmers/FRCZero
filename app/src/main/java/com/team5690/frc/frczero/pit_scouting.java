package com.team5690.frc.frczero;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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


    /* When the onclick method of BackButton is called it opens the Home.class*/
    public void BackButton(View view){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    /*These are our private variables that we declared, telling the app that hey, these things exist*/
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
    private String filename = "PitScouting.csv";
    private String filepath = "ScoutingData";
    File myFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pit_scouting);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


//        Intent intent = getIntent();
//        String message = intent.getStringExtra(Home.EXTRA_MESSAGE);
//
//        // Capture the layout's TextView and set the string as its text
//        TextView textView = (TextView) findViewById(ScouterNumber);
//        textView.setText(message);


        /* we are defining what some of these things mean, like vPitComments being and EditText field with the id of vPitComments*/
        vPitComments = (EditText) findViewById(R.id.vPitComments);
        vPitTeamNumber = (EditText) findViewById(R.id.vPitTeamNumber);
        SavePit = (Button) findViewById(R.id.SavePit);
       /* Here we made the Button SavePit and set an OnClickListener so it listens for a onclick or a button press*/
        SavePit.setOnClickListener(new View.OnClickListener() {


            @Override
            /*This calls the onclick we just set with the OnClickListener. Note you can set onclicks in two ways. 1. the way above
            * 2. Going to the .xml and adding android:onclick="Whateveryouwant" and putting that in place of the OnClick in "public void OnClick"*/
            public void onClick(View v) {

                /*This is for the booleans we have. since we have multiple selections for drivetrians and etc,
                we want the one that is selected to be printed into our file we save*/

                /*First we state private variable we initialized at the top, what it is and its ID*/
                DriveGroup = (RadioGroup) findViewById(R.id.DriveGroup);
                GearGroup = (RadioGroup) findViewById(R.id.GearGroup);
                FuelGroup = (RadioGroup) findViewById(R.id.FuelGroup);
                ClimberGroup = (RadioGroup) findViewById(R.id.ClimberGroup);

                /*Then we int a thing to call it, like selectedId(it can be whatever you wanna call it just make it the same throughout for simplicity
                * We set the certain selectedId to the name of a RadioGroup stated above and we ask for the CheckedRadioButton*/

                int selectedId = DriveGroup.getCheckedRadioButtonId();
                int selectedId2 = GearGroup.getCheckedRadioButtonId();
                int selectedId3 = FuelGroup.getCheckedRadioButtonId();
                int selectedId4 = ClimberGroup.getCheckedRadioButtonId();

                /*Once we know what button is check we wanna capture it and set it equal to something
                * We take the private variables we initialized at the top, tell it that its a RadioButton, then tell it that it
                * has the selectedId of what was selected before*/
                DriveButtons = (RadioButton) findViewById(selectedId);
                GearButtons = (RadioButton) findViewById(selectedId2);
                FuelButtons = (RadioButton) findViewById(selectedId3);
                ClimberButtons = (RadioButton) findViewById(selectedId4);

                /*This includes all the saving of the inputs*/
                try{
                    /*This is the tool we use to output string data to "myFile" we added a line separator to separate
                    * every entry with an enter. This allows the data to be put on a separate row in a Spreadsheet  */
                    FileOutputStream fos = new FileOutputStream(myFile, true);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    String separator = System.getProperty("line.separator");

                    /*Here we say fos(FileOutputStream) write this data and put it into a string so we can read it and add a , after it
                    * Adding a , after it puts each entry into its own cell in a spreadsheet(Also know as CSV or comma separated values*/
                    fos.write((vPitTeamNumber.getText().toString() + ",").getBytes());
                    fos.write((DriveButtons.getText().toString() + ",").getBytes());
                    fos.write((GearButtons.getText().toString() + ",").getBytes());
                    fos.write((FuelButtons.getText().toString() + ",").getBytes());
                    fos.write((ClimberButtons.getText().toString() + ",").getBytes());
                    fos.write(vPitComments.getText().toString().getBytes());
                    /*We tell the app, all done close up shop pls*/
                    osw.append("");
                    osw.append(separator);
                    osw.flush();
                    osw.close();
                    fos.close();
                    /*I dont know what catches are so not much to say but I think its needed....*/
                } catch (IOException e) {
                    e.printStackTrace();
                }
                /*Once we successfully save the file and there wasnt some weird error that caused an IOException catch thingy
                * the app makes a little toast notification saying "Pit Scouting Data Saved" */
                Toast.makeText(pit_scouting.this, ("Pit Scouting Data Saved"), Toast.LENGTH_LONG).show();
            }
        });

        /*This here checks to see if the External storage is available and if its read only, if so then it sets it to false
        * But if its there and not read only it saves the file with the filepath and filename*/
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            SavePit.setEnabled(false);
        } else {
            myFile = new File(getExternalFilesDir(filepath), filename);
        }


    }
    /*These two find out if the External Storage is Available and if its ReadOnly*/
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



