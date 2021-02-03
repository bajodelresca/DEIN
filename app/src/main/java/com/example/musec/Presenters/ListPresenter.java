package com.example.musec.Presenters;

import com.example.musec.Interfaces.ListInterface;
import com.example.musec.Models.InstrumentEntity;
import com.example.musec.Models.InstrumentModel;
import com.example.musec.R;
import com.example.musec.Views.MyApplication;

import java.util.ArrayList;

public class ListPresenter implements ListInterface.Presenter {
    private ListInterface.View view;
    private InstrumentModel instrumentModel;

    public ListPresenter(ListInterface.View view) {
        this.view = view;
        this.instrumentModel=new InstrumentModel();
    }

    @Override
    public void onClickFloatingButton() {
        view.StartFormActivity();
    }

    @Override
    public void onClickAboutButton() {
        view.StartAboutActivity();
    }

    @Override
    public void onClickSearchButton() {
        view.StartSearchActivity();
    }

    @Override
    public InstrumentEntity getbyid(String id) {
        return instrumentModel.getbyid(id);
    }

    @Override
    public void delete(InstrumentEntity instru) {
        instrumentModel.delete(instru);
    }

    @Override
    public void onClickRecyclerViewItem(String id) {
        view.StartFormActivity(id);
    }
    @Override
    public String getError(int error_code) {
        String error_msg = "";
        switch (error_code) {
            case 1:
                error_msg = MyApplication.getContext().getResources().getString(R.string.Borrar);
                break;
            case 2:
                error_msg = MyApplication.getContext().getResources().getString(R.string.ErrorBorrar);
                break;
        }
        return error_msg;
    }

    @Override
    public ArrayList<InstrumentEntity> getAllSummarize() {
        return instrumentModel.getAllSummarize();
    }

    @Override
    public ArrayList<String> getStats() {
        return instrumentModel.getStats();
    }

    @Override
    public ArrayList<InstrumentEntity> getItemsFilter(String name, String date, String state) {
        return instrumentModel.getWithFilter(name, date, state);
    }
}



