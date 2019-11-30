package com.example.osamanadeem.seekhloo;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
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

import java.io.File;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class classroom_Resources_frag extends Fragment {
  Uri uri;
  String sid,check,filename;
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View v = inflater.inflate(R.layout.classroom_resources_frag,container,false);
    final View view = v;
    FirebaseListAdapter<resourcesinfo> adapter;

    FloatingActionButton fab = v.findViewById(R.id.resaddfab);
    ListView listView = v.findViewById(R.id.reslist);
    SharedPreferences prefs = getActivity().getSharedPreferences("User", MODE_PRIVATE);
    check = prefs.getString("theme_preference","1");
    final String c_id = prefs.getString("c_name", "null");
    final String student_id = prefs.getString("s_id", "null");
    sid=student_id;

    adapter = new FirebaseListAdapter<resourcesinfo>(getActivity(), resourcesinfo.class,
            R.layout.resourceslay, FirebaseDatabase.getInstance().getReference().child("Student").child(sid).child("Classroom").child(c_id).child("Resources")) {
      @Override
      protected void populateView(View v, resourcesinfo model, int position) {
        TextView messageUser = (TextView) v.findViewById(R.id.res_user);
        if (check.equals("2"))
        {
          messageUser.setTextColor(ContextCompat.getColor(getContext(), R.color.white));

        }

        messageUser.setText(model.getFilename());
      }


    };
    listView.setAdapter(adapter);



    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        resourcesinfo cm =new resourcesinfo();
        cm = (resourcesinfo) adapterView.getItemAtPosition(i);
        String urlString = cm.getPath();
        Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse(urlString));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setPackage("com.android.chrome");
        try {
          getActivity().startActivity(intent);
        } catch (ActivityNotFoundException ex) {
          // Chrome browser presumably not installed so allow user to choose instead
          intent.setPackage(null);
          getActivity().startActivity(intent);
        }
      }
    });





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


          Uri uri = data.getData();
          String uriString = uri.toString();
          File myFile = new File(uriString);
          String path = myFile.getAbsolutePath();
          String displayName = null;


          if (uriString.startsWith("content://")) {
            Cursor cursor = null;
            try {
              cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
              if (cursor != null && cursor.moveToFirst()) {
                displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
              }
            } finally {
              cursor.close();
            }
          } else if (uriString.startsWith("file://")) {
            displayName = myFile.getName();
          }
          filename = displayName;
          Log.e("saad",displayName);
          uploadres(uri);
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
    final String t_id = prefs.getString("t_id", "null");
    final String student_id = prefs.getString("s_id", "null");
    sid=student_id;
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
                  resourcesinfo src = new resourcesinfo(downloadUrl+"",student_id,filename);
                  FirebaseDatabase.getInstance().getReference().child("Student").child(student_id).child("Classroom").child(name).child("Resources").push().setValue(src, new DatabaseReference.CompletionListener() {
                    public void onComplete(DatabaseError error, DatabaseReference ref) {

                    }
                  });
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