package com.example.osamanadeem.seekhloo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class AllRequestsActivity extends AppCompatActivity {

    private ListView listView;
    FirebaseListAdapter<Requestclass> adapter;
    String studentid;
    HashMap<String,Object>map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_requests);

        listView = findViewById(R.id.reqall);








/*
        adapter = new FirebaseListAdapter<Requestclass>(this, Requestclass.class,
                R.layout.requests, FirebaseDatabase.getInstance().getReference().child("Student").child(student_id).child("Classroom").child(c_name).child("chat")) {
            @Override
            protected void populateView(View v, Requestclass model, int position) {
                TextView messageTime = findViewById(R.id.reqid);

                // Set their text
                messageTime.setText("");
            }


        };

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Requestclass cm =new Requestclass();
                cm = (Requestclass) adapterView.getItemAtPosition(i);



                //Toast.makeText(getContext(), cm.getMessageText(), Toast.LENGTH_SHORT).show();
            }
        });
*/
    }
}
