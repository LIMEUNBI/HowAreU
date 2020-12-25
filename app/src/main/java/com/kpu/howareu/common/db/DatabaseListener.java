package com.kpu.howareu.common.db;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.kpu.howareu.common.utils.Users;

public class DatabaseListener {

    public ValueEventListener mValueEventListener;
    public static DatabaseListener instance;

    public static DatabaseListener getInstance() {
        if (instance == null) {
            instance = new DatabaseListener();
        }
        return instance;
    }

    public void userValueListener() {
        mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String key = postSnapshot.getKey();
                    Users users = postSnapshot.getValue(Users.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
    }
}
