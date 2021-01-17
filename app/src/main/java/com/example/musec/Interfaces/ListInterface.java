package com.example.musec.Interfaces;

import com.example.musec.Models.InstrumentEntity;

import java.util.ArrayList;

public interface ListInterface {
    public interface View{
        void StartFormActivity();
        void StartAboutActivity();
        void StartSearchActivity();
        void StartFormActivity(String id);
        }
    public interface Presenter{
        void onClickFloatingButton();
        void onClickAboutButton();
        void onClickSearchButton();
        void onClickRecyclerViewItem(String id);
        public String getError(int error_code);
        ArrayList<InstrumentEntity> getAllSummarize();
    }
}
