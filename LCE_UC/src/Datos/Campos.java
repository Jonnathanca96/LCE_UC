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
public class Campos implements Serializable{
    private String camp;
    private int valor;
    private int resta;
    private String nombre;
    private int totalCam;
    private int claveEsp;
    private int claveGen;
    private String key;
    private String encriptacion;

    public Campos(String camp, int valor, int resta, String nombre, int totalCam, int claveEsp, int claveGen, String key,String encriptacion) {
        this.camp = camp;
        this.valor = valor;
        this.resta = resta;
        this.nombre = nombre;
        this.totalCam = totalCam;
        this.claveEsp = claveEsp;
        this.claveGen = claveGen;
        this.key = key;
        this.encriptacion=encriptacion;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    

    public void setClaveEsp(int claveEsp) {
        this.claveEsp = claveEsp;
    }

    public void setClaveGen(int claveGen) {
        this.claveGen = claveGen;
    }

    public int getClaveEsp() {
        return claveEsp;
    }

    public int getClaveGen() {
        return claveGen;
    }
  

    public int getTotalCam() {
        return totalCam;
    }

    public void setTotalCam(int totalCam) {
        this.totalCam = totalCam;
    }
    

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
       
    public void setCamp(String camp) {
        this.camp = camp;
    }

    public void setResta(int resta) {
        this.resta = resta;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    public int getValor() {
        return valor;
    }

    public int getResta() {
        return resta;
    }


    public String getCamp() {
        return camp;
    }

    public String getEncriptacion() {
        return encriptacion;
    }

    public void setEncriptacion(String encriptacion) {
        this.encriptacion = encriptacion;
    }
    
}
