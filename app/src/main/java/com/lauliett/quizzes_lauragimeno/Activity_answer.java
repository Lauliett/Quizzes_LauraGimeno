package com.lauliett.quizzes_lauragimeno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Activity_answer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        TextView correccion = findViewById(R.id.correccionRespuesta_txtView);
        Button btnContinuar = findViewById(R.id.btnContinuar);

        btnContinuar.setText(R.string.boton_continuar);

        //recogemos el valor del intent!!
        Bundle extras = getIntent().getExtras();
        correccion.setText(extras.getString("correccion"));

        if(extras.getBoolean("esFinal")){
            btnContinuar.setText(R.string.boton_reiniciar_partida);
        }
        btnContinuar.setOnClickListener(view -> {
            this.finish();
        });

    }
}