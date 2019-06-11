package com.example.osamanadeem.seekhloo;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    PaperOnboardingPage scr1 = new PaperOnboardingPage("Hotels",
            "All hotels and hostels are sorted by hospitality rating",
            Color.parseColor("#678FB4"), R.drawable.logo, R.drawable.ic_launcher_background);
    PaperOnboardingPage scr2 = new PaperOnboardingPage("Banks",
            "We carefully verify all banks before add them into the app",
            Color.parseColor("#65B0B4"), R.drawable.ic_dashboard_black_24dp, R.drawable.ic_launcher_background);
    PaperOnboardingPage scr3 = new PaperOnboardingPage("Stores",
            "All local stores are categorized for your convenience",
            Color.parseColor("#9B90BC"), R.drawable.onboarding_pager_circle_icon, R.drawable.ic_launcher_background);

    ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
    elements.add(scr1);
    elements.add(scr2);
    elements.add(scr3);

    PaperOnboardingFragment onBoardingFragment = PaperOnboardingFragment.newInstance(elements);

    new Handler().postDelayed(new Runnable(){
      @Override
      public void run() {
        /* Create an Intent that will start the Menu-Activity. */
        Intent mainIntent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(mainIntent);
        finish();
      }
    }, 3000);
  }



}