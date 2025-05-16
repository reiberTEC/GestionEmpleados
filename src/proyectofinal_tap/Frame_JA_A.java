/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectofinal_tap;

import Logica.CalendarioEventos;
import Logica.Empleado;
import Logica.calendario;
import java.util.List;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.IDateEditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rafae
 */
public class Frame_JA_A extends javax.swing.JFrame {
    private DefaultTableModel modelo;
    private JTable tablaEmpleados;
    Empleado empleado;
    List<Empleado> listaEmpleados = new ArrayList<>();
    int cambio = 0;
    int salario;
    JLabel labelTexto;
    String nombre;
    String apellido;
    String idEmpleado;
    String departamentoSeleccionado;
    private Set<String> diasOcupados = new HashSet<>(Arrays.asList("2025-05-20", "2025-05-22"));
    private Set<String> diasProhibidos = new HashSet<>(Arrays.asList("2025-05-18", "2025-05-25"));

    
            //  CONSTRUCTOR
    public Frame_JA_A() {
        initComponents();
        jScrollPane1.setVisible(false);
        setLocationRelativeTo(null); //Centrar Frame
        jPanel4.setLayout(null); // permite usar setBounds

    }
    private void CambiarPanel() {
        jPanel4.removeAll(); //limpiar el panel
        if (cambio == 1) {
            REPORTES(); // Si n es 1, mostrar el primer panel
        } else if (cambio == 2) {
            EMPLEADOS(); // Si n es 2, mostrar el segundo panel
        } else if (cambio == 3) {
            VACACIONES(); // si n  es 3, mostrar el tercer panel
        } else if (cambio == 4) {
            CALENDARIO(); // si n  es 4, mostrar el cuarto panel
        }
        jPanel4.revalidate(); //revalidar el PANEL
        jPanel4.repaint(); //volver a dibujar el panel4
    }

    public void REPORTES() {
        JLabel labelTitulo = new JLabel("REPORTES");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        labelTitulo.setBounds(15, 5, 400, 30);
        jPanel4.add(labelTitulo);
    
        jScrollPane1.setVisible(true); // Mostrar la tabla
        jPanel4.add(jScrollPane1);     // Agregarla de nuevo por si se eliminó
    }

    public void EMPLEADOS() {
    JLabel labelTitulo = new JLabel("GESTION DE EMPLEADOS");
    labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));
    labelTitulo.setBounds(15, 5, 400, 30);
    jPanel4.add(labelTitulo);
    
    // Panel para agrupar campos
    JPanel panelCampos = new JPanel();
    panelCampos.setLayout(null);
    panelCampos.setBorder(BorderFactory.createTitledBorder("Datos del Empleado"));
    panelCampos.setBounds(15, 40, 450, 250);
    jPanel4.add(panelCampos);
    
    // Campos del formulario
    JLabel lblNombre = new JLabel("Nombre:");
    lblNombre.setBounds(20, 30, 100, 20);
    panelCampos.add(lblNombre);
    
    JTextField txtNombre = new JTextField();
    txtNombre.setBounds(120, 30, 300, 25);
    panelCampos.add(txtNombre);
    
    JLabel lblApellido = new JLabel("Apellido:");
    lblApellido.setBounds(20, 70, 100, 20);
    panelCampos.add(lblApellido);
    
    JTextField txtApellido = new JTextField();
    txtApellido.setBounds(120, 70, 300, 25);
    panelCampos.add(txtApellido);
    
    JLabel lblCedula = new JLabel("Cedula:");
    lblCedula.setBounds(20, 110, 100, 20);
    panelCampos.add(lblCedula);
    
    JTextField txtCedula = new JTextField();
    txtCedula.setBounds(120, 110, 150, 25);
    panelCampos.add(txtCedula);
    
    JLabel lblDepartamento = new JLabel("Departamento:");
    lblDepartamento.setBounds(20, 150, 100, 20);
    panelCampos.add(lblDepartamento);
    
    JComboBox<String> cmbDepartamento = new JComboBox<>();
    cmbDepartamento.setBounds(120, 150, 200, 25);
    cmbDepartamento.addItem("Seleccione...");
    cmbDepartamento.addItem("Ventas");
    cmbDepartamento.addItem("Contabilidad");
    cmbDepartamento.addItem("Sistemas");
    cmbDepartamento.addItem("Recursos Humanos");
    panelCampos.add(cmbDepartamento);
    
    JLabel lblSalario = new JLabel("Salario:");
    lblSalario.setBounds(20, 190, 100, 20);
    panelCampos.add(lblSalario);
    
    JTextField txtSalario = new JTextField();
    txtSalario.setBounds(120, 190, 150, 25);
    panelCampos.add(txtSalario);
    
//tabla de empleados
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(15, 360, 450, 200);
    jPanel4.add(scrollPane);
    
    JTable tablaEmpleados = new JTable();
    scrollPane.setViewportView(tablaEmpleados);
    
    String[] columnas = {"ID", "Nombre", "Apellido", "Cedula", "Departamento", "Salario"};
    modelo = new DefaultTableModel(columnas, 0);
    tablaEmpleados.setModel(modelo);
    
    //Botones
    JPanel panelBotones = new JPanel();
    panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
    panelBotones.setBounds(15, 300, 450, 50);
    jPanel4.add(panelBotones);
    
    // Botones de acción
    JButton btnGuardar = new JButton("Guardar");
    btnGuardar.setPreferredSize(new Dimension(100, 30));
    btnGuardar.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        nombre = txtNombre.getText();
        apellido = txtApellido.getText();
        idEmpleado = txtCedula.getText();
        departamentoSeleccionado = (String) cmbDepartamento.getSelectedItem();
        
        try {
            salario = Integer.parseInt(txtSalario.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(jPanel4, "Ingrese un salario válido.");
            return;
        }

        boolean status = true;
        Empleado empleado = new Empleado(nombre, status, departamentoSeleccionado, salario, "", idEmpleado);
        listaEmpleados.add(empleado);
        Object[] fila = {idEmpleado, nombre, apellido, idEmpleado, departamentoSeleccionado, salario};
        modelo.addRow(fila);

        
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        txtSalario.setText("");
        cmbDepartamento.setSelectedItem("Seleccione...");
    }
    });
    panelBotones.add(btnGuardar);
   
   
    JButton btnLimpiar = new JButton("Limpiar");
    btnLimpiar.setPreferredSize(new Dimension(100, 30));
    panelBotones.add(btnLimpiar);
    
    JButton btnCancelar = new JButton("Cancelar");
    btnCancelar.setPreferredSize(new Dimension(100, 30));
    panelBotones.add(btnCancelar);
    
    
}
 
    public void VACACIONES() {
        jPanel4.removeAll(); 
        jPanel4.setLayout(null);

        JLabel titulo = new JLabel("VACACIONES");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBounds(110, 10, 200, 25);
        jPanel4.add(titulo);

        JLabel lblEmpleado = new JLabel("Empleado:");
        lblEmpleado.setBounds(30, 50, 100, 25);
        JTextField txtEmpleado = new JTextField();
        txtEmpleado.setBounds(150, 50, 200, 25);

        JLabel lblInicio = new JLabel("Fecha de inicio:");
        lblInicio.setBounds(30, 90, 120, 25);
        JTextField txtInicio = new JTextField("01/06/2024");
        txtInicio.setBounds(150, 90, 200, 25);

        JLabel lblFin = new JLabel("Fecha de fin:");
        lblFin.setBounds(30, 130, 120, 25);
        JTextField txtFin = new JTextField("10/06/2024");
        txtFin.setBounds(150, 130, 200, 25);

        JLabel lblDias = new JLabel("Dias de vacaiones:");
        lblDias.setBounds(30, 170, 150, 25);
        JTextField txtDias = new JTextField("8");
        txtDias.setBounds(180, 170, 50, 25);

        JLabel lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setBounds(30, 210, 100, 25);
        JTextArea txtDescripcion = new JTextArea();
        JScrollPane scroll = new JScrollPane(txtDescripcion);
        scroll.setBounds(150, 210, 200, 50);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(150, 270, 100, 30);
        btnGuardar.setBackground(new Color(0, 190, 230));
        btnGuardar.setForeground(Color.WHITE);

        // Acción del botón GUARDAR
        btnGuardar.addActionListener(e -> {
            String empleado = txtEmpleado.getText();
            String inicio = txtInicio.getText();
            String fin = txtFin.getText();
            String dias = txtDias.getText();
            String descripcion = txtDescripcion.getText();

            JOptionPane.showMessageDialog(this,
                    "Empleado: " + empleado
                    + "\nFecha inicio: " + inicio
                    + "\nFecha fin: " + fin
                    + "\nDias: " + dias
                    + "\nDescripcion: " + descripcion,
                    "Datos guardados", JOptionPane.INFORMATION_MESSAGE);
        });

        jPanel4.add(lblEmpleado);
        jPanel4.add(txtEmpleado);
        jPanel4.add(lblInicio);
        jPanel4.add(txtInicio);
        jPanel4.add(lblFin);
        jPanel4.add(txtFin);
        jPanel4.add(lblDias);
        jPanel4.add(txtDias);
        jPanel4.add(lblDescripcion);
        jPanel4.add(scroll);
        jPanel4.add(btnGuardar);
        jPanel4.add(titulo);

        jPanel4.revalidate();
        jPanel4.repaint();
    }
    

    public void CALENDARIO() {
        JLabel labelTitulo = new JLabel("CALENDARIO DE EVENTOS");
        labelTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        labelTitulo.setForeground(new Color(0, 51, 102)); // Azul oscuro
        labelTitulo.setBounds(15, 10, 400, 30);
        jPanel4.add(labelTitulo);
    
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NOMBRE DE LA EMPRESA");
        setMaximumSize(new java.awt.Dimension(1280, 720));
        setMinimumSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(72, 202, 228));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CALENDARIO");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 100, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("REPORTES");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 90, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("EMPLEADOS");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 90, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("VACACIONES");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 100, 30));

        jLabel9.setText("Icon");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, -1, -1));

        jLabel10.setText("Icon");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel11.setText("Icon");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel12.setText("Icon");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        jLabel13.setText("icon");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText(".........");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 140, 660));

        jPanel2.setBackground(new java.awt.Color(0, 180, 216));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("NOMBRE DE LA EMPRESA");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 310, 40));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Logo");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 10, 60, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Icon");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 20, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nombre_Usuario");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 20, 120, -1));

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Cerrar Sesion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 20, 110, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 60));

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CATEGORIA", "TITULAR", "ASUNTO", "DESCRIPCION", "FECHA Y HORA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 1120, 600));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 1140, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Login p4 = new Login();
        p4.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    
    //Eventos de los botones para los cambios de ventana
    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        cambio = 1;
        CambiarPanel();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        cambio = 2;
        CambiarPanel();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        cambio = 3;
        CambiarPanel();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        cambio = 4;
        CambiarPanel();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame_JA_A.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame_JA_A.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame_JA_A.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame_JA_A.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame_JA_A().setVisible(true);
            }
        });
    }
}
