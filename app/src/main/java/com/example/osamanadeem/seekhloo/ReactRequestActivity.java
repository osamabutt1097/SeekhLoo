package com.example.osamanadeem.seekhloo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import ca.antonious.materialdaypicker.MaterialDayPicker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReactRequestActivity extends AppCompatActivity {


    private requests req;
    private String studentid,tutorid,classname;
    ArrayList<requests> news = new ArrayList<>();
    private ImageView imageView;
    Requestclass requestclass;
    private TextView Studentname,catagory,Time,description;
    private MaterialDayPicker.Weekday weekday;
    private MaterialDayPicker dayPicker;
    private HashMap<String,Object> map = new HashMap<>();
    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react_request);

        init();


    }

    public void init() {
        lottieAnimationView = findViewById(R.id.load_request);
        Studentname = findViewById(R.id.reqTutorInfoname);
        catagory = findViewById(R.id.reqTutorInfocatagory);
        Time = findViewById(R.id.reqTutorInfoTime);
        description = findViewById(R.id.reqTutorInfoDescription);
        dayPicker = findViewById(R.id.req_day_picker_tutorinfo);

        lottieAnimationView.setVisibility(View.VISIBLE);

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().getRef().child("FriendRequest").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(abc.studentId);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             //   Map<String, Object> objectMap = (HashMap<String, Object>) dataSnapshot.getValue();
//
//
//
//            Toast.makeText(ReactRequestActivity.this, objectMap.get("userCount").toString(), Toast.LENGTH_SHORT).show();
                req = dataSnapshot.getValue(requests.class);
                Toast.makeText(ReactRequestActivity.this, req.getStudentid() +"", Toast.LENGTH_SHORT).show();


                /////////////////////////////////////////////////////////////////////////
                /*Class Data*/
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().getRef().child("Student")
                        .child(abc.studentId).child("Classroom").child(req.getName());

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        classattributes  ca = dataSnapshot.getValue(classattributes.class);

                        catagory.setText(ca.getCatagory());
                        Time.setText(ca.getTime());
                        description.setText(ca.getDescription());
                        dayPicker.setSelectedDays(ca.getWeekdayList());

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                //////////////////////////////////////////////////////////////////////////

                /*NAME*/

                DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference().getRef().child("Student")
                        .child(req.getStudentid());

                ref1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        UserInfo info = dataSnapshot.getValue(UserInfo.class);
                        Studentname.setText(info.getFirstname() + " "+info.getLastname());
                        studentid = req.getStudentid();
                        classname = req.getName();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

//                //////////////////////////////////////////////////////////////////////////

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //Toast.makeText(this, temp + "", Toast.LENGTH_SHORT).show();



    lottieAnimationView.setVisibility(View.GONE);

    }


    public void acceptreques(View view) {


        DatabaseReference dreference = FirebaseDatabase.getInstance().getReference("FriendRequest");
        dreference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Student").child(studentid)
                .child("Classroom").child(classname);
        HashMap<String,Object>map = new HashMap<>();
        map.put("tutor",FirebaseAuth.getInstance().getCurrentUser().getUid());
        map.put("requestedtutor","no");
        reference.updateChildren(map);


        reference = FirebaseDatabase.getInstance().getReference("Tutor").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Classroom");
        HashMap<String,Object>mini = new HashMap<>();
        mini.put("studentid",studentid);
        mini.put("classname",classname);
        reference.child(classname).setValue(mini, new DatabaseReference.CompletionListener() {
            public void onComplete(DatabaseError error, DatabaseReference ref) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("FriendRequest");
                reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
            }
        });

    }






    public void Deletereq(View view) {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("FriendRequest");
        reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).removeValue();
    }
}

