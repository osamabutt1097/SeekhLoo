package com.example.osamanadeem.seekhloo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchTutorActivity extends AppCompatActivity {

    private Toolbar mTopToolbar;
    private RecyclerView recyclerView;
    private ArrayList<TutorInfo> classes = new ArrayList<>();
    ArrayList<classattributes> gig = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                        attr.setTokenid(token);
                    }
                    classes.add(attr);
                    //add you mediaItem to list that you provided
                }
                //Toast.makeText(getContext(), classes.size() + "", Toast.LENGTH_SHORT).show();
                init_recyclerview();
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

}
