package com.example.musec.Models;

import android.util.Log;

import java.util.UUID;

import io.realm.Realm;

public class InstrumentModel {
    public boolean insert(InstrumentEntity i){
        boolean result=true;
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(r -> {

            i.setId(UUID.randomUUID().toString());
            realm.copyToRealm(i);
            Log.d("DemoAndroidRealm", "Path: " + realm.getPath());
        });

        realm.close();


        return result;
    }
}
