package com.lauliett.quizzes_lauragimeno.pseudopersistencia;


import com.lauliett.quizzes_lauragimeno.Pregunta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author laura gimeno villanueva
 * Clase no bien usada para "persistencia" de las preguntas
 * y multidioma de las pregutnas
 */

/*Como todavía no he aprendido como funciona la persistencia en android
y el acceso a los ficheros no es igual que en un ordenador, de momento creo esta clase
para almacenar las preguntas. Soy consciente de que ESTO NO se hace así,
pero salimos del paso. ¡Ver también como es multiidoma en estos casos!
Formas de persistencia: ¿¿¿¿room??, sqlLie, sharedPreferences, webservice
Ver como acceder a archivos .txt en android. ¿¿Res/raw??
 */
public class PreguntasPorIdioma {

    //refactorizar switch (ifs anidados)

    /**
     * Devuelve un listado de preguntas según el idioma pasado por parámetro
     * @param idioma
     * @return listado de preguntas
     */
   public List<Pregunta> devolverPreguntas(String idioma) {
       List<Pregunta> preguntas = new ArrayList<>();

       if (idioma.equals("en")) {
            preguntas = inglesDevolverPreguntas();
       }
       else if(idioma.equals("es")){
           preguntas=espanolDevolverPreguntas();
       }
       return  preguntas;
   }

    /**
     * Devuelve un listado de preguntas en el idioma español
     * @return
     */
    private static List<Pregunta> espanolDevolverPreguntas(){
        List<Pregunta> preguntas = new ArrayList<>();

        preguntas.add(new Pregunta(
                "¿Cuál es la primera llamada que se efectúa al lanzar un activity?",
                new ArrayList<String>(Arrays.asList(
                        "onStart()",
                        "onCreate()",
                        "onRestart()")),
                1
        ));
        preguntas.add(new Pregunta(
                "¿Qué es un Intent en android?",
                new ArrayList<String>(Arrays.asList(
                        "un objeto map de clave tipo String y valor objeto parceable",
                        "un objeto de mensajería que puede usarse para solicitar acciones de otros componentes",
                        "una interfaz chachi")),
                1
        ));
        preguntas.add(new Pregunta(
                "El objeto parceable es una interfaz que permite generar parcelas de mapa de forma automática. ¿Falso o Verdadero?",
                new ArrayList<String>(Arrays.asList("Verdadero", "Falso")),
                1
        ));
        return preguntas;
    }

    /**
     * Devuelve un listado de preguntas en el idioma inglés.
     * @return
     */
    //perdonenme mis traducciones al inglés.
    private List<Pregunta> inglesDevolverPreguntas(){

        List<Pregunta> preguntas = new ArrayList<>();

        preguntas.add(new Pregunta(
                "When an activit is launched, what is the first callback method who is called?",
                new ArrayList<String>(Arrays.asList(
                        "onStart()",
                        "onCreate()",
                        "onRestart()")),
                1
        ));
        preguntas.add(new Pregunta(
                "¿What is an Intent in android?",
                new ArrayList<String>(Arrays.asList(
                        "a map object whose key is a String and its value is a parceable object",
                        "a messaging object that can be use to request actions from another componenets",
                        "a nice interface")),
                1
        ));
        preguntas.add(new Pregunta(
                "Parcebale object is an interface that allows us tu generate map parcels automatically. False or True?",
                new ArrayList<String>(Arrays.asList("True", "False")),
                1
        ));
        return preguntas;
    }

}
