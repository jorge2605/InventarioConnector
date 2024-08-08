package emergente.inventario;

import Conexiones.Conexion;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AgregarArticulo extends javax.swing.JDialog implements FocusListener {

    public void setBordeA(JComponent field) {
        field.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 222, 0)));
    }

    public void setBordeG(JComponent field) {
        field.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
    }

    public void limpiarDatos(){
        txtCantidad.setText("");
        txtDescripcion.setText("");
        txtCodigo.setText("");
        txtUbicacion.setText("");
        txtMaximos.setText("");
        txtMinimos.setText("");
        txtProveedor.setText("");
    }
    
    public AgregarArticulo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtCantidad.addFocusListener(this);
        txtCodigo.addFocusListener(this);
        txtDescripcion.addFocusListener(this);
        txtMaximos.addFocusListener(this);
        txtMinimos.addFocusListener(this);
        txtProveedor.addFocusListener(this);
        txtUM.addFocusListener(this);
        txtUbicacion.addFocusListener(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblDesc = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        lblum = new javax.swing.JLabel();
        txtUM = new javax.swing.JTextField();
        lblProveedor = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        lblUbicacion = new javax.swing.JLabel();
        txtUbicacion = new javax.swing.JTextField();
        lblMaximos = new javax.swing.JLabel();
        txtMaximos = new javax.swing.JTextField();
        lblMinimos = new javax.swing.JLabel();
        txtMinimos = new javax.swing.JTextField();
        panelSalida = new javax.swing.JPanel();
        btnSalida = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(496, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout(20, 20));

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 204));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Agregar articulo inventario");
        jPanel1.add(jLabel11, java.awt.BorderLayout.NORTH);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWeights = new double[] {0.0, 1.0, 1.0};
        jPanel2Layout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        jPanel2.setLayout(jPanel2Layout);

        lblCodigo.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblCodigo.setForeground(new java.awt.Color(153, 153, 153));
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCodigo.setText("Codigo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 20);
        jPanel2.add(lblCodigo, gridBagConstraints);

        txtCodigo.setBackground(new java.awt.Color(255, 255, 255));
        txtCodigo.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCodigo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 20);
        jPanel2.add(txtCodigo, gridBagConstraints);

        lblDesc.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblDesc.setForeground(new java.awt.Color(153, 153, 153));
        lblDesc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDesc.setText("Descripcion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(13, 0, 13, 20);
        jPanel2.add(lblDesc, gridBagConstraints);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 20);
        jPanel2.add(jScrollPane1, gridBagConstraints);

        lblCantidad.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblCantidad.setForeground(new java.awt.Color(153, 153, 153));
        lblCantidad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCantidad.setText("Cantidad");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 20);
        jPanel2.add(lblCantidad, gridBagConstraints);

        txtCantidad.setBackground(new java.awt.Color(255, 255, 255));
        txtCantidad.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCantidad.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 20);
        jPanel2.add(txtCantidad, gridBagConstraints);

        lblum.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblum.setForeground(new java.awt.Color(153, 153, 153));
        lblum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblum.setText("UM");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 20, 3, 20);
        jPanel2.add(lblum, gridBagConstraints);

        txtUM.setBackground(new java.awt.Color(255, 255, 255));
        txtUM.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtUM.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 20, 3, 20);
        jPanel2.add(txtUM, gridBagConstraints);

        lblProveedor.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblProveedor.setForeground(new java.awt.Color(153, 153, 153));
        lblProveedor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblProveedor.setText("Proveedor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 20);
        jPanel2.add(lblProveedor, gridBagConstraints);

        txtProveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtProveedor.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtProveedor.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 20);
        jPanel2.add(txtProveedor, gridBagConstraints);

        lblUbicacion.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblUbicacion.setForeground(new java.awt.Color(153, 153, 153));
        lblUbicacion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblUbicacion.setText("Ubicacion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 20);
        jPanel2.add(lblUbicacion, gridBagConstraints);

        txtUbicacion.setBackground(new java.awt.Color(255, 255, 255));
        txtUbicacion.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtUbicacion.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 20);
        jPanel2.add(txtUbicacion, gridBagConstraints);

        lblMaximos.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblMaximos.setForeground(new java.awt.Color(153, 153, 153));
        lblMaximos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaximos.setText("Maximos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 20);
        jPanel2.add(lblMaximos, gridBagConstraints);

        txtMaximos.setBackground(new java.awt.Color(255, 255, 255));
        txtMaximos.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtMaximos.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 20);
        jPanel2.add(txtMaximos, gridBagConstraints);

        lblMinimos.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblMinimos.setForeground(new java.awt.Color(153, 153, 153));
        lblMinimos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMinimos.setText("Minimos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 20, 3, 20);
        jPanel2.add(lblMinimos, gridBagConstraints);

        txtMinimos.setBackground(new java.awt.Color(255, 255, 255));
        txtMinimos.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtMinimos.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(3, 20, 3, 20);
        jPanel2.add(txtMinimos, gridBagConstraints);

        panelSalida.setBackground(new java.awt.Color(255, 255, 255));

        btnSalida.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btnSalida.setForeground(new java.awt.Color(51, 51, 51));
        btnSalida.setText("Guardar");
        btnSalida.setBorder(null);
        btnSalida.setBorderPainted(false);
        btnSalida.setContentAreaFilled(false);
        btnSalida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalida.setFocusPainted(false);
        btnSalida.setPreferredSize(new java.awt.Dimension(150, 30));
        btnSalida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalidaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalidaMouseExited(evt);
            }
        });
        btnSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalidaActionPerformed(evt);
            }
        });
        panelSalida.add(btnSalida);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 11, 0);
        jPanel2.add(panelSalida, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setText("*");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel2.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 0));
        jLabel2.setText("*");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel2.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 0));
        jLabel3.setText("*");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel2.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 0));
        jLabel4.setText("*");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 0);
        jPanel2.add(jLabel4, gridBagConstraints);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed

    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnSalidaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalidaMouseEntered
        panelSalida.setBackground(new Color(255, 222, 0));
        btnSalida.setForeground(Color.white);
    }//GEN-LAST:event_btnSalidaMouseEntered

    private void btnSalidaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalidaMouseExited
        panelSalida.setBackground(Color.white);
        btnSalida.setForeground(new Color(54, 54, 54));
    }//GEN-LAST:event_btnSalidaMouseExited

    private void btnSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalidaActionPerformed
        if (txtCodigo.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debes llenar el campo de Codigo", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else if (txtDescripcion.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debes llenar el campo de Descripcion", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else if (txtCantidad.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debes llenar el campo de Cantidad", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else if (txtUbicacion.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debes llenar el campo de Ubicacion", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            try{
                Connection con;
                Conexion con1 = new Conexion();
                con = con1.getConnection();
                String sql = "select * from inventario where codigo like '" + txtCodigo.getText() + "'";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                if(rs.next()){
                    JOptionPane.showMessageDialog(this, "Este codigo ya esta dentro de la base de datos","Advertencia",JOptionPane.WARNING_MESSAGE);
                }else{
                    String sql2 = "insert into inventario (codigo, descripcion, cantidad, UM, proveedor, ubicacion, maximos, minimos) values (?,?,?,?,?,?,?,?)";
                    PreparedStatement pst = con.prepareStatement(sql2);
                    
                    double maximos = 0;
                    double minimos = 0;
                    
                    if(!txtMaximos.getText().equals("")){
                        maximos = Double.parseDouble(txtMaximos.getText());
                    }
                    
                    if(!txtMinimos.getText().equals("")){
                        minimos = Double.parseDouble(txtMinimos.getText());
                    }
                    
                    pst.setString(1, txtCodigo.getText());
                    pst.setString(2, txtDescripcion.getText());
                    pst.setString(3, txtCantidad.getText());
                    pst.setString(4, txtUM.getText());
                    pst.setString(5, txtProveedor.getText());
                    pst.setString(6, txtUbicacion.getText());
                    pst.setDouble(7, maximos);
                    pst.setDouble(8, minimos);
                    
                    int n = pst.executeUpdate();
                    
                    if(n > 0){
                        JOptionPane.showMessageDialog(this, "Articulo guardado correctamente");
                        limpiarDatos();
                    }else{
                        JOptionPane.showMessageDialog(this, "El articulo que intentaste agregar no se guardo correctamente","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    
                }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, "Error: "+e,"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnSalidaActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AgregarArticulo dialog = new AgregarArticulo(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblMaximos;
    private javax.swing.JLabel lblMinimos;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JLabel lblUbicacion;
    private javax.swing.JLabel lblum;
    private javax.swing.JPanel panelSalida;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtMaximos;
    private javax.swing.JTextField txtMinimos;
    private javax.swing.JTextField txtProveedor;
    private javax.swing.JTextField txtUM;
    private javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == txtCantidad) {
            setBordeA(txtCantidad);
            lblCantidad.setForeground(new Color(52, 52, 52));
        } else if (e.getSource() == txtCodigo) {
            setBordeA(txtCodigo);
            lblCodigo.setForeground(new Color(52, 52, 52));
        } else if (e.getSource() == txtDescripcion) {
            setBordeA(txtDescripcion);
            lblDesc.setForeground(new Color(52, 52, 52));
        } else if (e.getSource() == txtProveedor) {
            setBordeA(txtProveedor);
            lblProveedor.setForeground(new Color(52, 52, 52));
        } else if (e.getSource() == txtUM) {
            setBordeA(txtUM);
            lblum.setForeground(new Color(52, 52, 52));
        } else if (e.getSource() == txtMaximos) {
            setBordeA(txtMaximos);
            lblMaximos.setForeground(new Color(52, 52, 52));
        } else if (e.getSource() == txtMinimos) {
            setBordeA(txtMinimos);
            lblMinimos.setForeground(new Color(52, 52, 52));
        } else if (e.getSource() == txtUbicacion) {
            setBordeA(txtUbicacion);
            lblUbicacion.setForeground(new Color(52, 52, 52));
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == txtCantidad) {
            setBordeG(txtCantidad);
            lblCantidad.setForeground(new Color(153, 153, 153));
        } else if (e.getSource() == txtCodigo) {
            setBordeG(txtCodigo);
            lblCodigo.setForeground(new Color(153, 153, 153));
        } else if (e.getSource() == txtDescripcion) {
            setBordeG(txtDescripcion);
            lblDesc.setForeground(new Color(153, 153, 153));
        } else if (e.getSource() == txtProveedor) {
            setBordeG(txtProveedor);
            lblProveedor.setForeground(new Color(153, 153, 153));
        } else if (e.getSource() == txtUM) {
            setBordeG(txtUM);
            lblum.setForeground(new Color(153, 153, 153));
        } else if (e.getSource() == txtMaximos) {
            setBordeG(txtMaximos);
            lblMaximos.setForeground(new Color(153, 153, 153));
        } else if (e.getSource() == txtMinimos) {
            setBordeG(txtMinimos);
            lblMinimos.setForeground(new Color(153, 153, 153));
        } else if (e.getSource() == txtUbicacion) {
            setBordeG(txtUbicacion);
            lblUbicacion.setForeground(new Color(153, 153, 153));
        }
    }
}
