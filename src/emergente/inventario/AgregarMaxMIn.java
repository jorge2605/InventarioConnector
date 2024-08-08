package emergente.inventario;

import Conexiones.Conexion;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class AgregarMaxMIn extends javax.swing.JDialog {

    TextAutoCompleter au;
    
    public final void setCodigos(){
        try{
            au = new TextAutoCompleter(txtCodigo);
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            String sql = "select * from inventario order by codigo asc";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                au.addItem(rs.getString("codigo"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error: "+e, "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void limpiarDatos(){
        txtCodigo.setText("");
        txtMinimos.setText("");
        txtMaximos.setText("");
    }
    
    public AgregarMaxMIn(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setCodigos();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtCodigo = new javax.swing.JTextField();
        lblCodigo = new javax.swing.JLabel();
        lblMaximos = new javax.swing.JLabel();
        lblMinimos = new javax.swing.JLabel();
        txtMaximos = new javax.swing.JTextField();
        txtMinimos = new javax.swing.JTextField();
        panelSalida = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout(0, 20));

        jLabel11.setFont(new java.awt.Font("Lexend", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 204));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Agregar Maximos y Minimos");
        jPanel1.add(jLabel11, java.awt.BorderLayout.NORTH);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWeights = new double[] {1.0, 1.0};
        jPanel2.setLayout(jPanel2Layout);

        txtCodigo.setBackground(new java.awt.Color(255, 255, 255));
        txtCodigo.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCodigo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel2.add(txtCodigo, gridBagConstraints);

        lblCodigo.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblCodigo.setForeground(new java.awt.Color(153, 153, 153));
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCodigo.setText("Codigo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel2.add(lblCodigo, gridBagConstraints);

        lblMaximos.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblMaximos.setForeground(new java.awt.Color(153, 153, 153));
        lblMaximos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaximos.setText("Maximos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 2, 20);
        jPanel2.add(lblMaximos, gridBagConstraints);

        lblMinimos.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblMinimos.setForeground(new java.awt.Color(153, 153, 153));
        lblMinimos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMinimos.setText("Minimos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 2, 20);
        jPanel2.add(lblMinimos, gridBagConstraints);

        txtMaximos.setBackground(new java.awt.Color(255, 255, 255));
        txtMaximos.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtMaximos.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel2.add(txtMaximos, gridBagConstraints);

        txtMinimos.setBackground(new java.awt.Color(255, 255, 255));
        txtMinimos.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtMinimos.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel2.add(txtMinimos, gridBagConstraints);

        panelSalida.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardar.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(51, 51, 51));
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(null);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setPreferredSize(new java.awt.Dimension(150, 30));
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
        panelSalida.add(btnGuardar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 11, 0);
        jPanel2.add(panelSalida, gridBagConstraints);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        panelSalida.setBackground(new Color(255, 222, 0));
        btnGuardar.setForeground(Color.white);
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void btnGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseExited
        panelSalida.setBackground(Color.white);
        btnGuardar.setForeground(new Color(54, 54, 54));
    }//GEN-LAST:event_btnGuardarMouseExited

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(txtCodigo.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debes llenar el campo de codigo","Advertencia",JOptionPane.WARNING_MESSAGE);
        }else if(txtMaximos.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debes llenar el campo de maximos","Advertencia",JOptionPane.WARNING_MESSAGE);
        }else if(txtMinimos.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debes llenar el campo de minimos","Advertencia",JOptionPane.WARNING_MESSAGE);
        }else{
            try{
                Connection con;
                Conexion con1 = new Conexion();
                con = con1.getConnection();
                String sql = "update inventario set maximos = ?, minimos = ? where codigo = ?";
                PreparedStatement pst = con.prepareStatement(sql);
                
                pst.setDouble(1, Double.parseDouble(txtMaximos.getText()));
                pst.setDouble(2, Double.parseDouble(txtMinimos.getText()));
                pst.setString(3, (txtCodigo.getText()));
                
                int n = pst.executeUpdate();
                
                if(n > 0){
                    JOptionPane.showMessageDialog(this, "Datos actualizados correctamente");
                }
                
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, "Error: " + e,"error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AgregarMaxMIn dialog = new AgregarMaxMIn(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblMaximos;
    private javax.swing.JLabel lblMinimos;
    private javax.swing.JPanel panelSalida;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtMaximos;
    private javax.swing.JTextField txtMinimos;
    // End of variables declaration//GEN-END:variables
}
