package com.levantat.anton.levantat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LT061 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lt061);

        Button b = (Button) findViewById(R.id.button_ringTone);
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(LT061.this, PopUp.class));
            }
        });
    }
}
