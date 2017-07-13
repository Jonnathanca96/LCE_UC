package natural;

import java.io.File;
import java.io.IOException;

/**Algoritmo utilizado para el ordenamiento realizado por
 * INTEGRANTES : VANESSA ROMERO BELEN TOLEDO DIEGO PANDO VARGAS GRUPO #2
 * PROGRAMACION 3
 */
public class OrdenamientoNatural {
    public static void Procesar(int campo, File archivo,int orden) throws IOException
    {
        System.out.println("campo: "+campo);
        MezclaNatural mezcla = new MezclaNatural(archivo,campo,orden);
        mezcla.ordenar();
        
        System.out.println("Mezcla Natural Terminada..");   
    }
}