package com.example.musec.Interfaces;

public interface FormInterface {
    public interface View{
        void CloseFormActivity();
        void alertDelete();

    }
    public interface Presenter{
        void onClickSaveButton();
        public String getError(int error_code);
        void onClickDeleteButton();
    }
}
