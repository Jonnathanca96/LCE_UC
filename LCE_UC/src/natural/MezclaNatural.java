package natural;

import com.csvreader.CsvReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

/**Algoritmo utilizado para el ordenamiento realizado por
 * INTEGRANTES : VANESSA ROMERO BELEN TOLEDO DIEGO PANDO VARGAS GRUPO #2
 * PROGRAMACION 3
 */
public class MezclaNatural {
    private String primeraLinea="";
    private String directorio;
    private String nombreArchivo;
    private int campo;
    private static int formaoOrdenar;

    int cont = 0;
    //Si desea ordenar de forma ascendente se debe de utilizar 1
    //Para poder ordenar de forma descendete se debe de utilizar -1

    MezclaNatural(File archivo, int campo, int formaOrdenar) {
        directorio = archivo.getParent();
        nombreArchivo = archivo.getName();
        this.campo = campo;
        this.formaoOrdenar = formaOrdenar;
    }

    public void ordenar() {

        while (particion()) {
            fusion();
        }
    }

    private boolean particion() {
       boolean primeralinea=true;
        //Se utilizara una logica similar a la del metodo de verificar orden
        //por lo que los indices son declarados de la misma manera
        String actual = null;
        String anterior = null;

        //Variable para controlar el indice del archivo al cual se va a escribir.
        int indice = 0;
        //Variable que determina si existe un cambio de secuencia en el ordenamiento
        boolean hayCambioDeSecuencia = false;
        //Declaracion de los objetos asociados a los archivos y del arreglo de archivos
        PrintWriter archivosAux[] = new PrintWriter[2];
        CsvReader Archivo = null;
        
        try {

            archivosAux[0] = new PrintWriter(directorio + "\\" + "F1.csv");
            archivosAux[1] = new PrintWriter(directorio + "\\" + "F2.csv");
            Archivo = new CsvReader(directorio + "//" + nombreArchivo);

            //Primero, verifica si existen datos en el archivo que se va a leer
            while (Archivo.readRecord())
            {
                
                anterior = actual;
                actual = Archivo.get(campo);
                String linea = Archivo.getRawRecord();
                if (anterior == null) {
                    anterior = actual;
                }

                //Cambio de secuencia. Manipulacion del indice del arreglo de archivos
                if (compararClaves(anterior, actual) == false) {       //si anterior es mayor a actual
                    indice = (indice == 0) ? 1 : 0;
                    //Actualizacion de la variable booleana, esto indica la existencia
                    //de un cambio de secuencia
                    hayCambioDeSecuencia = true;
                }
                archivosAux[indice].println(linea);
            }
            Archivo.close();
            archivosAux[0].close();
            archivosAux[1].close();
        } catch (FileNotFoundException e) {
            System.out.println("Error lectura/escritura");
        } catch (IOException e) {
            System.out.println("Error en la creacion o apertura de los archivos aux");
        }
        //El valor retornado sirve para determinar cuando existe una particion
        return hayCambioDeSecuencia;
    }

    private int contarRegistros(int seleccion) {
        int numRegistros = 0;
        CsvReader archivo = null;
        try {
            if (seleccion == 1) {
                archivo = new CsvReader(directorio + "//" + "F1.csv");
            } else if (seleccion == 2) {
                archivo = new CsvReader(directorio + "//" + "F2.csv");
            } else {
                archivo = new CsvReader(directorio + "//" + nombreArchivo);
            }
            while (archivo.readRecord()) {
                numRegistros += 1;
            }
            archivo.close();
        } catch (IOException e) {
        }

        return numRegistros;
    }

    //Metodo de fusion de los datos obtenidos en el metodo de particion
    private void fusion() {
        //Variables para almacenar los datos del campo que estamos ordenando       
        String[] actual = new String[2];
        String[] anterior = new String[2];
        //Registros
        String[] linea = new String[2];
        boolean[] finArchivo = new boolean[2];
        int indexArchivo = 0;

        CsvReader Auxiliares[] = new CsvReader[2];
        PrintWriter Archivo = null;

        boolean archivoExiste = new File(directorio + "\\" + nombreArchivo).exists();
        if (archivoExiste) {
            //Borramos el archivo principal desordenado
            File archivoDelete = new File(directorio + "\\" + nombreArchivo);
            archivoDelete.delete();
        }

        try {
            //Determina el numero de registros de cada archivo
            int contAux1 = contarRegistros(1);
            int contAux2 = contarRegistros(2);
            Auxiliares[0] = new CsvReader(directorio + "\\" + "F1.csv");
            Auxiliares[1] = new CsvReader(directorio + "\\" + "F2.csv");
            Archivo = new PrintWriter(directorio + "\\" + nombreArchivo);
            boolean entroPrimeraVez = false;
            while (contAux1 > 0 && contAux2 > 0) {

                // inicializar con el primer campo de cada archivo
                if (entroPrimeraVez == false) {
                    Auxiliares[0].readRecord();
                    actual[0] = Auxiliares[0].get(campo);
                    linea[0] = Auxiliares[0].getRawRecord();
                    contAux1--;
                    Auxiliares[1].readRecord();
                    actual[1] = Auxiliares[1].get(campo);
                    linea[1] = Auxiliares[1].getRawRecord();
                    contAux2--;
                    entroPrimeraVez = true;
                }

                // al inicio del procesamiento de dos secuencias, anterior y
                // actual apuntan al primer campo de cada secuencia.
                anterior[0] = actual[0]; // 7
                anterior[1] = actual[1]; //6

                // mezclamos las dos secuencias hasta que una acaba
                while (compararClaves(anterior[0], actual[0]) && compararClaves(anterior[1], actual[1])) {
                    //  asigna el indice comparando las claves actuales
                    indexArchivo = (compararClaves(actual[0], actual[1])) ? 0 : 1;
                    //Escribimos el registro en el archivo original
                    Archivo.println(linea[indexArchivo]);

                    anterior[indexArchivo] = actual[indexArchivo];
                    // salir del while cuando no haya registros, pero ya procesamos
                    // el ultimo nombre del archivo
                    if (indexArchivo == 0) {
                        if (contAux1 > 0) {
                            Auxiliares[0].readRecord();
                            actual[0] = Auxiliares[0].get(campo);
                            linea[0] = Auxiliares[0].getRawRecord();
                            contAux1--;
                        } else {
                            finArchivo[0] = true;
                            break;
                        }
                    }
                    if (indexArchivo == 1) {
                        if (contAux2 > 0) {
                            Auxiliares[1].readRecord();
                            actual[1] = Auxiliares[1].get(campo);
                            linea[1] = Auxiliares[1].getRawRecord();
                            contAux2--;
                        } else {
                            finArchivo[1] = true;
                            break;
                        }
                    }
                }

                // en este punto indexArchivo nos dice que archivo causo
                // que salieramos del while anterior, por lo que tenemos
                // que vaciar el otro archivo
                //Rompe el orden de la secuencia
                if (indexArchivo == 0) {
                    while (compararClaves(anterior[1], actual[1])) {
                        Archivo.println(linea[1]);
                        anterior[1] = actual[1];
                        if (contAux2 > 0) {
                            Auxiliares[1].readRecord();
                            actual[1] = Auxiliares[1].get(campo);
                            linea[1] = Auxiliares[1].getRawRecord();
                            contAux2--;
                        } else {
                            finArchivo[1] = true;
                            break;
                        }
                    }
                }
                if (indexArchivo == 1) {
                    while (compararClaves(anterior[0], actual[0])) {
                        Archivo.println(linea[0]);
                        anterior[0] = actual[0];
                        if (contAux1 > 0) {
                            Auxiliares[0].readRecord();
                            actual[0] = Auxiliares[0].get(campo);
                            linea[0] = Auxiliares[0].getRawRecord();
                            contAux1--;
                        } else {
                            finArchivo[0] = true;
                            break;
                        }
                    }
                }
            }

            // vaciamos los dos archivos en caso de que alguna secuencia
            // haya quedado sola al final del archivo.
            // Para salir del while anterior alguno de los 2 archivos
            // debio terminar, por lo que a lo mas uno de los dos whiles
            // siguientes se ejecutara
            if (!finArchivo[0]) {
                Archivo.println(linea[0]);
                while (contAux1 > 0) {
                    Auxiliares[0].readRecord();
                    Archivo.println(Auxiliares[0].getRawRecord());
                    contAux1--;
                }
            }

            if (!finArchivo[1]) {
                Archivo.println(linea[1]);
                while (contAux2 > 0) {
                    Auxiliares[1].readRecord();
                    Archivo.write(Auxiliares[1].getRawRecord());
                    Archivo.println();
                    contAux2--;
                }
            }
            Auxiliares[0].close();
            Auxiliares[1].close();
            Archivo.close();

        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    //  METODO QUE COMPARA LAS CLAVES DEPENDIENDO EL CAMPO
    //  RETORNA TRUE SI CLAVE_1<=CLAVE_2
    boolean compararClaves(String clave1, String clave2) {
        //  PREPARO LA CONDICION DEPENDIENDO EL CAMPO QUE SEA...
        int condicion1 = 0;
        int condicion2 = 0;
        if (formaoOrdenar == 1) {
            if (clave1.compareTo(clave2) <= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            if (clave1.compareTo(clave2) >= 0) {
                return true;
            } else {
                return false;
            }

        }
    }
   /* void primeraLinea() throws FileNotFoundException{
        CsvReader Archivo=null;
        Archivo = new CsvReader(directorio + "//" + nombreArchivo);
        primeraLinea=Archivo.readRecord();
    }*/

}
