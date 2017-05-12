package levantat.com.lt034;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LT034_config extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lt034_config);
    }
    // "Send text back" button click
    public void onButtonClick(View view) {

        // get the text from the EditText
        EditText editText = (EditText) findViewById(R.id.txt_destino);
        String stringToPassBack = editText.getText().toString();

        // put the String to pass back into an Intent and close this activity
        Intent intent = new Intent();
        intent.putExtra("direccion", stringToPassBack);
        setResult(RESULT_OK, intent);
        finish();
    }
}
