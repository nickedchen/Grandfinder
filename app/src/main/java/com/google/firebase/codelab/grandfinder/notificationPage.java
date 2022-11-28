package com.google.firebase.codelab.grandfinder;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class notificationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_page);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Notifications");
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
     public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}