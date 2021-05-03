/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea;

import javax.xml.bind.JAXBException;

/**
 *
 * @author aleja
 */
public class PruebaEj12 {
    
    public static void main(String[] args) {
        
        try{
            ServicioFicheroXML.leerXML("./appsxml/Aplicaciones.xml");
        }catch(JAXBException e){
            e.printStackTrace();
        }
    }
}
