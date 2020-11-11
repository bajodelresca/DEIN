package com.example.musec.Views;

import android.content.Intent;
import android.os.Bundle;

import com.example.musec.Interfaces.FormInterface;
import com.example.musec.Presenters.FormPresenter;
import com.example.musec.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class FormActivity extends AppCompatActivity implements FormInterface.View {
    private FormInterface.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        presenter=new FormPresenter(this);

        Button save=(Button) findViewById(R.id.button4);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickSaveButton();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] estado = {"Nuevo","Usado"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estado));
    }


    @Override
    public void CloseFormActivity() {
        finish();
    }
}