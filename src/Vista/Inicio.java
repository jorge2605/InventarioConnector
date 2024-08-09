package Vista;

import Vista.InternalFrame.Admin;
import Vista.InternalFrame.Almacen;
import Vista.InternalFrame.AñadirEmpleado;
import Vista.InternalFrame.Inventario;
import Vista.InternalFrame.Reportes;
import Vista.InternalFrame.Requisiciones;
import Vista.InternalFrame.VerRequisiciones;
import Vista.InternalFrame.licencia;
import java.awt.Color;
import java.awt.EventQueue;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Inicio extends javax.swing.JFrame {

    Requisiciones requisiciones;
    AñadirEmpleado empleado;
    VerRequisiciones verRequisiciones;
    Almacen almacen;
    Inventario inventario;
    Reportes reportes;
    licencia licencia;
    Admin admin;
    JInternalFrame frame;
    String numeroEmpleado;
    String nombreEmpleado;
    int seleccionado;
    
    public void setWhite(JPanel panel, JButton boton){
        pnlAlmacen.setBackground(Color.white);
        btnAlmacen.setForeground(new Color(54,54,54));
        pnlEmpleado.setBackground(Color.white);
        btnEmpleado.setForeground(new Color(54,54,54));
        pnlInventario.setBackground(Color.white);
        btnInventario.setForeground(new Color(54,54,54));
        pnlRequisiciones.setBackground(Color.white);
        btnRequisiciones.setForeground(new Color(54,54,54));
        pnlVerRequisiciones.setBackground(Color.white);
        btnVerRequisiciones.setForeground(new Color(54,54,54));
        pnlReportes.setBackground(Color.white);
        btnReportes.setForeground(new Color(54,54,54));
        pnlLicencia.setBackground(Color.white);
        btnLicencia.setForeground(new Color(54,54,54));
        pnlAdmin.setBackground(Color.white);
        btnAdmin.setForeground(new Color(54,54,54));
        panel.setBackground(new Color(255,222,0));
        boton.setForeground(Color.gray);
    }
    
    public void setWhite(){
        pnlAlmacen.setBackground(Color.white);
        btnAlmacen.setForeground(new Color(54,54,54));
        pnlEmpleado.setBackground(Color.white);
        btnEmpleado.setForeground(new Color(54,54,54));
        pnlInventario.setBackground(Color.white);
        btnInventario.setForeground(new Color(54,54,54));
        pnlRequisiciones.setBackground(Color.white);
        btnRequisiciones.setForeground(new Color(54,54,54));
        pnlVerRequisiciones.setBackground(Color.white);
        btnVerRequisiciones.setForeground(new Color(54,54,54));
        pnlReportes.setBackground(Color.white);
        btnReportes.setForeground(new Color(54,54,54));
        pnlLicencia.setBackground(Color.white);
        btnLicencia.setForeground(new Color(54,54,54));
        pnlAdmin.setBackground(Color.white);
        btnAdmin.setForeground(new Color(54,54,54));
    }
    
    public void cerrarFrame(){
        if(frame != null){
            frame.dispose();
        }
    }
    
    public Inicio() {
        initComponents();
        this.setExtendedState(Inicio.MAXIMIZED_BOTH);
        this.setIconImage(new ImageIcon(getClass().getResource("/Recursos/Imagenes/Hubbell_chico.png")).getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        pnlRequisiciones = new javax.swing.JPanel();
        btnRequisiciones = new javax.swing.JButton();
        pnlAlmacen = new javax.swing.JPanel();
        btnAlmacen = new javax.swing.JButton();
        pnlCerrar = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        pnlEmpleado = new javax.swing.JPanel();
        btnEmpleado = new javax.swing.JButton();
        pnlCerrar1 = new javax.swing.JPanel();
        btnCerrar1 = new javax.swing.JButton();
        pnlVerRequisiciones = new javax.swing.JPanel();
        btnVerRequisiciones = new javax.swing.JButton();
        pnlInventario = new javax.swing.JPanel();
        btnInventario = new javax.swing.JButton();
        pnlReportes = new javax.swing.JPanel();
        btnReportes = new javax.swing.JButton();
        pnlLicencia = new javax.swing.JPanel();
        btnLicencia = new javax.swing.JButton();
        pnlAdmin = new javax.swing.JPanel();
        btnAdmin = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWidths = new int[] {0};
        jPanel1Layout.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        jPanel1Layout.columnWeights = new double[] {1.0};
        jPanel1Layout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
        jPanel1.setLayout(jPanel1Layout);

        lblNombre.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(0, 102, 204));
        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombre.setText("<html>\n<div style=\"width: 120px; text-align: center;\">\n<p>Jorge Santacruz</p>\n<p style=\"color: rgb(54, 117, 45)\">Almacen</p>");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lblNombre, gridBagConstraints);

        pnlRequisiciones.setBackground(new java.awt.Color(255, 255, 255));
        pnlRequisiciones.setLayout(new java.awt.BorderLayout());

        btnRequisiciones.setBackground(new java.awt.Color(255, 255, 255));
        btnRequisiciones.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnRequisiciones.setText("Requisiciones");
        btnRequisiciones.setBorder(null);
        btnRequisiciones.setBorderPainted(false);
        btnRequisiciones.setContentAreaFilled(false);
        btnRequisiciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRequisiciones.setFocusPainted(false);
        btnRequisiciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRequisicionesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRequisicionesMouseExited(evt);
            }
        });
        btnRequisiciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRequisicionesActionPerformed(evt);
            }
        });
        pnlRequisiciones.add(btnRequisiciones, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        jPanel1.add(pnlRequisiciones, gridBagConstraints);

        pnlAlmacen.setBackground(new java.awt.Color(255, 255, 255));
        pnlAlmacen.setLayout(new java.awt.BorderLayout());

        btnAlmacen.setBackground(new java.awt.Color(255, 255, 255));
        btnAlmacen.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnAlmacen.setText("Almacen");
        btnAlmacen.setBorder(null);
        btnAlmacen.setBorderPainted(false);
        btnAlmacen.setContentAreaFilled(false);
        btnAlmacen.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAlmacen.setFocusPainted(false);
        btnAlmacen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAlmacenMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAlmacenMouseExited(evt);
            }
        });
        btnAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlmacenActionPerformed(evt);
            }
        });
        pnlAlmacen.add(btnAlmacen, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        jPanel1.add(pnlAlmacen, gridBagConstraints);

        pnlCerrar.setBackground(new java.awt.Color(255, 255, 255));
        pnlCerrar.setLayout(new java.awt.BorderLayout());

        btnCerrar.setBackground(new java.awt.Color(255, 255, 255));
        btnCerrar.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(102, 0, 0));
        btnCerrar.setText("< Cerrar sesion");
        btnCerrar.setBorder(null);
        btnCerrar.setBorderPainted(false);
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrar.setFocusPainted(false);
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarMouseExited(evt);
            }
        });
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        pnlCerrar.add(btnCerrar, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        jPanel1.add(pnlCerrar, gridBagConstraints);

        pnlEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        pnlEmpleado.setLayout(new java.awt.BorderLayout());

        btnEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        btnEmpleado.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnEmpleado.setText("Registrar empleado");
        btnEmpleado.setBorder(null);
        btnEmpleado.setBorderPainted(false);
        btnEmpleado.setContentAreaFilled(false);
        btnEmpleado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEmpleado.setFocusPainted(false);
        btnEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEmpleadoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEmpleadoMouseExited(evt);
            }
        });
        btnEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleadoActionPerformed(evt);
            }
        });
        pnlEmpleado.add(btnEmpleado, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        jPanel1.add(pnlEmpleado, gridBagConstraints);

        pnlCerrar1.setBackground(new java.awt.Color(255, 255, 255));
        pnlCerrar1.setLayout(new java.awt.BorderLayout());

        btnCerrar1.setBackground(new java.awt.Color(255, 255, 255));
        btnCerrar1.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnCerrar1.setForeground(new java.awt.Color(102, 0, 0));
        btnCerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/Hubbell_chico_1.png"))); // NOI18N
        btnCerrar1.setText(" ");
        btnCerrar1.setBorder(null);
        btnCerrar1.setBorderPainted(false);
        btnCerrar1.setContentAreaFilled(false);
        btnCerrar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrar1.setFocusPainted(false);
        btnCerrar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrar1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrar1MouseExited(evt);
            }
        });
        btnCerrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrar1ActionPerformed(evt);
            }
        });
        pnlCerrar1.add(btnCerrar1, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 22;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        jPanel1.add(pnlCerrar1, gridBagConstraints);

        pnlVerRequisiciones.setBackground(new java.awt.Color(255, 255, 255));
        pnlVerRequisiciones.setLayout(new java.awt.BorderLayout());

        btnVerRequisiciones.setBackground(new java.awt.Color(255, 255, 255));
        btnVerRequisiciones.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnVerRequisiciones.setText("Ver requisiciones");
        btnVerRequisiciones.setBorder(null);
        btnVerRequisiciones.setBorderPainted(false);
        btnVerRequisiciones.setContentAreaFilled(false);
        btnVerRequisiciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnVerRequisiciones.setFocusPainted(false);
        btnVerRequisiciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVerRequisicionesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVerRequisicionesMouseExited(evt);
            }
        });
        btnVerRequisiciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerRequisicionesActionPerformed(evt);
            }
        });
        pnlVerRequisiciones.add(btnVerRequisiciones, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        jPanel1.add(pnlVerRequisiciones, gridBagConstraints);

        pnlInventario.setBackground(new java.awt.Color(255, 255, 255));
        pnlInventario.setLayout(new java.awt.BorderLayout());

        btnInventario.setBackground(new java.awt.Color(255, 255, 255));
        btnInventario.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnInventario.setText("Inventario");
        btnInventario.setBorder(null);
        btnInventario.setBorderPainted(false);
        btnInventario.setContentAreaFilled(false);
        btnInventario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInventario.setFocusPainted(false);
        btnInventario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInventarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInventarioMouseExited(evt);
            }
        });
        btnInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventarioActionPerformed(evt);
            }
        });
        pnlInventario.add(btnInventario, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        jPanel1.add(pnlInventario, gridBagConstraints);

        pnlReportes.setBackground(new java.awt.Color(255, 255, 255));
        pnlReportes.setLayout(new java.awt.BorderLayout());

        btnReportes.setBackground(new java.awt.Color(255, 255, 255));
        btnReportes.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnReportes.setText("Reportes");
        btnReportes.setBorder(null);
        btnReportes.setBorderPainted(false);
        btnReportes.setContentAreaFilled(false);
        btnReportes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReportes.setFocusPainted(false);
        btnReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReportesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReportesMouseExited(evt);
            }
        });
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });
        pnlReportes.add(btnReportes, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        jPanel1.add(pnlReportes, gridBagConstraints);

        pnlLicencia.setBackground(new java.awt.Color(255, 255, 255));
        pnlLicencia.setLayout(new java.awt.BorderLayout());

        btnLicencia.setBackground(new java.awt.Color(255, 255, 255));
        btnLicencia.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnLicencia.setText("Licencia");
        btnLicencia.setBorder(null);
        btnLicencia.setBorderPainted(false);
        btnLicencia.setContentAreaFilled(false);
        btnLicencia.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLicencia.setFocusPainted(false);
        btnLicencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLicenciaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLicenciaMouseExited(evt);
            }
        });
        btnLicencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLicenciaActionPerformed(evt);
            }
        });
        pnlLicencia.add(btnLicencia, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        jPanel1.add(pnlLicencia, gridBagConstraints);

        pnlAdmin.setBackground(new java.awt.Color(255, 255, 255));
        pnlAdmin.setLayout(new java.awt.BorderLayout());

        btnAdmin.setBackground(new java.awt.Color(255, 255, 255));
        btnAdmin.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnAdmin.setText("Admin");
        btnAdmin.setBorder(null);
        btnAdmin.setBorderPainted(false);
        btnAdmin.setContentAreaFilled(false);
        btnAdmin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdmin.setFocusPainted(false);
        btnAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAdminMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAdminMouseExited(evt);
            }
        });
        btnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminActionPerformed(evt);
            }
        });
        pnlAdmin.add(btnAdmin, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        jPanel1.add(pnlAdmin, gridBagConstraints);

        jPanel2.add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jDesktopPane1.setBackground(new java.awt.Color(255, 255, 255));
        jDesktopPane1.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                jDesktopPane1ComponentRemoved(evt);
            }
        });
        jDesktopPane1.setLayout(new java.awt.BorderLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/Hubbell.png"))); // NOI18N
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jDesktopPane1.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanel3.add(jDesktopPane1, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRequisicionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRequisicionesMouseEntered
        pnlRequisiciones.setBackground(new Color(255, 222, 0));
        btnRequisiciones.setForeground(Color.gray);
    }//GEN-LAST:event_btnRequisicionesMouseEntered

    private void btnRequisicionesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRequisicionesMouseExited
        if(seleccionado != 1){
            pnlRequisiciones.setBackground(Color.white);
            btnRequisiciones.setForeground(Color.black);
        }
    }//GEN-LAST:event_btnRequisicionesMouseExited

    private void btnAlmacenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlmacenMouseEntered
        pnlAlmacen.setBackground(new Color(255, 222, 0));
        btnAlmacen.setForeground(Color.gray);
    }//GEN-LAST:event_btnAlmacenMouseEntered

    private void btnAlmacenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAlmacenMouseExited
        if(seleccionado != 3){
            pnlAlmacen.setBackground(Color.white);
            btnAlmacen.setForeground(Color.black);
        }
    }//GEN-LAST:event_btnAlmacenMouseExited

    private void btnCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseEntered
        
    }//GEN-LAST:event_btnCerrarMouseEntered

    private void btnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseExited
        
    }//GEN-LAST:event_btnCerrarMouseExited

    private void btnRequisicionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRequisicionesActionPerformed
        cerrarFrame();
        seleccionado = 1;
        requisiciones = new Requisiciones(nombreEmpleado, numeroEmpleado);
        jDesktopPane1.add(requisiciones);
        requisiciones.toFront();
        requisiciones.setLocation(jDesktopPane1.getWidth() / 2 - requisiciones.getWidth() / 2, jDesktopPane1.getHeight() / 2 - requisiciones.getHeight() / 2);
        try{
            requisiciones.setMaximum(true);
        }catch(PropertyVetoException e){
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE,null,e);
        }
        requisiciones.setVisible(true);
        frame = requisiciones;
        setWhite(pnlRequisiciones, btnRequisiciones);
    }//GEN-LAST:event_btnRequisicionesActionPerformed

    private void btnEmpleadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmpleadoMouseEntered
        pnlEmpleado.setBackground(new Color(255, 222, 0));
        btnEmpleado.setForeground(Color.gray);
    }//GEN-LAST:event_btnEmpleadoMouseEntered

    private void btnEmpleadoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEmpleadoMouseExited
        if(seleccionado != 5){
            pnlEmpleado.setBackground(Color.white);
            btnEmpleado.setForeground(Color.black);
        }   
    }//GEN-LAST:event_btnEmpleadoMouseExited

    private void btnEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleadoActionPerformed
        cerrarFrame();
        seleccionado = 5;
        empleado = new AñadirEmpleado();
        jDesktopPane1.add(empleado);
        empleado.toFront();
        empleado.setLocation(jDesktopPane1.getWidth() / 2 - empleado.getWidth() / 2, jDesktopPane1.getHeight() / 2 - empleado.getHeight() / 2);
        try{
            empleado.setMaximum(true);
        }catch(PropertyVetoException e){
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE,null,e);
        }
        empleado.setVisible(true);
        frame = empleado;
        setWhite(pnlEmpleado, btnEmpleado);
    }//GEN-LAST:event_btnEmpleadoActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        int opc = JOptionPane.showConfirmDialog(this, "¿Estas seguro de cerrar esta sesion?");
        if(opc == 0){
            InicioSesion inicio = new InicioSesion();
            inicio.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnCerrar1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrar1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrar1MouseEntered

    private void btnCerrar1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrar1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrar1MouseExited

    private void btnCerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCerrar1ActionPerformed

    private void btnVerRequisicionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerRequisicionesMouseEntered
        pnlVerRequisiciones.setBackground(new Color(255, 222, 0));
        btnVerRequisiciones.setForeground(Color.gray);
    }//GEN-LAST:event_btnVerRequisicionesMouseEntered

    private void btnVerRequisicionesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVerRequisicionesMouseExited
        if(seleccionado != 2){
            pnlVerRequisiciones.setBackground(Color.white);
            btnVerRequisiciones.setForeground(Color.black);
        }
    }//GEN-LAST:event_btnVerRequisicionesMouseExited

    private void btnVerRequisicionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerRequisicionesActionPerformed
        cerrarFrame();
        seleccionado = 2;
        verRequisiciones = new VerRequisiciones( numeroEmpleado);
        jDesktopPane1.add(verRequisiciones);
        verRequisiciones.toFront();
        verRequisiciones.setLocation(jDesktopPane1.getWidth() / 2 - verRequisiciones.getWidth() / 2, jDesktopPane1.getHeight() / 2 - verRequisiciones.getHeight() / 2);
        try{
            verRequisiciones.setMaximum(true);
        }catch(PropertyVetoException e){
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE,null,e);
        }
        verRequisiciones.setVisible(true);
        frame = verRequisiciones;
        setWhite(pnlVerRequisiciones, btnVerRequisiciones);
    }//GEN-LAST:event_btnVerRequisicionesActionPerformed

    private void btnAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlmacenActionPerformed
        cerrarFrame();
        seleccionado = 3;
        almacen = new Almacen( numeroEmpleado);
        jDesktopPane1.add(almacen);
        almacen.toFront();
        almacen.setLocation(jDesktopPane1.getWidth() / 2 - almacen.getWidth() / 2, jDesktopPane1.getHeight() / 2 - almacen.getHeight() / 2);
        try{
            almacen.setMaximum(true);
        }catch(PropertyVetoException e){
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE,null,e);
        }
        almacen.setVisible(true);
        frame = almacen;
        setWhite(pnlAlmacen, btnAlmacen);
    }//GEN-LAST:event_btnAlmacenActionPerformed

    private void btnInventarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInventarioMouseEntered
        pnlInventario.setBackground(new Color(255, 222, 0));
        btnInventario.setForeground(Color.gray);
    }//GEN-LAST:event_btnInventarioMouseEntered

    private void btnInventarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInventarioMouseExited
        if(seleccionado != 4){
            pnlInventario.setBackground(Color.white);
            btnInventario.setForeground(Color.black);
        }
    }//GEN-LAST:event_btnInventarioMouseExited

    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventarioActionPerformed
        cerrarFrame();
        seleccionado = 4;
        inventario = new Inventario( numeroEmpleado, nombreEmpleado);
        jDesktopPane1.add(inventario);
        inventario.toFront();
        inventario.setLocation(jDesktopPane1.getWidth() / 2 - inventario.getWidth() / 2, jDesktopPane1.getHeight() / 2 - inventario.getHeight() / 2);
        try{
            inventario.setMaximum(true);
        }catch(PropertyVetoException e){
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE,null,e);
        }
        inventario.setVisible(true);
        frame = inventario;
        setWhite(pnlInventario, btnInventario);
    }//GEN-LAST:event_btnInventarioActionPerformed

    private void jDesktopPane1ComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jDesktopPane1ComponentRemoved
        setWhite();
        seleccionado = 0;
    }//GEN-LAST:event_jDesktopPane1ComponentRemoved

    private void btnReportesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseEntered
        pnlReportes.setBackground(new Color(255, 222, 0));
        btnReportes.setForeground(Color.gray);
    }//GEN-LAST:event_btnReportesMouseEntered

    private void btnReportesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseExited
        if(seleccionado != 6){
            pnlReportes.setBackground(Color.white);
            btnReportes.setForeground(Color.black);
        }
    }//GEN-LAST:event_btnReportesMouseExited

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        cerrarFrame();
        seleccionado = 6;
        reportes = new Reportes( numeroEmpleado, nombreEmpleado);
        jDesktopPane1.add(reportes);
        reportes.toFront();
        reportes.setLocation(jDesktopPane1.getWidth() / 2 - reportes.getWidth() / 2, jDesktopPane1.getHeight() / 2 - reportes.getHeight() / 2);
        try{
            reportes.setMaximum(true);
        }catch(PropertyVetoException e){
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE,null,e);
        }
        reportes.setVisible(true);
        frame = reportes;
        setWhite(pnlReportes, btnReportes);
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnLicenciaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLicenciaMouseEntered
        pnlLicencia.setBackground(new Color(255, 222, 0));
        btnLicencia.setForeground(Color.gray);
    }//GEN-LAST:event_btnLicenciaMouseEntered

    private void btnLicenciaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLicenciaMouseExited
        if(seleccionado != 7){
            pnlLicencia.setBackground(Color.white);
            btnLicencia.setForeground(Color.black);
        }
    }//GEN-LAST:event_btnLicenciaMouseExited

    private void btnLicenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLicenciaActionPerformed
        cerrarFrame();
        seleccionado = 7;
        licencia = new licencia( numeroEmpleado, nombreEmpleado);
        jDesktopPane1.add(licencia);
        licencia.toFront();
        licencia.setLocation(jDesktopPane1.getWidth() / 2 - licencia.getWidth() / 2, jDesktopPane1.getHeight() / 2 - licencia.getHeight() / 2);
        try{
            licencia.setMaximum(true);
        }catch(PropertyVetoException e){
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE,null,e);
        }
        licencia.setVisible(true);
        frame = licencia;
        setWhite(pnlLicencia, btnLicencia);
    }//GEN-LAST:event_btnLicenciaActionPerformed

    private void btnAdminMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminMouseEntered
        pnlAdmin.setBackground(new Color(255, 222, 0));
        btnAdmin.setForeground(Color.gray);
    }//GEN-LAST:event_btnAdminMouseEntered

    private void btnAdminMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdminMouseExited
        if(seleccionado != 8){
            pnlAdmin.setBackground(Color.white);
            btnAdmin.setForeground(Color.black);
        }
    }//GEN-LAST:event_btnAdminMouseExited

    private void btnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminActionPerformed
        cerrarFrame();
        seleccionado = 8;
        admin = new Admin( numeroEmpleado, nombreEmpleado);
        jDesktopPane1.add(admin);
        admin.toFront();
        admin.setLocation(jDesktopPane1.getWidth() / 2 - admin.getWidth() / 2, jDesktopPane1.getHeight() / 2 - admin.getHeight() / 2);
        try{
            admin.setMaximum(true);
        }catch(PropertyVetoException e){
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE,null,e);
        }
        admin.setVisible(true);
        frame = admin;
        setWhite(pnlAdmin, btnAdmin);
    }//GEN-LAST:event_btnAdminActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAdmin;
    public javax.swing.JButton btnAlmacen;
    public javax.swing.JButton btnCerrar;
    public javax.swing.JButton btnCerrar1;
    public javax.swing.JButton btnEmpleado;
    public javax.swing.JButton btnInventario;
    public javax.swing.JButton btnLicencia;
    public javax.swing.JButton btnReportes;
    public javax.swing.JButton btnRequisiciones;
    public javax.swing.JButton btnVerRequisiciones;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JLabel lblNombre;
    public javax.swing.JPanel pnlAdmin;
    public javax.swing.JPanel pnlAlmacen;
    public javax.swing.JPanel pnlCerrar;
    public javax.swing.JPanel pnlCerrar1;
    public javax.swing.JPanel pnlEmpleado;
    public javax.swing.JPanel pnlInventario;
    public javax.swing.JPanel pnlLicencia;
    public javax.swing.JPanel pnlReportes;
    public javax.swing.JPanel pnlRequisiciones;
    public javax.swing.JPanel pnlVerRequisiciones;
    // End of variables declaration//GEN-END:variables
}
