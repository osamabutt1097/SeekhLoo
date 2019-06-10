package com.example.osamanadeem.seekhloo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SeeNewsletter extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageView imageView;
    private Toolbar mTopToolbar;

    private TextView message,subject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_newsletter);
        mTopToolbar = (Toolbar) findViewById(R.id.seenewslettertoolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        init();
        init_values();

    }

    void init()
    {
        imageView = findViewById(R.id.seenewsletter_pic);
        message = findViewById(R.id.seenewsletter_message);
        subject = findViewById(R.id.seenewsletter_subject);
    }

    void init_values(){
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String msg =(String) b.get("messagebody");
            String sub =(String) b.get("subject");
            String url =(String) b.get("picurl");
            message.setText(msg);
            subject.setText(sub);
            if (!url.isEmpty())
                Glide.with(this).load(url).into(imageView);

        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();

        return true;
    }
}
