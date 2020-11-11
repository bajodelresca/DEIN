package com.example.musec.Presenters;

import com.example.musec.Interfaces.FormInterface;
import com.example.musec.Interfaces.ListInterface;

public class FormPresenter implements FormInterface.Presenter {
    private FormInterface.View view;
    public FormPresenter (FormInterface.View view) {this.view=view;}

    @Override
    public void onClickSaveButton() {
        view.CloseFormActivity();
    }
}
