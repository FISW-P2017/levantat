package com.levantat.anton.levantat;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by anton on 11/05/2017.
 */

public class PopUp extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        //getWindow().setLayout((int)(width*.8), (int)(height*.8));

        addRadioButtons(getRingtones());
        setRingtone(1);     //DEFAULT is 1

        addListenerToButton();
    }

    public void addListenerToButton(){
        Button b = (Button) findViewById(R.id.buttonSaveRingTone);
        final RadioGroup rg = new RadioGroup(this);
        b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // get selected radio button from radioGroup
                int selectedId = rg.getCheckedRadioButtonId();
                setRingtone(selectedId);
                finish();
            }
        });
    }

    public void addRadioButtons(String[] RingTones){
        RadioGroup rg = new RadioGroup(this);
        rg.setOrientation(LinearLayout.VERTICAL);
        int i = 0;
        //Checar si for sintaxis es correcta.
        for (String s: RingTones
             ) {
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(i);
            /**/rdbtn.setText(s);
            rg.addView(rdbtn);
            i++;
        }
        ((ViewGroup) findViewById(R.id.radiogroup)).addView(rg);
    }

    public void setRingtone(int id){
        //This method must set the ringtone "phisically"? for the alarm, getting it from the
        //system in order to be reproduced.
    }

    public String[] getRingtones(){
        //This method needs to retrieve the ringtone pool from somewhere in the app...
        String[] RingTones = {
                "Bell",
                "Bee",
                "Classic",
                "Radiant",
                "Piano"
        };
        return  RingTones;
    }
    //TODO:
    /*
     * Llamar Método que substraiga ringtones como si fueran del telefono en strings
     * Método que imprima con radius button los ringtones con strings
     * Clase de java que tenga método de obtener ringtones pero que solo mande un arreglo prehecho
     * Cuando usuario seleccione un ringtone deberá guardarse su selección y mandar llamar un
     *  método ficticio que actualice ringtone.
     * Subir a Git Repository.
     *
     * http://stackoverflow.com/questions/19380526/how-to-add-radio-button-dynamically-as-per-the-given-number-of-counts
     */

}
