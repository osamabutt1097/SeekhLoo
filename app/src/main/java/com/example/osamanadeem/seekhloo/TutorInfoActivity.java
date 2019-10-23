package com.example.osamanadeem.seekhloo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import ca.antonious.materialdaypicker.MaterialDayPicker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.data.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class TutorInfoActivity extends AppCompatActivity {

    private Toolbar mTopToolbar;
    private TextView name,time,description,category;
    private ImageView picUrl;
    private MaterialDayPicker dayPicker;
    private ArrayList<classattributes> classes = new ArrayList<>();
    private String id;
    private String token;
    int a;
    UserInfo info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_info);
        mTopToolbar = (Toolbar) findViewById(R.id.TutorInfoActivity);
        setSupportActionBar(mTopToolbar);
        mTopToolbar.setTitle("Tutor Info");

        init();


        set_data();
//        Toast.makeText(this,a, Toast.LENGTH_SHORT).show();

    }


    public void set_data()
    {
        Intent iin= getIntent();
        Bundle b = iin.getExtras();


        if(b!=null)
        {
            String n =(String) b.get("TutorName");
            name.setText(n);

            category.setText(n);
//            n = b.getString("Time").toString();
            //time.setText(n);
            n = b.getString("PicUrl").toString();
            id = b.getString("id").toLowerCase();
            Glide.with(getApplicationContext()).load(n).into(picUrl);
            a = b.getInt("position");
            token = b.getString("token");
            classes = (ArrayList<classattributes>) iin.getSerializableExtra("gig");
            time.setText(classes.get(1).getTime());
            dayPicker.setSelectedDays(classes.get(1).getWeekdayList());
            category.setText(classes.get(1).getList().toString());
            description.setText(classes.get(1).getDescription());

        }
    }

    public void init()
    {
        name = findViewById(R.id.TutorInfoname);
        time = findViewById(R.id.TutorInfoTime);
        description = findViewById(R.id.TutorInfoDescription);
        picUrl = findViewById(R.id.TutorInfoImage);
        category = findViewById(R.id.TutorInfocatagory);
        dayPicker = findViewById(R.id.day_picker_tutorinfo);

    }




    public void sendreq(View view) {
        DatabaseReference stdref = FirebaseDatabase.getInstance().getReference("Student").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        stdref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("firstname").getValue().toString();
                String useracnt = dataSnapshot.child("userCount").getValue().toString();
                SharedPreferences preferences = getSharedPreferences("User",MODE_PRIVATE);
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("FriendRequest").child(id)
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                HashMap<String, Object>map = new HashMap<>();
                map.put("name",name);
                map.put("userCount",useracnt);
                map.put("token",token);
                ref.updateChildren(map);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    public void back(View view) {
        finish();
    }
}
