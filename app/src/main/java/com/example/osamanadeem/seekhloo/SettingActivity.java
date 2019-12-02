package com.example.osamanadeem.seekhloo;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    ListView listView;
    CircleImageView circleImageView;
    ArrayList<String> arrayList = new ArrayList<>();
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }


    void init()
    {
        listView = findViewById(R.id.setting_list);
    }
}
