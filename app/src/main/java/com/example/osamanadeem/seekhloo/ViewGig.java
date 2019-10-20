package com.example.osamanadeem.seekhloo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewGig extends AppCompatActivity  {

    private ArrayList<classattributes> classes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_gig);


        Toolbar toolbar= (Toolbar) findViewById(R.id.createclassroomtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        init_data();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addbtnstudent) {

            startActivity(new Intent(ViewGig.this,GigCreatingActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init_data()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://seekhloo.firebaseio.com/User/"+currentFirebaseUser.getUid()+"/Gigs");

        //Toast.makeText(getContext(), currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                classes.clear();
                for (DataSnapshot children : dataSnapshot.getChildren()) {
                    classattributes attr = children.getValue(classattributes.class);
                    classes.add(attr);
                    //add you mediaItem to list that you provided
                }
                //Toast.makeText(getContext(), classes.size() + "", Toast.LENGTH_SHORT).show();
                init_recyclerview();
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

            }

        });
    }
    private void init_recyclerview()
    {




        //LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        LinearLayoutManager layoutManager;
        layoutManager = new GridLayoutManager(ViewGig.this, 2);

        RecyclerView recyclerView = findViewById(R.id.show_gig_recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapterClasses adapter = new RecyclerViewAdapterClasses(classes,ViewGig.this);
        recyclerView.setAdapter(adapter);

        ////////////////
        if(classes.size() == 0)
        {
            //imageView.setVisibility(View.VISIBLE);
            //textView.setVisibility(View.VISIBLE);
        }
        else
        {
           // imageView.setVisibility(View.GONE);
           // textView.setVisibility(View.GONE);

        }


    }


}
