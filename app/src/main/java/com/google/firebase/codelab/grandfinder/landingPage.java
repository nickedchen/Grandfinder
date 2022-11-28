package com.google.firebase.codelab.grandfinder;

import static com.google.firebase.codelab.grandfinder.MainActivity.ANONYMOUS;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.codelab.grandfinder.model.post;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.firebase.ui.auth.AuthUI;


public class landingPage extends AppCompatActivity {
    ArrayList<String> names = new ArrayList<>();
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        TextView txt = findViewById(R.id.nameBio);
        getData();
        txt.setText(names.toString());

    }public void jump(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }public void getData(){
        TextView txt = findViewById(R.id.nameBio);
        DatabaseReference myRef = database.child("UserInfo");
        System.out.println(myRef.toString());
        DatabaseReference ex = myRef.child("User1");

        myRef.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                TextView txt = findViewById(R.id.nameBio);
                TextView txt2 = findViewById(R.id.bio1);
                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String ma  = snapshot1.child("Name").getValue().toString();
                    String ma2  = snapshot1.child("Personality").getValue().toString();
                    txt.setText(ma);
                    txt2.setText(ma2);

                }

//                ArrayList<String> toReturn = new ArrayList<>();
//                for(DataSnapshot snapshot1 : snapshot.getChildren()){
//                    String ma = snapshot1.child("Name").getValue().toString();

//                    toReturn.add(ma);
//                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive

            }
        });
    }
//    }private void String getPhotoUrl() {
//
//        String user = auth.currentUser;
//        return user?.photoUrl?.toString()
//    }


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
                startActivity(new Intent(this, notificationPage.class));
                return true;

            case R.id.chat:
                startActivity(new Intent(this, MainActivity.class));
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