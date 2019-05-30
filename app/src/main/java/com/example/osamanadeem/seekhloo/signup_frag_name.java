package com.example.osamanadeem.seekhloo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class signup_frag_name extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v =inflater.inflate(R.layout.signup_frag_name,container,false);
        Button btn = (Button) v.findViewById(R.id.su_name_btn);
        final EditText fname = v.findViewById(R.id.signup_Fname),lname = v.findViewById(R.id.signup_Lname);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!fname.getText().toString().isEmpty() || !lname.getText().toString().isEmpty() ) {
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("User", MODE_PRIVATE).edit();

                    editor.putString("fname", fname.getText().toString());
                    editor.putString("lname", lname.getText().toString());
                    editor.apply();
                    showOtherFragment();
                }

                else
                {
                    final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                    Snackbar.make(v, "Invalid Entries", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        return v;

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public interface FragmentChangeBirthday
    {
        public void replaceBirthday(Fragment fragment);
    }
    public void showOtherFragment()
    {

        Fragment fr=new signup_frag_birthday();
        FragmentChangeBirthday fc=(FragmentChangeBirthday)getActivity();
        fc.replaceBirthday(fr);
    }
}
