package com.team5690.frc.frczero;
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
    Button SaveMatch;
    private RadioGroup AutoYN;
    private RadioButton AutoButton;
    private RadioGroup ClimbGroup;
    private RadioButton ClimbButton;

    private String filename = "MatchScouting.txt";
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


        SaveMatch = (Button) findViewById(R.id.Save);
        SaveMatch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                    AutoYN = (RadioGroup) findViewById(R.id.AutoGroup);
                    ClimbGroup = (RadioGroup) findViewById(R.id.ClimbGroup);
                    SaveMatch = (Button) findViewById(R.id.Save);

                    // get selected radio button from radioGroup
                    int selectedId = AutoYN.getCheckedRadioButtonId();
                    int selectedId2 = ClimbGroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    AutoButton = (RadioButton) findViewById(selectedId);
                    ClimbButton = (RadioButton) findViewById(selectedId2);

                try {
                    FileOutputStream fos = new FileOutputStream(myExternalFile, true);
                    OutputStreamWriter osw = new OutputStreamWriter(fos);
                    String separator = System.getProperty("line.separator");

                    /* User input */
                    fos.write((vMatchNumber.getText().toString() + ",").getBytes());
                    fos.write((vTeamNumber.getText().toString() + ",").getBytes());
                    fos.write((vGears.getText().toString() + ",").getBytes());
                    fos.write((vFuel.getText().toString() + ",").getBytes());
                    fos.write((AutoButton.getText().toString() + ",").getBytes());
                    fos.write((ClimbButton.getText().toString() + ",").getBytes());
                    fos.write(vComments.getText().toString().getBytes());
                    osw.append("");
                    osw.append(separator);
                    osw.flush();
                    osw.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(Scouting.this, ("File Saved..."),
                        Toast.LENGTH_LONG).show();

            }
        });

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            SaveMatch.setEnabled(false);
        } else {
            myExternalFile = new File(getExternalFilesDir(filepath), filename);
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






