package com.example.musec.Presenters;



import com.example.musec.Interfaces.SearchInterface;

public class SearchPresenter implements SearchInterface.Presenter {
    private SearchInterface.View view;
    public SearchPresenter (SearchInterface.View view) {this.view=view;}
    @Override
    public void onClickSaveButton() {
        view.CloseSearchActivity();
    }
}
