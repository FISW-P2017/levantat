package com.example.fernando.levantat_lt003;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class datosDireccion extends AppCompatActivity {

    EditText direccionEdit, direccionText;
    Button editarButton, guardarButton;
    int id;
    SharedPreferences sharedPref;
    String nombresReferencia, direccionesReferencia;
    String[] nombres, direcciones;
    String nombre = "";
    String direccion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_direccion);

        direccionEdit = (EditText)findViewById(R.id.direccionEdit);
        direccionText = (EditText) findViewById(R.id.direccionText);
        editarButton = (Button)findViewById(R.id.editarButton);
        guardarButton = (Button)findViewById(R.id.guardarButton);

        sharedPref = getSharedPreferences("levantaT", MODE_PRIVATE);


        nombresReferencia = sharedPref.getString("nombres", "");
        direccionesReferencia = sharedPref.getString("direcciones", "");

        nombres = nombresReferencia.split(",");
        direcciones = direccionesReferencia.split(",");

        Intent intent = getIntent();
        id = intent.getIntExtra("index", -1);


        if(id == -1){
            direccion = "";
            nombre = "";
        }
        else{

            direccion = direcciones[id];
            nombre = nombres[id];

        }

        editarButton.setOnClickListener(editarButtonOnClickListener);
        guardarButton.setOnClickListener(guardarButtonOnClickListener);



        direccionEdit.setText(nombre);
        direccionText.setText(direccion);
    }

    View.OnClickListener editarButtonOnClickListener =
            new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                    intent.putExtra("nombre", direccionEdit.getText().toString());
                    intent.putExtra("direccion", direccionText.getText().toString());
                    startActivity(intent);
                }
            };

    View.OnClickListener guardarButtonOnClickListener =
            new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    String regresar = "";
                    double longitude = 0, latitude = 0;

                    if(direccionText.getText().toString().equals("") || direccionEdit.getText().toString().equals(""))
                        regresar = "No dejes datos vacíos";
                    else {

                        Geocoder coder = new Geocoder(getBaseContext());
                        try {
                            ArrayList<Address> adresses = (ArrayList<Address>) coder.getFromLocationName(direccionText.getText().toString(), 50);
                            for (Address add : adresses) {
                                longitude = add.getLongitude();
                                latitude = add.getLatitude();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (latitude != 0 || longitude != 0){

                            if (id == -1) {
                                ArrayList<String> temporalNombres = new ArrayList<String>(Arrays.asList(nombres));
                                ArrayList<String> temporalDirecciones = new ArrayList<String>(Arrays.asList(direcciones));
                                temporalNombres.add(direccionEdit.getText().toString());
                                temporalDirecciones.add(direccionText.getText().toString());
                                nombres = temporalNombres.toArray(new String[nombres.length + 1]);
                                direcciones = temporalDirecciones.toArray(new String[direcciones.length + 1]);

                            } else {

                                for(int j = 0; j < nombres.length; j++){
                                    if(nombres[j].equals(direccionEdit.getText().toString()) && j != id){
                                        regresar = "Este nombre ya existe";
                                        j = nombres.length + 1;
                                    }
                                }

                                for(int j = 0; j < direcciones.length; j++){
                                    if(direcciones[j].equals(direccionText.getText().toString()) && j != id){
                                        regresar = "Esta dirección ya existe";
                                        j = direcciones.length + 1;
                                    }
                                }

                                if(regresar.equals("")) {

                                    nombres[id] = direccionEdit.getText().toString();
                                    direcciones[id] = direccionText.getText().toString();

                                }
                            }
                        }
                        else
                            regresar = "La dirección que ingresaste no es válida";
                    }

                    if(regresar.equals("")) {

                        String stringNormalNombres = nombres[0], stringNormalDirecciones = direcciones[0];

                        for(int j = 1; j < nombres.length; j++)
                            stringNormalNombres = stringNormalNombres + "," + nombres[j];

                        for(int j = 1; j < direcciones.length; j++)
                            stringNormalDirecciones = stringNormalDirecciones + "," + direcciones[j];

                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("nombres", stringNormalNombres);
                        editor.putString("direcciones", stringNormalDirecciones);
                        editor.apply();
                    }
                    else
                        Toast.makeText(getApplicationContext(),
                                regresar, Toast.LENGTH_LONG)
                                .show();

                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                }
            };
}
