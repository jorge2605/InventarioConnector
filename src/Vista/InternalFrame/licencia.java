package Vista.InternalFrame;

import Conexiones.Conexion;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class licencia extends javax.swing.JInternalFrame {

    public void añadirLicencia(String token, String fecha, String id){
        JPanel panel = new javax.swing.JPanel();
        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setLayout(new java.awt.GridLayout());
        panel.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 0, 2, 0, new java.awt.Color(255,222,0)));

        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(7, 50, 7, 50);
        panelLicencias.add(panel, gridBagConstraints);
        
        JLabel label = new JLabel();
        label.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        label.setForeground(new java.awt.Color(51, 51, 51));
        label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label.setText("<html>\n<p style = \"width: 400px;\">\n" + token + "\n</p>");
        label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int opc = JOptionPane.showConfirmDialog(null, "¿Estas seguro de eliminar esta licencia?");
                if(opc == 0){
                    eliminarLicencia(id);
                }
            }
        });
        panel.add(label);
        
        JLabel lab = new javax.swing.JLabel();
        lab.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lab.setForeground(new java.awt.Color(51, 51, 51));
        lab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lab.setText(fecha);
        panel.add(lab);
        
        revalidate();
        repaint();
    }
    
    public final void verLicencias(){
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            String sql = "select * from licencia order by idlicencia desc";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                String token = rs.getString("accessToken");
                String fecha = rs.getString("fecha");
                String id = rs.getString("idlicencia");
                añadirLicencia(token, fecha, id);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error: " + e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void eliminarLicencia(String idlicencia){
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            String sql = "delete from licencia where idlicencia = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, idlicencia);
            
            int n = pst.executeUpdate();
            
            if(n > 0){
                JOptionPane.showMessageDialog(this, "Licencia eliminada correctamente");
                limpiarPanel();
                verLicencias();
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error al eliminar licencia: " + e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void limpiarPanel(){
        panelLicencias.removeAll();
        revalidate();
        repaint();
    }
    
    public licencia(String numEmpleado, String nomEmpleado) {
        initComponents();
        jScrollPane1.getVerticalScrollBar().setUnitIncrement(15);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        verLicencias();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        PanelX = new javax.swing.JPanel();
        lblX = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblLicencia = new javax.swing.JLabel();
        txtLicencia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelLicencias = new javax.swing.JPanel();

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel11.setFont(new java.awt.Font("Lexend", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 204));
        jLabel11.setText("Licencia");
        jPanel3.add(jLabel11);

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

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel5Layout = new java.awt.GridBagLayout();
        jPanel5Layout.columnWidths = new int[] {1};
        jPanel5Layout.columnWeights = new double[] {1.0};
        jPanel5Layout.rowWeights = new double[] {0.0, 0.0, 1.0};
        jPanel5.setLayout(jPanel5Layout);

        lblLicencia.setFont(new java.awt.Font("Roboto Black", 0, 24)); // NOI18N
        lblLicencia.setForeground(new java.awt.Color(204, 204, 204));
        lblLicencia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLicencia.setText("Insertar licencia");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 200, 0, 200);
        jPanel5.add(lblLicencia, gridBagConstraints);

        txtLicencia.setBackground(new java.awt.Color(255, 255, 255));
        txtLicencia.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtLicencia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        txtLicencia.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLicenciaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLicenciaFocusLost(evt);
            }
        });
        txtLicencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLicenciaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.insets = new java.awt.Insets(10, 200, 10, 200);
        jPanel5.add(txtLicencia, gridBagConstraints);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.GridLayout());

        jLabel1.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Licencia");
        jPanel7.add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Roboto Black", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Fecha");
        jPanel7.add(jLabel2);

        jPanel6.add(jPanel7, java.awt.BorderLayout.NORTH);

        panelLicencias.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel8Layout = new java.awt.GridBagLayout();
        jPanel8Layout.columnWeights = new double[] {1.0};
        panelLicencias.setLayout(jPanel8Layout);
        jPanel6.add(panelLicencias, java.awt.BorderLayout.CENTER);

        jScrollPane1.setViewportView(jPanel6);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel5.add(jScrollPane1, gridBagConstraints);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXMouseClicked
        this.dispose();
    }//GEN-LAST:event_lblXMouseClicked

    private void lblXMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXMouseEntered
        PanelX.setBackground(Color.red);
        lblX.setForeground(Color.white);
    }//GEN-LAST:event_lblXMouseEntered

    private void lblXMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblXMouseExited
        PanelX.setBackground(Color.white);
        lblX.setForeground(Color.black);
    }//GEN-LAST:event_lblXMouseExited

    private void txtLicenciaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLicenciaFocusGained
        txtLicencia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255,222,0)));
        lblLicencia.setForeground(new Color(54,54,54));
    }//GEN-LAST:event_txtLicenciaFocusGained

    private void txtLicenciaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLicenciaFocusLost
        txtLicencia.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        lblLicencia.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_txtLicenciaFocusLost

    private void txtLicenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLicenciaActionPerformed
        if(txtLicencia.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debes ingresar la licencia","Error",JOptionPane.ERROR_MESSAGE);
        }else if(txtLicencia.getText().length() <= 100){
            JOptionPane.showMessageDialog(this, "Licencia no valida","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            try{
                Connection con;
                Conexion con1 = new Conexion();
                con = con1.getConnection();
                String sql = "insert into licencia (accessToken, fecha) values (?,?)";
                PreparedStatement pst = con.prepareStatement(sql);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date d = new Date();

                pst.setString(1, txtLicencia.getText());
                pst.setString(2, sdf.format(d));

                int n = pst.executeUpdate();

                if (n > 0) {
                    JOptionPane.showMessageDialog(this, "Licencia Actualizada");
                    limpiarPanel();
                    verLicencias();
                    txtLicencia.setText("");
                }

            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, "Error: " + e,"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_txtLicenciaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelX;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblLicencia;
    private javax.swing.JLabel lblX;
    private javax.swing.JPanel panelLicencias;
    private javax.swing.JTextField txtLicencia;
    // End of variables declaration//GEN-END:variables
}
