package com.lauliett.quizzes_lauragimeno;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
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

        send.setOnClickListener(view -> {
            if(respeustasRadioGroup.getCheckedRadioButtonId() == -1){
                Toast.makeText(this,"Debe seleccionar una respuesta", Toast.LENGTH_SHORT).show();
            }else{
                if(mostrarResultadoRespuesta()){
                    actualizarValores();
                }
            }
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

        numeroPreguntaMostrada++;
        if(numeroPreguntaMostrada >= preguntas.size()) {
            numeroPreguntaMostrada = 0;
        }
        textoPregunta.setText(preguntas.get(numeroPreguntaMostrada).getPregunta());
        numeroPregunta.setText(devolverFormatoNumeroPreguntaMostrada());
        respeustasRadioGroup.removeAllViews();
        generarRadioButtonsPorPregunta();
        respeustasRadioGroup.check(-1);
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

        for (String respuesta : respuestas) {
            RadioButton respuestaRadio = crearRadioButton(respuesta);
            respeustasRadioGroup.addView(respuestaRadio);
        }
    }


    private boolean mostrarResultadoRespuesta(){
        boolean esPreguntaFinal = (numeroPreguntaMostrada == preguntas.size() - 1) ;
        boolean esCorrecta = (preguntas.get(numeroPreguntaMostrada).getRespuestaCorrecta() ==
                respeustasRadioGroup.indexOfChild(findViewById(respeustasRadioGroup.getCheckedRadioButtonId())));

        Intent mostrarcorreccion = new Intent(this, Activity_answer.class);
        mostrarcorreccion.putExtra("esCorrecta", esCorrecta);
        mostrarcorreccion.putExtra("esFinal", esPreguntaFinal);

        startActivity(mostrarcorreccion);
        return esCorrecta;
    }
}