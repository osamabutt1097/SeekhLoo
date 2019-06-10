package com.example.osamanadeem.seekhloo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class student_fag_home extends Fragment {

    private ImageView imageView;
    private TextView textView;
    private RecyclerView recyclerView;
    private ArrayList<classattributes> classes = new ArrayList<>();
    private DatabaseReference mDatabase;
    private DatabaseReference mRef;
    private FirebaseUser firebaseUser;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.student_frag_home,container,false);
        Toast.makeText(getContext(), "frag", Toast.LENGTH_SHORT).show();
        init(v);
        init_data();
        return v;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    void init(View v)
    {
        imageView = v.findViewById(R.id.classImage);
        textView = v.findViewById(R.id.noclasstxt);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mRef = mDatabase.getRef();

    }

    private void init_data()
    {
        final FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

        mRef.child("User").child(currentFirebaseUser.getUid()).child("Classroom");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                    classattributes attr = childDataSnapshot.getValue(classattributes.class);
                    classes.add(attr);
                    Toast.makeText(getContext(), classes.size() + "", Toast.LENGTH_SHORT).show();
                }

                init_recyclerview();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

            }

        });
    }

    private void init_recyclerview()
    {
        Toast.makeText(getContext(), "recycler", Toast.LENGTH_SHORT).show();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        RecyclerView recyclerView = getActivity().findViewById(R.id.class_recycler);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapterClasses adapter = new RecyclerViewAdapterClasses(classes,getContext());

        recyclerView.setAdapter(adapter);
    }

}
