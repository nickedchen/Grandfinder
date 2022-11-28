package com.google.firebase.codelab.grandfinder;

import static com.google.firebase.codelab.grandfinder.MainActivity.ANONYMOUS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.auth.AuthUI;

public class landingPage extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
    }


    @Override
     public boolean onCreateOptionsMenu(Menu menu){
           getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.notification_menu:
                // User chose the "notification" item, show the notification activity
                return true;

            case R.id.chat:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.sign_out_menu:
                signOut();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private void signOut() {
        AuthUI.getInstance().signOut(this);
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    public void notif(View view){
        Intent intent = new Intent(this, notificationPage.class);
        startActivity(intent);
    }

}