/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Alejandro Moreno Martin 1°DAW
 */

@XmlRootElement(name = "aplicacion")
@XmlAccessorType(XmlAccessType.FIELD)
public class App {

    private final int codigoUnico;
    private String nombre;
    private String descripcion;
    private double tamanio;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate fechaCreacion;

    private static int contadorCodigoUnico = 1;

    private static Random random = new Random();

    private static String[] descripciones = {"GTA",
        "Netflix",
        "YouTube",
        "Twich",
        "League of Legends",
        "Spotify",
        "Classroom",
        "NetBeans",
        "Eclipse",
        "Visual Studio Code"};

    /*CONSTRUCTORES*/
    
    public App() {

        this.codigoUnico = contadorCodigoUnico;
        this.nombre = generarNombre();

        contadorCodigoUnico++;
    }

    public App(int codigoUnico, String descripcion, String nombre, double tamaño, LocalDate fechaCreacion) {

        this.codigoUnico = codigoUnico;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tamanio = tamaño;
        this.fechaCreacion = fechaCreacion;

        contadorCodigoUnico++;
    }
    
    /**<html><pre>
     * Metodo de clase App que crea un objeto de este tipo. 
     * Se genera de manera
     * aleatoria instanciando un objeto por defecto y por medio de setters se
     * estableceran los atributos
     * </pre>
     *
     * @return Devuelve un objeto de tipo App, generado de manera aleatoria
     * </html>
     */
    public static App crearAleatorio() {
        
        final double VALORMAX = 1024;
        final double VALORMIN = 100;

        App aplicacion = new App();
        
        aplicacion.setDescripcion(generarDescripcion());
        aplicacion.setFechaCreacion(generarFecha());
        aplicacion.setTamanio(generarTamanio(VALORMAX, VALORMIN));
        
        return aplicacion;
    }

//  GETTERS AND SETTERS
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getCodigoUnico() {
        return codigoUnico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    /*
    No vamos a tener setter del atributo Codiog UInico ya que al ser unico e
    identificativo no vamosa a poder variar ese parametro
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTamanio() {
        return tamanio;
    }

    public void setTamanio(double tamanio) {
        this.tamanio = tamanio;
    }

//  EQUALS & HASHCODE
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final App other = (App) obj;
        if (this.codigoUnico != other.codigoUnico) {
            return false;
        }
        if (Double.doubleToLongBits(this.tamanio) != Double.doubleToLongBits(other.tamanio)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.fechaCreacion, other.fechaCreacion)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.codigoUnico;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.tamanio) ^ (Double.doubleToLongBits(this.tamanio) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.fechaCreacion);
        return hash;
    }

    /*toString*/
    @Override
    public String toString() {

        DateTimeFormatter formatoEspaniol = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        return codigoUnico + "\t" + nombre + "\t" + descripcion + "\t" + tamanio + "\t" + fechaCreacion.format(formatoEspaniol);
    }

    /**<pre>
     *Metodo que genera un nombre. El algoritmo sera el siguiente:
     *    -Cadena de caracteres definida: app
     *    -Cogido: Codigo caracteristico de cada aplicaion
     *    -Letra: Letra aleatoria (desdde la a ahsta la z)
     * </pre>
     *
     * @return Cadena de caracteres con el siguiente formato: “Cadena de
     * caracteres definida + Codigo + Letra”.
     */
    private String generarNombre() {

        final String APP = "app";

        String letraAleatoria = String.valueOf((char) (random.nextInt(26) + 'a'));

        return APP + String.valueOf(this.codigoUnico) + letraAleatoria;
    }

    /** <html>
     * Metodo que genera un objeto de tipo DoubleStream convertido a double que
     * define el tamanio del objeto, en este caso define el tamanio de una
     * apliacion
     * <pre>
     * El metodo usa el metodo "doubles" de la clase Ramdon.
     *     Este metodo genera un Stream de doubles.
     *     En este caso el .sum(que hace un sumativo de todos los valores generados)
     * Nos valdria ya que al ser solo un unico valor nos funciona
     * </pre>
     *
     * @param valorMax Valor maximo que podra tener el archivo
     * @param valorMin Valor minimo que podra tener el archivo
     * @return "Cuanto pesa el archivo"
     * </html>
     */
    private static double generarTamanio(double valorMax, double valorMin) {
        return random.doubles(1, valorMin, valorMax).sum();
    }

    /** <html><pre>
     *Metodo que crea una fecha totalmente aleatoria.
     *    -Primero se crean el mes y el anio de manera aleatoria usando un objeto
     *de la clase Ramdon
     *    -Segundo, dependiendo del mes tendremos o 30 o 31, sin contar con
     *Febrero.
     *        -Los meses de 30 dias son : 4(Abril), 6(Junio), 9(Septimebte),
     *         11(Noviembre)
     *        -Los que tienen 31 son: 1(Enero), 3(Marzo), 5(Mayo), 7(Julio),
     *         8(Agosto), 10(Octubre), 12(Diciembre)
     *    -Si sale Febrero, dependiendo de la fecha de referencia(Fecha creada
     *a partir del anio creado de manera aleatoria con el mes y el dia no
     *importantes, por eso el numero 1 en mabos), si el anio es bisiesto se
     *generaran hasta 29. Si no es bisiesto se generaran hasta 28
     * </pre>
     *
     * @return Fecha creada de amnera aleatoria
     * </html>
     */
    private static LocalDate generarFecha() {

        int day = 0, year, month;

//      El anio y el mes no tienen ninguna restriccion ya que van a ser 
//      solamente numeros, en cambio el dia dependera del mes y año
        month = random.nextInt(12) + 1;
        year = random.nextInt(22) + 2000;

//      Una vez creamos una fecha de referencia que usaremos ams tarde para
//      identificar si es bisiesto en año o no
        LocalDate referencia = LocalDate.of(year, 1, 1);

        switch (month) {

            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = random.nextInt(31) + 1;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = random.nextInt(30) + 1;
                break;
        }

        if (month == 2) {

            if (referencia.isLeapYear()) {
                day = random.nextInt(29) + 1;
            } else {
                day = random.nextInt(28) + 1;
            }
        }

        return LocalDate.of(year, month, day);
    }

    /**
     * Usando un obtjeto de clase random, y teniendo unas descripciones
     * previamente creadsa y guardadas en un array, elegimos una de ellas de
     * manera aleatoria para nuestra aplicacion
     *
     * @return Devuelve una de las descripciones creadas
     */
    private static String generarDescripcion() {

        return descripciones[random.nextInt(10)];
    }
    
    /** <html><pre>
     *Metodo que realiza una secuencia de creaciones de objetos de tipo App.
     *La cantidad de repeticiones se tiene que introducir manualmente.
     *La creacion de la App se realiza con el metodo de la clase App 
     *"crearAleatorio"
     * </pre>
     *
     * @param repeticiones Numero de veces que re repetira el bucle
     * @return Lista con todos los objetos de tipo App que se han creado
     * </html>
     */
    public static ArrayList<App> generarListaApp(int repeticiones) {

        ArrayList<App> lista = new ArrayList<>();

        for (int i = 0; i < repeticiones; i++) {
            lista.add(App.crearAleatorio());
        }

        return lista;
    }
}
