package com.example.osamanadeem.seekhloo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class signup_frag_name extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.signup_frag_name,container,false);
        Button btn = (Button) v.findViewById(R.id.su_name_btn);
        final EditText fname = v.findViewById(R.id.signup_Fname),lname = v.findViewById(R.id.signup_Lname);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = getActivity().getSharedPreferences("User", MODE_PRIVATE).edit();
                editor.putString("fname", fname.getText().toString());
                editor.putString("lname", lname.getText().toString());
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


    public interface FragmentChangeListener
    {
        public void replaceFragment(Fragment fragment);
    }
    public void showOtherFragment()
    {

        Fragment fr=new signup_frag_birthday();
        FragmentChangeListener fc=(FragmentChangeListener)getActivity();
        fc.replaceFragment(fr);
    }
}
