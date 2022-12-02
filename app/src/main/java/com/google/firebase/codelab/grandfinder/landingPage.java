package com.google.firebase.codelab.grandfinder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    int i = 0;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> hobbies = new ArrayList<>();
    ArrayList<String> personality = new ArrayList<>();
    ArrayList<String> habits = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        next();
        System.out.println("DONE");
        System.out.println(names.toString());

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            String value = bundle.getString("name");
            TextView textView = findViewById(R.id.textView6);
            textView.setText("Signed in as " + value);
        }
    }

    public void jump(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void getData(ArrayList<String> names, ArrayList<String> hobbies, ArrayList<String> personality, ArrayList<String> habits, String bo) {
        //Initialize the elements
        TextView txt = findViewById(R.id.nameBio);
        DatabaseReference myRef = database.child("UserInfo");
    //    Toast.makeText(getApplicationContext(), "Current is " + i, Toast.LENGTH_SHORT).show();

        DatabaseReference ex = myRef.child("User1");
        if (bo == "undo") {
            if (i > 0) {
                i--;
            }
        }
        setImages(i);
        myRef.addValueEventListener(new ValueEventListener() {
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    // this method is call to get the realtime
                    TextView txt = findViewById(R.id.nameBio);
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        String ma = snapshot1.child("Name").getValue().toString();
                        String ma2 = snapshot1.child("Hobbies").getValue().toString();
                        String ma3 = snapshot1.child("Personality").getValue().toString();
                        String ma4 = snapshot1.child("Habits").getValue().toString();
                        /////////
                        names.add(ma);
                        hobbies.add(ma2);
                        personality.add(ma3);
                        habits.add(ma4);
                        //////////
                    }
                    TextView txt2 = findViewById(R.id.bio1);
                    String test1 = "Hobbies: " + hobbies.get(i) + "\n";
                    String test2 = "Personality: " + personality.get(i) + "\n";
                    String test3 = "Habits: " + habits.get(i) + "\n";

                    String initial = test1 + test2 + test3;
                    txt2.setText(initial);
                    txt.setText(names.get(i));

                    if (i == names.size() - 1) {
                        i = 0;
                    }
                    else{
                        i++;
                    }

                    if (i < 0) {
                        i = 0;
                    }

                    System.out.println("After is " + i);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "No more people", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

            case R.id.edit_profile_menu:
                startActivity(new Intent(this, CreateBio.class));
                return true;

            case R.id.sign_out_menu:
                signOut();
                return true;


            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void signOut() {
        String signout = "Are you sure you want to sign out?";
        String confirm = "Confirm";
        String cancel = "Cancel";

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog);
        builder.setTitle("Signing Out");
        builder.setMessage(signout)
                .setPositiveButton(confirm, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AuthUI.getInstance().signOut(landingPage.this);
                        finish();
                    }
                })
                .setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        builder.create().show();
    }

    public void undo(View view) {
        i = i - 1;
        setImages(i);// Sets the images
        System.out.println(i);
        getData(names, hobbies, personality, habits, "undo");
    }public void dislike(View view){
        Toast.makeText(getApplicationContext(), "Disliked the profile!", Toast.LENGTH_SHORT).show();
        next();
    }

    public void like(View view) {
        Toast.makeText(getApplicationContext(), "Liked the profile!", Toast.LENGTH_SHORT).show();
        next();
    }

    public void nextbtn(View view) {
        next();
    }

    public void next() {
        getData(names, hobbies, personality, habits, "do");
    }

    public void setImages(int i) {
        ImageView img = findViewById(R.id.PhotoView);
        switch (i) {
            case 0:
                img.setImageResource(R.drawable.profilepic1);
                break;
            case 1:
                img.setImageResource(R.drawable.profilepic2);
                break;
            case 2:
                img.setImageResource(R.drawable.profilepic3);
                break;
            case 3:
                img.setImageResource(R.drawable.profilepic4);
                break;
            case 4:
                img.setImageResource(R.drawable.profilepic5);
                break;
        }
    }

    public void setText(ArrayList<String> names, ArrayList<String> hobbies, ArrayList<String> personality, ArrayList<String> habits) {

        TextView txt = findViewById(R.id.nameBio);
        TextView txt2 = findViewById(R.id.bio1);
        txt.setText(names.get(0));
        String test1 = "Hobbies: " + hobbies.get(0) + "\n";
        String test2 = "Personality: " + personality.get(0) + "\n";
        String test3 = "Habits: " + habits.get(0) + "\n";
        String initial = test1 + test2 + test3;
        txt2.setText(initial);

    }
}