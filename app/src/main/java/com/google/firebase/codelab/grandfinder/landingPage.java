package com.google.firebase.codelab.grandfinder;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.codelab.grandfinder.R;

public class landingPage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        TextView txt = findViewById(R.id.nameBio);
        txt.setText("TESTED");

    }public void jump(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }public void trial(View view){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }public void onEditBio(View view) {
        Intent intent = new Intent(this, CreateBio.class);
        startActivity(intent);
    }
}