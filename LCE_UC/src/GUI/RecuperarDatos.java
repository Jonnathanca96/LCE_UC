/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Datos.Campos;
import Logica.CreadorTabla;
import Logica.InicializadorArchivo;
import Logica.GestorArchivo;
import static Logica.GestorArchivo.LongitudesCampos;
import Logica.GestorRegistro;
import Patrones.TemplateSerializar;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Original Emily Arteaga, Jefferson Arias, Christian Salinas
 * Programacion 3: Estructura de Archivo - Universidad de Cuenca
 * Modificaciones realizadas para un mejor funcionamiento hechas por Jonnathan Campoberde,Ariel Bravo, Vanessa Romero
 */
public class RecuperarDatos extends javax.swing.JFrame {

    private static String PATH_ARCHIVO = "archivos/META_BD.csv";
    public String histo=null;
    /**
     * Creates new form RecuperarDatos
     */
    public RecuperarDatos() {
        initComponents();
        setLocationRelativeTo(null);
        fondoPane();
    }
    
    private void showVentanaPrincipal() throws IOException {
        Principal ventana = new Principal();
        ventana.setVisible(true);
        ventana.setHistorial(histo);
    }
    public void fondoPane(){
        UIManager UI=new UIManager();
        UI.put("OptionPane.background", Color.BLACK);
        UI.put("Panel.background", Color.BLACK);
        UI.put("OptionPane.messageForeground", Color.white);
    }
    public String recuperarHist()throws FileNotFoundException, IOException, ClassNotFoundException{
        String histor="";
        try (ObjectInputStream input_hist = new ObjectInputStream(new FileInputStream("serializar/Principal_Historial.dat"))) {
            histo=(String)input_hist.readObject();
            input_hist.close();
        }catch(IOException x){
            
        }
        return histor;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        bAceptar = new javax.swing.JButton();
        bNuevos = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Recuperar Datos");
        setBackground(new java.awt.Color(0, 0, 51));
        setLocationByPlatform(true);

        jLabel1.setFont(new java.awt.Font("Noto Sans", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("¿Desea recuperar los datos anteriores?");

        bAceptar.setBackground(new java.awt.Color(0, 0, 51));
        bAceptar.setForeground(new java.awt.Color(255, 255, 255));
        bAceptar.setText("Aceptar");
        bAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAceptarActionPerformed(evt);
            }
        });

        bNuevos.setBackground(new java.awt.Color(0, 0, 51));
        bNuevos.setForeground(new java.awt.Color(255, 255, 255));
        bNuevos.setText("Nuevos");
        bNuevos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNuevosActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("Sistema de Base de Datos LCE_UC");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(bAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(bNuevos, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(73, 73, 73))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bNuevos)
                    .addComponent(bAceptar))
                .addGap(50, 50, 50))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAceptarActionPerformed
        
        try {
            
            if ( InicializadorArchivo.recuperarDatos(PATH_ARCHIVO) ) {
                    //            GestorArchivo.desSerializarGestorArchivo();
                //CreadorTabla.DesSerializar_CreadorTabla();\
                    //           GestorRegistro.deserializar_GestorRegistro();
                TemplateSerializar cargar = new GestorArchivo();
                cargar.Deserializar();
                //CreadorTabla.DesSerializar_CreadorTabla();
                histo=recuperarHist();
                JOptionPane.showMessageDialog(null, "Se ha recuperado los datos");
                
            } else {
                JOptionPane.showMessageDialog(null, "Se ha creado un nuevo archivo");
            }
            this.dispose();
            showVentanaPrincipal();
        } catch (IOException ex) {
            Logger.getLogger(RecuperarDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RecuperarDatos.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }//GEN-LAST:event_bAceptarActionPerformed

    private void bNuevosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNuevosActionPerformed
        
        try {
            this.dispose();
            if ( InicializadorArchivo.sobreescribirDatos(PATH_ARCHIVO) ) {
                histo=null;
                JOptionPane.showMessageDialog(null, "Se ha sobreescrito el archivo anterior", null, JOptionPane.PLAIN_MESSAGE);          
            } else {
                histo=null;
                JOptionPane.showMessageDialog(null, "Se ha creado un nuevo archivo", null, JOptionPane.PLAIN_MESSAGE);   
            }
            showVentanaPrincipal();
        } catch (IOException ex) {
            Logger.getLogger(RecuperarDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bNuevosActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAceptar;
    private javax.swing.JButton bNuevos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
