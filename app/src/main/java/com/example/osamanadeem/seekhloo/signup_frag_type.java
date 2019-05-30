package com.example.osamanadeem.seekhloo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class signup_frag_type extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.signup_frag_type,container,false);
        Button tutor = v.findViewById(R.id.su_type_tutor),student=v.findViewById(R.id.su_type_student);

        tutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("User", MODE_PRIVATE).edit();
                editor.putString("type","tutor");
                editor.apply();
                showOtherFragment();
            }
        });


        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("User", MODE_PRIVATE).edit();
                editor.putString("type","student");
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


    public interface FragmentChangeName
    {
        public void replaceName(Fragment fragment);
    }
    public void showOtherFragment()
    {

        Fragment fr=new signup_frag_birthday();
        signup_frag_type.FragmentChangeName fc=(signup_frag_type.FragmentChangeName)getActivity();
        fc.replaceName(fr);
    }
}
