package com.example.osamanadeem.seekhloo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SearchTutorActivity extends AppCompatActivity {

    private Toolbar mTopToolbar;
    ArrayList<gigattributes> gigs = new ArrayList<>();

    private RecyclerView recyclerView;
    private ArrayList<TutorInfo> classes = new ArrayList<>();
    private final int Kvalue = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final SharedPreferences prefs = getSharedPreferences("User", MODE_PRIVATE);

        String themeMode = prefs.getString("theme_preference","0");
        if (themeMode.equals("2"))
        {
            setTheme(R.style.DarkTheme);
        }
        else
            setTheme(R.style.AppTheme);

        setTheme(R.style.AppTheme);


        setContentView(R.layout.activity_search_tutor);


        mTopToolbar = (Toolbar) findViewById(R.id.createclassroomtoolbar);
        setSupportActionBar(mTopToolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        init_data();

    }



    public void init_data()
    {
        recyclerView = findViewById(R.id.searchtutor_recyclerview);

         load_data();

    }




    public void load_data()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://seekhloo.firebaseio.com/Tutor");

        Toast.makeText(SearchTutorActivity.this, currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                classes.clear();
                for (DataSnapshot children : dataSnapshot.getChildren()) {
                    TutorInfo attr = children.getValue(TutorInfo.class);
                    if(children.child("token").exists())
                    {
                        String token = children.child("token").getValue().toString();
                        attr.setToken(token);
                    }
                    classes.add(attr);
                    //add you mediaItem to list that you provided
                }
                //Toast.makeText(getContext(), classes.size() + "", Toast.LENGTH_SHORT).show();
               algo_data(classes);

                //init_recyclerview();
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(SearchTutorActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }

        });

    }






    private void init_recyclerview()
    {




        //LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        LinearLayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(SearchTutorActivity.this,RecyclerView.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapterSearchTutor adapter = new RecyclerViewAdapterSearchTutor(classes,SearchTutorActivity.this);
        recyclerView.setAdapter(adapter);

        ////////////////


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.searchtutor, menu);
        return true;
    }

    void algo_data(ArrayList<TutorInfo> info) {


        gigs.clear();
        for (int i = 0; i < info.size(); i++) {
            DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://seekhloo.firebaseio.com/Tutor/" + info.get(i).getId() + "/Gigs");

            mRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    for (DataSnapshot children : dataSnapshot.getChildren()) {
                        gigattributes attr = children.getValue(gigattributes.class);

                        if (dataSnapshot.exists())
                                gigs.add(attr);

                    }


                    calculateNumericValues(gigs);
                 //  Toast.makeText(SearchTutorActivity.this, classes.size() + "", Toast.LENGTH_SHORT).show();


                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }


            });
        }
        }

    void calculateNumericValues(ArrayList<gigattributes> gigs)
    {
        ArrayList<intvalues> nit = new ArrayList<>();

        for (int i = 0 ; i< gigs.size();i++)
        {
            int city = cityToNumeric(gigs.get(i).getCity());
            int catagory = catagoryToNumeric(gigs.get(i).getCatagory());
            int time = time(gigs.get(i).getTime());
            int type = typeToNumeric(gigs.get(i).getType());
            intvalues val = new intvalues(city,catagory,time,type,gigs.get(i).getT_id(),-1);
            nit.add(val);


        }

        get_class_info(nit);


    }

    void get_class_info(ArrayList<intvalues> nit)
    {
        ArrayList<intvalues> snit = new ArrayList<>();

        final SharedPreferences prefs = getSharedPreferences("User", MODE_PRIVATE);
        final String t_id, student_id, c_name;

        t_id = prefs.getString("t_id", "null");
        student_id = prefs.getString("s_id", "null");
        c_name = prefs.getString("c_name", "null");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().getRef()
                .child("Student").child(student_id).child("Classroom").child(c_name);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                classattributes attr = dataSnapshot.getValue(classattributes.class);
                intvalues val = new intvalues(cityToNumeric(attr.getCity()),catagoryToNumeric(attr.getCatagory())
                ,time(attr.getTime()),typeToNumeric(attr.getType()),student_id,-1);
                snit.add(val);
                calculate_distance(nit,snit);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    void calculate_distance(ArrayList<intvalues> Tutors,ArrayList<intvalues>Student)
    {

        for (int i =0 ; i<Tutors.size(); i++)
        {
            double city = Tutors.get(i).getCity() - Student.get(0).getCity();
            city *= city;

            double type = Tutors.get(i).getType() - Student.get(0).getType();
            type *= type;

            double catagory = Tutors.get(i).getCatagory() - Student.get(0).getCatagory();
            catagory *= catagory;

            double time = Tutors.get(i).getTime() - Student.get(0).getTime();
            time *= time;

            double temp = city + type + catagory + time;
            temp = Math.sqrt(temp);

            Tutors.get(i).setDistance((long) temp);

        }



    }

    int cityToNumeric(String city)
    {
       if (city.equals("Faisalabad"))
           return 1;
       else if (city.equals("Islamabad"))
            return 2;
        else if (city.equals("Lahore"))
            return 3;
        else
            return 0;

    }

    int catagoryToNumeric(String catagory) {
        if (catagory.equals("Arts and Humanities")) {
            return 1;
        }
        else if (catagory.equals("Business")) {

            return 2;
        }
        else if (catagory.equals("Computer Sciences")) {
        return 3;
        }
        else if (catagory.equals("Health")) {
        return 4;
        }
        else if (catagory.equals("Mathematics")) {
        return 5;
        }
        else if (catagory.equals("Physical Science")) {
        return 6;
        }
        else if (catagory.equals("Social Studies")) {
            return 7;
        }
        else
            return 0;
    }

    int time(String time)
    {
        if (time.equals("1"))
            return 1;
        else if (time.equals("2"))
            return 2;
        else if (time.equals("3"))
            return 3;
        else if (time.equals("4"))
            return 4;
        else if (time.equals("5"))
            return 5;
        else if (time.equals("6"))
            return 6;
        else if (time.equals("7"))
            return 7;
        else if (time.equals("8"))
            return 8;
        else if (time.equals("9"))
            return 9;
        else if (time.equals("10"))
            return 10;
        else if (time.equals("11"))
            return 11;
        else if (time.equals("12"))
            return 12;
        else if (time.equals("13"))
            return 13;
        else if (time.equals("14"))
            return 14;
        else if (time.equals("15"))
            return 15;
        else if (time.equals("16"))
            return 16;
        else if (time.equals("17"))
            return 17;
        else if (time.equals("18"))
            return 18;
        else if (time.equals("19"))
            return 19;
        else if (time.equals("20"))
            return 20;
        else if (time.equals("21"))
            return 21;
        else if (time.equals("22"))
            return 22;
        else if (time.equals("23"))
            return 23;
        else if (time.equals("0"))
            return 24;
        else
            return 0;



    }

    int typeToNumeric(String type)
    {
        if (type.equals("Home"))
            return 1;
        else if (type.equals("Away"))
            return 2;
        else
            return 0;
    }
}
