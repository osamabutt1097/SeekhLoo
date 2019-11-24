package com.example.osamanadeem.seekhloo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import ca.antonious.materialdaypicker.MaterialDayPicker;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.libaml.android.view.chip.ChipLayout;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class GigCreatingActivity extends AppCompatActivity {

    private EditText className, timeset,description;
    private String[] array;
    private MaterialDayPicker materialDayPicker;
    private ChipLayout chip;
    private Toolbar mTopToolbar;
    private Spinner spinner;
    private String type;
    private RadioGroup rgroup;
    private RadioButton radioButton;
    private List<MaterialDayPicker.Weekday> weekdayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gig_creating);


        init();
        spin();
        Toolbar toolbar= (Toolbar) findViewById(R.id.creategigtoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_classroom_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_create) {
            CreateGig();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    public void init()
    {
        description = findViewById(R.id.descriptiontutor);
        className = findViewById(R.id.gigname);
        spinner = findViewById(R.id.spinGig);
        materialDayPicker = findViewById(R.id.GIG_day_picker);
        timeset = findViewById(R.id.GIG_time_create_class);
        rgroup = findViewById(R.id.GIG_radiotype);
        chip = findViewById(R.id.GIGchipText);
    }



    public void spin()
    {
        array = getResources().getStringArray(R.array.myaray);
        final ArrayAdapter adapter =  ArrayAdapter.createFromResource(this,R.array.myaray,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



                if (adapter.getItem(i).toString().equals("Arts and Humanities"))
                {

                    String[] arr = getResources().getStringArray(R.array.arts);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arr);
                    chip.setAdapter(adapter);

                }
                if (adapter.getItem(i).toString().equals("Business"))
                {

                    String[] arr = getResources().getStringArray(R.array.business);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arr);
                    chip.setAdapter(adapter);

                }
                if (adapter.getItem(i).toString().equals("Computer Sciences"))
                {
                    String[] arr = getResources().getStringArray(R.array.computer);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arr);
                    chip.setAdapter(adapter);


                }if (adapter.getItem(i).toString().equals("Health"))
                {
                    String[] arr = getResources().getStringArray(R.array.health);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arr);
                    chip.setAdapter(adapter);


                }if (adapter.getItem(i).toString().equals("Mathematics"))
                {
                    String[] arr = getResources().getStringArray(R.array.math);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arr);
                    chip.setAdapter(adapter);


                }if (adapter.getItem(i).toString().equals("Physical Science"))
                {
                    String[] arr = getResources().getStringArray(R.array.physical);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arr);
                    chip.setAdapter(adapter);


                }if (adapter.getItem(i).toString().equals("Social Studies"))
                {

                    String[] arr = getResources().getStringArray(R.array.social);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,arr);
                    chip.setAdapter(adapter);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    public void CreateGig()
    {
        final HashMap<String,Object> map = new HashMap<>();
        map.put("Description",description.getText().toString());
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        final FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        classattributes  attr = new classattributes(className.getText().toString(),spinner.getSelectedItem().toString(),chip.getText(),timeset.getText().toString(),getType(),getdays());
        myRef.child("Tutor").child(currentFirebaseUser.getUid()).child("Gigs").child(className.getText().toString()).setValue(attr, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                myRef.child("Tutor").child(currentFirebaseUser.getUid()).child("Gigs").child(className.getText().toString()).child("Description").setValue(description.getText().toString());
            }
        });



    }
    public void settime(View view) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(GigCreatingActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                timeset.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }


    public String getType()
    {
        int selectedId = rgroup.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radioButton = (RadioButton) findViewById(selectedId);

        return radioButton.getText().toString();
    }

    public List<MaterialDayPicker.Weekday> getdays()
    {

        weekdayList = materialDayPicker.getSelectedDays();
        return weekdayList;

    }
}
