package com.example.osamanadeem.seekhloo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class signup_frag_birthday extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.signup_frag_birthday,container,false);
        final DatePicker datePicker = v.findViewById(R.id.fragment_date_picker_control);
        Button btn = v.findViewById(R.id.su_birthday_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("User", MODE_PRIVATE).edit();
                editor.putString("dob",datePicker.getDayOfMonth()+"-"+(datePicker.getMonth()+1)+"-"+datePicker.getYear() );
                editor.apply();
                showOtherFragment();
            }
        });
        return v;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public interface FragmentChangeGender
    {
        public void replaceGender(Fragment fragment);
    }
    public void showOtherFragment()
    {

        Fragment fr=new signup_frag_gender();
        signup_frag_birthday.FragmentChangeGender fc=(signup_frag_birthday.FragmentChangeGender)getActivity();
        fc.replaceGender(fr);
    }
}
