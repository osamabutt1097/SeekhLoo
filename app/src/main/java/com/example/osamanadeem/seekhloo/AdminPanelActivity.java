package com.example.osamanadeem.seekhloo;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;


public class AdminPanelActivity extends AppCompatActivity {

  BottomNavigationView bottomNavigationView;

  //This is our viewPager
  private ViewPager viewPager;


  //Fragments
  private Frag_AdminPostNewsletter frag_adminPostNewsletter;
  private frag_admin_review review;

  MenuItem prevMenuItem;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_admin_panel);

    //Initializing viewPager
    viewPager = (ViewPager) findViewById(R.id.viewpager);

    //Initializing the bottomNavigationView
    bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);

    bottomNavigationView.setOnNavigationItemSelectedListener(
            new BottomNavigationView.OnNavigationItemSelectedListener() {
              @Override
              public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                  case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                  case R.id.navigation_dashboard:
                    viewPager.setCurrentItem(1);
                    return true;
                  case R.id.navigation_settings:
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(AdminPanelActivity.this, LoginActivity.class));
                    finish();
                    return true;
                }
                return false;
              }
            });

    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      @Override
      public void onPageSelected(int position) {
        if (prevMenuItem != null) {
          prevMenuItem.setChecked(false);
        }
        else
        {
          bottomNavigationView.getMenu().getItem(0).setChecked(false);
        }
        bottomNavigationView.getMenu().getItem(position).setChecked(true);
        prevMenuItem = bottomNavigationView.getMenu().getItem(position);

      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });

    //Disable ViewPager Swipe
       /*viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });
        */


    setupViewPager(viewPager);
  }

  private void setupViewPager(ViewPager viewPager) {
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    frag_adminPostNewsletter=new Frag_AdminPostNewsletter();
    review=new frag_admin_review();
    adapter.addFragment(frag_adminPostNewsletter);
    adapter.addFragment(review);
    viewPager.setAdapter(adapter);
  }
}