package com.team5690.frc.frczero;
import android.os.Environment;
import android.provider.MediaStore;
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
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class Scouting extends AppCompatActivity {


    Button Save;
    private RadioGroup AutoYN;
    private RadioButton AutoButton;
   /* private RadioGroup Climbing;
    private RadioButton ClimbYBN;
    private RadioGroup PlayStyle;
    private RadioButton OBD;*/


    private String filename = "SampleFile.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;


    Button pGears, mGears, pFuel, mFuel;
    TextView tGears, vGears, vFuel, tFuel;
    EditText vTeamNumber, vMatchNumber;
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


        vGears = (TextView) findViewById(R.id.vGears);
        vFuel = (TextView) findViewById(R.id.vFuel);


        Save = (Button) findViewById(R.id.Save);
        Save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                   /* Climbing = (RadioGroup) findViewById(R.id.Climbing);
                    PlayStyle = (RadioGroup) findViewById(R.id.PlayStyle);*/
                    AutoYN = (RadioGroup) findViewById(R.id.valuesGroup);
                    Save = (Button) findViewById(R.id.Save);

                    // get selected radio button from radioGroup
                    int selectedId = AutoYN.getCheckedRadioButtonId();
                /*    int selectedId2 = PlayStyle.getCheckedRadioButtonId();
                    int selectedId3 = Climbing.getCheckedRadioButtonId();
*/
                    // find the radiobutton by returned id
                    AutoButton = (RadioButton) findViewById(selectedId);
                /*    OBD = (RadioButton) findViewById(selectedId2);
                    ClimbYBN = (RadioButton) findViewById(selectedId3);*/



                OutputStreamWriter osw;

                try {
                    FileOutputStream fos = new FileOutputStream(myExternalFile);
                   /* fos.write(vTeamNumber.getEditableText().toString().getBytes()); THIS CAUSES APP TO CRASH
                    fos.write(vMatchNumber.getEditableText().toString().getBytes());*/
                    fos.write(vGears.getText().toString().getBytes());
                    fos.write(vFuel.getText().toString().getBytes());
                    fos.write(AutoButton.getText().toString().getBytes());
                    osw = new OutputStreamWriter(fos); /*TEST THIS, IT SAVES BUT DOES IT WORK*/
                    osw.append("\r\n");

                /*    fos.write(OBD.getText().toString().getBytes());
                    fos.write(ClimbYBN.getText().toString().getBytes());*/
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(Scouting.this, ("File saved to External Storage..."),
                        Toast.LENGTH_LONG).show();

            }
        });

        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Save.setEnabled(false);
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






