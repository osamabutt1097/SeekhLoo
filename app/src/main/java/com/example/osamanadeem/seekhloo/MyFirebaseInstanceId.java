package com.example.osamanadeem.seekhloo;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.HashMap;

public class MyFirebaseInstanceId extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Students").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        String token = FirebaseInstanceId.getInstance().getToken();
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",token);
        ref.updateChildren(map);

    }
}
