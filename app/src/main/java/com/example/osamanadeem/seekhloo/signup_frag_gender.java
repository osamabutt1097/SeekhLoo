package com.example.osamanadeem.seekhloo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class signup_frag_gender extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.signup_frag_gender,container,false);
        Button btn = v.findViewById(R.id.su_gender_btn);
        final SharedPreferences.Editor editor = getActivity().getSharedPreferences("User", MODE_PRIVATE).edit();
        editor.putString("gender", "Male");
        editor.apply();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.radioSex);

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                {
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        // checkedId is the RadioButton selected

                        switch(checkedId) {
                            case R.id.radioMale:
                                editor.putString("gender", "Male");
                                editor.apply();
                                Toast.makeText(getContext(), "Male", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.radioFemale:
                                editor.putString("gender", "Female");
                                editor.apply();
                                Toast.makeText(getContext(), "Female", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                /*SharedPreferences.Editor editor = getActivity().getSharedPreferences("User", MODE_PRIVATE).edit();
                editor.putString("gender", "");
                editor.apply();*/

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
