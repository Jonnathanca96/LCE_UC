/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Datos.Registros;
import static Logica.GestorArchivo.LongitudesCampos;
import com.csvreader.CsvWriter;
import encriptacion.Encriptacion;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Original Emily Arteaga, Jefferson Arias, Christian Salinas
 * Programacion 3: Estructura de Archivo - Universidad de Cuenca
 * Modificaciones realizadas para un mejor funcionamiento hechas por Jonnathan Campoberde,Ariel Bravo, Vanessa Romero
 */
public class GestorRegistro implements Serializable {

    public static ArrayList<Registros> LongitudesRegistros = new ArrayList<Registros>();
    public static ArrayList<Object[]> Sel = new ArrayList<Object[]>();
    public static ArrayList<Object[]> Unir = new ArrayList<Object[]>();
    private static int contUnir = 0;
    public static ArrayList<Integer> ContadoresUnir = new ArrayList<Integer>();

    public static void EliminarRegistros(String nomTabla) throws IOException {

        for (int i = (LongitudesRegistros.size()) - 1; i >= 0; i--) {
            if (LongitudesRegistros.get(i).getNombreTabla().equals(nomTabla)) {

                LongitudesRegistros.remove(i);
            }
        }
        serializar_GestorRegistro();
    }

    public static boolean crearTabla(String text, String nombreArchivo) throws IOException {
        boolean campo = text.contains(" VALORES ");
        int cont = 0;
        int cont2 = 0;
        int cont3 = 0;
        boolean ban = false;
        if (campo) {
            int inicio = text.indexOf(" VALORES ");
            String campos[] = text.substring(inicio + 9, text.length()).split(",");
            String nomb = text.substring(inicio + 9, text.length());
            boolean encriptado = false;

            for (int i = 0; i < LongitudesCampos.size(); i++) {
                if ((LongitudesCampos.get(i).getNombre().equals(nombreArchivo)) && (LongitudesCampos.get(i).getTotalCam() == campos.length) && ban != true) {
                    cont2 = LongitudesCampos.get(i).getTotalCam();
                    for (int j = 0; j < LongitudesCampos.size(); j++) {
                        if ((LongitudesCampos.get(i).getNombre().equals(nombreArchivo))) {
                            cont = j;
                            for (int z = 0; z < campos.length; z++) {
                                if (LongitudesCampos.get(cont).getValor() >= campos[z].length()) {//campos[z].length()

                                    cont++;

                                    cont3++;

                                    ban = true;
                                }
                            }
                            System.out.println(cont);
                        }
                        break;
                    }
                }
            }

            if (cont3 == cont2 && cont3 > 0) {
                int key = 0;

                for (int i = 0; i < LongitudesCampos.size(); i++) {
                    if (LongitudesCampos.get(i).getNombre().equals(nombreArchivo)) {
                        key = LongitudesCampos.get(i).getClaveGen();
                    }
                }

                if (!esRepetido(campos, nombreArchivo, key)) {  //Tiene que retornar true para que no sea repetido
                    for (int i = 0; i < LongitudesCampos.size(); i++) {
                        for (int j = 0; j < campos.length; j++) {
                            if (LongitudesCampos.get(i).getNombre().equals(nombreArchivo)) {
                                if (LongitudesCampos.get(i).getEncriptacion().equals("T")) {
                                    campos[j] = Encriptacion.encriptar(campos[j]);
                                }
                            }
                        }

                    }

                    separarLongitud(campos, nombreArchivo, campos.length, nomb);
                    escribirArchivo(campos, nombreArchivo);
                    contarRegistros(nombreArchivo);
                    actualizarTabla(nombreArchivo);
                    /////////////////////////////////////
                    serializar_GestorRegistro();
                    /////////////////////////////////////
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Verifique que no se repita el valor del campo clave", "Error", JOptionPane.WARNING_MESSAGE);
                    return false;
                }

            } else {
                JOptionPane.showMessageDialog(null, "Unos sus campos ha sobrepasado la longitud especificada o la tabla no existe!", "Error", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        }
        return false;
    }

    public static void separarLongitud(String palabras[], String nombre, int cantidad, String nombres) {
        Registros reg = new Registros(nombre, palabras, cantidad, nombres);
        LongitudesRegistros.add(reg);

    }

    public static boolean esRepetido(String palabras[], String nombre, int clave) {
        for (int i = 0; i < LongitudesCampos.size(); i++) {
            if (LongitudesCampos.get(i).getNombre().equals(nombre)) {
                boolean encriptado = LongitudesCampos.get(i).getEncriptacion().equals("T");
                for (int j = 0; j < LongitudesRegistros.size(); j++) {
                    if (LongitudesRegistros.get(j).getNombreTabla().equals(nombre)) {
                        String comp[] = LongitudesRegistros.get(j).getNombres().split(",");

                        if (comp[clave].equals(palabras[clave])) {
                            return true; //si es repetido
                        }
                    }
                }
            }
        }
        return false; //FALSO SI NO ES REPETIDO
    }

    public static boolean esRepetidoMod(String nombreTab, String clave, String nuevoCamp) {
        int key = 0;
        for (int i = 0; i < LongitudesCampos.size(); i++) {
            if (LongitudesCampos.get(i).getNombre().equals(nombreTab) && LongitudesCampos.get(i).getKey().equals(clave)) {
                key = LongitudesCampos.get(i).getClaveGen();
                for (int j = 0; j < LongitudesRegistros.size(); j++) {
                    String comp[] = LongitudesRegistros.get(j).getNombres().split(",");
                    if (comp[key].equals(nuevoCamp)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void escribirArchivo(String palabras[], String nombre) throws IOException {//palabras son los campos
        File ficheroTabla = new File("archivos/" + nombre + ".csv");
        CsvWriter csvOutput;
        int band = 0;
        int cont = 0;

        try (
                FileWriter archivo = new FileWriter(ficheroTabla, true)) {
            csvOutput = new CsvWriter(archivo, ',');
            for (int i = 0; i < LongitudesRegistros.size(); i++) {
                if (band == 0) {
                    if (LongitudesRegistros.get(i).getNombreTabla().equals(nombre)) {
                        csvOutput.writeRecord(palabras);
                        band++;
                    }
                }
            }
        }
        csvOutput.close();
    }

    public static int contarRegistros(String nombre) {
        int cont = 0;
        for (int i = 0; i < LongitudesRegistros.size(); i++) {
            if (LongitudesRegistros.get(i).getNombreTabla().equals(nombre)) {
                cont++;
            }
        }
        return cont;
    }

    public static void actualizarTabla(String nombreArchivo) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("archivos/META_BD.csv");
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        String aux = "";
        String newLine;
        int cont = CreadorTabla.nombresTablas.size();
        int cont2 = 0;

        while ((line = br.readLine()) != null) {
            cont2++;
            String campoMeta[] = line.split(",");
            if (campoMeta.length >= 3) {
                if (campoMeta[1].equals(nombreArchivo) && (campoMeta[0].equals("0"))) {

                    newLine = "0," + nombreArchivo + "," + contarRegistros(nombreArchivo) + "," + line.substring(4 + nombreArchivo.length() + String.valueOf(contarRegistros(nombreArchivo)).length(), line.length());

                    if (cont2 == cont) {
                        aux = aux + newLine;
                    } else {
                        aux = aux + newLine + "\n";
                    }

                } else {

                    aux = aux + line + "\n";

                }
            }
        }
        br.close();
        fr.close();
        RandomAccessFile fileNew = new RandomAccessFile("archivos/META_BD.csv", "rw");
        fileNew.seek(0);
        fileNew.writeBytes(aux);
        fileNew.close();
    }

    public static boolean cambiarRegistro(String nombreT, String clave, String campAnt, String campNue) throws IOException {
        int band = 0;
        String nuevo = "";
        for (int i = 0; i < LongitudesCampos.size(); i++) {//LongitudesCampos.get(i).getKey()
            if (LongitudesCampos.get(i).getNombre().equals(nombreT)) {//retire LongitudesCampos.get(i).getKey().equals(clave))

                for (int j = 0; j < LongitudesRegistros.size(); j++) {
                    if (LongitudesRegistros.get(j).getNombreTabla().equals(nombreT)) {
                        String reempl[] = LongitudesRegistros.get(j).getNombres().split(",");
                        for (int k = 0; k < reempl.length; k++) {
                            if (reempl[k].equals(campAnt) && band != 1) {
                                reempl[k] = campNue;
                                band = 1;
                            }
                        }
                        if (band == 1) {
                            for (int k = 0; k < reempl.length; k++) {
                                if (k == reempl.length - 1) {
                                    nuevo += reempl[k];
                                } else {
                                    nuevo += reempl[k] + ",";
                                }
                            }
                            LongitudesRegistros.get(j).setNombres(nuevo);
                            serializar_GestorRegistro();
                            return true;
                        }
                    }

                }

            }
        }
        serializar_GestorRegistro();
        return false;
    }

    public static void cambiarRegistroArchivo(String nombreT) throws FileNotFoundException, IOException {
        FileWriter fr = new FileWriter("archivos/" + nombreT + ".csv");
        String newLine = "";
        String encabezado = "";
        String agregar = "";
        for (int i = 0; i < LongitudesCampos.size(); i++) {
            if (i == LongitudesCampos.size() - 1) {
                if (LongitudesCampos.get(i).getNombre().equals(nombreT)) {
                    encabezado += LongitudesCampos.get(i).getCamp();
                }
            } else if (LongitudesCampos.get(i).getNombre().equals(nombreT)) {
                agregar = LongitudesCampos.get(i).getCamp() + ",";
                encabezado += agregar;
            }

        }

        fr.write(encabezado + "\n");
        for (int i = 0; i < LongitudesRegistros.size(); i++) {
            if (LongitudesRegistros.get(i).getNombreTabla().equals(nombreT)) {
                String cadena[] = LongitudesRegistros.get(i).getNombres().split(",");
                for (int j = 0; j < LongitudesCampos.size(); j++) {
                    if (LongitudesCampos.get(j).getNombre().equals(nombreT)) {
                        if (LongitudesCampos.get(j).getEncriptacion().equals("T")) {
                            cadena[j] = Encriptacion.encriptar(cadena[j]);
                        }
                    }
                }

                for (int j = 0; j < cadena.length; j++) {
                    if (j != cadena.length - 1) {
                        newLine += cadena[j] + ",";
                    } else {
                        newLine += cadena[j];
                    }
                }
                newLine += "\n";

                fr.write(newLine);
                newLine = "";
            }
        }
        serializar_GestorRegistro();
        fr.close();

    }

    public static boolean modificarReg(String nombreT, String campo) {
        int key = 0;
        String regBorrar[];

        for (int i = 0; i < LongitudesCampos.size(); i++) {
            if (LongitudesCampos.get(i).getNombre().equals(nombreT)) {

                key = LongitudesCampos.get(i).getClaveGen();

                for (int j = 0; j < LongitudesRegistros.size(); j++) {
                    if (LongitudesRegistros.get(j).getNombreTabla().equals(nombreT)) {

                        regBorrar = LongitudesRegistros.get(j).getNombres().split(",");
                        if (regBorrar[key].equals(campo)) {

                            LongitudesRegistros.remove(j);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean seleccionarDe(String nombreTabla, String campo, String valor) {
        int key = 0;
        String selecciones[];

        Sel.clear();
        for (int i = 0; i < LongitudesCampos.size(); i++) {
            if (LongitudesCampos.get(i).getNombre().equals(nombreTabla) && LongitudesCampos.get(i).getCamp().equals(campo)) {
                key = LongitudesCampos.get(i).getClaveEsp();
                for (int j = 0; j < LongitudesRegistros.size(); j++) {
                    if (LongitudesRegistros.get(j).getNombreTabla().equals(nombreTabla)) {
                        selecciones = LongitudesRegistros.get(j).getNombres().split(",");
                        if (selecciones[key].equals(valor) || selecciones[key].toLowerCase().equals(valor) || selecciones[key].toUpperCase().equals(valor)) {
                            Sel.add(LongitudesRegistros.get(j).getNombres().split(","));

                        }
                    }
                }
            }
        }
        if (Sel.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean unirPor(String nombreTabla, String campo, String valor) {
        int key = 0;
        int cont = 0;
        if (contUnir == 2) {
            contUnir = 0;
            Unir.clear();
            ContadoresUnir.clear();
        }
        String selecciones[];
        for (int i = 0; i < LongitudesCampos.size(); i++) {
            if (LongitudesCampos.get(i).getNombre().equals(nombreTabla) && LongitudesCampos.get(i).getCamp().equals(campo)) {
                key = LongitudesCampos.get(i).getClaveEsp();
                for (int j = 0; j < LongitudesRegistros.size(); j++) {
                    if (LongitudesRegistros.get(j).getNombreTabla().equals(nombreTabla)) {
                        selecciones = LongitudesRegistros.get(j).getNombres().split(",");
                        if (selecciones[key].equals(valor) || selecciones[key].toLowerCase().equals(valor) || selecciones[key].toUpperCase().equals(valor)) {
                            Unir.add(LongitudesRegistros.get(j).getNombres().split(","));
                            cont = selecciones.length;
                        }
                    }
                }
            }
        }
        if (Unir.isEmpty()) {
            return false;
        } else {
            contUnir++;
            ContadoresUnir.add(cont);
            return true;
        }

    }

    public static void unirArchivo(String titulo) throws IOException {

        CsvWriter csvOutput;
        int band = 0;
        csvOutput = new CsvWriter("unir/" + titulo + ".csv");
        for (int i = 0; i < Unir.size(); i++) {
            String camp[] = (String[]) Unir.get(i);
            for (int j = 0; j < camp.length; j++) {
                if (j == camp.length - 1) {
                    csvOutput.write(camp[j]);
                    csvOutput.endRecord();
                } else {
                    csvOutput.write(camp[j]);
                }

            }
        }
        csvOutput.close();
    }

    public static void serializar_GestorRegistro() throws IOException {
        try (ObjectOutputStream output_LongitudesRegistros = new ObjectOutputStream(new FileOutputStream("serializar/GestorRegistro_LongitudRegistros.dat"))) {
            // LinkedList<Registros> LongitudesRegistros = new LinkedList<Registros>();
            output_LongitudesRegistros.writeObject(LongitudesRegistros);
            output_LongitudesRegistros.close();
        } catch (IOException X) {

        }
    }

    public static void deserializar_GestorRegistro() throws IOException, ClassNotFoundException {
        try (ObjectInputStream input_LongCamp = new ObjectInputStream(new FileInputStream("serializar/GestorRegistro_LongitudRegistros.dat"))) {
            LongitudesRegistros = (ArrayList<Registros>) input_LongCamp.readObject();

        } catch (IOException X) {

        }
        crearTablaDeserializar();
    }

    public static void crearTablaDeserializar() throws IOException {
        for (int i = 0; i < LongitudesRegistros.size(); i++) {
            escribirArchivo(LongitudesRegistros.get(i).getNombres().split(","), LongitudesRegistros.get(i).getNombreTabla());
            contarRegistros(LongitudesRegistros.get(i).getNombreTabla());
            //actualizarTabla(LongitudesRegistros.get(i).getNombreTabla());
            /////////////////////////////////////
            serializar_GestorRegistro();
            /////////////////////////////////////

        }
    }
    /*
    protected void cargarGestorRegistro() throws IOException, ClassNotFoundException {
        try (ObjectInputStream input_LongCamp = new ObjectInputStream(new FileInputStream("serializar/GestorRegistro_LongitudRegistros.dat"))) {
            LongitudesRegistros=(ArrayList<Registros>)input_LongCamp.readObject();
        }catch (IOException X) {

        }
     
        crearTablaDeserializar();
        
    }
     */

}
