/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Datos.Campos;
import Datos.Registros;
import Logica.CreadorTabla;
import static Logica.CreadorTabla.nombresTablas;
import Logica.EliminadorTabla;
import Logica.GestorArchivo;
import static Logica.GestorArchivo.LongitudesCampos;
import static Logica.GestorArchivo.nombres;
import static Logica.GestorArchivo.serializarGestorArchivo;
import Logica.GestorRegistro;
import static Logica.GestorRegistro.LongitudesRegistros;
import Logica.Time;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import encriptacion.Encriptacion;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import natural.MezclaNatural;
import natural.OrdenamientoNatural;

/**
 *
 * @author Original Emily Arteaga, Jefferson Arias, Christian Salinas
 * Programacion 3: Estructura de Archivo - Universidad de Cuenca
 * Modificaciones realizadas para un mejor funcionamiento hechas por Jonnathan Campoberde,Ariel Bravo, Vanessa Romero
 */
public class Principal extends javax.swing.JFrame {

    private String nombreTabla;
    private String campos;
    private DefaultStyledDocument doc;
    private ListSelectionListener lslBoton;
    private ArrayList<String> hist = new ArrayList();
    public static String historial = "";

    /**
     * Creates new form Principal
     */
    public Principal() throws IOException {
        reconocerpalabras();
        initComponents();
        //setJpane();
        generarTabla();
        setLocationRelativeTo(null);
        txtHistorial.setText(historial);
        btnMostrarTab.setEnabled(false);
        //fondoPane();
    }

    public void fondoPane() {
        UIManager UI = new UIManager();
        UI.put("OptionPane.background", Color.BLACK);
        UI.put("Panel.background", Color.BLACK);
        UI.put("OptionPane.messageForeground", Color.white);
    }

    public void historial() {
        String aux = "";
        for (int i = 1; i < hist.size(); i++) {
            aux = aux + hist.get(i);
        }
        txtHistorial.setText(aux);
    }

    public void setHistorial(String text) {
        historial = text;
        txtHistorial.setText(historial);
    }

    public void generarTabla() throws FileNotFoundException, IOException {
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] tabla = new Object[3];
        tabla[0] = "Nombre";
        tabla[1] = "Número de Registros";
        tabla[2] = "Campos Actuales";

        modelo.setColumnIdentifiers(tabla);

        List<Object[]> listaTablas = GestorArchivo.crearSalida();

        if (listaTablas == null || listaTablas.isEmpty()) {
            this.tSalida.setModel(modelo);
            return;
        }

        for (int i = 0; i < listaTablas.size(); i++) {
            System.out.println("Los valores de las listas es: " + listaTablas.get(i).toString());
            modelo.addRow(listaTablas.get(i));
        }

        this.tSalida.getSelectionModel().removeListSelectionListener(lslBoton);
        this.tSalida.setModel(modelo);

        this.lslBoton = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    nombreTabla = (String) tSalida.getModel().getValueAt(tSalida.getSelectedRow(), 0);
                    System.out.println("Esto esta pasando en agregar: " + (String) tSalida.getModel().getValueAt(tSalida.getSelectedRow(), 0));
                    campos = (String) tSalida.getModel().getValueAt(tSalida.getSelectedRow(), 2);
                    btnMostrarTab.setEnabled(true);
                }
            }
        };

        this.tSalida.getSelectionModel().addListSelectionListener(lslBoton);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btEjecutar = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtHistorial = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tSalida = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtEntradaComando = new javax.swing.JTextPane(doc);
        btnMostrarTab = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        ComandCrearTab = new javax.swing.JMenuItem();
        ComandoElimTab = new javax.swing.JMenuItem();
        ComandoModTab = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        ComandoCrearReg = new javax.swing.JMenuItem();
        ComandoModiReg = new javax.swing.JMenuItem();
        ComandoElimReg = new javax.swing.JMenuItem();
        ComandoSelec = new javax.swing.JMenuItem();
        ComandoJoin = new javax.swing.JMenuItem();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestor SQL UC");
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(1080, 582));
        setSize(new java.awt.Dimension(1090, 542));
        getContentPane().setLayout(null);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botonhistorial.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 160, 200, 50);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botontablas.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(460, 160, 180, 50);

        btEjecutar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btEjecutar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ejecutar.png"))); // NOI18N
        btEjecutar.setBorder(null);
        btEjecutar.setBorderPainted(false);
        btEjecutar.setContentAreaFilled(false);
        btEjecutar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEjecutarActionPerformed(evt);
            }
        });
        getContentPane().add(btEjecutar);
        btEjecutar.setBounds(800, 70, 110, 110);

        txtHistorial.setBackground(new java.awt.Color(0, 0, 0));
        txtHistorial.setColumns(20);
        txtHistorial.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        txtHistorial.setForeground(new java.awt.Color(255, 255, 255));
        txtHistorial.setRows(5);
        jScrollPane1.setViewportView(txtHistorial);

        jScrollPane5.setViewportView(jScrollPane1);

        getContentPane().add(jScrollPane5);
        jScrollPane5.setBounds(50, 210, 390, 270);

        jScrollPane4.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane4.getViewport().setBackground(Color.DARK_GRAY);

        jScrollPane3.setBackground(new java.awt.Color(51, 51, 51));
        jScrollPane3.setForeground(new java.awt.Color(51, 51, 51));
        jScrollPane3.getViewport().setBackground(Color.DARK_GRAY);

        tSalida.setBackground(new java.awt.Color(0, 0, 0));
        tSalida.setFont(new java.awt.Font("Lucida Fax", 1, 14)); // NOI18N
        tSalida.setForeground(new java.awt.Color(255, 255, 255));
        tSalida.setGridColor(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(tSalida);

        jScrollPane4.setViewportView(jScrollPane3);

        getContentPane().add(jScrollPane4);
        jScrollPane4.setBounds(460, 209, 574, 270);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botonIngresoComandos.png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 50, 260, 50);

        txtEntradaComando.setBackground(new java.awt.Color(0, 0, 0));
        txtEntradaComando.setFont(new java.awt.Font("Lucida Fax", 1, 13)); // NOI18N
        txtEntradaComando.setForeground(new java.awt.Color(255, 255, 255));
        txtEntradaComando.setCaretColor(new java.awt.Color(255, 255, 255));
        jScrollPane6.setViewportView(txtEntradaComando);

        getContentPane().add(jScrollPane6);
        jScrollPane6.setBounds(50, 100, 740, 50);

        btnMostrarTab.setBackground(new java.awt.Color(153, 153, 153));
        btnMostrarTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botonMostrar.png"))); // NOI18N
        btnMostrarTab.setToolTipText("");
        btnMostrarTab.setBorder(null);
        btnMostrarTab.setBorderPainted(false);
        btnMostrarTab.setContentAreaFilled(false);
        btnMostrarTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarTabActionPerformed(evt);
            }
        });
        getContentPane().add(btnMostrarTab);
        btnMostrarTab.setBounds(830, 160, 220, 50);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        jLabel5.setText("Sistema de Base de Datos LCE_UC");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(60, 10, 460, 40);

        jMenuBar1.setBackground(new java.awt.Color(51, 51, 51));

        jMenu1.setForeground(new java.awt.Color(0, 0, 0));
        jMenu1.setText("Comandos");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/images.jpg"))); // NOI18N
        jMenu3.setText("TABLAS");

        ComandCrearTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/new-doc-128.png"))); // NOI18N
        ComandCrearTab.setText("CREAR TABLA ...");
        ComandCrearTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComandCrearTabActionPerformed(evt);
            }
        });
        jMenu3.add(ComandCrearTab);

        ComandoElimTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ic_delete_48px-128.png"))); // NOI18N
        ComandoElimTab.setText("ELIMINAR TABLA ...");
        ComandoElimTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComandoElimTabActionPerformed(evt);
            }
        });
        jMenu3.add(ComandoElimTab);

        ComandoModTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit_modify_icon-icons.com_49882.png"))); // NOI18N
        ComandoModTab.setText("MODIFICAR TABLA ...");
        ComandoModTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComandoModTabActionPerformed(evt);
            }
        });
        jMenu3.add(ComandoModTab);

        jMenu1.add(jMenu3);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tests-icon.png"))); // NOI18N
        jMenu2.setText("REGISTROS");

        ComandoCrearReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/new-doc-128.png"))); // NOI18N
        ComandoCrearReg.setText("CREAR REGISTRO ...");
        ComandoCrearReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComandoCrearRegActionPerformed(evt);
            }
        });
        jMenu2.add(ComandoCrearReg);

        ComandoModiReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/edit_modify_icon-icons.com_49882.png"))); // NOI18N
        ComandoModiReg.setText("MODIFICAR REGISTRO ...");
        ComandoModiReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComandoModiRegActionPerformed(evt);
            }
        });
        jMenu2.add(ComandoModiReg);

        ComandoElimReg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/ic_delete_48px-128.png"))); // NOI18N
        ComandoElimReg.setText("ELIMINAR REGISTRO ...");
        ComandoElimReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComandoElimRegActionPerformed(evt);
            }
        });
        jMenu2.add(ComandoElimReg);

        jMenu1.add(jMenu2);

        ComandoSelec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/push-512.png"))); // NOI18N
        ComandoSelec.setText("SELECCIONAR DE ...");
        ComandoSelec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComandoSelecActionPerformed(evt);
            }
        });
        jMenu1.add(ComandoSelec);

        ComandoJoin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/686917-link_connection_chains_network_relationship_tie_attach_attachment_join_bond-256.png"))); // NOI18N
        ComandoJoin.setText("UNIR ...");
        ComandoJoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComandoJoinActionPerformed(evt);
            }
        });
        jMenu1.add(ComandoJoin);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEjecutarActionPerformed
        String comando = txtEntradaComando.getText();

        try {
            if (comando.contains("ELIMINAR TABLA ")) {
                Time nuevotime=new Time();
                nuevotime.inicio();
                if (EliminadorTabla.eliminarTablaArchivo((String) comando.subSequence(15, comando.length()))) {
                    //GestorArchivo.remover((String) comando.subSequence(15, comando.length()));
                    GestorRegistro.EliminarRegistros(comando.substring(15, comando.length()));
                    GestorArchivo.EliminarArchivo(comando.substring(15, comando.length()));
                    historial += "Se ha eliminado la tabla " + (String) comando.subSequence(15, comando.length()) + "\n";
                    txtHistorial.setText(historial);
                    CreadorTabla.serializar_CreadorTabla();
                    GestorRegistro.serializar_GestorRegistro();
                    GestorArchivo.serializarGestorArchivo();
                    generarTabla();

                } else {
                    JOptionPane.showMessageDialog(null, "No se ha eliminado el archivo", "Error", JOptionPane.WARNING_MESSAGE);
                }
                nuevotime.fin();

            } else if (comando.contains("CREAR TABLA ")) {
                Time tiempo1=new Time();
                if (!comando.contains(" CLAVE ") && !comando.contains(" CAMPOS ") && !comando.contains(" LONGITUD ") || comando.length() <= (comando.indexOf(" CAMPOS ") + " CAMPOS ".length())) {
                    JOptionPane.showMessageDialog(null, "No se ha especificado los campos");
                } else if (comando.contains(" ENCRIPTADO") && (comando.indexOf(" ENCRIPTADO") != -1)) {
                    int indiceLongitud = comando.indexOf(" LONGITUD ");

                    if (GestorArchivo.crearTabla((String) comando.subSequence(12, comando.length()))) {
                        JOptionPane.showMessageDialog(null, "Ya existe una tabla con ese nombre o no se puede crear revise los comandos");
                    } else {

                        historial += "Se ha creado la tabla " + (String) comando.subSequence(12, comando.indexOf(" CAMPOS ")) + "\n";
                        txtHistorial.setText(historial);
                        GestorArchivo.serializarGestorArchivo();
                    }
                    generarTabla();
                } else {
                    System.out.println("El comando es:" + comando.subSequence(12, comando.length()));
                    int indiceLongitud = comando.indexOf(" LONGITUD ");
                    if (GestorArchivo.crearTabla((String) comando.subSequence(12, comando.length()))) {
                        JOptionPane.showMessageDialog(null, "Ya existe una tabla con ese nombre o no se puede crear revise los comandos");

                    } else {
                        historial += "Se ha creado la tabla " + (String) comando.subSequence(12, comando.indexOf(" CAMPOS ")) + "\n";
                        txtHistorial.setText(historial);
                        GestorArchivo.serializarGestorArchivo();
                    }
                    generarTabla();

                }
                tiempo1.fin();
            } else if (comando.contains("MODIFICAR TABLA ")) {
                    Time tiempo2=new Time();
                    tiempo2.inicio();
                if (!comando.contains(" CAMPO ") || !comando.contains(" POR ") || comando.length() <= (comando.indexOf(" CAMPO ") + " CAMPO ".length())) {
                    JOptionPane.showMessageDialog(null, "No se ha especificado los campos");
                } else {
                    //MODIFICAR TABLA compania CAMPO nombres POR informacion;
                    String nombreTabla = (String) (comando.subSequence(16, comando.indexOf(" CAMPO ")));
                    String campo = (String) (comando.subSequence(comando.indexOf(" CAMPO ") + 7, comando.indexOf(" POR ")));
                    String actual = (String) (comando.subSequence(comando.lastIndexOf(" POR ") + 5, comando.length()));
                    System.out.println(nombreTabla + "---" + campo + "---" + actual);
                    if (confirmarCambio(nombreTabla)) {
                        if (cambiarCampos(nombreTabla, campo, actual)) {

                            historial += "Se ha Modificado la tabla " + (String) comando.subSequence(16, comando.indexOf(" CAMPO ")) + "\n";
                            txtHistorial.setText(historial);
                            guardarHistorial();
                            Principal pr = new Principal();
                            pr.generarTabla();
                            pr.setVisible(true);
                            this.setVisible(false);

                        } else {
                            JOptionPane.showMessageDialog(null, "No se ha encontrada el campo el cual quiere cambiar");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se puede modificar el nombre de los campos de una tabla que ya tiene registros");
                    }

                }
                tiempo2.fin();
            } else if (comando.contains("INSERTAR EN ")) {
                Time time4=new Time();
                time4.inicio();
                if (!comando.contains(" VALORES ") || comando.length() <= (comando.indexOf(" VALORES ") + " VALORES ".length())) {
                    JOptionPane.showMessageDialog(null, "Comando incorrecto");
                } else {
                    String nombreTabla = (String) (comando.subSequence(12, comando.indexOf(" VALORES ")));
                    String campos = (String) (comando.subSequence(comando.indexOf(" VALORES ") + 9, comando.length()));
                    System.out.println(nombreTabla + "--" + campos);
                    GestorRegistro regis = new GestorRegistro();
                    if (regis.crearTabla(comando,nombreTabla)) {
                        historial += "Se ha creado un nuevo Registro en la tabla " + (String) comando.subSequence(12, comando.indexOf(" VALORES ")) + "\n";
                        txtHistorial.setText(historial);
                        generarTabla();
                    }
                }
                time4.fin();
            } else if (comando.contains("ACTUALIZAR REGISTRO ")) {
                Time tiempo5=new Time();
                tiempo5.inicio();
                if (!comando.contains(" CLAVE ") || !comando.contains(" CAMPO ") || !comando.contains(" POR ")
                        || comando.length() <= (comando.indexOf(" POR ") + " POR ".length())) {
                    JOptionPane.showMessageDialog(null, "No se ha especificado algun comando");
                } else {
                    String nombreTabla = comando.substring(20, comando.indexOf(" CLAVE "));
                    String clave = comando.substring(comando.lastIndexOf(" CLAVE ") + 7, comando.indexOf(" CAMPO "));
                    String campoAnt = comando.substring(comando.lastIndexOf(" CAMPO ") + 7, comando.indexOf(" POR "));
                    String campoNuev = comando.substring(comando.lastIndexOf(" POR ") + 5, comando.length());
                    System.out.println("NOM: " + nombreTabla + " CLAV: " + clave + " ANT: " + campoAnt + " NUEV: " + campoNuev);
                    if (!GestorRegistro.esRepetidoMod(nombreTabla, clave, campoNuev)) {
                        if (GestorRegistro.cambiarRegistro(nombreTabla, clave, campoAnt, campoNuev)) {
                            GestorRegistro.cambiarRegistroArchivo(nombreTabla);
                            /////////////////////////////////////////
                            GestorRegistro.serializar_GestorRegistro();
                            /////////////////////////////////////////
                            historial += "Se ha modificado el registro " + (String) comando.subSequence(18, comando.indexOf(" CLAVE ")) + "\n";
                            txtHistorial.setText(historial);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se pudo realizar el cambio, compruebe valores");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El campo nuevo no puede reemplazar a una clave ya existente");
                    }
                }
                tiempo5.fin();
            } else if (comando.contains("BORRAR REGISTRO ")) {
                Time tiempo6=new Time();
                tiempo6.inicio();
                if (!comando.contains(" CLAVE ") || comando.length() <= (comando.indexOf(" CLAVE ") + " CLAVE ".length())) {
                    JOptionPane.showMessageDialog(null, "Comando BORRAR incompleto");
                } else {
                    String nombreTabla = comando.substring(16, comando.indexOf(" CLAVE "));
                    String borrarReg = comando.substring(comando.indexOf(" CLAVE ") + 7, comando.length());
                    System.out.println("Nom;" + nombreTabla + " Reg: " + borrarReg);
                    if (GestorRegistro.modificarReg(nombreTabla, borrarReg)) {
                        GestorRegistro.cambiarRegistroArchivo(nombreTabla);
                        GestorRegistro.contarRegistros(nombreTabla);
                        GestorRegistro.contarRegistros(nombreTabla);
                        GestorRegistro.actualizarTabla(nombreTabla);
                        /////////////////////////////////////////
                        GestorRegistro.serializar_GestorRegistro();
                        /////////////////////////////////////////
                        generarTabla();
                        historial += "Se ha eliminado el Registro de la tabla " + nombreTabla + "\n";
                        txtHistorial.setText(historial);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha podido eliminar el Registro, revise los valores");
                    }
                }
                tiempo6.fin();
            } else if (comando.contains("SELECCIONAR DE ")) {
                Time tiempo=new Time();
                tiempo.inicio();
                if ((!comando.contains(" DONDE ")) && (!comando.contains(" POR ")) || comando.length() <= (comando.indexOf(" DONDE ") + " DONDE ".length())) {
                    JOptionPane.showMessageDialog(null, "Comando SELECCIONAR INCOMPLETO");
                } else {
                    int campo = 0;
                    String nombreTabla = comando.substring(15, comando.indexOf(" DONDE "));
                    String nombreCampo = comando.substring(comando.indexOf(" DONDE ") + 7, comando.indexOf("="));
                    String valorCampo = comando.substring(comando.indexOf("=") + 1, comando.length());
                    System.out.println("Nom; " + nombreTabla + " Reg: " + nombreCampo + " valor: " + valorCampo);
                    if (comando.contains(" ORDENADO ")) {
                        valorCampo = comando.substring(comando.indexOf("=") + 1, comando.indexOf(" ORDENADO "));
                        File archivo = new File("archivos/" + nombreTabla + ".csv");
                        String formaOrdenar = comando.substring(comando.indexOf(" ORDENADO ") + 10, comando.length());
                        if (GestorRegistro.seleccionarDe(nombreTabla, nombreCampo, valorCampo) && (archivo.exists()) && ((formaOrdenar.equals("asc") || (formaOrdenar.equals("desc"))))) {
                            System.out.println("entro sel");
                            int orden = 0;
                            if (formaOrdenar.equals("asc")) {
                                orden = 1;
                            } else {
                                orden = -1;
                            }
                            for (int i = 0; i < LongitudesCampos.size(); i++) {
                                if (LongitudesCampos.get(i).getNombre().equals(nombreTabla)) {
                                    if (LongitudesCampos.get(i).getCamp().equals(valorCampo)) {
                                        String campoClave = LongitudesCampos.get(i).getKey();
                                        for (int j = 0; j < LongitudesCampos.size(); j++) {
                                            if (LongitudesCampos.get(i).getNombre().equals(nombreTabla)) {
                                                if(LongitudesCampos.get(i).getCamp().equals(campoClave)){
                                                    campo=j;
                                                }
                                            }
                                        }

                                    }

                                }
                            }

                            OrdenamientoNatural mezcla = new OrdenamientoNatural();
                            mezcla.Procesar(campo, archivo, orden);

                            MostrarTabla t = new MostrarTabla(this, rootPaneCheckingEnabled, nombreTabla, 1, null, null, valorCampo);
                            t.generarTabla1(nombreTabla);
                            t.setVisible(true);
                            /////////////////////////////////////////
                            GestorRegistro.serializar_GestorRegistro();
                            /////////////////////////////////////////
                            historial += "Se ha mostrado la tabla : " + nombreTabla + ", con los registros con los campos : " + nombreCampo + "=" + valorCampo + "\n";
                            txtHistorial.setText(historial);

                        } else {
                            JOptionPane.showMessageDialog(this, "Tabla no encontrada o campo ingresado no existe");
                        }
                    } else if (GestorRegistro.seleccionarDe(nombreTabla, nombreCampo, valorCampo)) {
                        System.out.println("entro sel");
                        MostrarTabla t = new MostrarTabla(this, rootPaneCheckingEnabled, nombreTabla, 1, null, null, valorCampo);
                        t.generarTabla1(nombreTabla);
                        t.setVisible(true);
                        /////////////////////////////////////////
                        GestorRegistro.serializar_GestorRegistro();
                        /////////////////////////////////////////
                        historial += "Se ha mostrado la tabla : " + nombreTabla + ", con los registros con los campos : " + nombreCampo + "=" + valorCampo + "\n";
                        txtHistorial.setText(historial);

                    } else {
                        JOptionPane.showMessageDialog(this, "Tabla no encontrada o campo ingresado no existe");
                    }

                }
                tiempo.fin();
            } else if (comando.contains("UNIR ")) {//&& (comando.contains(" ORDENADO "))
                Time tiempo7=new Time();
                tiempo7.inicio();
                if (!comando.contains(" POR ") || comando.length() <= (comando.indexOf(" POR ") + " POR ".length())) {
                    JOptionPane.showMessageDialog(null, "Comando UNIR incompleto");
                } else {
                    String nombreTabla1 = comando.substring(5, comando.indexOf(","));
                    String nombreTabla2 = comando.substring(comando.indexOf(",") + 1, comando.indexOf(" POR "));

                    String nombreCampo = comando.substring(comando.indexOf(" POR ") + 5, comando.indexOf("="));

                    String valorCampo = comando.substring(comando.indexOf("=") + 1, comando.length());
                    System.out.println("Nom1; " + nombreTabla1 + " nombre2: " + nombreTabla2 + " nombreCAM: " + nombreCampo + " valor: " + valorCampo);
                    if (GestorRegistro.unirPor(nombreTabla1, nombreCampo, valorCampo)) {
                        if (GestorRegistro.unirPor(nombreTabla2, nombreCampo, valorCampo)) {
                            System.out.println("entro unir");
                            MostrarTabla t = new MostrarTabla(this, rootPaneCheckingEnabled, nombreTabla, 2, nombreTabla1, nombreTabla2, valorCampo);
                            t.generarTabla2(nombreTabla1, nombreTabla2);

                            String nombreUnir = nombreTabla1 + "," + nombreTabla2 + "," + valorCampo;

                            GestorRegistro.unirArchivo(nombreUnir);

                            t.setVisible(true);
                            /////////////////////////////////////////
                            GestorRegistro.serializar_GestorRegistro();
                            /////////////////////////////////////////
                            historial += "Se ha mostrado la tabla : Union" + nombreTabla1 + " con " + nombreTabla2 + ", con los registros con los campos : " + nombreCampo + "=" + valorCampo + "\n";
                            txtHistorial.setText(historial);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se han encontrado coincidencias, segunda tabla");
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "No se han encontrado coincidencias, primera tabla");
                    }

                }
                tiempo7.fin();
            }else if(comando.contains("AGREGAR ARCHIVO ")){
                Time tiempo8=new Time();
                tiempo8.inicio();
                String nombrearchivo = comando.substring(16, comando.length());
                System.out.println(nombrearchivo);
                
                
                    scriptLeerTabla(nombrearchivo);
                    //agregarRegistros(nombrearchivo);
                tiempo8.fin();
            } else {
                JOptionPane.showMessageDialog(null, "No se ha especificado la acción(CREAR TABLA/ELIMINAR TABLA)");
            }
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btEjecutarActionPerformed

    private void ComandCrearTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComandCrearTabActionPerformed
        // TODO add your handling code here:
        txtEntradaComando.setText("CREAR TABLA nombre_tabla CAMPOS campo1,campoN CLAVE campo1 LONGITUD longitudCampo1,longitudCampoN");
    }//GEN-LAST:event_ComandCrearTabActionPerformed

    private void ComandoJoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComandoJoinActionPerformed
        // TODO add your handling code here:
        txtEntradaComando.setText("UNIR nombre_tabla1,nombre_tabla2 POR nombre_campo = ”Algo” ORDENADO asc/desc VER número_registros");
    }//GEN-LAST:event_ComandoJoinActionPerformed

    private void ComandoElimTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComandoElimTabActionPerformed
        // TODO add your handling code here:
        txtEntradaComando.setText("ELIMINAR TABLA nombre_tabla");
    }//GEN-LAST:event_ComandoElimTabActionPerformed

    private void ComandoModTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComandoModTabActionPerformed
        // TODO add your handling code here:
        txtEntradaComando.setText("MODIFICAR TABLA nombre_tabla CAMPO nombre_campo POR nombre_campo");
    }//GEN-LAST:event_ComandoModTabActionPerformed

    private void ComandoCrearRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComandoCrearRegActionPerformed
        // TODO add your handling code here:
        txtEntradaComando.setText("INSERTAR EN nombre_tabla VALORES vCampo1,vCampo2,vCampoN");
    }//GEN-LAST:event_ComandoCrearRegActionPerformed

    private void ComandoModiRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComandoModiRegActionPerformed
        // TODO add your handling code here:
        txtEntradaComando.setText("ACTUALIZAR REGISTRO nombre_tabla CLAVE valorCampoClave CAMPO campo POR valor_campo_nuevo");
    }//GEN-LAST:event_ComandoModiRegActionPerformed

    private void ComandoElimRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComandoElimRegActionPerformed
        // TODO add your handling code here:
        txtEntradaComando.setText("BORRAR REGISTRO nombre_tabla CLAVE valorCampoClave");
    }//GEN-LAST:event_ComandoElimRegActionPerformed

    private void ComandoSelecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComandoSelecActionPerformed
        // TODO add your handling code here:
        txtEntradaComando.setText("SELECCIONAR DE nombre_tabla DONDE nombre_campo=“Algo” ORDENADO asc/desc VER número_registros");
    }//GEN-LAST:event_ComandoSelecActionPerformed

    private void btnMostrarTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarTabActionPerformed
        // TODO add your handling code here:
        MostrarTabla mostrarTabla = new MostrarTabla(new javax.swing.JFrame(), true, nombreTabla, 3, null, null, null);
        try {
            mostrarTabla.generarTabla3(campos, nombreTabla);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        mostrarTabla.setVisible(true);
    }//GEN-LAST:event_btnMostrarTabActionPerformed

    private boolean confirmarCambio(String nombreTab) throws FileNotFoundException, IOException {
        boolean retorno = false;
        String line;
        String datos[];
        FileReader fr = new FileReader("archivos/META_BD.csv");
        BufferedReader br = new BufferedReader(fr);
        line = "";
        while ((line = br.readLine()) != null) {
            System.out.println("linea modificar ->>> " + line);
            datos = line.split(",");
            if (datos[0].equals("0") && datos[1].equals(nombreTab) && datos[2].equals("0")) {
                System.out.println("es posible");
                retorno = true;
            }
        }
        br.close();
        fr.close();
        return retorno;

    }

    private boolean cambiarCampos(String nomTabla, String campo, String nuevo) throws IOException {

        Iterator<Campos> iterador = LongitudesCampos.iterator();
        boolean band1 = false;

        while (iterador.hasNext()) {
            Campos lineIterador = iterador.next();
            if (lineIterador.getNombre().equals(nomTabla) && lineIterador.getCamp().equals(campo)) {
                band1 = true;
            }
        }
        if (band1) {
            String line = null;
            String[] campos;
            RandomAccessFile file = new RandomAccessFile("archivos/" + nomTabla + ".csv", "rw");
            line = file.readLine();
            campos = line.split(",");
            int longitud = 0;
            while (longitud < campos.length) {
                if (campos[longitud].equals(campo)) {
                    campos[longitud] = nuevo;
                    longitud++;
                }
                longitud++;
            }
            longitud = 0;
            file.seek(0);
            while (longitud < campos.length) {
                if (longitud == campos.length - 1) {
                    file.write(campos[longitud].getBytes());
                    longitud++;
                } else {
                    file.write(campos[longitud].getBytes());
                    file.write(",".getBytes());
                    longitud++;
                }
            }
            file.close();
            line = null;
            String newLine = null;
            //String[] camposmeta;
            String aux = new String();
            int band = 0;
            RandomAccessFile fileMeta = new RandomAccessFile("archivos/META_BD.csv", "rw");
            FileReader fr = new FileReader("archivos/META_BD.csv");
            BufferedReader br = new BufferedReader(fr);
            line = "";
            while ((line = br.readLine()) != null) {
                String camposMeta[] = line.split(",");
                //if(camposMeta[1].equals(nomTabla) && camposMeta[3].contains(campo)){
                if (line.contains(nomTabla) && line.contains(campo)) {

                    newLine = line.replaceAll(campo, nuevo);

                    aux = aux + newLine + "\n";

                } else {

                    aux = aux + line + "\n";

                }
            }
            fileMeta.close();
            br.close();
            fr.close();
            RandomAccessFile fileNew = new RandomAccessFile("archivos/META_BD.csv", "rw");
            fileNew.seek(0);
            fileNew.writeBytes(aux);
            fileNew.close();

            for (int i = 0; i < LongitudesCampos.size(); i++) {

                if (LongitudesCampos.get(i).getCamp().equals(campo) && LongitudesCampos.get(i).getNombre().equals(nomTabla)) {
                    LongitudesCampos.get(i).setCamp(nuevo);
                }
            }
            /////////////////////////////////////////

            GestorArchivo.serializarGestorArchivo();
            GestorRegistro.serializar_GestorRegistro();
            ////////////////////////////////////////
            JOptionPane.showMessageDialog(null, "Edicion Exitosa");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Tabla o Campo no registrados.");
            return false;
        }
    }

    public static void guardarHistorial() throws IOException {
        try (ObjectOutputStream output_historial = new ObjectOutputStream(new FileOutputStream("serializar/Principal_Historial.dat"))) {
            output_historial.writeObject(historial);
            output_historial.close();
            System.out.println("Historial Serializado");
        } catch (IOException x) {

        }
    }

    public void setJpane() {
        JTextPane txt = new JTextPane(doc);
        txtEntradaComando = txt;
        this.add(new JScrollPane(txtEntradaComando));
        txtEntradaComando.setVisible(true);
    }

    private int findLastNonWordChar(String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    private int findFirstNonWordChar(String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }

    public void reconocerpalabras() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        final StyleContext cont = StyleContext.getDefaultStyleContext();
        final AttributeSet attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.BLUE);
        final AttributeSet attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, Color.WHITE);
        doc = new DefaultStyledDocument() {

            public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offset);
                if (before < 0) {
                    before = 0;
                }
                int after = findFirstNonWordChar(text, offset + str.length());
                int wordL = before;
                int wordR = before;

                while (wordR <= after) {
                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                        if (text.substring(wordL, wordR).matches("(\\W)*(CREAR|MODIFICAR|EN|TABLA|DE|CAMPOS|CAMPO|CLAVE|LONGITUD|ENCRIPTADO|ELIMINAR|INSERTAR|VALORES|ACTUALIZAR|REGISTRO|BORRAR|VER|SELECCIONAR|DE|DONDE|POR|ORDENADO|VER|AGREGAR|ARCHIVO)")) {
                            setCharacterAttributes(wordL, wordR - wordL, attr, false);
                        } else {
                            setCharacterAttributes(wordL, wordR - wordL, attrBlack, false);
                        }
                        wordL = wordR;
                    }
                    wordR++;
                }
            }

            public void remove(int offs, int len) throws BadLocationException {
                super.remove(offs, len);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offs);
                if (before < 0) {
                    before = 0;
                }
                int after = findFirstNonWordChar(text, offs);

                if (text.substring(before, after).matches("(\\W)*(private|public|protected)")) {
                    setCharacterAttributes(before, after - before, attr, false);
                } else {
                    setCharacterAttributes(before, after - before, attrBlack, false);
                }
            }
        };
    }

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ComandCrearTab;
    private javax.swing.JMenuItem ComandoCrearReg;
    private javax.swing.JMenuItem ComandoElimReg;
    private javax.swing.JMenuItem ComandoElimTab;
    private javax.swing.JMenuItem ComandoJoin;
    private javax.swing.JMenuItem ComandoModTab;
    private javax.swing.JMenuItem ComandoModiReg;
    private javax.swing.JMenuItem ComandoSelec;
    private javax.swing.JButton btEjecutar;
    private javax.swing.JButton btnMostrarTab;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tSalida;
    private javax.swing.JTextPane txtEntradaComando;
    private javax.swing.JTextArea txtHistorial;
    // End of variables declaration//GEN-END:variables
public  void scriptLeerTabla(String nombreArchivo){
    GestorArchivo gestor=new GestorArchivo();
    GestorRegistro gestorRegistro=new GestorRegistro();
    /*
        String llave = "";
        String camposLongitudes = "";
        File archivoTabla = new File(nombreArchivo);
        File archivoMasterTablas = new File("archivos/META_BD.csv");
        CsvReader lectorArchivoTabla;
        CsvWriter salidaParaMasterTablas;
        try{
            lectorArchivoTabla = new CsvReader(nombreArchivo);
            String[] campos;
            String[] primeraFila;
            if (lectorArchivoTabla.readHeaders()) {
                campos = lectorArchivoTabla.getHeaders();
                lectorArchivoTabla.close();
                String camposComando="";
                String longitudes="";
                for(int i=0;i<campos.length;i++){
                    camposComando+=campos[i]+",";
                    longitudes+="100"+",";
                }
                String nombreTabla=nombreArchivo.substring(nombreArchivo.lastIndexOf("/")+1,nombreArchivo.lastIndexOf("."));
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa    "+longitudes.substring(0, (longitudes.length()-1)));
                return "CREAR TABLA "+nombreTabla+" CAMPOS "+camposComando.substring(0, (camposComando.length()-2))+" CLAVE "+campos[0]+" LONGITUD "+
                        longitudes.substring(0, (longitudes.length()-1));//String comando="CREAR TABLA";
                //llave = campos[0];
                
                        /*StringBuilder textoDelArchivo = new StringBuilder();
                        String lector;
                        long numeroCampos=-1;
                        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nombreTabla)));
                        while ((lector = br.readLine())!=null) {
                            //textoDelArchivo.append((char) lector);
                            numeroCampos++;
                            textoDelArchivo.append(lector+"\n");                        
                        }
                        br.close();
                        System.out.println(textoDelArchivo);
                        
                       
                        
                    }else{
                System.out.println("Archivo vacio");
                return "";
            } 
        } catch (FileNotFoundException ex) {
            System.err.println("Error: No se encuentra el Archivo.");
            return "";
        } catch (IOException ex) {
            System.err.println("Error: No se tienen los permisos.");
            return "";
        }*/
     String nombreTabla = nombreArchivo;
        String llave = "";
        String camposLongitudes = "";
        File archivoTabla = new File(nombreTabla+".csv");
        File archivoMasterTablas = new File("archivos/META_BD.csv");
        CsvReader lectorArchivoTabla;
        CsvWriter salidaParaMasterTablas;
        try{
            lectorArchivoTabla = new CsvReader(nombreArchivo+".csv");
            String[] campos;
            String[] primeraFila;
            if (lectorArchivoTabla.readHeaders()) {
                campos = lectorArchivoTabla.getHeaders();
                if (lectorArchivoTabla.readRecord()) {
                    primeraFila = lectorArchivoTabla.getValues();
                    if (campos.length == primeraFila.length) {
                        //StringBuilder textoDelArchivo = new StringBuilder();
                        String lector;
                        long numeroCampos=-1;
                        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(nombreArchivo+".csv")));
                        while ((lector=br.readLine())!=null) {
                            //textoDelArchivo.append((char) lector);
                            numeroCampos++;
                            Registros reg = new Registros(nombreArchivo.substring(nombreArchivo.lastIndexOf("/")+1,nombreArchivo.length()),lector.split(","),lector.split(",").length,lector);
                            LongitudesRegistros.add(reg);
                           
                            //textoDelArchivo.append(lector+"\n");                        
                        }
                        br.close();
                         gestorRegistro.serializar_GestorRegistro();
                       // System.out.println(textoDelArchivo);
                        String camposArchivo="";
                        for(int i=0;i<campos.length;i++){
                            camposArchivo+=campos[i]+";";
                        }
                        String[] vectorInfoTabla = {"0",nombreArchivo.substring(nombreArchivo.lastIndexOf("/")+1,nombreArchivo.length()),String.valueOf(numeroCampos).toString(),camposArchivo.substring(0, camposArchivo.length()-1)};
                        lectorArchivoTabla.close();
                        CreadorTabla creador=CreadorTabla.getInstancia();
                        if(nombresTablas.contains(nombreArchivo)){
                            JOptionPane.showMessageDialog(null, "Ya existe una tabla con el mismo nombre");
                        }else{
                            nombresTablas.add(nombreArchivo);
                            creador.serializar_CreadorTabla();
                            CsvWriter salidaParaMaster
                                = new CsvWriter(new FileWriter(archivoMasterTablas,
                                        true), ',');
                        
                        
                        salidaParaMaster.writeRecord(vectorInfoTabla);
                        salidaParaMaster.close();
                        nombres = new String[campos.length];
                       for (int i = 0; i < campos.length; i++) {
                           System.out.println("aaaaaaaaaaaaaaaaaaaaaaa");
                       gestor.separarLongitud(campos[i], i, nombreArchivo.substring(nombreArchivo.lastIndexOf("/")+1,nombreArchivo.length()), campos.length, i, 0, campos[0],"1000","F");
                }
                       serializarGestorArchivo();
                       generarTabla(); 
                        }
                        
                    } else {
                        System.err.println("Error: El archivo es incorrecto.");
                    }
                }
            } else {
                System.err.println("Error: el archivo está vacío o es incorrecto.");
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Error: No se encuentra el Archivo.");
        } catch (IOException ex) {
            System.err.println("Error: No se tienen los permisos.");
        }
    }
}
