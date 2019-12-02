package com.example.osamanadeem.seekhloo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class frag_classroom_tutor  extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.classroom_frag_tutor, container, false);
        final CircleImageView circleImageView = v.findViewById(R.id.frag_tutorimg_classroom);
        final TextView textView = v.findViewById(R.id.tutorname_frag_classroom);
        Button button = v.findViewById(R.id.tutor_frag_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SearchTutorActivity.class);
                startActivity(intent);
            }
        });

        Intent iin = new Intent();


        final SharedPreferences prefs = getActivity().getSharedPreferences("User", MODE_PRIVATE);
        FirebaseListAdapter<ChatMessage> adapter;
        final String t_id, student_id, c_name;
        final String stud_name, tutor_name,check;

        t_id = prefs.getString("t_id", "null");
        student_id = prefs.getString("s_id", "null");
        c_name = prefs.getString("c_name", "null");
        check = prefs.getString("theme_preference","1");

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().getRef().child("Tutor").child(t_id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TutorInfo info = dataSnapshot.getValue(TutorInfo.class);
                Glide.with(getContext()).load(info.getPicUrL()).into(circleImageView);
                textView.setText(info.firstname+" "+info.getLastname());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return v;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


}