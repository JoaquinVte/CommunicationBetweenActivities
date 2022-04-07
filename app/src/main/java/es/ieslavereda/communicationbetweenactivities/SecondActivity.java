package es.ieslavereda.communicationbetweenactivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String text = getIntent().getExtras().getString("text","nothing sent!");

        Button aceptar = findViewById(R.id.button_aceptar);
        Button cancelar = findViewById(R.id.button_cancelar);
        name = findViewById(R.id.name);

        name.setText(text);

        cancelar.setOnClickListener(view -> {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED,intent);
            finish();
        });
        aceptar.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("name",name.getText().toString());
            setResult(RESULT_OK,intent);
            finish();
        });
    }
}