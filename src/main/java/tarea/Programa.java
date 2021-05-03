/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea;

import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;

/**
 *
 * @author aleja
 */
public class Programa {

    final static String DIRECTORIOTSV = "./appstsv/";
    final static String DIRECTORIOXML = "./appsxml/";
    final static String DIRECTORIOJSON = "./appsjson/";
    final static String DIRECTORIOCOPIAS = "./copias/";
    final static String DIRECTORIOAPLICACIONES = "./aplicaciones/";

    public static void main(String[] args) throws IOException {

//      GENERACION DE LA LISTA        
        ArrayList<App> listaDeAplicaciones = App.generarListaApp(50);

//      MUESTRA POR PANTALLA DE LA LISTA CON LAS APPS        
        listaDeAplicaciones.forEach(aplicaciones -> {
            System.out.println(aplicaciones);
        });

//      CREAR FICHERO TSV
        ServicioFicheroTSV.escribirTSV(listaDeAplicaciones, DIRECTORIOTSV + "Aplicaciones.tsv");

//      CREAR FICHERO JSON (1 ARCHIVO -> TODAS LAS APPS)        
        ServicioFicheroJSON.escribirFicheroJSON(listaDeAplicaciones, DIRECTORIOJSON + "Aplicaciones.json");

//      CREAR FICHERO XML        
        escribirFicheroXML(listaDeAplicaciones, DIRECTORIOXML);

//      CREAR FICHERO JSON (1 ARCHIVO -> 1 APP)        
        JSONporAplicacion(listaDeAplicaciones, DIRECTORIOAPLICACIONES);
    }

    /**
     * Metodo que crea un archivo de extension .json por cada una de las
     * aplicaciones que tengamos en la lista
     *
     * @param listaDeAplicaciones Lista con los objetos de tipo App
     * @param directorio Destino de creacion del archivo
     */
    private static void JSONporAplicacion(ArrayList<App> listaDeAplicaciones, String directorio) {

        listaDeAplicaciones.forEach(aplicacion -> {

            String idArchivo = aplicacion.getNombre() + ".json";

            ServicioFicheroJSON.escribirFicheroJSON(aplicacion, directorio + idArchivo);
        });
    }

    /** <html>
     * Metodo que crea un archivo de extension .xml con todas las aplicaciones
     * que tengamos en la lista.
     * <pre>
     *Se crea por medio de la instanciacion de un objeto de tipo "CatalogoApp"
     * </pre>
     *
     * @param listaDeAplicaciones Lista con los objetos de tipo App
     * @param directorio Destino de creacion del archivo
     */
    private static void escribirFicheroXML(ArrayList<App> listaDeAplicaciones, String directorio)  {

        CatalogoApp catalogo = new CatalogoApp(listaDeAplicaciones);

        String nombreAplicaion = "Aplicaciones.xml";

        try {
            ServicioFicheroXML.escribirXML(catalogo, directorio + nombreAplicaion);
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
        }
    }
}
