package com.example.musec.Interfaces;

import com.example.musec.Models.InstrumentEntity;

import java.util.ArrayList;


public interface FormInterface {
    public interface View{
        void CloseFormActivity();
        void alertDelete();
        void selectPicture();
        void showError();
        void IntentChooser();
        void StartHelpActivity();

    }
    public interface Presenter{
        void onClickSaveButton(InstrumentEntity instrument);
        public String getError(int error_code);
        void onClickDeleteButton();
        void clicAcceptDelete();
        void onClickHelpButton();
        void onClickImage();
        public ArrayList<String> getStats();
        void PermissionGranted();
        void delete(InstrumentEntity instru);
        void PermissionDenied();
        public InstrumentEntity getbyid(String id);
        ArrayList<InstrumentEntity> getAllSummarize();
    }
}
