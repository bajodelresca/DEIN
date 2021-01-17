package com.example.musec.Views;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();


        // Inicializar la base de datos sólo una vez por aplicación
        Realm.init(sContext);

        RealmConfiguration config = new RealmConfiguration
                .Builder()
                //.name("demoandroid.realm")
               // .directory(getExternalFilesDirs(null)[1])
                .allowQueriesOnUiThread(true)
                .allowWritesOnUiThread(true)
                .build();
        Realm.setDefaultConfiguration(config);
        Realm realm = Realm.getDefaultInstance();
        Log.d("Musec", "Path: " + realm.getPath());
        realm.close();

    }

    public static Context getContext() {
        return sContext;
    }
}
