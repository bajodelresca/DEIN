package com.example.musec.Presenters;



import com.example.musec.Interfaces.SearchInterface;
import com.example.musec.Models.InstrumentModel;

import java.util.ArrayList;

public class SearchPresenter implements SearchInterface.Presenter {
    private InstrumentModel instrumentModel;
    private SearchInterface.View view;
    public SearchPresenter (SearchInterface.View view) {
        this.view=view;
        this.instrumentModel=new InstrumentModel();
    }
    @Override
    public void onClickSaveButton() {
        view.CloseSearchActivity();
    }
    @Override
    public ArrayList<String> getStats() {
        return instrumentModel.getStats();
    }
}
