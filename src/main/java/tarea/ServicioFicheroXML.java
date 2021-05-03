/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author aleja
 */
public class ServicioFicheroXML {

    /**<pre>
     *Metodo que crea un fichero con extension xml a partir de un catologo con
     *una lista de objetos de tipo App en un directorio determinado.
     *
     *El archivo se genera de la siguiente forma:
     *
     *Primero:
     *    Crea el contexto JAXB. Se encarga de definir los objetos que vamos
     *    a guardar. En nuestro caso de tipo App
     *    (linea 64)
     *
     *Segundo:
     *    El contexto JAXB permite crear un objeto Marshaller, que sirve
     *    para generar la estructura del fichero XML El proceso de pasar objetos
     *    Java (CatalogoMuebles) a ficheros XML se conoce como "marshalling" o
     *    "serialización"
     *    (linea 66)
     * 
     *Tercero:
     *    Especificamos que la propiedad del formato de salida del
     *    serializador sea true, lo que implica que el formato se realiza con
     *    indentación y saltos de línea
     *    (linea 68)
     * 
     *Cuarto:
     *    Generacion del archivo en el directorio
     *    (linea 70)
     * 
     * 
     *Apunte:
     *    Llamando al método de serialización marshal (sobrecargado) se pueden 
     *    serializar objetos java en formato XML y volcarlos donde necesitemos 
     *    (consola, ficheros, etc) . El proceso consiste en que el contexto es 
     *    el encargo de leer los objetos java, pasarlos al serializador y éste 
     *    crear la salida de serialización
     * </pre>
     * 
     * @param catalogo Catalogo con todas las aplicaciones
     * @param directorio Lugar donde generaremos el archivo
     * @throws JAXBException Fallo en la creacion del archivo
     */
    public static void escribirXML(CatalogoApp catalogo, String directorio) throws JAXBException {

        JAXBContext contexto = JAXBContext.newInstance(CatalogoApp.class);
        
        Marshaller serializador = contexto.createMarshaller();
        
        serializador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        serializador.marshal(catalogo, new File(directorio));
    }
    
    /**
     * <html><pre>
     *Metodo que lee un fichero con extension xml en un directorio determinado.
     *Este en especifico leera un catalogo de una lista de objetos de tipo App
     *
     *El archivo se genera de la siguiente forma:
     *
     *Primero:
     *    Crea el contexto JAXB. Se encarga de definir los objetos que vamos
     *    a guardar. En nuestro caso de tipo App
     *    (linea 104)
     *
     *Segundo:
     *    Se intancia un objeto de tipo Unmarshaller (linea 106)
     * 
     *Tercero:
     *    Se llama al metodo de unmarshaling para leer el archivo (linea 108)
     * 
     *Cuarto:
     *    Almacenamos cada objeto en un lista y la mostramos por consola
     *    (lineas 110 y 111)
     * </pre>
     * 
     * @param directorio Lugar del que leeremos el archivo
     * @throws JAXBException Malformacion del archivo
     * </html>
     */
    public static void leerXML (String directorio) throws JAXBException{
        
        JAXBContext contexto = JAXBContext.newInstance(CatalogoApp.class);

        Unmarshaller um = contexto.createUnmarshaller();

        CatalogoApp catalogo = (CatalogoApp) um.unmarshal(new File(directorio));

        ArrayList<App> listaMuebles = catalogo.getCatalogo();
        listaMuebles.forEach(System.out::println);

    }
}
