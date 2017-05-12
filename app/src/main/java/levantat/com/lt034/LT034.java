package levantat.com.lt034;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static levantat.com.lt034.R.id.lbl_destino;

public class LT034 extends AppCompatActivity {


    private static final int CODE_RESULT = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lt034);
    }

    // Goes to the second activity
    public void configuracion(View view){
        // Starts the second activity
        Intent intent = new Intent(this, LT034_config.class);
        startActivityForResult(intent, CODE_RESULT);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        // Check that the second activity it's ok
        if(requestCode==CODE_RESULT){
            if(resultCode==RESULT_OK){
                // Get string data from intent
                String returnString = data.getStringExtra("direccion");

                // Set the textview with the string
                TextView textView = (TextView) findViewById(R.id.lbl_destino);
                textView.setText(returnString);
            }
        }
    }
}
