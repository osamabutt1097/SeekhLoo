package com.example.osamanadeem.seekhloo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import ca.antonious.materialdaypicker.MaterialDayPicker;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.libaml.android.view.chip.ChipLayout;

import java.util.Calendar;
import java.util.List;

public class CreateClassroom extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText className, timeset;
    private String[] array;
    private MaterialDayPicker materialDayPicker;
    private ChipLayout chip;
    private Toolbar mTopToolbar;
    private Spinner spinner;
    private String type;
    private RadioGroup rgroup;
    private RadioButton radioButton;
    private EditText desc;
    private List<MaterialDayPicker.Weekday> weekdayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final SharedPreferences prefs = getSharedPreferences("User", MODE_PRIVATE);

        String themeMode = prefs.getString("theme_preference","0");
        if (themeMode.equals("2"))
        {
            setTheme(R.style.DarkTheme);
        }
        else
            setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_classroom);
        mTopToolbar = (Toolbar) findViewById(R.id.createclassroomtoolbar);
        setSupportActionBar(mTopToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        chip = (ChipLayout) findViewById(R.id.chipText);
        init();
        spin();

        day_picker();



        String[] countries = {"india","australia","austria","indonesia","canada"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,countries);
        chip.setAdapter(adapter);




    }

    void init(){
        className = findViewById(R.id.classname);
        spinner = findViewById(R.id.spin);
        materialDayPicker = findViewById(R.id.day_picker);
        timeset = findViewById(R.id.time_create_class);
        rgroup = findViewById(R.id.radiotype);
        desc = findViewById(R.id.descriptioncreateclass);
    }


    void day_picker()
    {
        //val selectedDays = materialDayPicker.selectedDays
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(this, "Clicked on: " + array[i], Toast.LENGTH_SHORT).show();
        type = array[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();

        return true;
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
             startActivity(new Intent(CreateClassroom.this,SearchTutorActivity.class));
            //createClassroom();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createClassroom()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        final FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        classattributes  attr = new classattributes(className.getText().toString(),spinner.getSelectedItem().toString(),chip.getText(),timeset.getText().toString(),getType(),getdays(),desc.getText().toString(),"no","no",currentFirebaseUser.getUid());
        myRef.child("Student").child(currentFirebaseUser.getUid()).child("Classroom").child(className.getText().toString()).setValue(attr);

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


    public void settime(View view) {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(CreateClassroom.this, new TimePickerDialog.OnTimeSetListener() {
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
