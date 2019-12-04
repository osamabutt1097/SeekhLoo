package com.example.osamanadeem.seekhloo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class SettingActivity extends AppCompatActivity {


    Uri uri;
    String sid, check, filename;
    CircleImageView circleImageView;
    TextView email, name;
    private Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        final SharedPreferences prefs = getSharedPreferences("User", MODE_PRIVATE);

        String themeMode = prefs.getString("theme_preference", "0");
        if (themeMode.equals("2")) {
            setTheme(R.style.DarkTheme);
        } else
            setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_setting);
        init();

        if (themeMode.equals(2)) {
            aSwitch.setChecked(true);
        }
        //Again dark panga
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            aSwitch.setChecked(true);
        }
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferences.Editor editor = getSharedPreferences("User", MODE_PRIVATE).edit();
                    editor.putString("theme_preference", "2");
                    editor.apply();
                    restartApp();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferences.Editor editor = getSharedPreferences("User", MODE_PRIVATE).edit();
                    editor.putString("theme_preference", "1");
                    editor.apply();
                    restartApp();
                }
            }
        });

    }

    private void restartApp() {
        Intent i = new Intent(getApplicationContext(), SettingActivity.class);
        startActivity(i);
        finish();
    }

    void init() {
        name = findViewById(R.id.t1);
        email = findViewById(R.id.emailsetting);

        aSwitch = findViewById(R.id.darkswitch);
        init_values();

    }

    void init_values() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().getRef()
                .child("Student").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                UserInfo info = dataSnapshot.getValue(UserInfo.class);
                name.setText(info.getFirstname() + " " + info.getLastname());
                email.setText(info.getEmail());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1000:
                if (resultCode == -1) {


                    Uri uri = data.getData();
                    String uriString = uri.toString();
                    File myFile = new File(uriString);
                    String path = myFile.getAbsolutePath();
                    String displayName = null;


                    if (uriString.startsWith("content://")) {
                        Cursor cursor = null;
                        try {
                            cursor = getContentResolver().query(uri, null, null, null, null);
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
                    Log.e("saad", displayName);
                    uploadres(uri);
                }
                break;
        }

    }
    void uploadres(Uri uri)
    {
        StorageReference mStorageRef;
        mStorageRef = FirebaseStorage.getInstance().getReference();
        SharedPreferences prefs = getSharedPreferences("User", MODE_PRIVATE);
        final String t_id = prefs.getString("t_id", "null");
        final String student_id = prefs.getString("s_id", "null");
        sid=student_id;
        final String name = prefs.getString("c_name","null");

        StorageReference riversRef = mStorageRef.child("Resources/"+uri.getPath());

        if(uri!=null)
        {
            riversRef.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Student").child(student_id);
                            HashMap<String,Object> map = new HashMap<>();
                            map.put("picUrL",downloadUrl);
                            ref.updateChildren(map);

                            Toast.makeText(SettingActivity.this, "Resources upload", Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                            Toast.makeText(SettingActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else
            Toast.makeText(SettingActivity.this, "No URI", Toast.LENGTH_SHORT).show();

    }
}
