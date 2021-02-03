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
    public boolean delete(InstrumentEntity i){
        boolean result=false;
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(r -> {
            InstrumentEntity in = realm.where(InstrumentEntity.class)
                    .equalTo("id",i.getId())
                    .findFirst();

            in.deleteFromRealm();
        });

        realm.close();
        return result;

    }
    public boolean insert(InstrumentEntity i){
        boolean result=false;
        Realm realm = Realm.getDefaultInstance();
        if(i.getId().equals("")){

            realm.executeTransaction(r -> {

                i.setId(UUID.randomUUID().toString());
                realm.copyToRealm(i);

            });
            Log.d("DemoAndroidRealm", "Path: " + realm.getPath());
            result=true;

            realm.close();


        }
        else{
            realm.executeTransaction(r -> {

                realm.copyToRealmOrUpdate(i);

            });
            Log.d("DemoAndroidRealm", "Path: " + realm.getPath());

            result = true;
            realm.close();

        }
        return result;
    }
    public InstrumentEntity getbyid(String id){
        Realm realm=Realm.getDefaultInstance();
        InstrumentEntity ientity=realm.where(InstrumentEntity.class).equalTo("id",id).findFirst();
        InstrumentEntity instrument=new InstrumentEntity();
        instrument=realm.copyFromRealm(ientity);
        realm.close();
        return instrument;
    }

    public ArrayList<InstrumentEntity> getWithFilter(String name, String date, String state){
        Realm realm = Realm.getDefaultInstance();

        RealmResults<InstrumentEntity> result;



            result = realm.where(InstrumentEntity.class).contains("Name", name).contains("Date", date).contains("state", state).findAll();


        ArrayList<InstrumentEntity> bList = new ArrayList<>();
        bList.addAll(realm.copyFromRealm(result));

        realm.close();

        ArrayList<InstrumentEntity> InstruListSummirize= new ArrayList<>();
        for (InstrumentEntity b: bList) {
            InstruListSummirize.add(new InstrumentEntity(b.getId(),b.getName(),b.getDescription(),b.getImage()));
        }
        return InstruListSummirize;
    }

    public ArrayList<String> getStats(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<InstrumentEntity> c=realm.where(InstrumentEntity.class).distinct("state").findAll();
        ArrayList<String> result=new ArrayList<>();
        for (InstrumentEntity b : c){
            result.add(b.getState());
        }
        realm.close();
        return result;
    }
}
