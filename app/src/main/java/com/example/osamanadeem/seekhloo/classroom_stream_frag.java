package com.example.osamanadeem.seekhloo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class classroom_stream_frag extends Fragment {
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View v = inflater.inflate(R.layout.classroom_stream_frag_layout, container, false);
    Intent iin = new Intent();

    final SharedPreferences prefs = getActivity().getSharedPreferences("User", MODE_PRIVATE);
    FirebaseListAdapter<ChatMessage> adapter;
    final String t_id, student_id, c_name;
    final String stud_name, tutor_name,check;

    t_id = prefs.getString("t_id", "null");
    student_id = prefs.getString("s_id", "null");
    c_name = prefs.getString("c_name", "null");
    check = prefs.getString("theme_preference","1");
   // stud_name = getnameSTUD(student_id);
   // tutor_name = getnameTutor(t_id);
   // Toast.makeText(v.getContext(), t_id + "", Toast.LENGTH_SHORT).show();
    FloatingActionButton fab = v.findViewById(R.id.sendfloatingbtn);
    final EditText editText = v.findViewById(R.id.typemsg);


    /////////////////////
    final ListView listOfMessages = v.findViewById(R.id.streamlist);

    adapter = new FirebaseListAdapter<ChatMessage>(getActivity(), ChatMessage.class,
            R.layout.message, FirebaseDatabase.getInstance().getReference().child("Student").child(student_id).child("Classroom").child(c_name).child("chat")) {
      @Override
      protected void populateView(View v, ChatMessage model, int position) {
        // Get references to the views of message.xml
        TextView messageText = (TextView) v.findViewById(R.id.message_text);
        TextView messageUser = (TextView) v.findViewById(R.id.message_user);
        TextView messageTime = (TextView) v.findViewById(R.id.message_time);

        if (check.equals("2"))
        {
          messageText.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
          messageUser.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
          messageTime.setTextColor(ContextCompat.getColor(getContext(), R.color.white));

        }

        // Set their text
        messageText.setText(model.getMessageText());
        if (model.getMessageUser().equals(FirebaseAuth.getInstance().getCurrentUser().getUid()))
        {
          messageUser.setText("You");
        }
        else
          messageUser.setText("User");

        // Format the date before showing it
        messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                model.getMessageTime()));
      }
    };

    listOfMessages.setAdapter(adapter);
  listOfMessages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
      ChatMessage cm =new ChatMessage();
      cm = (ChatMessage) adapterView.getItemAtPosition(i);
      Toast.makeText(getContext(), cm.getMessageText(), Toast.LENGTH_SHORT).show();
    }
  });

    //////////////////////
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

  public String getnameSTUD(String sttud_id) {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://seekhloo.firebaseio.com/Student/" + sttud_id);
    final String[] Sname = new String[1];
   // Toast.makeText(getContext(), currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();

    mRef.addChildEventListener(new ChildEventListener() {
      @Override
      public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//        UserInfo value = dataSnapshot.getValue(UserInfo.class);
      //  Sname[0] = value.getFirstname();
      }

      @Override
      public void onChildChanged(DataSnapshot dataSnapshot, String s) {

      }

      @Override
      public void onChildRemoved(DataSnapshot dataSnapshot) {

      }

      @Override
      public void onChildMoved(DataSnapshot dataSnapshot, String s) {

      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }

    });
    return Sname[0];

  }

  public String getnameTutor(String tutor_id) {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://seekhloo.firebaseio.com/Tutor/" + tutor_id);
    final String[] Sname = new String[1];
   // Toast.makeText(getContext(), currentFirebaseUser.getUid(), Toast.LENGTH_SHORT).show();
    mRef.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(DataSnapshot dataSnapshot) {


        for (DataSnapshot children : dataSnapshot.getChildren()) {
          TutorInfo attr = children.getValue(TutorInfo.class);
          Sname[0] = attr.getFirstname();
          //add you mediaItem to list that you provided
        }
        //Toast.makeText(getContext(), classes.size() + "", Toast.LENGTH_SHORT).show();

      }

      @Override
      public void onCancelled(DatabaseError databaseError) {

      }
    });
    return Sname[0];

  }
}