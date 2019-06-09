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

    EditText schoolname,schoolcity,scoolobtn,schooltotal,schoolRoll,collegename,collegecity,collegeobtn,collegetotal,CollegeRoll;
    EditText universityname,universitydegree,universityGpa,universityMajor;
    EditText Masteruniversityname,Masteruniversitydegree,MasteruniversityGpa,MasteruniversityMajor;
    EditText PHDuniversityname,PHDuniversitydegree,PHDuniversityGpa,PHDuniversityMajor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.signup_frag_birthday,container,false);
        init();
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
}
