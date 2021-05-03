/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author aleja
 */
public class ServicioFicheroJSON {

    /** 
     * Genera un fichero con la extension json a partir de una lista que
     * recibimos como parametro.
     * <pre>
     *Se genera un archivo con la informacion de todos los objetos que tenemos
     *en la lista
     * </pre>
     *
     * @param listaAplicaciones Lista de objetos de tipo App
     * @param idArchivo Directorio del archivo
     * 
     */
    public static void escribirFicheroJSON(ArrayList<App> listaAplicaciones, String idArchivo) {

        ObjectMapper mapeador = new ObjectMapper();

        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);

        try {
            // Escribe en un fichero JSON el catálogo de muebles
            mapeador.writeValue(new File(idArchivo), listaAplicaciones);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("El fichero " + idArchivo + " se ha creado correctamente");
    }

    /**
     * Genera un fichero con la extension json a partir de un objeto de tipo App
     * que recibimos como parametro.
     * <pre>
     *Se genera un archivo con la informacion del objeto que tenemos en la lista
     * </pre>
     *
     * @param aplicaion Objeto de tipo App
     * @param idArchivo Directorio donde se creara el archivo
     * 
     */
    public static void escribirFicheroJSON(App aplicaion, String idArchivo) {

        ObjectMapper mapeador = new ObjectMapper();

        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);

        try {
            // Escribe en un fichero JSON el catálogo de muebles
            mapeador.writeValue(new File(idArchivo), aplicaion);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("El fichero " + idArchivo + " se ha creado correctamente");
    }

    /**
     * Metodo que almacena en una lista de App cada uno de los objetos escritos en un fichero jackson
     *
     * @param ruta Directorio del que localizaremos y leeremos el archivo
     * @return Lista con todos los objetos de tipo App de un fichero ubicado en
     * la ruta indicada
     */
    public static ArrayList<App> leerFicheroJSONconLista (String ruta) {

        ObjectMapper mapeador = new ObjectMapper();

        try {
            return mapeador.readValue(new File(ruta),
                    mapeador.getTypeFactory().constructCollectionType(ArrayList.class, App.class));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    /**
     * Metodo que devuelve un objeto de App escrito en un fichero jackson
     * 
     * @param ruta Directorio del que leera el archivo
     * @return Objeto de tipo App
     */
    public static App leerFicheroJSONconApp(String ruta){

        ObjectMapper mapeador = new ObjectMapper();

        try{
            return mapeador.readValue(new File(ruta),mapeador.constructType(App.class));
        } catch (IOException ex){
            System.out.println("No se encuentra el archivo");
        }
        return null;
    }
}
