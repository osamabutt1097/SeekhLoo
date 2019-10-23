package com.example.osamanadeem.seekhloo;

import androidx.appcompat.app.AppCompatActivity;
import ca.antonious.materialdaypicker.MaterialDayPicker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ReactRequestActivity extends AppCompatActivity {


    private String studentid,tutorid,classname;
    private ImageView imageView;
    private TextView Studentname,catagory,Time,description;
    private MaterialDayPicker.Weekday weekday;
    private MaterialDayPicker dayPicker;
    private HashMap<String,Object> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react_request);

        init();


    }

    public void init()
    {
        Studentname = findViewById(R.id.reqTutorInfoname);
        catagory = findViewById(R.id.reqTutorInfocatagory);
        Time = findViewById(R.id.reqTutorInfoTime);
        description = findViewById(R.id.reqTutorInfoDescription);
        dayPicker = findViewById(R.id.req_day_picker_tutorinfo);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("FriendRequest").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                SharedPreferences prefs = getSharedPreferences("Classdata", MODE_PRIVATE);
                classname = prefs.getString("name", "No name defined");//"No name defined" is the default value.

                studentid = dataSnapshot.child("studentid").toString();
                tutorid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                Toast.makeText(ReactRequestActivity.this, studentid, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void acceptreques(View view) {




        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Student").child(studentid)
                .child("Classroom").child(classname);
        HashMap<String,Object>map = new HashMap<>();
        map.put("tutor",FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference.updateChildren(map);


        reference = FirebaseDatabase.getInstance().getReference("Tutor").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Classroom");
        HashMap<String,Object>mini = new HashMap<>();
        mini.put("studentid",studentid);
        mini.put("classname",classname);
        reference.push();
    }

    public void Deletereq(View view) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("FriendRequest");
        reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
    }
}
