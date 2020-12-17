package com.example.musec.Presenters;

import com.example.musec.Interfaces.ListInterface;
import com.example.musec.R;
import com.example.musec.Views.MyApplication;

public class ListPresenter implements ListInterface.Presenter {
    private ListInterface.View view;

    public ListPresenter(ListInterface.View view) {
        this.view = view;
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
    }

