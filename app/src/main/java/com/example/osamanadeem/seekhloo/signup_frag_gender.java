package com.example.osamanadeem.seekhloo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class signup_frag_gender extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.signup_frag_gender,container,false);
        Button btn = v.findViewById(R.id.su_gender_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOtherFragment();
            }
        });
        return v;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    public interface FragmentChangeAcademicInfo
    {
        public void replaceAcademicInfo(Fragment fragment);
    }
    public void showOtherFragment()
    {

        Fragment fr=new signup_frag_academic();
        signup_frag_gender.FragmentChangeAcademicInfo fc=(signup_frag_gender.FragmentChangeAcademicInfo)getActivity();
        fc.replaceAcademicInfo(fr);
    }
}
