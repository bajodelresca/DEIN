package com.example.musec.Views;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import com.example.musec.Interfaces.FormInterface;
import com.example.musec.Models.InstrumentEntity;
import com.example.musec.Presenters.FormPresenter;
import com.example.musec.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class FormActivity extends AppCompatActivity implements FormInterface.View {
    String TAG = "Musec/FormActivity";
    private FormInterface.Presenter presenter;
    private TextInputLayout nameTIL;
    private TextInputEditText nameET;
    private TextInputLayout descriptionTIL;
    private TextInputEditText descriptionET;
    private TextInputLayout priceTIL;
    private TextInputEditText priceET;
    private TextInputLayout dateTIL;
    private TextInputEditText dateET;
    private InstrumentEntity instrument;
    private String id;
    private String Name;
    private String Description;
    private String Price;
    private String Date;
    private String Image;
    public String state;
    public boolean bag;
    private static final int REQUEST_CAPTURE_IMAGE = 200;
    private static final int REQUEST_SELECT_IMAGE = 201;
    private Uri uri;
    final private int CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 123;
    private Context myContext;
    private ConstraintLayout constraintLayoutFormActivity;




    private EditText editTextDate;
    private ImageButton buttonDate;
    private ImageButton buttonadd;
    private Calendar calendar ;
    private DatePickerDialog datePickerDialog ;
    private  int Year, Month, Day ;
    ImageView img;
    CheckBox checked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        checked = (CheckBox) findViewById(R.id.checkBox2);
        constraintLayoutFormActivity = findViewById(R.id.Guardar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(R.string._NuevoInstrumento);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Asignar la acción necesaria. En este caso "volver atrás"
                    onBackPressed();
                }
            });
        } else {
            Log.d("SobreNosotros", "Error al cargar toolbar");
        }
        presenter=new FormPresenter(this);


        instrument = new InstrumentEntity();

        nameET = findViewById(R.id.NameET);
        nameTIL = findViewById(R.id.NameTIL);

        nameET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (instrument.setName(nameET.getText().toString()) == false ) {
                        nameET.setError(presenter.getError(1));
                    } else {
                        nameTIL.setError(null);
                        nameTIL.setBoxStrokeColor(Color.GREEN);
                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                }

            }
        });

        descriptionET = findViewById(R.id.DescriptionET);
        descriptionTIL = findViewById(R.id.dateeTIL);

        descriptionET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (instrument.setDescription(descriptionET.getText().toString()) == false ) {
                        descriptionET.setError(presenter.getError(2));
                    } else {
                        descriptionTIL.setError(null);
                        descriptionTIL.setBoxStrokeColor(Color.GREEN);
                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                }

            }
        });


        priceET = findViewById(R.id.PriceET);
        priceTIL = findViewById(R.id.PriceTIL);

        priceET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (instrument.setPrice(priceET.getText().toString()) == false ) {
                        priceET.setError(presenter.getError(3));
                    } else {
                        priceTIL.setError(null);
                        priceTIL.setBoxStrokeColor(Color.GREEN);
                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                }

            }
        });

        dateET = findViewById(R.id.DateET);
        dateTIL = findViewById(R.id.DateTIL);

        dateET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("FormActivity", "Exit EditText");
                    if (instrument.setDate(dateET.getText().toString()) == false ) {
                        dateET.setError(presenter.getError(4));
                    } else {
                        dateTIL.setError(null);
                        dateET.setError(null);
                        dateTIL.setBoxStrokeColor(Color.GREEN);
                    }
                }else{
                    Log.d("FormActivity", "Input EditText");
                }

            }
        });
        buttonadd = (ImageButton)findViewById(R.id.ButtonAdd);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> estado =new ArrayList();
        ArrayList<InstrumentEntity> iEnt=presenter.getAllSummarize();
        for(InstrumentEntity i: iEnt){
            if(!estado.contains(presenter.getbyid(i.getId()).getState())){
                estado.add(presenter.getbyid(i.getId()).getState());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, estado);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Recuperación de la vista del AlertDialog a partir del layout de la Actividad
                LayoutInflater layoutActivity = LayoutInflater.from(myContext);
                View viewAlertDialog = layoutActivity.inflate(R.layout.alert_dialog, null);

                // Definición del AlertDialog
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(myContext);

                // Asignación del AlertDialog a su vista
                alertDialog.setView(viewAlertDialog);

                // Recuperación del EditText del AlertDialog
                final EditText dialogInput = (EditText) viewAlertDialog.findViewById(R.id.messageET);

                // Configuración del AlertDialog
                alertDialog
                        .setCancelable(false)
                        // Botón Añadir
                        .setPositiveButton(getResources().getString(R.string.confirm),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        if(!dialogInput.getText().toString().isEmpty()){
                                            adapter.add(dialogInput.getText().toString());
                                            spinner.setSelection(adapter.getPosition(dialogInput.getText().toString()));
                                        }else{
                                            Toast.makeText(getApplicationContext(),presenter.getError(5),Toast.LENGTH_LONG).show();
                                        }
                                    }
                                })
                        // Botón Cancelar
                        .setNeutralButton(getResources().getString(R.string.cancel),
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                })
                        .create()
                        .show();
            }

        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                instrument.setState(estado.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ImageView buttonGallery = (ImageView) findViewById(R.id.imageView4);
        buttonGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickImage();
            }
        });

        deleteIMG();

        myContext = this;
        // Obtener la fecha actual
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR) ;
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        editTextDate = (EditText)findViewById(R.id.DateET);

        // Definir la acción del botón para abrir el calendario
        buttonDate = (ImageButton)findViewById(R.id.DateButton);
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Definir el calendario con la fecha seleccionada por defecto
                datePickerDialog = new DatePickerDialog(myContext, new DatePickerDialog.OnDateSetListener() {
                    // Definir la acción al pulsar OK en el calendario
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        // Asignar la fecha a un campo de texto
                        editTextDate.setText(String.valueOf(day) + "/" + String.valueOf(month+1) + "/" + String.valueOf(year));
                    }
                },Year, Month, Day);
                // Mostrar el calendario
                datePickerDialog.show();
            }
        });
        Button delete=(Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickDeleteButton();
            }
        });
        id=getIntent().getStringExtra("id");

        if(id!=null){
            InstrumentEntity instru=presenter.getbyid(id);
            instrument.setId(id);

            nameET.setText(instru.getName());
            descriptionET.setText(instru.getDescription());
            dateET.setText(instru.getDate());
            priceET.setText(instru.getPrice());
            checked.setChecked(instru.isBag());
            Image=instru.getImage();
            byte[] decodedString = Base64.decode(Image, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            buttonGallery.setImageBitmap(decodedByte);
            spinner.setSelection(adapter.getPosition(instru.getState()));

            //Recupero la info de esa entidad
        }else{
            Button delete2 = (Button) findViewById(R.id.delete);
            delete2.setEnabled(false);
        }
        img = (ImageView) findViewById(R.id.imageView4);
        Button save=(Button) findViewById(R.id.button4);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(instrument.setName(nameET.getText().toString())&&
                        instrument.setDescription(descriptionET.getText().toString())&&
                        instrument.setPrice(priceET.getText().toString())&&
                        instrument.setDate(dateET.getText().toString())){

                    instrument.setBag(checked.isChecked());



                    if(buttonGallery!=null&&buttonGallery.getDrawable()!=null){
                        Bitmap bitmap = ((BitmapDrawable) buttonGallery.getDrawable()).getBitmap();
                        if(bitmap!=null){
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            String fotoEnBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                            instrument.setImage(fotoEnBase64);
                        }
                    }

                    instrument.setState(spinner.getSelectedItem().toString());
                    presenter.onClickSaveButton(instrument);
                    Log.d("prueba",instrument.toString());








                }else {
                    Toast.makeText(getApplicationContext(),presenter.getError(6),Toast.LENGTH_LONG).show();

                }

                Log.d("prueba",instrument.toString());

            }
        });


    }
    @Override
    public void selectPicture(){
        // Se le pide al sistema una imagen del dispositivo
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, getResources().getString(R.string.choose_picture)),
                REQUEST_SELECT_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case (REQUEST_CAPTURE_IMAGE):
                if (resultCode == Activity.RESULT_OK) {
                    // Se carga la imagen desde un objeto URI al imageView
                    ImageView imageView = findViewById(R.id.imageView4);
                    imageView.setImageURI(uri);

                    // Se le envía un broadcast a la Galería para que se actualice
                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    mediaScanIntent.setData(uri);
                    sendBroadcast(mediaScanIntent);

                } else if (resultCode == Activity.RESULT_CANCELED) {
                    // Se borra el archivo temporal
                    File file = new File(uri.getPath());
                    file.delete();
                }
                break;

            case (REQUEST_SELECT_IMAGE):
                if (resultCode == Activity.RESULT_OK) {
                    // Se carga la imagen desde un objeto Bitmap
                    Uri selectedImage = data.getData();
                    String selectedPath = selectedImage.getPath();

                    if (selectedPath != null) {
                        // Se leen los bytes de la imagen
                        InputStream imageStream = null;
                        try {
                            imageStream = getContentResolver().openInputStream(selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        // Se transformam los bytes de la imagen a un Bitmap
                        Bitmap bmp = BitmapFactory.decodeStream(imageStream);

                        // Se carga el Bitmap en el ImageView
                        Bitmap imageScaled = Bitmap.createScaledBitmap(bmp, 200, 200, false);
                        ImageView imageView = findViewById(R.id.imageView4);
                        imageView.setImageBitmap(imageScaled);
                    }
                }
                break;
        }
    }
    @Override
    public void IntentChooser(){
        ActivityCompat.requestPermissions(FormActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
    }

    @Override
    public void StartHelpActivity() {
        Log.d(TAG, "Starting HelpActivity");
        Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
        intent.putExtra("help", "form");
        startActivityForResult(intent, 0);
    }

    @Override
    public void showError(){
        Snackbar.make(findViewById(android.R.id.content), getResources().getString(R.string.write_permission_denied), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CODE_WRITE_EXTERNAL_STORAGE_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.PermissionGranted();
                } else {
                    presenter.PermissionDenied();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @Override
    public boolean onNavigateUp() {
        // Asignar la acción necesaria. En este caso terminar la actividad
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_help) {
            presenter.onClickHelpButton();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void CloseFormActivity() {
        finish();
    }
    @Override
    public void alertDelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);
        builder.setTitle(R.string.deletequest);

        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.clicAcceptDelete();
                presenter.delete(instrument);
                // Toast.makeText(getApplicationContext(),"Yes button Clicked", Toast.LENGTH_LONG).show();
                Log.i("Code2care ", "Yes button Clicked!");
            }
        });

        builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //    Toast.makeText(getApplicationContext(),"Cancel button Clicked",Toast.LENGTH_LONG).show();
                Log.i("Code2care ","Cancel button Clicked!");
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void deleteIMG(){
        Button deleteimg = (Button) findViewById(R.id.button5);
        deleteimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView buttonGallery = findViewById(R.id.imageView4);
                buttonGallery.setImageBitmap(null);
            }
        });
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "Starting onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "Starting onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "Starting onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Starting onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Log.d(TAG, "Starting onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "Starting onDestroy");
        super.onDestroy();
    }
}