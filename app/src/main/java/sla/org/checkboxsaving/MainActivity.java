package sla.org.checkboxsaving;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivity extends AppCompatActivity {
    // Model
    Model theModel;
    // UI Elements
    CheckBox yoCheckBox;
    TextView yoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // wire up the UI Elements to fields
        yoCheckBox = findViewById(R.id.yoCheckBox1);
        yoTextView = findViewById(R.id.yoTextView1);

        // restore the saved model
        initializeModel();
    }

    @Override
    protected void onStop() {
        // save the model before quitting
        this.saveModel();

        super.onStop();
    }

    private void initializeModel() {
        // try to read (deserialize) model object from disk
        File savedModelFile = new File(getApplicationContext().getFilesDir(), "savedCheckboxModel");
        if (savedModelFile.exists()) {
            try {
                FileInputStream savedModelFileStream = new FileInputStream(savedModelFile);
                ObjectInputStream in = new ObjectInputStream(savedModelFileStream);
                theModel = (Model)in.readObject();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (theModel == null) {
            theModel = new Model();
        }

        // update the UI based on the data in the restored model
        if (theModel.getAmountOfChecks() > 0) {
            yoTextView.setText("Yo! Checked " + theModel.getAmountOfChecks() + " times!");
        }
        yoCheckBox.setChecked(theModel.isYoCurrentlyChecked());
    }

    private void saveModel() {
        // write (serialize) the model object
        try {
            File savedModelFile = new File(getApplicationContext().getFilesDir(), "savedCheckboxModel");
            FileOutputStream savedModelFileStream = new FileOutputStream(savedModelFile);
            ObjectOutputStream out = new ObjectOutputStream(savedModelFileStream);
            out.writeObject(theModel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void yoCheckBoxCheckingHappened(View view) {
        // update model with extra check happening
        theModel.incrementAmountOfChecks();
        theModel.setYoCurrentlyChecked(!theModel.isYoCurrentlyChecked());

        // update UI to report number of checks that have happened
        yoTextView.setText("Yo! Checked " + theModel.getAmountOfChecks() + " times!");
    }

}
