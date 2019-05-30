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

public class signup_frag_email extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.signup_frag_email,container,false);
        final EditText text = v.findViewById(R.id.signup_Email);
        Button btn = v.findViewById(R.id.su_email_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), text.getText().toString(), Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = getActivity().getSharedPreferences("User", MODE_PRIVATE).edit();
                editor.putString("email", text.getText().toString());
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
    public interface FragmentChangePassword
    {
        public void replacePassword(Fragment fragment);
    }
    public void showOtherFragment()
    {
        Fragment fr=new signup_frag_email();
        signup_frag_email.FragmentChangePassword fc=(signup_frag_email.FragmentChangePassword)getActivity();
        fc.replacePassword(fr);
    }
}
