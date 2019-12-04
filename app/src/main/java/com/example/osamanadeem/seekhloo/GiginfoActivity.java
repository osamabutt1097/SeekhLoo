package com.example.osamanadeem.seekhloo;

import androidx.appcompat.app.AppCompatActivity;
import ca.antonious.materialdaypicker.MaterialDayPicker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GiginfoActivity extends AppCompatActivity {

    private TextView name,catagory,time,description;
    private MaterialDayPicker dayPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giginfo);
        init();
        SharedPreferences prefs = this.getSharedPreferences("User", Context.MODE_PRIVATE);
        String s_id = prefs.getString("s_id",null);
        String c_name = prefs.getString("c_name",null);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().getRef()
                .child("Tutor").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Gigs").child(c_name);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                gigattributes attr = dataSnapshot.getValue(gigattributes.class);
                name.setText(attr.getName());
                catagory.setText(attr.getCatagory());
                description.setText(attr.getDescription());
                dayPicker.setSelectedDays(attr.getWeekdayList());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    void init()
    {
        name = findViewById(R.id.gigname);
        catagory = findViewById(R.id.gigcatagory);
        description = findViewById(R.id.gigdsc);
        dayPicker = findViewById(R.id.gigdaypicker);

    }
}
