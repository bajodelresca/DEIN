package com.example.musec.Interfaces;

public interface FormInterface {
    public interface View{
        void CloseFormActivity();
        void alertDelete();
        void selectPicture();
        void showError();
        void IntentChooser();

    }
    public interface Presenter{
        void onClickSaveButton();
        public String getError(int error_code);
        void onClickDeleteButton();
        void clicAcceptDelete();
        void onClickImage();
        void PermissionGranted();
        void PermissionDenied();
    }
}
