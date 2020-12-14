package com.example.musec.Presenters;

import android.util.Log;

import com.example.musec.Interfaces.FormInterface;
import com.example.musec.Interfaces.ListInterface;
import com.example.musec.R;
import com.example.musec.Views.MyApplication;

public class FormPresenter implements FormInterface.Presenter {
    private FormInterface.View view;
    public FormPresenter (FormInterface.View view) {this.view=view;}

    @Override
    public void onClickSaveButton() {
        view.CloseFormActivity();
    }
    @Override
    public String getError(int error_code) {
        String error_msg = "";
        switch (error_code) {
            case 1:
                error_msg = MyApplication.getContext().getResources().getString(R.string.ErrorName);
                break;
            case 2:
                error_msg = MyApplication.getContext().getResources().getString(R.string.ErrorDescription);
                break;
            case 3:
                error_msg = MyApplication.getContext().getResources().getString(R.string.ErrorPrice);
                break;
            case 4:
                error_msg = MyApplication.getContext().getResources().getString(R.string.ErrorDate);
                break;
            case 5:
                error_msg = MyApplication.getContext().getResources().getString(R.string.ErrorAdd);
                break;
            default:
                error_msg = "";
        }
        return error_msg;
    }

    @Override
    public void onClickDeleteButton() {
        view.alertDelete();
    }

    @Override
    public void clicAcceptDelete() {
        view.CloseFormActivity();
    }
}

