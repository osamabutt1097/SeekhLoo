package com.example.osamanadeem.seekhloo;

import androidx.appcompat.app.AppCompatActivity;
import ca.antonious.materialdaypicker.MaterialDayPicker;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GiginfoActivity extends AppCompatActivity {

    private TextView name,catagory,time,description;
    private MaterialDayPicker dayPicker;
    private ImageView circleImageView;

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
                Glide.with(getApplicationContext()).load(typetopic(attr.getCatagory())).into(circleImageView);
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

        circleImageView = findViewById(R.id.gigimg);

    }

    String typetopic(String type)
    {
        if (type.equals("Arts and Humanities"))
        {

            return "https://firebasestorage.googleapis.com/v0/b/seekhloo.appspot.com/o/ClassBackgroundImages%2Farts_humanities_424_283_0.jpg?alt=media&token=9e1aad87-f691-4326-a28f-8eeef77064d3";
        }
        else if (type.equals("Business"))
        {


            return "https://firebasestorage.googleapis.com/v0/b/seekhloo.appspot.com/o/ClassBackgroundImages%2Fbusiness.jpg?alt=media&token=c75415a0-f910-48af-9c05-38b41134ab06";

        }
        else if (type.equals("Computer Sciences"))
        {

            return "https://firebasestorage.googleapis.com/v0/b/seekhloo.appspot.com/o/ClassBackgroundImages%2Fbtech-csbs2018.jpg?alt=media&token=7753cc57-2332-4349-98b9-6f09d9ad1505";

        }else if (type.equals("Health"))
        {
            return "https://firebasestorage.googleapis.com/v0/b/seekhloo.appspot.com/o/ClassBackgroundImages%2Fdownload.png?alt=media&token=aeda7f04-c7d8-4fe8-9afa-da755048d30e";


        }else if (type.equals("Mathematics"))
        {

            return "https://firebasestorage.googleapis.com/v0/b/seekhloo.appspot.com/o/ClassBackgroundImages%2Fmaxresdefault.jpg?alt=media&token=a2dc83c5-b3f0-4bd2-b4ba-4f0b3879c1e4";

        }else if (type.equals("Physical Science"))
        {

            return "";


        }else if (type.equals("Social Studies"))
        {

            return "https://firebasestorage.googleapis.com/v0/b/seekhloo.appspot.com/o/ClassBackgroundImages%2Fsocial%20studies.jpg?alt=media&token=f25ac667-7b14-4fc5-a7b4-d497f36f5820";

        }


        return null;
    }
}
