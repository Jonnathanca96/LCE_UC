/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

import java.io.IOException;

/**
 *
 * @author Original Emily Arteaga, Jefferson Arias, Christian Salinas
 * Programacion 3: Estructura de Archivo - Universidad de Cuenca
 * Modificaciones realizadas para un mejor funcionamiento hechas por Jonnathan Campoberde,Ariel Bravo, Vanessa Romero
 */
public abstract class TemplateSerializar {
    public final void Deserializar() throws IOException, ClassNotFoundException{
        cargarGestorArchivo();
        cargarGestorRegistro();
        
    }
    //metodos template
    protected abstract void cargarGestorArchivo() throws IOException, ClassNotFoundException ;
    protected abstract void cargarGestorRegistro() throws IOException, ClassNotFoundException ;
    
}
