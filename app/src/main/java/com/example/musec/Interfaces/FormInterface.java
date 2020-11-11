package com.example.musec.Interfaces;

public interface FormInterface {
    public interface View{
        void CloseFormActivity();
    }
    public interface Presenter{
        void onClickSaveButton();
    }
}
