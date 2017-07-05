package com.team5690.frc.frczero;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;



public class Scouting extends AppCompatActivity {


    /* When the onclick method of BackButton is called it opens the Home.class*/
    public void BackButton(View view){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }


    /*These are our private variables that we declared, telling the app that hey, these things exist*/
    Button SaveMatch;
    private RadioGroup AutoYN;
    private RadioButton AutoButton;
    private RadioGroup ClimbGroup;
    private RadioButton ClimbButton;

    private String filename = "MatchScouting.csv";
    private String filepath = "ScoutingData";
    File myExternalFile;

    Button pGears, mGears, pFuel, mFuel;
    TextView tGears, vGears, vFuel, tFuel;
    EditText vTeamNumber, vMatchNumber, vComments;
    int GearCounter;
    int FuelCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting);

        /*Here we tell the app that hey this is what this is and start the counters at 0 pls*/
        GearCounter = 0;
        vComments = (EditText) findViewById(R.id.vComments);
        vTeamNumber = (EditText) findViewById(R.id.vTeamNumber);
        vMatchNumber = (EditText) findViewById(R.id.vMatchNumber);
        pGears = (Button) findViewById(R.id.pGears);
        mGears = (Button) findViewById(R.id.mGears);
        vGears = (TextView) findViewById(R.id.vGears);
        tGears = (TextView) findViewById(R.id.tGears);

        FuelCounter = 0;
        pFuel = (Button) findViewById(R.id.pFuel);
        mFuel = (Button) findViewById(R.id.mFuel);
        vFuel = (TextView) findViewById(R.id.vFuel);
        tFuel = (TextView) findViewById(R.id.tFuel);


        /*These things are onclicks that listen for onclicks or presses. In this case we set it up so
        * if the pGears(Plus gears for short :P) is pressed it increases the GearCounter by one. Same thing goes
        * for mGears(Minus gears) but it takes one away*/
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

        /*We got a button with the id of Save*/
        SaveMatch = (Button) findViewById(R.id.Save);
        /*You wanna watch this button and see if people click it pal?*/
        SaveMatch.setOnClickListener(new OnClickListener() {



            @Override
            /*Hey that SavedMatch button was pressed, what now?*/
            public void onClick(View v) {


                /*We got selectors and multiple things that could be pressed. we wanna tell the app that
                * a certain one was pressed. We tell it there is a group of buttons (RadioGroups)*/
                AutoYN = (RadioGroup) findViewById(R.id.AutoGroup);
                ClimbGroup = (RadioGroup) findViewById(R.id.ClimbGroup);
                SaveMatch = (Button) findViewById(R.id.Save);

                /*get selected radio button from radioGroup*/
                int selectedId = AutoYN.getCheckedRadioButtonId();
                int selectedId2 = ClimbGroup.getCheckedRadioButtonId();

                /* find the radiobutton by returned id*/
                AutoButton = (RadioButton) findViewById(selectedId);
                ClimbButton = (RadioButton) findViewById(selectedId2);


                /*This is the tool we use to output string data to "myFile" we added a line separator to separate
                 * every entry with an enter. This allows the data to be put on a separate row in a Spreadsheet  */
                try {
                    FileOutputStream fos = new FileOutputStream(myExternalFile, true);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    String separator = System.getProperty("line.separator");

                    /* User input */

                    /*Here we say fos(FileOutputStream) write this data and put it into a string so we can read it and add a , after it
                    * Adding a , after it puts each entry into its own cell in a spreadsheet(Also know as CSV or comma separated values*/
                    fos.write((vMatchNumber.getText().toString() + ",").getBytes());
                    fos.write((vTeamNumber.getText().toString() + ",").getBytes());
                    fos.write((vGears.getText().toString() + ",").getBytes());
                    fos.write((vFuel.getText().toString() + ",").getBytes());
                    fos.write((AutoButton.getText().toString() + ",").getBytes());
                    fos.write((ClimbButton.getText().toString() + ",").getBytes());
                    fos.write(vComments.getText().toString().getBytes());
                    /*All done pls close this I has nothing left for you to write*/
                    osw.append("");
                    osw.append(separator);
                    osw.flush();
                    osw.close();
                    fos.close();
                    /*Weird catch thing I dont understand but its needed*/
                } catch (IOException e) {
                    e.printStackTrace();
                }
                /*Cheers, make a toast notification to tell the user your "Scouting Data Saved" rejoice
                * as you get ready to scout for the next 4 hours, YAY!*/
                Toast.makeText(Scouting.this, ("Scouting Data Saved"),
                        Toast.LENGTH_LONG).show();

            }

        });
        /*This here checks to see if the External storage is available and if its read only, if so then it sets it to false
        * But if its there and not read only it saves the file with the filepath and filename*/
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            SaveMatch.setEnabled(false);
        } else {
           myExternalFile = new File(getExternalFilesDir(filepath), filename);
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






