package com.example.musec.Interfaces;

import java.util.ArrayList;

public interface SearchInterface {
    public interface View{
        void CloseSearchActivity();
    }
    public interface Presenter{
        void onClickSaveButton();
        public ArrayList<String> getStats();
    }
}
