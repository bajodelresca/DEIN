package com.example.musec.Interfaces;

public interface ListInterface {
    public interface View{
        void StartFormActivity();
        void StartAboutActivity();
        void StartSearchActivity();
        }
    public interface Presenter{
        void onClickFloatingButton();
        void onClickAboutButton();
        void onClickSearchButton();
    }
}
