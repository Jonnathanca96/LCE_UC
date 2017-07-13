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
public class NumeroRegistro implements Serializable{
    private int numReg;
    private String nombre;

    public NumeroRegistro(int numReg, String nombre) {
        this.numReg = numReg;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumReg() {
        return numReg;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumReg(int numReg) {
        this.numReg = numReg;
    }
    
}
