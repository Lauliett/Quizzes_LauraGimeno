package com.lauliett.quizzes_lauragimeno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lauliett.quizzes_lauragimeno.pseudopersistencia.PreguntasPorIdioma;

import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private List<Pregunta> preguntas;
    private int numeroPreguntaMostrada;

    TextView numeroPregunta;
    TextView textoPregunta;
    Button send;
    RadioGroup respeustasRadioGroup;

    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_Quizzes_LauraGimeno);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //agregamos los ids a los elementos
        numeroPregunta = findViewById(R.id.numeroPregunta_txtView);
        textoPregunta = findViewById(R.id.pregunta_txtView);
        respeustasRadioGroup = findViewById((R.id.respeustasRadioGroup));
        send = findViewById(R.id.send_btn);

        send.setText(getResources().getText(R.string.boton_enviar));

        send.setOnClickListener(view -> {
            actualizarValores();
        });
        //inicializamos los datos. Ya sé que esto es horrendibiloso hacerlo aquí maś bueno //aprenderemos MVVM + dao!
        inicializarDatos();
    }

    private void inicializarDatos(){
        PreguntasPorIdioma idioma = new PreguntasPorIdioma();
        //esto va a funcionar incluso cuando la app está abierta, porque ante un cambio de
        //configuración las activitys se "destruyen" y se vuelven a crear!!!!!!
        this.preguntas = idioma.devolverPreguntas(Locale.getDefault().getLanguage());
        numeroPreguntaMostrada = 0;
        textoPregunta.setText(preguntas.get(numeroPreguntaMostrada).getPregunta());
        numeroPregunta.setText(devolverFormatoNumeroPreguntaMostrada());
        generarRadioButtonsPorPregunta();
    }

    private void actualizarValores(){
        if(respeustasRadioGroup.getCheckedRadioButtonId() == -1){
            Toast.makeText(this,"Debe seleccionar una respuesta", Toast.LENGTH_SHORT).show();
            return;
        }
        mostraResultadoRespuesta();
        numeroPreguntaMostrada++;
        if(numeroPreguntaMostrada >= preguntas.size()) {
            numeroPreguntaMostrada = 0;
        }
        textoPregunta.setText(preguntas.get(numeroPreguntaMostrada).getPregunta());
        numeroPregunta.setText(devolverFormatoNumeroPreguntaMostrada());
        respeustasRadioGroup.removeAllViews();
        generarRadioButtonsPorPregunta();
    }

    private String devolverFormatoNumeroPreguntaMostrada(){
        return String.valueOf(numeroPreguntaMostrada + 1) + "/" + String.valueOf(preguntas.size());
    }

    private RadioButton crearRadioButton(String respuesta){
        RadioButton nuevoRadio = new RadioButton(this);
        LinearLayout.LayoutParams params = new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.WRAP_CONTENT,
                RadioGroup.LayoutParams.WRAP_CONTENT);
        nuevoRadio.setLayoutParams(params);
        nuevoRadio.setTextSize(20);
        nuevoRadio.setPadding(2,20,10,20);
        nuevoRadio.setText(respuesta);
        return nuevoRadio;
    }
    private void generarRadioButtonsPorPregunta(){
        List<String> respuestas = preguntas.get(numeroPreguntaMostrada).getRespuestas();
        int numeroRespuesta = 0;
        for (String respuesta : respuestas) {
            RadioButton respuestaRadio = crearRadioButton(respuesta);
            respeustasRadioGroup.addView(respuestaRadio);
        }
    }

    private void mostraResultadoRespuesta(){
        int opcionCorrecta = preguntas.get(numeroPreguntaMostrada).getRespuestaCorrecta();
        int respeustaEscogida =  respeustasRadioGroup.indexOfChild(findViewById(respeustasRadioGroup.getCheckedRadioButtonId()));
        String resultado = getResources().getString(R.string.resuesta_correcta);
        boolean esPreguntaFinal = false;

        if(opcionCorrecta != respeustaEscogida){
            resultado = getResources().getString(R.string.resuesta_incorrecta);
        }

        if(numeroPreguntaMostrada == preguntas.size() - 1){
            resultado = resultado + "\n" + getResources().getString(R.string.informacion_fin_juego);
            esPreguntaFinal = true;
        }

        Intent mostrarCorrecion = new Intent(this, Activity_answer.class);
        mostrarCorrecion.putExtra("correccion", resultado);
        mostrarCorrecion.putExtra("esFinal", esPreguntaFinal);
        startActivity(mostrarCorrecion);
    }
}