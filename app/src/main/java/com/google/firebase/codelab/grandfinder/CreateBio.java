package com.google.firebase.codelab.grandfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateBio extends AppCompatActivity {
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    private CheckBox c1, c2, c3;
    int i = 0;
    private int[] myImages = {R.drawable.mete1, R.drawable.mete2, R.drawable.mete3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bio);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Your Profile");
    }

    public void saveChange(View view) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Create Bio");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void cont(View view) {
        DatabaseReference myRef = database.child("UserProfile");

        EditText editText1 = (EditText) findViewById(R.id.name);
        EditText editText2 = (EditText) findViewById(R.id.personality);
        EditText editText3 = (EditText) findViewById(R.id.hobby);
        EditText editText4 = (EditText) findViewById(R.id.about);

        String habits = "";

        c1 = (CheckBox) findViewById(R.id.checkBox1);
        c2 = (CheckBox) findViewById(R.id.checkBox2);
        c3 = (CheckBox) findViewById(R.id.checkBox3);

        if (c1.isChecked() && c2.isChecked() && c3.isChecked())
            habits = "Smoking, Alcohol, Drugs";
        else if (c1.isChecked() && c2.isChecked() && !c3.isChecked())
            habits = "Smoking, Alcohol";
        else if (c1.isChecked() && !c2.isChecked() && c3.isChecked())
            habits = "Smoking, Drugs";
        else if (!c1.isChecked() && c2.isChecked() && c3.isChecked())
            habits = "Alcohol, Drugs";
        else if (c1.isChecked() && !c2.isChecked() && !c3.isChecked())
            habits = "Smoking";
        else if (!c1.isChecked() && c2.isChecked() && !c3.isChecked())
            habits = "Alcohol";
        else if (!c1.isChecked() && !c2.isChecked() && c3.isChecked())
            habits = "Drugs";
        else

            c1 = (CheckBox) findViewById(R.id.checkBox1);
        c2 = (CheckBox) findViewById(R.id.checkBox2);
        c3 = (CheckBox) findViewById(R.id.checkBox3);

        if (c1.isChecked() && c2.isChecked() && c3.isChecked())
            habits = "Smoking, Alcohol, Drugs";
        else if (c1.isChecked() && c2.isChecked() && !c3.isChecked())
            habits = "Smoking, Alcohol";
        else if (c1.isChecked() && !c2.isChecked() && c3.isChecked())
            habits = "Smoking, Drugs";
        else if (!c1.isChecked() && c2.isChecked() && c3.isChecked())
            habits = "Alcohol, Drugs";
        else if (c1.isChecked() && !c2.isChecked() && !c3.isChecked())
            habits = "Smoking";
        else if (!c1.isChecked() && c2.isChecked() && !c3.isChecked())
            habits = "Alcohol";
        else if (!c1.isChecked() && !c2.isChecked() && c3.isChecked())
            habits = "Drugs";
        else if (!c1.isChecked() && !c2.isChecked() && !c3.isChecked())

            habits = "none";

        String message1 = editText1.getText().toString();
        String message2 = editText2.getText().toString();
        String message3 = editText3.getText().toString();
        String message4 = editText4.getText().toString();

        if (message1.matches("")) {
            Toast.makeText(getApplicationContext(), "Please fill in your name", Toast.LENGTH_SHORT).show();
            return;
        } else if (message2.matches("")) {
            Toast.makeText(getApplicationContext(), "Please fill in your personality", Toast.LENGTH_SHORT).show();
            return;
        } else if (message3.matches("")) {
            Toast.makeText(getApplicationContext(), "Please fill in your hobby", Toast.LENGTH_SHORT).show();
            return;
        } else if (message4.matches("")) {
            Toast.makeText(getApplicationContext(), "Please fill in the about you space", Toast.LENGTH_SHORT).show();
            return;
        }

        myRef.child("User1").child("Name").setValue(message1);
        myRef.child("User1").child("Hobby").setValue(message2);
        myRef.child("User1").child("Personality").setValue(message3);
        myRef.child("User1").child("About").setValue(message4);
        myRef.child("User1").child("Habits").setValue(habits);


        Toast.makeText(getApplicationContext(), "Success Creating Bio", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, landingPage.class);
        Bundle bundle = new Bundle();
        //Add your data from getFactualResults method to bundle
        bundle.putString("name", message1);
        //Add the bundle to the intent
        intent.putExtras(bundle);
        startActivity(intent);
    }


    public void next(View view) {
        i++;
        if (i == 3) {
            i = 0;
        }
        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(myImages[i]);
    }

    public void previous(View view) {
        i--;
        if (i == -1) {
            i = 3;
        }
        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setImageResource(myImages[i]);
    }
}