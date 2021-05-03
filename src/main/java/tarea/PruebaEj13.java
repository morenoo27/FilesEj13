/*

////      VARIABLES NECESARIAS
//        ArrayList<App> listaDeApps;
//        String archivoALeer;
//        App aplicacionALeer;


////      LLENAMOS LA LISTA CON EL ARCHIVO "Aplicaciones.json"
//        listaDeApps = ServicioFicheroJSON.leerFicheroJSONconLista(DIRECTORIOJSON + ARCHIVOAPLICACIONES);
//
////      CREAMOS OTRA LISTA CON SOLO LOS NOMBRES
//        List<String> nombreDeApps = soloNombres(listaDeApps);
//
////      CAPTAMOS EL NOMBRE DEL ARCHIVO QUE QUEREMOIS LEER
//        archivoALeer = elegirarchivo(nombreDeApps);
//
////      LEEMOS ESE FICHERO
//        ServicioFicheroJSON.leerFicheroJSONconApp(DIRECTORIOAPLICACIONES + archivoALeer);

 */
package tarea;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author aleja
 */
public class PruebaEj13 {

    private static Scanner teclado = new Scanner(System.in);

    //      DIRECTORIOS NECESARIOS
    final static String DIRECTORIOJSON = "./appsjson/";
    final static String DIRECTORIOAPLICACIONES = "./aplicaciones/";
    final static String ARCHIVOAPLICACIONES = "Aplicaciones.json";

    public static void main(String[] args) {

//      VARIABLES NECESARIAS
        App aplicacionALeer;
        final double VALORMAX = 500;
        final double VALORMIN = 200;

//      LLENAMOS LA LISTA CON EL ARCHIVO "Aplicaciones.json"
//      CREAMOS OTRA LISTA CON SOLO LOS NOMBRES
//      CAPTAMOS EL NOMBRE DEL ARCHIVO QUE QUEREMOIS LEER
//      LEEMOS ESE FICHERO (QUE ESE METODO DEVUELVE UNA APP)
//      LO ALMACENAMOS EN UN OBJETO DE TIPO APP
        aplicacionALeer = ServicioFicheroJSON.leerFicheroJSONconApp(DIRECTORIOAPLICACIONES + elegirarchivo(soloNombres(ServicioFicheroJSON.leerFicheroJSONconLista(DIRECTORIOJSON + ARCHIVOAPLICACIONES))));
        /*Realizacion paso a paso en la cabeza del codigo*/
        
        
//      MOSTRAMOS POR PANTALLA LA APLICACION
        System.out.println("Mostrando App...\n");
        System.out.println(aplicacionALeer.toString()
                + "\nA continuacion se pocede a eliminar el archivo "
                + aplicacionALeer.getNombre().concat(".json")
                + "...");

//      BORRAMOS EL ARCHIVO
        eliminarArchivo(DIRECTORIOAPLICACIONES + aplicacionALeer.getNombre().concat(".json"));
        System.out.println("Buscando archivo...");
        comprobarArchivo(aplicacionALeer);

//      ALMACENAMOS EN UN MAP CON LOS REQUISITOS DEL EJERCICIO LAS APPS VALIDAS
//      MOSTRAMOS EL MAP
        System.out.println("\n\nAplicaciones que pesan entre 200 y 500kB:");
        mostrarMapeado(aplicacionesEntreValores(VALORMIN, VALORMAX));

    }

    /**
     * Metodo que realiza un mapeo a partir de una lista almacenando en otra
     * solo los nombres de los archivos<pre>
     *
     *Como el nombre de los archivos se forma a partir del nombre de la aplicacion,
     *con captar el nombre y aniadirle una cadena de texto ".json" ya tendriamos
     *especifico de cada uno de los archivos quie se encuentran en el directorio
     *"./aplicaciones"
     * </pre>
     *
     * @param listaDeApps Lista con las aplicaciones
     * @return Lista de Strings con los nombres de los archivos con el formato
     * "nombre.json"
     */
    private static List<String> soloNombres(ArrayList<App> listaDeApps) {
        return listaDeApps.stream()
                .map(app -> app.getNombre().concat(".json"))
                .collect(Collectors.toList());
    }

    /**
     * Metodo en el que mostramos por pantalla cada uno de los nombres de todas
     * las Apps almacenadas en una lista y por medio de un objeto tipo scanner
     * seleccionamos cual es la que queremos elegir
     *
     * @param nombreDeApps Lista con los nombre de todas las Apps
     * @return Nombre completo del archivo(con extension)
     */
    private static String elegirarchivo(List<String> nombreDeApps) {

        String nombreDeAplicaion = null;

        boolean appNoValida;//true no existe el archivo  ||  false si existe

        do {

            System.out.println("-------------ARCHIVOS------------");
            mostrarNombres(nombreDeApps);
            System.out.println("\nINTRODUCE EL NOMBRE COMPLETO DEL ARCHIVO(CON EXTENSION)");

            String aplicacionElegida = teclado.nextLine().toLowerCase();
            nombreDeAplicaion = aplicacionElegida;

            appNoValida = nombreDeApps.stream()
                    .noneMatch(nombre -> nombre.equals(aplicacionElegida));

            if (appNoValida) {
                System.out.println("No se encuentra el archivo");
            }

        } while (appNoValida);

        return nombreDeAplicaion;
    }

    /**
     * Metodo que muestra por consola cada uno de los nombres de los archivos
     * con el formato "nombre.json"
     *
     * @param nombreDeApps Lista con los nombres de los archivos
     */
    private static void mostrarNombres(List<String> nombreDeApps) {

        int contador = 1;

        //muestra por consola
        for (String nombre : nombreDeApps) {
            System.out.println(contador + " - " + nombre);
            contador++;
        }
    }

    /**
     * Metodo que realiza un borrado del archivo que queremos<pre>
     *La ruta debe empezar en el directorio raiz (./)
     * </pre>
     *
     * @param ruta Directorio que queremos eliminar
     */
    private static void eliminarArchivo(String ruta) {

        Path file = Paths.get(ruta);

        try {
            Files.delete(file);
            System.out.println("Archivo eliminado con exito");
        } catch (NoSuchFileException ex) {
            ex.printStackTrace();
        } catch (DirectoryNotEmptyException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que comprueba que una app existe en el directorio<pre>
     *Este metodo se realiza por medio de otra ejecucion del metodo de la clase
     *ServicioFicheroJSON "leerFicheroJSONconApp".
     *Si no se encuentra el archivo, se notificara por pantalla
     * </pre>
     *
     * @param aplicacionABuscar App que queremos buscar
     */
    private static void comprobarArchivo(App aplicacionABuscar) {

        ServicioFicheroJSON.leerFicheroJSONconApp(DIRECTORIOAPLICACIONES + aplicacionABuscar.getNombre().concat(".json"));
    }

    /**
     * Metodo que, teniendo en cuenta unos parametros limites(min y max),
     * almacena en una estructura Map aquellas Apps que cumplan la condicion de
     * que el tamanio de dicha app este entre los valores previamente
     * mencionados<pre>
     *LAS APPS ESTARAN ESTRICTAMENTE DENTRO DE ESOS VALORES 
     *(NO VALEN LOS VALORES QUE ESTAN COMO PARAMETRO)
     * </rpe>
     *
     * @param min Valor minimo
     * @param max Valor maximo
     * @return Estructura Map con los requisitos necesarios
     */
    private static Map<String, LocalDate> aplicacionesEntreValores(double min, double max) {

        ArrayList<App> listaApps = ServicioFicheroJSON.leerFicheroJSONconLista(DIRECTORIOJSON + ARCHIVOAPLICACIONES);

        return listaApps.stream()
                .filter(app -> app.getTamanio() < max && app.getTamanio() > min)
                .collect(Collectors.toMap(app -> app.getNombre(), app -> app.getFechaCreacion()));
    }

    /**
     * Metodo que muestra, a traves de un forEach, la relacion key-value de una
     * estructura Map
     *
     * @param mapNombreFecha Estructura Map
     */
    private static void mostrarMapeado(Map<String, LocalDate> mapNombreFecha) {

        DateTimeFormatter formatoEspaniol = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        mapNombreFecha.entrySet().forEach(folder -> {
            
            LocalDate fechaApp = folder.getValue();
            
            System.out.println(folder.getKey() + "-->" + fechaApp.format(formatoEspaniol));
        });
    }
}