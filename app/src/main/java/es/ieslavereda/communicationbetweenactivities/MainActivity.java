package es.ieslavereda.communicationbetweenactivities;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText textInputEditText;
    private final int ACTIVITY_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);
        textInputEditText = findViewById(R.id.text_to_send);

        // Metodo deprecated
        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext() , SecondActivity.class);
            intent.putExtra("text",textInputEditText.getText().toString());
            startActivityForResult(intent,ACTIVITY_CODE);
        });

        // Nuevo metodo
        ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    // No es necesario identificar la actividad que envia resultados
                    if(result.getResultCode() == RESULT_CANCELED)
                        Toast.makeText(this, "Cancelado por el usuario", Toast.LENGTH_LONG).show();
                    else if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String name = data.getExtras().getString("name");
                        Toast.makeText(this, "Nuevo, aceptado por " + name , Toast.LENGTH_LONG).show();
                    }
                });

        button2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
            intent.putExtra("text",textInputEditText.getText().toString());
            someActivityResultLauncher.launch(intent);
        });
    }

    // Metodo deprecated
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Identificamos la actividad que devuelve resultados
        if(requestCode == ACTIVITY_CODE ){
            if(resultCode == RESULT_CANCELED)
                Toast.makeText(this, "Cancelado por el usuario", Toast.LENGTH_LONG).show();
            else if (resultCode == RESULT_OK){

                String name = data.getExtras().getString("name");
                Toast.makeText(this, "Aceptado por " + name, Toast.LENGTH_LONG).show();
            }
        }
    }
}