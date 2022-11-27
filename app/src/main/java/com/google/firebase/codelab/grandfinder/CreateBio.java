package com.google.firebase.codelab.grandfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreateBio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_bio);
    }

    public void onBack(View view){
        Intent intent = new Intent(this, landingPage.class);
        startActivity(intent);
    }
}