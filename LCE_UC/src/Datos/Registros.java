/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.io.Serializable;

/**
 *
 * @author Original Emily Arteaga, Jefferson Arias, Christian Salinas
 * Programacion 3: Estructura de Archivo - Universidad de Cuenca
 * Modificaciones realizadas para un mejor funcionamiento hechas por Jonnathan Campoberde,Ariel Bravo, Vanessa Romero
 */
public class Registros implements Serializable{
    private String nombreTabla;
    private String nombreCampo[];
    private int longitud;
    private String nombres;

    public Registros(String nombreTabla, String[] nombreCampo, int longitud, String nombres) {
        this.nombreTabla = nombreTabla;
        this.nombreCampo = nombreCampo;
        this.longitud = longitud;
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    

    public String[] getNombreCampo() {
        return nombreCampo;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setNombreCampo(String[] nombreCampo) {
        this.nombreCampo = nombreCampo;
    }
    


    
    public String getNombreTabla() {
        return nombreTabla;
    }

    
    

    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }
    
    public  String cogerRegistro(int clave)
    {
        System.out.println("nonmbre del campo en la clave "+nombreCampo[clave] );
        return this.nombreCampo[clave];
    }
    
}
