package emergente.inventario;

import Conexiones.Conexion;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class infoInventario extends javax.swing.JDialog {

    Stack<String> proyectos;
    TextAutoCompleter au;
    public final int RESTA = 1;
    public final int SUMA = 2;
    public final int ACTUALIZAR = 3;
    public boolean guardado = false;
    
    private static class DoubleFilter extends DocumentFilter {
        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + string;

            try {
                Double.parseDouble(newStr);
                super.insertString(fb, offset, string, attr);
            } catch (NumberFormatException e) {
                // Ignorar la inserción si no es un número double válido
            }
        }

        @Override
        public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            String newStr = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

            try {
                Double.parseDouble(newStr);
                super.replace(fb, offset, length, text, attrs);
            } catch (NumberFormatException e) {
                // Ignorar la sustitución si no es un número double válido
            }
        }
    }
    
    public void transaccionBD(String codigo, int transaccion, double cantA){
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            String sql = "select * from inventario where codigo like '"+codigo+"'";
            ResultSet rs = st.executeQuery(sql);
            double cant = 0;
            while(rs.next()){
                try{cant = Double.parseDouble(rs.getString("cantidad"));}catch(Exception e){cant = 0;}
            }
            switch (transaccion) {
                case RESTA:
                    cant = cant - cantA;
                    break;
                case SUMA:
                    cant = cant + cantA;
                    break;
                default:
                    cant = cantA;
                    break;
            }
            if(transaccion == ACTUALIZAR){
                String sql2 = "update inventario set cantidad = ?, ubicacion = ? where codigo = ?";
                PreparedStatement pst = con.prepareStatement(sql2);

                pst.setString(1, String.valueOf(cant));
                pst.setString(2, txtUbicacion.getText());
                pst.setString(3, codigo);

                int n = pst.executeUpdate();

                if(n < 1){
                    JOptionPane.showMessageDialog(this, "DATOS NO GUARDADOS","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                String sql2 = "update inventario set cantidad = ? where codigo = ?";
                PreparedStatement pst = con.prepareStatement(sql2);

                pst.setString(1, String.valueOf(cant));
                pst.setString(2, codigo);

                int n = pst.executeUpdate();

                if(n < 1){
                    JOptionPane.showMessageDialog(this, "DATOS NO GUARDADOS","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "ERROR: "+e,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public boolean guardado(){
        this.setVisible(true);
        return guardado;
    }
    
    public infoInventario(java.awt.Frame parent, boolean modal, Stack proyectos) {
        super(parent, modal);
        initComponents();
        this.proyectos = proyectos;
//        addProyectos();
        ((AbstractDocument) txtCantRetirar.getDocument()).setDocumentFilter(new infoInventario.DoubleFilter());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtAlmacenista = new javax.swing.JTextField();
        txtEmpleado = new javax.swing.JTextField();
        lblEmpleado = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        txtCantStock = new javax.swing.JTextField();
        lblCantidad = new javax.swing.JLabel();
        txtCantRetirar = new javax.swing.JTextField();
        txtProyecto = new javax.swing.JTextField();
        lblMotivo = new javax.swing.JLabel();
        lblUbicacion = new javax.swing.JLabel();
        txtUbicacion = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        pnlGuardar = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        lblTitulo.setFont(new java.awt.Font("Lexend", 1, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 165, 252));
        lblTitulo.setText("Almacen");
        jPanel6.add(lblTitulo);

        jPanel5.add(jPanel6);

        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Almacenista:");

        txtAlmacenista.setEditable(false);
        txtAlmacenista.setBackground(new java.awt.Color(255, 255, 255));
        txtAlmacenista.setFont(new java.awt.Font("Lexend", 0, 12)); // NOI18N
        txtAlmacenista.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAlmacenista.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        txtAlmacenista.setEnabled(false);

        txtEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        txtEmpleado.setFont(new java.awt.Font("Lexend", 0, 12)); // NOI18N
        txtEmpleado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEmpleado.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        txtEmpleado.setEnabled(false);
        txtEmpleado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmpleadoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmpleadoFocusLost(evt);
            }
        });
        txtEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtEmpleadoMouseClicked(evt);
            }
        });
        txtEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpleadoActionPerformed(evt);
            }
        });

        lblEmpleado.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        lblEmpleado.setForeground(new java.awt.Color(204, 204, 204));
        lblEmpleado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmpleado.setText("Empleado:");

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Codigo:");

        txtCodigo.setEditable(false);
        txtCodigo.setBackground(new java.awt.Color(255, 255, 255));
        txtCodigo.setFont(new java.awt.Font("Lexend", 0, 12)); // NOI18N
        txtCodigo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        txtCodigo.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Descripcion:");

        jScrollPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));

        txtDescripcion.setEditable(false);
        txtDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Lexend", 0, 12)); // NOI18N
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(5);
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setEnabled(false);
        jScrollPane1.setViewportView(txtDescripcion);

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Cantidad en stock:");

        txtCantStock.setEditable(false);
        txtCantStock.setBackground(new java.awt.Color(255, 255, 255));
        txtCantStock.setFont(new java.awt.Font("Lexend", 0, 12)); // NOI18N
        txtCantStock.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantStock.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        txtCantStock.setEnabled(false);

        lblCantidad.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        lblCantidad.setForeground(new java.awt.Color(204, 204, 204));
        lblCantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidad.setText("Cantidad a retirar:");

        txtCantRetirar.setBackground(new java.awt.Color(255, 255, 255));
        txtCantRetirar.setFont(new java.awt.Font("Lexend", 0, 12)); // NOI18N
        txtCantRetirar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantRetirar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        txtCantRetirar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCantRetirarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCantRetirarFocusLost(evt);
            }
        });

        txtProyecto.setBackground(new java.awt.Color(255, 255, 255));
        txtProyecto.setFont(new java.awt.Font("Lexend", 0, 12)); // NOI18N
        txtProyecto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtProyecto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        txtProyecto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProyectoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProyectoFocusLost(evt);
            }
        });
        txtProyecto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtProyectoMouseClicked(evt);
            }
        });
        txtProyecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProyectoActionPerformed(evt);
            }
        });

        lblMotivo.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        lblMotivo.setForeground(new java.awt.Color(204, 204, 204));
        lblMotivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMotivo.setText("Motivo:");

        lblUbicacion.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        lblUbicacion.setForeground(new java.awt.Color(204, 204, 204));
        lblUbicacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUbicacion.setText("Ubicacion:");

        txtUbicacion.setBackground(new java.awt.Color(255, 255, 255));
        txtUbicacion.setFont(new java.awt.Font("Lexend", 0, 12)); // NOI18N
        txtUbicacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtUbicacion.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        txtUbicacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUbicacionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUbicacionFocusLost(evt);
            }
        });
        txtUbicacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUbicacionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtUbicacionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtUbicacionMouseExited(evt);
            }
        });
        txtUbicacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUbicacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCodigo)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtProyecto)
                    .addComponent(lblMotivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAlmacenista)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmpleado)
                            .addComponent(lblEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(lblUbicacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCantStock)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCantRetirar)
                            .addComponent(lblCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtUbicacion)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAlmacenista, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblCantidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantStock, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantRetirar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblMotivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProyecto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblUbicacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        pnlGuardar.setBackground(new java.awt.Color(255, 255, 255));
        pnlGuardar.setPreferredSize(new java.awt.Dimension(150, 30));
        pnlGuardar.setLayout(new java.awt.BorderLayout());

        btnGuardar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(51, 51, 51));
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(null);
        btnGuardar.setBorderPainted(false);
        btnGuardar.setContentAreaFilled(false);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
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
        pnlGuardar.add(btnGuardar, java.awt.BorderLayout.CENTER);

        jPanel3.add(pnlGuardar);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtEmpleadoMouseClicked
        txtEmpleado.setText("");
        txtEmpleado.setEnabled(true);
    }//GEN-LAST:event_txtEmpleadoMouseClicked

    private void txtEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpleadoActionPerformed
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            String slq = "select * from users where employeeNumber like '"+txtEmpleado.getText()+"'";
            ResultSet rs = st.executeQuery(slq);
            String empleado = null;
            while(rs.next()){
                empleado = rs.getString("name") + " " + rs.getString("lastname");
            }
            if(empleado != null){
                txtEmpleado.setText(empleado);
                txtEmpleado.setEnabled(false);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "ERROR: "+e,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtEmpleadoActionPerformed

    private void txtEmpleadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmpleadoFocusGained
        txtEmpleado.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255,222,0)));
        lblEmpleado.setForeground(Color.black);
    }//GEN-LAST:event_txtEmpleadoFocusGained

    private void txtEmpleadoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmpleadoFocusLost
        txtEmpleado.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        lblEmpleado.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_txtEmpleadoFocusLost

    private void txtCantRetirarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantRetirarFocusGained
        txtCantRetirar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255,222,0)));
        lblCantidad.setForeground(Color.black);
    }//GEN-LAST:event_txtCantRetirarFocusGained

    private void txtCantRetirarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantRetirarFocusLost
        txtCantRetirar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        lblCantidad.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_txtCantRetirarFocusLost

    private void txtProyectoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProyectoFocusGained
        txtProyecto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255,222,0)));
        lblMotivo.setForeground(Color.black);
    }//GEN-LAST:event_txtProyectoFocusGained

    private void txtProyectoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProyectoFocusLost
        txtProyecto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        lblMotivo.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_txtProyectoFocusLost

    private void txtProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProyectoActionPerformed
        if(proyectos.contains(txtProyecto.getText())){
            txtProyecto.setEnabled(false);
        }
    }//GEN-LAST:event_txtProyectoActionPerformed

    private void txtProyectoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtProyectoMouseClicked
        txtProyecto.setText("");
        txtProyecto.setEnabled(true);
    }//GEN-LAST:event_txtProyectoMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            if(txtCantRetirar.getText().equals("")){
                    JOptionPane.showMessageDialog(this, "Debes llenar el campo de Cantidad","Advertencia",JOptionPane.WARNING_MESSAGE);
            }else{
                if(lblTitulo.getText().equals("Salida")){
                    if(txtEmpleado.getText().equals("")){
                        JOptionPane.showMessageDialog(this, "Debes llenar el campo de Empleado","Advertencia",JOptionPane.WARNING_MESSAGE);
                    }else if(txtProyecto.getText().equals("")){
                        JOptionPane.showMessageDialog(this, "Debes llenar el campo de Proyecto","Advertencia",JOptionPane.WARNING_MESSAGE);
                    }else{
                        String sql = "insert into salidamaterial(empleado, fecha, codigo, cantidad, almacenista,motivo) values(?,?,?,?,?,?)";
                        PreparedStatement pst = con.prepareStatement(sql);

                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                        Date d = new Date();
                        String fecha2 = sdf2.format(d);

                        pst.setString(1, txtEmpleado.getText());
                        pst.setString(2, fecha2);
                        pst.setString(3, txtCodigo.getText());
                        pst.setString(4, txtCantRetirar.getText());
                        pst.setString(5, txtAlmacenista.getText());
                        pst.setString(6, txtProyecto.getText());
                        
                        int n = pst.executeUpdate();

                        if(n > 0){
                            transaccionBD(txtCodigo.getText(), RESTA, Double.parseDouble(txtCantRetirar.getText()));
                            JOptionPane.showMessageDialog(this, "Datos guardados en Salida de material");
                            guardado = true;
                            dispose();
                        }
                    }
                }else if(lblTitulo.getText().equals("Entrada")){
                    String sql = "insert into entradamaterial(fecha, codigo, cantidad, almacenista, motivo) values(?,?,?,?,?)";
                    PreparedStatement pst = con.prepareStatement(sql);

                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                    Date d = new Date();
                    String fecha2 = sdf2.format(d);

                    pst.setString(1, fecha2);
                    pst.setString(2, txtCodigo.getText());
                    pst.setString(3, txtCantRetirar.getText());
                    pst.setString(4, txtAlmacenista.getText());
                    pst.setString(5, txtProyecto.getText());

                    int n = pst.executeUpdate();

                    if(n > 0){
                        transaccionBD(txtCodigo.getText(), SUMA, Double.parseDouble(txtCantRetirar.getText()));
                        JOptionPane.showMessageDialog(this, "Datos guardados en Entrada de material");
                        guardado = true;
                        dispose();
                    }
                }else if(lblTitulo.getText().equals("Actualizar")){
                    String sql = "insert into actualizarmaterial(fecha, codigo, cantidad, almacenista, motivo) values(?,?,?,?,?)";
                    PreparedStatement pst = con.prepareStatement(sql);

                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                    Date d = new Date();
                    String fecha2 = sdf2.format(d);

                    pst.setString(1, fecha2);
                    pst.setString(2, txtCodigo.getText());
                    pst.setString(3, txtCantRetirar.getText());
                    pst.setString(4, txtAlmacenista.getText());
                    pst.setString(5, txtProyecto.getText());

                    int n = pst.executeUpdate();

                    if(n > 0){
                        transaccionBD(txtCodigo.getText(), ACTUALIZAR, Double.parseDouble(txtCantRetirar.getText()));
                        JOptionPane.showMessageDialog(this, "Datos guardados en Actualizacion de material");
                        guardado = true;
                        dispose();
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "Error al seleccionar estado","ERROR",JOptionPane.ERROR_MESSAGE);
                }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "ERROR: "+e,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtUbicacionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUbicacionFocusGained
        txtUbicacion.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255,222,0)));
        lblUbicacion.setForeground(Color.black);
    }//GEN-LAST:event_txtUbicacionFocusGained

    private void txtUbicacionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUbicacionFocusLost
        txtUbicacion.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(153, 153, 153)));
        lblUbicacion.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_txtUbicacionFocusLost

    private void txtUbicacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUbicacionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUbicacionMouseClicked

    private void txtUbicacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUbicacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUbicacionActionPerformed

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        pnlGuardar.setBackground(new Color(255,222,0));
        btnGuardar.setForeground(new Color(204,204,204));
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void btnGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseExited
        pnlGuardar.setBackground(new Color(255,255,255));
        btnGuardar.setForeground(new Color(54,54,54));
    }//GEN-LAST:event_btnGuardarMouseExited

    private void txtUbicacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUbicacionMouseEntered
        
    }//GEN-LAST:event_txtUbicacionMouseEntered

    private void txtUbicacionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUbicacionMouseExited
        
    }//GEN-LAST:event_txtUbicacionMouseExited

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                infoInventario dialog = new infoInventario(new javax.swing.JFrame(), true,null);
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
    public javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblEmpleado;
    private javax.swing.JLabel lblMotivo;
    public javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUbicacion;
    private javax.swing.JPanel pnlGuardar;
    public javax.swing.JTextField txtAlmacenista;
    public javax.swing.JTextField txtCantRetirar;
    public javax.swing.JTextField txtCantStock;
    public javax.swing.JTextField txtCodigo;
    public javax.swing.JTextArea txtDescripcion;
    public javax.swing.JTextField txtEmpleado;
    public javax.swing.JTextField txtProyecto;
    public javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables
}
