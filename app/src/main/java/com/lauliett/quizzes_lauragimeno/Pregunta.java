package com.lauliett.quizzes_lauragimeno;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Pregunta {
    String pregunta;
    List<String> respuestas;
    int respuestaCorrecta;

    public Pregunta(String pregunta, List<String> respuestas, int respuestaCorrecta){
        this.pregunta = pregunta;
        this.respuestas = respuestas;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public List<String> getRespuestas() {
        return respuestas;
    }

    public int getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

}
