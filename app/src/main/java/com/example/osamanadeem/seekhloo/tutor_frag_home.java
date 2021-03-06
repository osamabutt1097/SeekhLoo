package com.example.osamanadeem.seekhloo;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
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
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class tutor_frag_home extends Fragment {

    ArrayList<UserInfo> info = new ArrayList<>();
    private ImageView imageView;
    private TextView textView;
    private RecyclerView recyclerView;
    private ArrayList<classattributes> classes = new ArrayList<>();
    private DatabaseReference mDatabase;
    HashMap<String,Object>map = new HashMap<>();



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

        init();
    }

    /*   void init()
       {
           DatabaseReference Ref = FirebaseDatabase.getInstance().getReference().getRef().child("Student").child("VwhiUnq69SboQce1xXwrq8FzAXC3").child("Classroom");
           classes.clear();
           Ref.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   for (DataSnapshot children : dataSnapshot.getChildren()) {
                       classattributes attr = children.getValue(classattributes.class);
                       if(attr.getTutor().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                           classes.add(attr);
                       }//add you mediaItem to list that you provided
                   }

                   init_recyclerview();
               }

               @Override
               public void onCancelled(DatabaseError databaseError) {

               }
           });


           Toast.makeText(getContext(), classes.size()+"", Toast.LENGTH_SHORT).show();
           init_recyclerview();
       }
   */

       void init()
       {

           DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().getRef().child("Tutor")
                   .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Students");
           rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   List<String> friends = new ArrayList<>();
                   for(DataSnapshot ds : dataSnapshot.getChildren()) {
                       String friend = ds.getKey();
                       friends.add(friend);
                   }
                   init_classData(friends);

               }

               @Override
               public void onCancelled(DatabaseError databaseError) {

               }
           });




       }



       void init_classData(final List<String> friends)
       {

           classes.clear();
           for (int i =0; i<friends.size();i++) {
               FirebaseDatabase database = FirebaseDatabase.getInstance();
               final FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
               DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://seekhloo.firebaseio.com/Student/" + friends.get(i) + "/Classroom");

               mRef.addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(DataSnapshot dataSnapshot) {



                       for (DataSnapshot children : dataSnapshot.getChildren()) {
                           classattributes attr = children.getValue(classattributes.class);

                           if (dataSnapshot.exists())
                           if (attr.getTutor().equals(FirebaseAuth.getInstance().getCurrentUser().getUid()))
                            classes.add(attr);

                           //add you mediaItem to list that you provided
                       }
                       init_recyclerview();
                       //Toast.makeText(getContext(), classes.size() + "", Toast.LENGTH_SHORT).show();

                   }


                   @Override
                   public void onCancelled(DatabaseError databaseError) {
                       Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

                   }

               });

           }
       }


            private void init_data()
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
//        DatabaseReference mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://seekhloo.firebaseio.com/Tutor/"+currentFirebaseUser.getUid()+"/Classroom/Algebra");
//        mRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//
//                    map.put("studentid",dataSnapshot.child("studentid"));
//                    map.put("classname",dataSnapshot.child("classname"));
//
//
//
//                    //add you mediaItem to list that you provided
//
//                //Toast.makeText(getContext(), classes.size() + "", Toast.LENGTH_SHORT).show();
//            }
//
//
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
//
//            }
//
//        });

    }

    private void get_class_data() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Student").child(map.get("studentid").toString())
                .child("Classroom").child(map.get("classname").toString());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                classes.clear();
                for (DataSnapshot children : dataSnapshot.getChildren()) {
                    classattributes attr = children.getValue(classattributes.class);
                    if (dataSnapshot.child("classname").toString().equals(map.get("classname"))) {
                        classes.add(attr);
                    }

                    //add you mediaItem to list that you provided
                }
                init_recyclerview();
                //Toast.makeText(getContext(), classes.size() + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
        RecyclerViewAdapterClassesTutor adapter = new RecyclerViewAdapterClassesTutor(classes,getContext());
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

