package com.example.tapclap.repositry;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserRepository {

    public FirebaseUser getAuthInstance() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public String getUserName() {
        FirebaseUser user = getAuthInstance();
        return user.getDisplayName();
    }

    public String getUserUid() {
        FirebaseUser user = getAuthInstance();
        return user.getUid();
    }
}
