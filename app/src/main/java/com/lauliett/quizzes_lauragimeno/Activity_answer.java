package com.lauliett.quizzes_lauragimeno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Activity_answer extends AppCompatActivity {

    private TextView correccion;
    private Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        correccion = findViewById(R.id.correccionRespuesta_txtView);
        btnContinuar = findViewById(R.id.btnContinuar);

        mostrarMensajeCorreccion();

        btnContinuar.setOnClickListener(view -> {
            this.finish();
        });
    }

    private void mostrarMensajeCorreccion(){
        //primero recogemos el valor del intent
        String mensajeResultado = getResources().getString(R.string.resuesta_correcta);
        Bundle extras = getIntent().getExtras();

        if(!extras.getBoolean("esCorrecta")){
            mensajeResultado = getResources().getString(R.string.resuesta_incorrecta);
        }

        if(extras.getBoolean("esFinal")){
            mensajeResultado += getResources().getString(R.string.informacion_fin_juego);
            btnContinuar.setText(getResources().getString(R.string.boton_reiniciar_partida));
        }

        correccion.setText(mensajeResultado);
    }
}
