package com.example.osamanadeem.seekhloo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

public class student_frag_newsletters extends Fragment {
    ArrayList<NewsLetters> news = new ArrayList<>();
    private DatabaseReference mDatabase;
    private DatabaseReference mRef;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.student_frag_notifications,container,false);
        init();
        init_data();

        return v;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void init()
    {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mRef = mDatabase.getRef().child("NewsLetters");
    }

    private void init_data()
    {
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {

                    NewsLetters newsLetters = childDataSnapshot.getValue(NewsLetters.class);
                    news.add(newsLetters);

                }

                init_recyclerview();
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    private void init_recyclerview()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        RecyclerView recyclerView = getActivity().findViewById(R.id.notify_stu_recycler);
        recyclerView.setLayoutManager(layoutManager);
        Toast.makeText(getContext(), news.get(0).getSubject()+"", Toast.LENGTH_SHORT).show();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(),news);
        recyclerView.setAdapter(adapter);
    }

}
