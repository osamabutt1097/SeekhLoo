package com.example.osamanadeem.seekhloo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class signup_frag_password extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.signup_frag_password,container,false);
        Button btn = v.findViewById(R.id.su_password_btn);
        final EditText text = v.findViewById(R.id.signup_Password);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("User", MODE_PRIVATE).edit();
                editor.putString("password", text.getText().toString());
                editor.apply();
                startActivity(new Intent(getActivity(),LoginActivity.class));
                getActivity().finish();
            }
        });
        return v;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
