package com.example.osamanadeem.seekhloo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.libaml.android.view.chip.ChipLayout;

public class CreateClassroom extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText className;
    private String[] array;
    private ChipLayout chip;
    private Toolbar mTopToolbar;
    private Spinner spinner;
    private String type;
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


        String[] countries = {"india","australia","austria","indonesia","canada"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,countries);
        chip.setAdapter(adapter);




    }

    void init(){
        className = findViewById(R.id.classname);
        spinner = findViewById(R.id.spin);
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
            createClassroom();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void createClassroom()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();
        final FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        classattributes  attr = new classattributes(className.getText().toString(),spinner.getSelectedItem().toString(),chip.getText());
        myRef.child("User").child(currentFirebaseUser.getUid()).child("Classroom").child(className.getText().toString()).setValue(attr);

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




}
