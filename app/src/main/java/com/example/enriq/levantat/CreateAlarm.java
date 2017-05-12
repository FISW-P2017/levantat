package com.example.enriq.levantat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * Created by enriq on 5/12/2017.
 */

public class CreateAlarm extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alarm);
    }

    public void Guardar(View view) {
        Intent intent = new Intent(CreateAlarm.this, AddAlarm.class);
        startActivity(intent);
    }
}
