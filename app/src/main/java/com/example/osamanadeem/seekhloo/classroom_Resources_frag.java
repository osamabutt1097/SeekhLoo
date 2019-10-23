package com.example.osamanadeem.seekhloo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class classroom_Resources_frag extends Fragment {
  Uri uri;
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View v = inflater.inflate(R.layout.classroom_resources_frag,container,false);

    FloatingActionButton fab = v.findViewById(R.id.resaddfab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Intent chooseFile;
        Intent intent;
        chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
        intent = Intent.createChooser(chooseFile, "Choose a file");
        startActivityForResult(intent, 1000);

        //////////////////////////


        /////////////////////////////
      }
    });
    return v;

  }
  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    switch(requestCode){
      case 1000:
        if(resultCode==-1){
          uri = data.getData();
          String filePath = uri.getPath();
          uri = data.getData();
          uploadres(uri);
          Toast.makeText(getActivity(), filePath,
                  Toast.LENGTH_LONG).show();
        }
        break;
    }
    super.onActivityResult(requestCode, resultCode, data);
  }

  void uploadres(Uri uri)
  {
    StorageReference mStorageRef;
    mStorageRef = FirebaseStorage.getInstance().getReference();
    SharedPreferences prefs = getActivity().getSharedPreferences("User", MODE_PRIVATE);
    String t_id = prefs.getString("t_id", "null");
    final String student_id = prefs.getString("s_id", "null");
    final String name = prefs.getString("c_name","null");

    StorageReference riversRef = mStorageRef.child("Resources/"+student_id+"/"+name+"/"+uri.getPath());

    if(uri!=null)
    {
      riversRef.putFile(uri)
              .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                  // Get a URL to the uploaded content
                  Uri downloadUrl = taskSnapshot.getDownloadUrl();
                  FirebaseDatabase.getInstance().getReference().child("Student").child(student_id).child("Classroom").child(name).child("Resources").setValue(downloadUrl+"");
                  Toast.makeText(getContext(), "Resources upload", Toast.LENGTH_SHORT).show();

                }
              })
              .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                  // Handle unsuccessful uploads
                  // ...
                  Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
              });
    }
    else
      Toast.makeText(getContext(), "No URI", Toast.LENGTH_SHORT).show();

  }
}