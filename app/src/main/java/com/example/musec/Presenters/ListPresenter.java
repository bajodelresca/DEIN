package com.example.musec.Presenters;

import com.example.musec.Interfaces.ListInterface;

public class ListPresenter implements ListInterface.Presenter {
    private ListInterface.View view;
    public ListPresenter (ListInterface.View view) {
        this.view = view;
            }
    @Override
    public void onClickFloatingButton() {
        //log.d("");
        view.startFormActivity();
    }
}
