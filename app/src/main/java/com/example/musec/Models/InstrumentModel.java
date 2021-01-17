package com.example.musec.Models;

import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class InstrumentModel {
    public ArrayList<InstrumentEntity> getAllSummarize(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<InstrumentEntity> result=realm.where(InstrumentEntity.class).findAll();
        ArrayList<InstrumentEntity> rList= new ArrayList<>();
        rList.addAll(realm.copyFromRealm(result));
        realm.close();
        ArrayList<InstrumentEntity> iListSummirize= new ArrayList<>();
        for (InstrumentEntity b: rList) {
            iListSummirize.add(new InstrumentEntity(b.getId(),b.getName(),b.getDescription(),b.getImage()));
        }
        return iListSummirize;
    }
    public boolean insert(InstrumentEntity i){
        boolean result=true;
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(r -> {

            i.setId(UUID.randomUUID().toString());
            realm.copyToRealm(i);

        });
        Log.d("DemoAndroidRealm", "Path: " + realm.getPath());

        realm.close();


        return result;
    }
}
