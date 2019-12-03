package com.example.osamanadeem.seekhloo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import de.hdodenhof.circleimageview.CircleImageView;

public class SettingTutorActivity extends AppCompatActivity {


    CircleImageView circleImageView;
    TextView email,name;
    private Switch aSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        final SharedPreferences prefs = getSharedPreferences("User", MODE_PRIVATE);

        String themeMode = prefs.getString("theme_preference","0");
        if (themeMode.equals("2"))
        {
            setTheme(R.style.DarkTheme);
        }
        else
            setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_setting);
        init();

        if(themeMode.equals(2))
        {
            aSwitch.setChecked(true);
        }
        //Again dark panga
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES   )
        {
            aSwitch.setChecked(true);
        }
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferences.Editor editor = getSharedPreferences("User", MODE_PRIVATE).edit();
                    editor.putString("theme_preference", "2");
                    editor.apply();
                    restartApp();
                }
                else
                {
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
        Intent i = new Intent(getApplicationContext(), SettingTutorActivity.class);
        startActivity(i);
        finish();
    }

    void init()
    {
        name = findViewById(R.id.t1);
        email = findViewById(R.id.emailsetting);

        aSwitch = findViewById(R.id.darkswitch);
        circleImageView = findViewById(R.id.setting_img);
        init_values();

    }
    void init_values()
    {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().getRef()
                .child("Tutor").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            TutorInfo info = dataSnapshot.getValue(TutorInfo.class);
            name.setText(info.getFirstname() + " "+ info.getLastname());
            email.setText(info.getEmail());

            Glide.with(getApplicationContext()).load(info.getPicUrL()).into(circleImageView);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
