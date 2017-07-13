/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package natural;

import java.io.File;
import java.io.IOException;


public class Probar {
    public static void main(String[] args) throws IOException {
        OrdenamientoNatural prueba=new OrdenamientoNatural();
        File archivo=new File("C:\\Users\\Userio\\Desktop\\Pruebas\\nombre_tabla.csv");        
        prueba.Procesar(0, archivo,1);
        
        
    }
}
