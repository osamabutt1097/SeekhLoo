package com.example.osamanadeem.seekhloo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class StudentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private ViewPager viewPager;
    private WebView webView;
    private student_frag_newsletters notifications;
    private student_fag_home home;
    private TextView name, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {





        ////

        final SharedPreferences prefs = getSharedPreferences("User", MODE_PRIVATE);

        String themeMode = prefs.getString("theme_preference","0");
        if (themeMode.equals("2"))
        {
            setTheme(R.style.DarkTheme);

        }
        else
            setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("SeekhLoo");


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        name = headerView.findViewById(R.id.sname);
        email = headerView.findViewById(R.id.studentemail);
        init();
        getUser();
        setupViewPager(viewPager);

    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else if (webView.getVisibility() == View.VISIBLE)
        {
            webView.setVisibility(View.GONE);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.addbtnstudent){
            startActivity(new Intent(this,CreateClassroom.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            viewPager.setCurrentItem(0);
            webView.setVisibility(View.GONE);
            getSupportActionBar().setTitle("SeekhLoo");
        } else if (id == R.id.nav_calender) {
            Intent launchIntent = this.getPackageManager().getLaunchIntentForPackage("com.google.android.calendar");
            if (launchIntent != null) {
                startActivity(launchIntent);//null pointer check in case package name was not found
            }
            else
                Toast.makeText(this, "Please install Google Calender", Toast.LENGTH_SHORT).show();


        } else if (id == R.id.nav_settings) {
            startActivity(new Intent(StudentActivity.this,SettingActivity.class));

        }else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(StudentActivity.this,LoginActivity.class));
            finish();

        } else if (id == R.id.nav_notify) {
            viewPager.setCurrentItem(1);
            getSupportActionBar().setTitle("NewsLetters");
//            startActivity(new Intent(StudentActivity.this,VideChatActivity.class));

        }
        else if (id == R.id.nav_personality_test)
        {
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl("https://www.16personalities.com/free-personality-test");
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.setVisibility(View.VISIBLE);
            getSupportActionBar().setTitle("SeekhLoo");

            //Uri uri = Uri.parse("https://www.16personalities.com/free-personality-test"); // missing 'http://' will cause crashed
            //Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            //startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    void init()
    {
        webView = findViewById(R.id.webView);
        viewPager = findViewById(R.id.viewpagerstudent);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        notifications = new student_frag_newsletters();
        home = new student_fag_home();
        //adapter.addFragment(frag_adminPostNewsletter);

        adapter.addFragment(home); // index 0
        adapter.addFragment(notifications);  // index 1
        viewPager.setAdapter(adapter);
    }


    void getUser()
    {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child("Student").child(user.getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                UserInfo value = dataSnapshot.getValue(UserInfo.class);
                Toast.makeText(StudentActivity.this, value.getFirstname()+"", Toast.LENGTH_SHORT).show();
                name.setText(value.getFirstname());
                email.setText(value.getEmail());

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }

}

