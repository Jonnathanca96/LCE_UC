/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Campos;
import Datos.NumeroRegistro;
import Datos.Registros;
import static Logica.GestorRegistro.LongitudesRegistros;
import static Logica.GestorRegistro.crearTablaDeserializar;
import Patrones.TemplateSerializar;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Original Emily Arteaga, Jefferson Arias, Christian Salinas
 * Programacion 3: Estructura de Archivo - Universidad de Cuenca
 * Modificaciones realizadas para un mejor funcionamiento hechas por Jonnathan Campoberde,Ariel Bravo, Vanessa Romero
 */
public class GestorArchivo extends TemplateSerializar implements Serializable {

    private ArrayList<NumeroRegistro> tab = new ArrayList();
    public static String nombres[];
    private static final String PATH_ARCHIVO = "archivos/META_BD.csv";
    public static ArrayList<Campos> LongitudesCampos = new ArrayList<Campos>();

    public static void EliminarArchivo(String nomTabla) {
        for (int i = (LongitudesCampos.size()) - 1; i >= 0; i--) {
            if (LongitudesCampos.get(i).getNombre().equals(nomTabla)) {
                System.out.println("long camp: " + LongitudesCampos.get(i).getNombre());
                LongitudesCampos.remove(i);
            }
        }
    }

    public static boolean crearTabla(String text) {

        try {
            File ficheroTabla = new File(PATH_ARCHIVO);
            FileWriter archivo = new FileWriter(ficheroTabla, true);
            CsvWriter csvOutput = new CsvWriter(archivo, ',');
            boolean encriptar = false;
            String nombreTabla;
            String subcadenaDeClave = text.substring(text.indexOf(" CLAVE ") + 7, text.length());
            System.out.println("la clave es: " + subcadenaDeClave);
            boolean campos = text.contains(" CAMPOS ");
            String camposEncriptar[];
            if (campos) {
                csvOutput.setDelimiter(',');
                int inicio = text.indexOf(" CAMPOS ");
                int posTabla = 0;
                String longitudes;
                nombreTabla = text.substring(0, inicio);
                System.out.println("El nombre de la tabla es:" + nombreTabla);
                String nuevoText = text.substring(inicio + 8, text.indexOf(" CLAVE "));
                System.out.println("nuevo texto:" + nuevoText);
                String tablas[] = nuevoText.substring(0, nuevoText.length()).split(",");

                String tamanosLongitudes[];
                camposEncriptar = new String[tablas.length];
                if (text.contains(" ENCRIPTADO")) {
                    String cadenaEncriptar;
                    encriptar = true;
                    longitudes = text.substring(text.indexOf(" LONGITUD ") + 10, text.indexOf(" ENCRIPTADO"));
                    tamanosLongitudes = longitudes.split(",");
                    cadenaEncriptar = text.substring(text.indexOf(" ENCRIPTADO") + 11, text.length());
                    if (cadenaEncriptar.length() == 0 && encriptar) {
                        encriptar = false;
                        for (int i = 0; i < tablas.length; i++) {
                            camposEncriptar[i] = "F";
                        }
                    } else {
                        cadenaEncriptar = text.substring(text.indexOf(" ENCRIPTADO ") + 12, text.length());
                        camposEncriptar = cadenaEncriptar.split(",");
                    }

                    for (int i = 0; i < camposEncriptar.length; i++) {

                        System.out.println("El valor del vector" + i + "es: " + camposEncriptar[i]);
                    }

                } else {
                    for (int i = 0; i < tablas.length; i++) {
                        camposEncriptar[i] = "F";
                    }
                    longitudes = text.substring(text.indexOf(" LONGITUD ") + 10);
                    tamanosLongitudes = longitudes.split(",");//contiene los campos
                }

                for (int i = 0; i < tablas.length; i++) {
                    if (tablas[i].equals(subcadenaDeClave)) {
                        posTabla = i;
                    }
                }

                nombres = new String[tablas.length];
                for (int i = 0; i < tablas.length; i++) {
                    separarLongitud(tablas[i], i, nombreTabla, tablas.length, i, posTabla, subcadenaDeClave, tamanosLongitudes[i], camposEncriptar[i]);
                }

                if (CreadorTabla.getInstancia().addNombreTabla(nombreTabla, nombres)) {
                    return true;
                } else {
                    csvOutput.write("0");
                    csvOutput.write(nombreTabla);
                    String cant = String.valueOf(GestorRegistro.contarRegistros(nombreTabla));
                    System.out.println("Valor de :" + cant);
                    csvOutput.write(cant);
                    csvOutput.write(nombres[0]);
                    csvOutput.setDelimiter(';');
                    
                    for (int i = 1; i < nombres.length; i++) {
                        csvOutput.write(nombres[i]);
                    }      
                    
                    csvOutput.setDelimiter(' ');
                    csvOutput.write(" ");
                    csvOutput.write("\n");
                    csvOutput.endRecord();
                    serializarGestorArchivo();
                }
            }
            csvOutput.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Object[]> crearSalida() throws FileNotFoundException, IOException {
        boolean alreadyExists = new File(PATH_ARCHIVO).exists();
        if (alreadyExists) {

            CsvReader tablas_import = new CsvReader("archivos/META_BD.csv");
            List<Object[]> tabla = new ArrayList<Object[]>();
            while (tablas_import.readRecord()) {
                String tablas[] = tablas_import.getValues();
                if (tablas[0].equals("0")) {
                    tablas[3] = tablas[3].replace(";", ",");
                    

                    tabla.add(Arrays.copyOfRange(tablas, 1, tablas.length));
                }
               
            }
            tablas_import.close();
           
            return tabla;
        } 
        return null;
    }

    public static void separarLongitud(String palabras, int val, String nombre, int cantidad, int claveEsp, int claveGen, String key, String longitud, String encriptar) throws IOException {

        int valor = 0;
        int resta;
        int tamano;
        boolean entro = true;

        try {
            valor = Integer.parseInt(longitud);
        } catch (NumberFormatException ex) {
            entro = false;
        }

        System.out.println(palabras.indexOf(0));
        if (entro == true) {
            resta = valor - palabras.length();
            Campos agregar = new Campos(palabras, valor, resta, nombre, cantidad, claveEsp, claveGen, key, encriptar);
            
            LongitudesCampos.add(agregar);
            

            nombres[val] = palabras;
            serializarGestorArchivo();

        }

    }

    public static void remover(String nombreTabla) {
        LongitudesCampos.remove(nombreTabla);
    }

    public static void serializarGestorArchivo() throws IOException {
        try (ObjectOutputStream output_LongitudesCampos = new ObjectOutputStream(new FileOutputStream("serializar/GestorArchivo_LongitudesCampos.dat"))) {
            output_LongitudesCampos.writeObject(LongitudesCampos);
            output_LongitudesCampos.close();
            
        } catch (IOException x) {

        }
    }

    public static void desSerializarGestorArchivo() throws FileNotFoundException, IOException, ClassNotFoundException {

        try (ObjectInputStream input_LongCamp = new ObjectInputStream(new FileInputStream("serializar/GestorArchivo_LongitudesCampos.dat"))) {
            LongitudesCampos = (ArrayList<Campos>) input_LongCamp.readObject();
            input_LongCamp.close();
           
        } catch (IOException x) {

        }

    }

    @Override
    protected void cargarGestorRegistro() throws IOException, ClassNotFoundException {
        try (ObjectInputStream input_LongCamp = new ObjectInputStream(new FileInputStream("serializar/GestorRegistro_LongitudRegistros.dat"))) {
            LongitudesRegistros = (ArrayList<Registros>) input_LongCamp.readObject();
        } catch (IOException X) {

        }
        crearTablaDeserializar();

    }

    @Override
    protected void cargarGestorArchivo() throws IOException, ClassNotFoundException {

        try (ObjectInputStream input_LongCamp = new ObjectInputStream(new FileInputStream("serializar/GestorArchivo_LongitudesCampos.dat"))) {
            LongitudesCampos = (ArrayList<Campos>) input_LongCamp.readObject();
            input_LongCamp.close();
        } catch (IOException x) {

        }
    }

}
