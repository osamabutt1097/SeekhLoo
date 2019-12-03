package com.example.osamanadeem.seekhloo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;
import static androidx.appcompat.app.AppCompatActivity.RESULT_OK;


public class Frag_AdminPostNewsletter extends Fragment {
  private EditText subject, body;
  private static final int PICK_IMAGE = 100;
  static final int REQUEST_IMAGE_CAPTURE = 1;
  private CircleImageView img;
  private Button submit;
  private Uri selectedImage;
  private StorageReference mStorageRef;
  FirebaseDatabase database;
  DatabaseReference myRef;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    return inflater.inflate(R.layout.frag_admin_postnewsletter,container,false);

  }


  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    init();
    //upload.setVisibility(View.VISIBLE);

    ////////////////////// Set Circular Image icon //////////////////

    img.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        openGallery();
      }
    });



    //////////////////// Post Newsletter Button ///////////////

    submit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        LottieAnimationView lottieAnimationView = getActivity().findViewById(R.id.upload_admin_data);
        lottieAnimationView.setVisibility(View.VISIBLE);
        if(subject.getText().toString().isEmpty() || body.getText().toString().isEmpty())
        {
          Toast.makeText(getContext(), "Provide some information", Toast.LENGTH_SHORT).show();
        }
        else
        {
          uploadPicAndData();
        }



      }
    });
  }



  void uploadPicAndData()
  {
    final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

    final LottieAnimationView lottieAnimationView = getActivity().findViewById(R.id.upload_admin_data);
    SharedPreferences prefs = getActivity().getSharedPreferences("picname", MODE_PRIVATE);
    StorageReference riversRef = mStorageRef.child("images/"+prefs.getInt("name",0)+".jpg");
    prefs.edit().putInt("name",prefs.getInt("name",0)+1).apply();
    if (selectedImage != null) {
      riversRef.putFile(selectedImage)
              .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                  // Get a URL to the uploaded content
                  Uri downloadUrl = taskSnapshot.getDownloadUrl();
                  NewsLetters newsLetters = new NewsLetters(subject.getText().toString(), body.getText().toString(), downloadUrl + "");

                  myRef.child("NewsLetters").child(subject.getText().toString()).setValue(newsLetters, new DatabaseReference.CompletionListener() {
                    public void onComplete(DatabaseError error, DatabaseReference ref) {
                      subject.setText(null);
                      body.setText(null);
                      img.setImageResource(R.drawable.camera);
                      Toast.makeText(getContext(), "Newsletter Published", Toast.LENGTH_SHORT).show();
                      lottieAnimationView.setVisibility(View.GONE);
                    }
                  });
                }
              })
              .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                  // Handle unsuccessful uploads
                  // ...
                  lottieAnimationView.setVisibility(View.GONE);
                  Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
              });
    }
    else
    {
      NewsLetters newsLetters = new NewsLetters(subject.getText().toString(), body.getText().toString(), "");

      myRef.child("NewsLetters").child(subject.getText().toString()).setValue(newsLetters, new DatabaseReference.CompletionListener() {
        public void onComplete(DatabaseError error, DatabaseReference ref) {
          subject.setText(null);
          body.setText(null);
          img.setImageResource(R.drawable.camera);
          Toast.makeText(getContext(), "Newsletter Published", Toast.LENGTH_SHORT).show();
          lottieAnimationView.setVisibility(View.GONE);
        }
      });
    }

  }


  //////// Initiallize varriables //////
  void init()
  {
    subject = getActivity().findViewById(R.id.subject_admin_postletter);
    body = getActivity().findViewById(R.id.body_admin_postletter);
    img = getActivity().findViewById(R.id.circleImg_admin_postletter);
    submit = getActivity().findViewById(R.id.btn_admin_postletter);
    mStorageRef = FirebaseStorage.getInstance().getReference();
    database = FirebaseDatabase.getInstance();
    myRef = database.getReference();
  }

  /////// Open Gallery to select images from device internal storage ///////
  void openGallery(){
    Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
    startActivityForResult(gallery,PICK_IMAGE);
  }



  ////// After image is selected successfully set image //////
  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch(requestCode) {
      case PICK_IMAGE:
        if(resultCode == RESULT_OK){
          selectedImage = data.getData();
          img.setImageURI(selectedImage);
        }

        break;

    }
  }
}