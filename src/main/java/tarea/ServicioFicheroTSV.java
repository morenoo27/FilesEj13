/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ALEJADNRO MORENO MARTIN 1°DAW
 */
public class ServicioFicheroTSV {

    /**<html><pre>
     *Genera un fichero con el formato tsv("tab separated values") a partir de
     *una lista que previamente hemos generado.
     *El metodo lo que realiza es una escritura simple de todos los valores que
     *tiene cada uno de los valores de cada una de las aplicaciones previamente 
     *creadas. Cada uno de los valores, aunque en el toString se separen por
     *comas, se reemplazan dichas comas por tabulaciones.
     * </pre>
     * @param listaAplicaciones Lista con las aplicaciones que vamosa a escirbir
     * en el archivo
     * @param directorio Ruta en al que queremos c rear el archivo
     * </html>
     */
    public static void escribirTSV(ArrayList<App> listaAplicaciones, String directorio) {

        try ( BufferedWriter flujo = new BufferedWriter(new FileWriter(directorio))) {

//          Escribimos en la primera linea el titulo de cada una de las celdas
//          y una nueva linea (cada vez quie escribamos)
            String cabecera = "Codigo|Nombre|Descripcion|Tamanio|Fecha de creacion";
//          Como esta vez el archivo esta separado por tabulaciones, las comas
//          las remplazaremos por el caracter que necesitemos
            flujo.write(cabecera.replace("|", "\t"));
//          Bajamos una nueva linea para escribir los siguientes elementos
            flujo.newLine();

//          Algoritmo que recorre todas las aplicaciones y le mete el toString
//          con el formato requerido por el fichero
            for (App aplicacion : listaAplicaciones) {
                flujo.write(aplicacion.toString());
                flujo.newLine();
            }

//          Metodo fluh() guarda cambios en disco 
            flujo.flush();

            System.out.println("Fichero " + directorio + " creado correctamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
