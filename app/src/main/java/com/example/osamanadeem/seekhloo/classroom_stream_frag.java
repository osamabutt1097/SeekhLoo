package com.example.osamanadeem.seekhloo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class classroom_stream_frag extends Fragment {
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.classroom_stream_frag_layout,container,false);
    Intent iin= new Intent();
    final SharedPreferences prefs = getActivity().getSharedPreferences("User", MODE_PRIVATE);
    private FirebaseListAdapter<ChatMessage> adapter;
    final String t_id,student_id,c_name;
    t_id =prefs.getString("t_id","null");
    student_id = prefs.getString("s_id","null");
    c_name =prefs.getString("c_name","null");
    Toast.makeText(v.getContext(), t_id+"", Toast.LENGTH_SHORT).show();
    FloatingActionButton fab = v.findViewById(R.id.sendfloatingbtn);
    final EditText editText = v.findViewById(R.id.typemsg);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        FirebaseDatabase.getInstance()
                .getReference().child("Student").child(student_id).child("Classroom").child(c_name).child("chat")
                .push()
                .setValue(new ChatMessage(editText.getText().toString(),
                        FirebaseAuth.getInstance().getCurrentUser().getUid()));
                editText.setText("");
      }
    });
    return v;

  }
  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }



}