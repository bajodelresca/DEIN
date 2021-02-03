package com.example.musec.Presenters;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.musec.Interfaces.FormInterface;
import com.example.musec.Interfaces.ListInterface;
import com.example.musec.Models.InstrumentEntity;
import com.example.musec.Models.InstrumentModel;
import com.example.musec.R;
import com.example.musec.Views.MyApplication;

import java.util.ArrayList;

public class FormPresenter implements FormInterface.Presenter {
    private FormInterface.View view;
    private InstrumentModel instrumentModel;
    public FormPresenter (FormInterface.View view) {this.view=view;
        this.instrumentModel=new InstrumentModel();}

    @Override
    public void onClickSaveButton(InstrumentEntity instrument) {
        if (instrumentModel.insert(instrument)){
            view.CloseFormActivity();
        } else {

        }


    }
    @Override
    public String getError(int error_code) {
        String error_msg = "";
        switch (error_code) {
            case 1:
                error_msg = MyApplication.getContext().getResources().getString(R.string.ErrorName);
                break;
            case 2:
                error_msg = MyApplication.getContext().getResources().getString(R.string.ErrorDescription);
                break;
            case 3:
                error_msg = MyApplication.getContext().getResources().getString(R.string.ErrorPrice);
                break;
            case 4:
                error_msg = MyApplication.getContext().getResources().getString(R.string.ErrorDate);
                break;
            case 5:
                error_msg = MyApplication.getContext().getResources().getString(R.string.ErrorAdd);
                break;
            case 6:
                error_msg = MyApplication.getContext().getResources().getString(R.string.Errorinsert);
                break;
            default:
                error_msg = "";
        }
        return error_msg;
    }

    @Override
    public void onClickDeleteButton() {
        view.alertDelete();
    }

    @Override
    public void clicAcceptDelete() {
        view.CloseFormActivity();
    }



    @Override
    public void PermissionGranted() {
        view.selectPicture();
    }

    @Override
    public void delete(InstrumentEntity instru) {
        instrumentModel.delete(instru);
    }

    @Override
    public void PermissionDenied() {
        view.showError();
    }

    @Override
    public void onClickImage() {
        int WriteExternalStoragePermission = ContextCompat.checkSelfPermission(MyApplication.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Log.d("FormPresenter", "WRITE_EXTERNAL_STORAGE Permission: " + WriteExternalStoragePermission);

        if (WriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            // Permiso denegado
            // A partir de Marshmallow (6.0) se pide aceptar o rechazar el permiso en tiempo de ejecución
            // En las versiones anteriores no es posible hacerlo
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                view.IntentChooser();
                // Una vez que se pide aceptar o rechazar el permiso se ejecuta el método "onRequestPermissionsResult" para manejar la respuesta
                // Si el usuario marca "No preguntar más" no se volverá a mostrar este diálogo
            } else {
                view.showError();
            }
        } else {
            // Permiso aceptado
            view.selectPicture();
        }
    }

    @Override
    public ArrayList<String> getStats() {
        return instrumentModel.getStats();
    }

    @Override
    public ArrayList<InstrumentEntity> getAllSummarize() {
        return instrumentModel.getAllSummarize();
    }
    @Override
    public InstrumentEntity getbyid(String id) {
        return instrumentModel.getbyid(id);
    }
}

