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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class student_fag_home extends Fragment {

    private ImageView imageView;
    private TextView textView;
    private RecyclerView recyclerView;
    private ArrayList<classattributes> classes = new ArrayList<>();
    private DatabaseReference mDatabase;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.student_frag_home,container,false);

        return v;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        imageView = getView().findViewById(R.id.classImage);
        textView = getView().findViewById(R.id.noclasstxt);

        init_data();
    }

    void init(View v)
    {

    }

    private void init_data()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://seekhloo.firebaseio.com/Student/"+currentFirebaseUser.getUid()+"/Classroom");

        Toast.makeText(getContext(), currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

            }

        });
    }

    private void init_recyclerview()
    {




        //LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        LinearLayoutManager layoutManager;
        layoutManager = new GridLayoutManager(getContext(), 2);

        RecyclerView recyclerView = getActivity().findViewById(R.id.class_recycler);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapterClasses adapter = new RecyclerViewAdapterClasses(classes,getContext());
        recyclerView.setAdapter(adapter);

        ////////////////
        if(classes.size() == 0)
        {
            imageView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
        }
        else
        {
            imageView.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);

        }


    }

}
