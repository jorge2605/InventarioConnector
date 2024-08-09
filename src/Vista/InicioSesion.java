package Vista;

import Conexiones.Conexion;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;

public class InicioSesion extends javax.swing.JFrame {

    int x,y;
    
    public void setBordeGris(){
        txtUsuario.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        txtContraseña.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
    }
    
    public void acceso(){
        if(txtUsuario.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debes ingresar tu numero de reloj", "error",JOptionPane.ERROR_MESSAGE);
        }else if(txtContraseña.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debes ingresar las contraseña", "error",JOptionPane.ERROR_MESSAGE);
        }else{
            try{
                Connection con;
                Conexion con1 = new Conexion();
                con = con1.getConnection();
                Statement st = con.createStatement();
                String sql = "select AES_DECRYPT(password,'mi_llave'),name, lastname, employeeNumber, role, position, requisiciones,"
                        + "almacen, entradas, salidas, registro from users where employeeNumber like '" + txtUsuario.getText() + "'";
                ResultSet rs = st.executeQuery(sql);
                String name = null, lastname = "", employee = null, role = "", pass = "";
                boolean requisiciones = false, almacen = false, entradas = false, salidas = false, registro = false;
                while(rs.next()){
                    name = rs.getString("name");
                    lastname = rs.getString("lastname");
                    employee = rs.getString("employeeNumber");
                    role = rs.getString("role");
                    pass = rs.getString("AES_DECRYPT(password,'mi_llave')");
                    requisiciones = rs.getBoolean("requisiciones");
                    almacen = rs.getBoolean("almacen");
                    entradas = rs.getBoolean("entradas");
                    salidas = rs.getBoolean("salidas");
                    registro = rs.getBoolean("registro");
                }

                if(employee == null){
                    JOptionPane.showMessageDialog(this, "El usuario que ingresaste no existe","Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    if(!pass.equals(txtContraseña.getText())){
                        JOptionPane.showMessageDialog(this, "Datos incorrectos","Error",JOptionPane.ERROR_MESSAGE);
                    }else{
                        Inicio inicio = new Inicio();
                        inicio.numeroEmpleado = employee;
                        inicio.nombreEmpleado = name + " " + lastname;
                        inicio.lblNombre.setText("""
                                                 <html><div style="width: 120px; text-align: center;">
                                                 <p>""" + name.toUpperCase() + " " + lastname.toUpperCase() + "</p>\n" +
                                            "<p style=\"color: rgb(54, 117, 45); padding: 10px 0 0 0\">" + role + "</p>");
                        if(!requisiciones){
                            inicio.pnlRequisiciones.setVisible(false);
                            inicio.pnlVerRequisiciones.setVisible(false);
                        }
                        if(!almacen){
                            inicio.pnlAlmacen.setVisible(false);
                            inicio.pnlInventario.setVisible(false);
                        }
                        if(!entradas){
//                            inicio.pnlEntradas.setVisible(false);
                        }
                        if(!salidas){
//                            inicio.pnlSalidas.setVisible(false);
                        }
                        if(!registro){
                            inicio.pnlEmpleado.setVisible(false);
                        }
                        if(!role.equals("Admin")){
                            inicio.pnlLicencia.setVisible(false);
                            inicio.pnlReportes.setVisible(false);
                            inicio.pnlAdmin.setVisible(false);
                        }
                        inicio.setVisible(true);
                        this.dispose();
                    }
                }

            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, "Error al conectarse a la base de datos" + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public InicioSesion() {
        initComponents();
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setIconImage(new ImageIcon(getClass().getResource("/Recursos/Imagenes/Hubbell_chico.png")).getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblPass = new javax.swing.JLabel();
        lblUsu = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        panelIngresar = new javax.swing.JPanel();
        btnIngresar = new javax.swing.JButton();
        txtContraseña = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        PanelX = new javax.swing.JPanel();
        lblX = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1051, 530));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(222, 222, 222), 3, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPass.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        lblPass.setForeground(new java.awt.Color(204, 204, 204));
        lblPass.setText("Contraseña:");
        jPanel5.add(lblPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 290, -1, -1));

        lblUsu.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        lblUsu.setForeground(new java.awt.Color(204, 204, 204));
        lblUsu.setText("Usuario:");
        jPanel5.add(lblUsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 210, -1, -1));

        txtUsuario.setBackground(new java.awt.Color(255, 255, 255));
        txtUsuario.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtUsuario.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusLost(evt);
            }
        });
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        jPanel5.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, 490, 30));

        panelIngresar.setBackground(new java.awt.Color(255, 255, 255));

        btnIngresar.setBackground(new java.awt.Color(255, 255, 255));
        btnIngresar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(225, 172, 5));
        btnIngresar.setText("Ingresar");
        btnIngresar.setBorder(null);
        btnIngresar.setBorderPainted(false);
        btnIngresar.setContentAreaFilled(false);
        btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setPreferredSize(new java.awt.Dimension(100, 25));
        btnIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIngresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIngresarMouseExited(evt);
            }
        });
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        panelIngresar.add(btnIngresar);

        jPanel5.add(panelIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 380, 80, -1));

        txtContraseña.setBackground(new java.awt.Color(255, 255, 255));
        txtContraseña.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtContraseña.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        txtContraseña.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtContraseñaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtContraseñaFocusLost(evt);
            }
        });
        txtContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraseñaActionPerformed(evt);
            }
        });
        jPanel5.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, 490, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/inicio_hubbell.png"))); // NOI18N
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 540, 560));

        jLabel7.setFont(new java.awt.Font("Roboto Medium", 0, 30)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("INICIAR SESION");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 160, 290, 40));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel3MouseDragged(evt);
            }
        });
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel3MousePressed(evt);
            }
        });

        jLabel2.setText(" ");
        jPanel3.add(jLabel2);

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        PanelX.setBackground(new java.awt.Color(255, 255, 255));

        lblX.setFont(new java.awt.Font("Lexend", 1, 12)); // NOI18N
        lblX.setText(" X ");
        lblX.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblXMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblXMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblXMouseExited(evt);
            }
        });
        PanelX.add(lblX);

        jPanel4.add(PanelX);

        jPanel2.add(jPanel4, java.awt.BorderLayout.EAST);

        jPanel5.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 3, 1050, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, -4, 1045, 530));

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarMouseEntered
        panelIngresar.setBackground(new Color(255, 222, 0));
        btnIngresar.setForeground(Color.gray);
    }//GEN-LAST:event_btnIngresarMouseEntered

    private void btnIngresarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIngresarMouseExited
        panelIngresar.setBackground(Color.white);
        btnIngresar.setForeground(new Color(225,172,5));
    }//GEN-LAST:event_btnIngresarMouseExited

    private void txtUsuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusGained
        setBordeGris();
        lblUsu.setForeground(new Color(225,172,5));
        txtUsuario.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 222, 0)));
    }//GEN-LAST:event_txtUsuarioFocusGained

    private void txtUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusLost
        setBordeGris();
        lblUsu.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_txtUsuarioFocusLost

    private void jPanel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_jPanel3MousePressed

    private void jPanel3MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseDragged
        int xx = evt.getXOnScreen();
        int yy = evt.getYOnScreen();
        this.setLocation(xx - (x), yy - y);
    }//GEN-LAST:event_jPanel3MouseDragged

    private void lblXMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXMouseEntered
        PanelX.setBackground(Color.red);
        lblX.setForeground(Color.white);
    }//GEN-LAST:event_lblXMouseEntered

    private void lblXMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXMouseExited
        PanelX.setBackground(Color.white);
        lblX.setForeground(Color.black);
    }//GEN-LAST:event_lblXMouseExited

    private void lblXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblXMouseClicked

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        acceso();
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        acceso();
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void txtContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraseñaActionPerformed
        acceso();
    }//GEN-LAST:event_txtContraseñaActionPerformed

    private void txtContraseñaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContraseñaFocusGained
        setBordeGris();
        lblPass.setForeground(new Color(225,172,5));
        txtContraseña.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 222, 0)));
    }//GEN-LAST:event_txtContraseñaFocusGained

    private void txtContraseñaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContraseñaFocusLost
        setBordeGris();
        lblPass.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_txtContraseñaFocusLost

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
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InicioSesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InicioSesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelX;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblUsu;
    private javax.swing.JLabel lblX;
    private javax.swing.JPanel panelIngresar;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
