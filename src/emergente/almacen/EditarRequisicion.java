package emergente.almacen;

import Conexiones.Conexion;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EditarRequisicion extends javax.swing.JDialog {

    public final void limpiarTabla(){
        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "No. Requisicion", "Codigo", "Descripcion", "Cantidad", "UM", "Proveedor", "Precio", "Llego", "OC", "Cant. Rec.", "Ubicacion", "Notas", "Remision"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        
        if (Tabla1.getColumnModel().getColumnCount() > 0) {
        Tabla1.getColumnModel().getColumn(0).setMinWidth(0);
        Tabla1.getColumnModel().getColumn(0).setPreferredWidth(0);
        Tabla1.getColumnModel().getColumn(0).setMaxWidth(0);
}
    }
    
    public void verRequisiciones(){
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            String sql = "select * from requisiciones where NumRequisicion like '" + lblRequi.getText() + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String datos[] = new String[20];
            DefaultTableModel miModelo = (DefaultTableModel) Tabla1.getModel();
            while(rs.next()){
                datos[0] = rs.getString("idrequisiciones");
                datos[1] = rs.getString("NumRequisicion");
                datos[2] = rs.getString("codigo");
                datos[3] = rs.getString("descripcion");
                datos[4] = rs.getString("cantidad");
                datos[5] = rs.getString("UM");
                datos[6] = rs.getString("proveedor");
                datos[7] = rs.getString("precio");
                datos[8] = rs.getString("llego");
                datos[9] = rs.getString("OC");
                datos[10] = rs.getString("cantidadRecibida");
                datos[11] = rs.getString("ubicacion");
                datos[12] = rs.getString("notas");
                datos[13] = rs.getString("remision");
                miModelo.addRow(datos);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error: "+e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public final void setTable(JTable tabla, JScrollPane scroll){
        tabla.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 14));
        tabla.getTableHeader().setOpaque(false);
        tabla.getTableHeader().setBackground(new Color(255, 222, 0));
        tabla.getTableHeader().setForeground(Color.gray);
        tabla.setRowHeight(25);
        tabla.setShowVerticalLines(false);
        tabla.setGridColor(new Color(240,240,240));
        
        scroll.getViewport().setBackground(new Color(255,255,255));
    }
    
    public EditarRequisicion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTable(Tabla1, jScrollPane2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtRequisicion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblRequi = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        pnlGuardar = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1191, 671));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel11.setFont(new java.awt.Font("Lexend", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 204));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Editar Requisicion");
        jPanel1.add(jLabel11, java.awt.BorderLayout.NORTH);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel6Layout = new java.awt.GridBagLayout();
        jPanel6Layout.columnWeights = new double[] {1.0, 0.0};
        jPanel6.setLayout(jPanel6Layout);

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Introduce requisicion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(jLabel2, gridBagConstraints);

        txtRequisicion.setBackground(new java.awt.Color(255, 255, 255));
        txtRequisicion.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtRequisicion.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        txtRequisicion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRequisicionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtRequisicionFocusLost(evt);
            }
        });
        txtRequisicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRequisicionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(7, 20, 7, 20);
        jPanel6.add(txtRequisicion, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Requisicion Selecionada");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(jLabel3, gridBagConstraints);

        lblRequi.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblRequi.setForeground(new java.awt.Color(51, 51, 51));
        lblRequi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRequi.setText("#");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(lblRequi, gridBagConstraints);

        jPanel2.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "No. Requisicion", "Codigo", "Descripcion", "Cantidad", "UM", "Proveedor", "Precio", "Llego", "OC", "Cant. Rec.", "Ubicacion", "Notas", "Remision"
            }
        ));
        Tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Tabla1);
        if (Tabla1.getColumnModel().getColumnCount() > 0) {
            Tabla1.getColumnModel().getColumn(0).setMinWidth(0);
            Tabla1.getColumnModel().getColumn(0).setPreferredWidth(0);
            Tabla1.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        pnlGuardar.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(null);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setPreferredSize(new java.awt.Dimension(100, 25));
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarMouseExited(evt);
            }
        });
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        pnlGuardar.add(btnGuardar);

        jPanel3.add(pnlGuardar);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRequisicionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRequisicionFocusGained
        txtRequisicion.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 222, 0)));
    }//GEN-LAST:event_txtRequisicionFocusGained

    private void txtRequisicionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRequisicionFocusLost
        txtRequisicion.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        revalidate();
        repaint();
    }//GEN-LAST:event_txtRequisicionFocusLost

    private void txtRequisicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRequisicionActionPerformed
        limpiarTabla();
        lblRequi.setText(txtRequisicion.getText());
        verRequisiciones();
    }//GEN-LAST:event_txtRequisicionActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked

    }//GEN-LAST:event_jLabel3MouseClicked

    private void Tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla1MouseClicked
        
    }//GEN-LAST:event_Tabla1MouseClicked

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        pnlGuardar.setBackground(new Color(255, 222, 0));
        btnGuardar.setForeground(Color.gray);
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void btnGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseExited
        pnlGuardar.setBackground(Color.white);
        btnGuardar.setForeground(Color.black);
    }//GEN-LAST:event_btnGuardarMouseExited

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(Tabla1.getRowCount() > 0){
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
//            "No. Requisicion", "Codigo", "Descripcion", "Cantidad", "UM", "Proveedor", "Precio", "Llego", "OC", "Cant. Rec.", "Ubicacion", "Notas", "Remision"
            String sql = "update requisiciones set NumRequisicion = ?, codigo = ?,descripcion = ?, cantidad = ?, UM = ?, proveedor = ?, precio = ?, llego = ?,"
                    + " llego = ?, OC = ?, cantidadRecibida = ?, ubicacion = ?, notas = ?, remision = ? where idrequisiciones = ?";
            try {
                PreparedStatement pst = con.prepareStatement(sql);
                int n = 0;
                for (int i = 0; i < Tabla1.getRowCount(); i++) {
                    try{
                        pst.setString(1, Tabla1.getValueAt(i, 1).toString());
                        pst.setString(2, Tabla1.getValueAt(i, 2).toString());
                        pst.setString(3, Tabla1.getValueAt(i, 3).toString());
                        pst.setString(4, Tabla1.getValueAt(i, 4).toString());
                        pst.setString(5, Tabla1.getValueAt(i, 5).toString());
                        pst.setString(6, Tabla1.getValueAt(i, 6).toString());
                        pst.setString(7, Tabla1.getValueAt(i, 7).toString());
                        pst.setString(8, Tabla1.getValueAt(i, 8).toString());
                        pst.setString(9, Tabla1.getValueAt(i, 9).toString());
                        pst.setString(10, Tabla1.getValueAt(i, 10).toString());
                        pst.setString(11, Tabla1.getValueAt(i, 11).toString());
                        pst.setString(12, Tabla1.getValueAt(i, 12).toString());
                        pst.setString(13, Tabla1.getValueAt(i, 13).toString());
                        pst.setString(14, Tabla1.getValueAt(i, 14).toString());
                        pst.setString(15, Tabla1.getValueAt(i, 0).toString());
                        
                        n += pst.executeUpdate();
                    }catch(SQLException e){
                        JOptionPane.showMessageDialog(this, "No se guardo la fila numero "+(i + 1));
                    }
                }
                if(n > 0){
                    JOptionPane.showMessageDialog(this, "Datos Guardadaos Correctamente");
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditarRequisicion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

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
            java.util.logging.Logger.getLogger(EditarRequisicion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarRequisicion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarRequisicion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarRequisicion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditarRequisicion dialog = new EditarRequisicion(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblRequi;
    private javax.swing.JPanel pnlGuardar;
    private javax.swing.JTextField txtRequisicion;
    // End of variables declaration//GEN-END:variables
}
