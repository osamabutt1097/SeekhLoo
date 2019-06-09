package com.example.osamanadeem.seekhloo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class signup_frag_academic extends Fragment {

    private EditText schoolname,schoolcity,scoolobtn,schooltotal,schoolRoll,collegename,collegecity,collegeobtn,collegetotal,CollegeRoll;
    private EditText universityname,universitydegree,universityGpa,universityMajor;
    private EditText Masteruniversityname,Masteruniversitydegree,MasteruniversityGpa,MasteruniversityMajor;
    private EditText PHDuniversityname,PHDuniversitydegree,PHDuniversityGpa,PHDuniversityMajor;
    private Button btn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.signup_frag_birthday,container,false);
        init();
        SignupActivity signupActivity = new SignupActivity();

        shared();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return v;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    void init()
    {
        schoolname = getActivity().findViewById(R.id.academic_name);
        schoolcity = getActivity().findViewById(R.id.academic_city);
        scoolobtn = getActivity().findViewById(R.id.academic_Omarks) ;
        schooltotal = getActivity().findViewById(R.id.academic_Tmarks);
        schoolRoll = getActivity().findViewById(R.id.academic_SchoolRoll);

        collegename = getActivity().findViewById(R.id.academic_Collegename);
        collegecity =getActivity().findViewById(R.id.academic_Colllegecity);
        collegeobtn = getActivity().findViewById(R.id.academic_CollegeObtainmarks);
        collegetotal = getActivity().findViewById(R.id.academic_CollegeTotalmarks);
        CollegeRoll = getActivity().findViewById(R.id.academic_CollegeRoll);


        universityname = getActivity().findViewById(R.id.academic_Universityname);
        universitydegree = getActivity().findViewById(R.id.academic_UniversityDegree);
        universityGpa = getActivity().findViewById(R.id.academic_UniversityGPA);
        universityMajor = getActivity().findViewById(R.id.academic_UniversityMajor);

        Masteruniversityname = getActivity().findViewById(R.id.academic_MasterUniversityname);
        Masteruniversitydegree = getActivity().findViewById(R.id.academic_MasterUniversityDegree);
        MasteruniversityGpa = getActivity().findViewById(R.id.academic_MasterUniversityGPA);
        MasteruniversityMajor = getActivity().findViewById(R.id.academic_MasterUniversityMajor);

        PHDuniversityname = getActivity().findViewById(R.id.academic_PHDUniversityname);
        PHDuniversitydegree = getActivity().findViewById(R.id.academic_PHDUniversityDegree);
        PHDuniversityGpa = getActivity().findViewById(R.id.academic_PHDUniversityGPA);
        PHDuniversityMajor = getActivity().findViewById(R.id.academic_PHDUniversityMajor);
    }

    void shared()
    {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("Academic Info", MODE_PRIVATE).edit();

        editor.putString("schoolname", schoolname.getText().toString());
        editor.putString("schoolcity", schoolcity.getText().toString());
        editor.putString("scoolobtn", scoolobtn.getText().toString());
        editor.putString("schooltotal", schooltotal.getText().toString());
        editor.putString("schoolRoll", schoolRoll.getText().toString());


        editor.putString("collegename", collegename.getText().toString());
        editor.putString("collegecity", collegecity.getText().toString());
        editor.putString("collegeobtn", collegeobtn.getText().toString());
        editor.putString("collegetotal", collegetotal.getText().toString());
        editor.putString("CollegeRoll", CollegeRoll.getText().toString());


        editor.putString("universityname", universityname.getText().toString());
        editor.putString("universitydegree", universitydegree.getText().toString());
        editor.putString("universityGpa", universityGpa.getText().toString());
        editor.putString("universityMajor", universityMajor.getText().toString());

        editor.putString("Masteruniversityname", Masteruniversityname.getText().toString());
        editor.putString("Masteruniversitydegree", Masteruniversitydegree.getText().toString());
        editor.putString("MasteruniversityGpa", MasteruniversityGpa.getText().toString());
        editor.putString("MasteruniversityMajor", MasteruniversityMajor.getText().toString());

        editor.putString("PHDuniversityname", PHDuniversityname.getText().toString());
        editor.putString("PHDuniversitydegree", PHDuniversitydegree.getText().toString());
        editor.putString("PHDuniversityGpa", PHDuniversityGpa.getText().toString());
        editor.putString("PHDuniversityMajor", PHDuniversityMajor.getText().toString());


        editor.apply();
    }



}
