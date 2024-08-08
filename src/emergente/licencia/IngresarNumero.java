package emergente.licencia;

import Componentes.ComboBoxItem;
import Componentes.ComboBoxRenderer;
import Conexiones.Conexion;
import Controlador.MensajeriaWhatsapp;
import Controlador.WhatsAppMessageSimple;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class IngresarNumero extends javax.swing.JDialog {

    JComboBox<ComboBoxItem> comboBox;
    
    public final void addCmb(){
        comboBox = new JComboBox<>();
        comboBox.addItem(new ComboBoxItem("+52",new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/mex.png"))));
        comboBox.addItem(new ComboBoxItem("+1",new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/usa.png"))));
        comboBox.setRenderer(new ComboBoxRenderer());
        
        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 7, 10);
        jPanel2.add(comboBox, gridBagConstraints);
    }
    
    public String getLada(){
        if(comboBox.getSelectedIndex() == 0){
            return "+52";
        }else{
            return "+1";
        }
    }
    
    public String getToken(){
        String token = "";
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            String sql = "select * from licencia";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                token = rs.getString("accessToken");
            }
            return token;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error: " + e,"error",JOptionPane.ERROR_MESSAGE);
        }
        return token;
    }
    
    private void enviarMensaje(String telefono) {
        WhatsAppMessageSimple mensaje = new WhatsAppMessageSimple();
        try {
            mensaje.ACCESS_TOKEN = getToken();
            mensaje.send(telefono, "Mensaje de prueba enviado correctamente");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se envio mensaje: "+ex,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public IngresarNumero(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        addCmb();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtTelefono.requestFocusInWindow();
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(644, 170));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel11.setFont(new java.awt.Font("Lexend", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 204));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Ingrese numero de Telefono");
        jPanel1.add(jLabel11, java.awt.BorderLayout.NORTH);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWeights = new double[] {1.0, 2.0};
        jPanel2.setLayout(jPanel2Layout);

        lblTelefono.setBackground(new java.awt.Color(204, 204, 204));
        lblTelefono.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(204, 204, 204));
        lblTelefono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTelefono.setText("Telefono:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(lblTelefono, gridBagConstraints);

        txtTelefono.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefono.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        try {
            txtTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)-###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefono.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        jPanel2.add(txtTelefono, gridBagConstraints);

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 255));
        jButton1.setText("Enviar mensaje de prueba");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255)));
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setFocusPainted(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(16, 7, 16, 7);
        jPanel2.add(jButton1, gridBagConstraints);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(txtTelefono.getText().equals("(   )-   -    ")){
            JOptionPane.showMessageDialog(this, "Debes ingresar un numero de telefono","Advertencia",JOptionPane.WARNING_MESSAGE);
            txtTelefono.requestFocusInWindow();
        }else{
            String telefono = getLada() + (txtTelefono.getText().replace("(", "").replace(")", "").replace("-", ""));
            enviarMensaje(telefono);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                IngresarNumero dialog = new IngresarNumero(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JFormattedTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
