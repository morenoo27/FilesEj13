/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aleja
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class CatalogoApp {
    
    @XmlElementWrapper(name = "catalogo")
            
    @XmlElement(name = "App")
    private ArrayList<App> catalogo;

    public CatalogoApp() {
    }
    
    public CatalogoApp(ArrayList<App> catalogo) {
        
        this.catalogo = catalogo;
    }

    public ArrayList<App> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(ArrayList<App> catalogo) {
        this.catalogo = catalogo;
    }
}
