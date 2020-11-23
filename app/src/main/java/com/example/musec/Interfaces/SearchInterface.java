package com.example.musec.Interfaces;

public interface SearchInterface {
    public interface View{
        void CloseSearchActivity();
    }
    public interface Presenter{
        void onClickSaveButton();
    }
}
