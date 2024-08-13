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
    
    public String getEmpleado(Connection con, String empleado) throws SQLException{
        Statement st = con.createStatement();
        String sql = "select * from users where employeeNumber like '" + empleado + "'";
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
            return (rs.getString("name") + " " + rs.getString("lastname"));
        }
        return null;
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
            
            String sql2 = "select * from requisicion where idrequisicion like '" + lblRequi.getText() + "'";
            Statement st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            while(rs2.next()){
                String empleado = rs2.getString("empleado");
                txtEmpleado.setText(getEmpleado(con, empleado));
                txtDescripcion.setText(rs2.getString("comentarios"));
                lblCompletado.setText(rs2.getString("completado"));
                lblFecha.setText(rs2.getString("fecha"));
                cmbEstado.setSelectedItem(rs2.getString("progreso"));
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
    
    public final void limpiarDatos(){
        txtEmpleado.setText("");
        lblCompletado.setText("");
        lblFecha.setText("");
        txtDescripcion.setText("");
    }
    
    public String getDatTabla(Object text){
        if(text == null){
            return null;
        }else{
            return text.toString();
        }
    }
    
    public EditarRequisicion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTable(Tabla1, jScrollPane2);
        limpiarDatos();
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
        jPanel3 = new javax.swing.JPanel();
        pnlGuardar = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtEmpleado = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        lblCompletado = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();

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

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel4Layout = new java.awt.GridBagLayout();
        jPanel4Layout.columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, 3.0};
        jPanel4.setLayout(jPanel4Layout);

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Empleado");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel4.add(jLabel4, gridBagConstraints);

        txtEmpleado.setEditable(false);
        txtEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        txtEmpleado.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtEmpleado.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        txtEmpleado.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmpleadoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmpleadoFocusLost(evt);
            }
        });
        txtEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmpleadoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(7, 20, 7, 20);
        jPanel4.add(txtEmpleado, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Estado de requisicion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel4.add(jLabel5, gridBagConstraints);

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COMPLETO", "INCOMPLETO", "NUEVO" }));
        cmbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel4.add(cmbEstado, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Completado");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel4.add(jLabel6, gridBagConstraints);

        lblCompletado.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblCompletado.setForeground(new java.awt.Color(153, 153, 153));
        lblCompletado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCompletado.setText("true");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel4.add(lblCompletado, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Fecha de creacion (yyyy-mm-dd)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel4.add(jLabel8, gridBagConstraints);

        lblFecha.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(153, 153, 153));
        lblFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFecha.setText("05-10-2024");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel4.add(lblFecha, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Comentarios");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel4.add(jLabel10, gridBagConstraints);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(15, 20, 15, 20);
        jPanel4.add(jScrollPane1, gridBagConstraints);

        jPanel5.add(jPanel4, java.awt.BorderLayout.NORTH);

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

        jPanel5.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel5, java.awt.BorderLayout.CENTER);

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
        limpiarDatos();
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
//            "ID", "No. Requisicion", "Codigo", "Descripcion", "Cantidad", "UM", "Proveedor", "Precio", "Llego", "OC", "Cant. Rec.", "Ubicacion", "Notas", "Remision"
            String sql = "update requisiciones set NumRequisicion = ?, codigo = ?,descripcion = ?, cantidad = ?, UM = ?, proveedor = ?, precio = ?,"
                    + " llego = ?, OC = ?, cantidadRecibida = ?, ubicacion = ?, notas = ?, remision = ? where idrequisiciones = ?";
            try {
                PreparedStatement pst = con.prepareStatement(sql);
                int n = 0;
                for (int i = 0; i < Tabla1.getRowCount(); i++) {
                    try{
                        pst.setString(1, getDatTabla(Tabla1.getValueAt(i, 1)));//requi
                        pst.setString(2, getDatTabla(Tabla1.getValueAt(i, 2)));//codigo
                        pst.setString(3, getDatTabla(Tabla1.getValueAt(i, 3)));//descripcion
                        pst.setString(4, getDatTabla(Tabla1.getValueAt(i, 4)));//cantidad
                        pst.setString(5, getDatTabla(Tabla1.getValueAt(i, 5)));//um
                        pst.setString(6, getDatTabla(Tabla1.getValueAt(i, 6)));//proveedor
                        pst.setString(7, getDatTabla(Tabla1.getValueAt(i, 7)));//precio
                        pst.setString(8, getDatTabla(Tabla1.getValueAt(i, 8)));//llego
                        pst.setString(9, getDatTabla(Tabla1.getValueAt(i, 9)));//oc
                        pst.setString(10, getDatTabla(Tabla1.getValueAt(i, 10)));//cant
                        pst.setString(11, getDatTabla(Tabla1.getValueAt(i, 11)));//ubicacion
                        pst.setString(12, getDatTabla(Tabla1.getValueAt(i, 12)));//notas 
                        pst.setString(13, getDatTabla(Tabla1.getValueAt(i, 13)));//remision 
                        pst.setString(14, getDatTabla(Tabla1.getValueAt(i, 0)));
                        
                        n += pst.executeUpdate();
                    }catch(SQLException e){
                        JOptionPane.showMessageDialog(this, "No se guardo la fila numero "+(i + 1));
                    }
                }
                
                String sql2 = "update requisicion set progreso = ?, completado = ?, comentarios = ? where idrequisicion = ?";
                PreparedStatement pst2 = con.prepareStatement(sql2);
                
                pst2.setString(1, cmbEstado.getSelectedItem().toString());
                pst2.setString(2, lblCompletado.getText());
                pst2.setString(3, txtDescripcion.getText());
                pst2.setString(4, lblRequi.getText());
                
                int n1 = pst2.executeUpdate();
                
                if(n > 0 && n1 > 0){
                    JOptionPane.showMessageDialog(this, "Datos Guardadaos Correctamente");
                }else{
                    JOptionPane.showMessageDialog(this, "No se guardaron los datos","Error",JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(EditarRequisicion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtEmpleadoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmpleadoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpleadoFocusGained

    private void txtEmpleadoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmpleadoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpleadoFocusLost

    private void txtEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmpleadoActionPerformed

    private void cmbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoActionPerformed
        if(isVisible()){
            switch(cmbEstado.getSelectedIndex()){
                case 0 -> lblCompletado.setText("SI");
                case 1 -> lblCompletado.setText("NO");
                case 2 -> lblCompletado.setText("NO");
            }
        }
    }//GEN-LAST:event_cmbEstadoActionPerformed

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
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCompletado;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblRequi;
    private javax.swing.JPanel pnlGuardar;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtEmpleado;
    private javax.swing.JTextField txtRequisicion;
    // End of variables declaration//GEN-END:variables
}
