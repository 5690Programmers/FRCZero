package com.team5690.frc.frczero;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;




public class Scouting extends AppCompatActivity {


    Button Save;

    private String filename = "SampleFile.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;


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


        vGears = (TextView) findViewById(R.id.vGears);
        vFuel = (TextView) findViewById(R.id.vFuel);


        Save = (Button) findViewById(R.id.Save);
        Save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = new FileOutputStream(myExternalFile);
                    fos.write(vGears.getText().toString().getBytes());
                    fos.write(vFuel.getText().toString().getBytes());
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


    private RadioGroup AutoYN;
    private RadioButton AutoButton;
    private Button button;



    public void button(View v) {
        AutoYN = (RadioGroup) findViewById(R.id.valuesGroup);
        button = (Button) findViewById(R.id.Save);

        // get selected radio button from radioGroup
        int selectedId = AutoYN.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        AutoButton = (RadioButton) findViewById(selectedId);

        Toast.makeText(Scouting.this, AutoButton.getText(), Toast.LENGTH_SHORT).show();

            }

        }








