package Vista.InternalFrame;

import Conexiones.Conexion;
import Controlador.MensajeriaWhatsapp;
import emergente.almacen.altaArticulos;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class Almacen extends javax.swing.JInternalFrame {

    public String numEmpleado;
    
    public final void limpiarTabla(){
        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "No. Requisicion", "Codigo", "Descripcion", "Cantidad", "UM", "Proveedor", "Precio", "Cant. Rec.", "Notas", "Ubicacion","Llego"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, true
            };
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
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
            Statement st = con.createStatement();
            String sql = "select * from requisiciones where OC like '" + lblOC.getText() + "' and llego is null";
            ResultSet rs = st.executeQuery(sql);
            String datos[] = new String[15];
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
                datos[8] = rs.getString("cantidadRecibida");
                datos[9] = rs.getString("notas");
                datos[10] = rs.getString("ubicacion");
                miModelo.addRow(datos);
            }
            if(datos[0] != null){
                if(!datos[0].equals("")){
                    txtOC.setText("");
                }
            }else{
                String sql2 = "select * from requisiciones where remision like '" + lblOC.getText() + "' and llego is null";
                Statement st2 = con.createStatement();
                ResultSet rs2 = st2.executeQuery(sql2);
                while(rs2.next()){
                    datos[0] = rs2.getString("idrequisiciones");
                    datos[1] = rs2.getString("NumRequisicion");
                    datos[2] = rs2.getString("codigo");
                    datos[3] = rs2.getString("descripcion");
                    datos[4] = rs2.getString("cantidad");
                    datos[5] = rs2.getString("UM");
                    datos[6] = rs2.getString("proveedor");
                    datos[7] = rs2.getString("precio");
                    datos[8] = rs2.getString("cantidadRecibida");
                    datos[9] = rs2.getString("notas");
                    datos[10] = rs2.getString("ubicacion");
                    datos[14] = rs2.getString("OC");
                    miModelo.addRow(datos);
                }
                lblOC.setText(datos[14]);
                if(datos[0] != null){
                    if(!datos[0].equals("")){
                        txtOC.setText("");
                    }
                }else{
                    lblOC.setText("#");
                }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error: "+e,"Error", JOptionPane.ERROR_MESSAGE);
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
    
    public String getNumero(String requi){
        String numero = "";
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            String sql = "select * from requisicion where idrequisicion like '" + requi + "'";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                numero = rs.getString("empleado");
            }
            String sql2 = "select * from users where employeeNumber like '" + numero +"'";
            Statement st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            
            while(rs2.next()){
                numero = rs2.getString("telefono");
            }
            
            return numero;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "ERROR: " + e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return numero;
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
    
    public Almacen(String numEmpleado) {
        initComponents();
        this.numEmpleado = numEmpleado;
        limpiarTabla();
        setTable(Tabla1, jScrollPane2);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        PanelX = new javax.swing.JPanel();
        lblX = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtOC = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lblOC = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();

        jMenuItem1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/editar.png"))); // NOI18N
        jMenuItem1.setText("Dar entrada a articulos seleccionados                                               ");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel11.setFont(new java.awt.Font("Lexend", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 204));
        jLabel11.setText("Almacen");
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
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel6Layout = new java.awt.GridBagLayout();
        jPanel6Layout.columnWeights = new double[] {1.0};
        jPanel6.setLayout(jPanel6Layout);

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Introduce orden de compra");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(jLabel2, gridBagConstraints);

        txtOC.setBackground(new java.awt.Color(255, 255, 255));
        txtOC.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtOC.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        txtOC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtOCFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtOCFocusLost(evt);
            }
        });
        txtOC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOCActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(7, 20, 7, 20);
        jPanel6.add(txtOC, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("OC Selecionada");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(jLabel3, gridBagConstraints);

        lblOC.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblOC.setForeground(new java.awt.Color(51, 51, 51));
        lblOC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOC.setText("#");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(lblOC, gridBagConstraints);

        jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "No. Requisicion", "Codigo", "Descripcion", "Cantidad", "UM", "Proveedor", "Precio", "Cant. Rec.", "Notas", "Llego"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla1.setComponentPopupMenu(jPopupMenu1);
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

        jPanel7.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel7, java.awt.BorderLayout.CENTER);

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

    private void txtOCFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOCFocusGained
        txtOC.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 222, 0)));
    }//GEN-LAST:event_txtOCFocusGained

    private void txtOCFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOCFocusLost
        txtOC.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        revalidate();
        repaint();
    }//GEN-LAST:event_txtOCFocusLost

    private void txtOCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOCActionPerformed
        limpiarTabla();
        lblOC.setText(txtOC.getText());
        verRequisiciones();
    }//GEN-LAST:event_txtOCActionPerformed

    private void Tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla1MouseClicked
        if(evt.getClickCount() == 2){
            JFrame f = (JFrame) JOptionPane.getFrameForComponent(this);
            altaArticulos ver = new altaArticulos(f,true);
            ver.setLocationRelativeTo(f);
            int fila = Tabla1.getSelectedRow();
            String requi = "";
            ver.cantidad = Double.parseDouble(Tabla1.getValueAt(fila, 4).toString());
            ver.cantidadRecibida = Double.parseDouble(Tabla1.getValueAt(fila, 8).toString());
            ver.txtID.setText(Tabla1.getValueAt(fila, 0).toString());
            ver.txtRequisicion.setText(Tabla1.getValueAt(fila, 1).toString());
            requi = Tabla1.getValueAt(fila, 1).toString();
            ver.txtCodigo.setText(Tabla1.getValueAt(fila, 2).toString());
            ver.txtDescripcion.setText(Tabla1.getValueAt(fila, 3).toString());
            ver.txtCantidadR.setText(Tabla1.getValueAt(fila, 8).toString());
            ver.almacen = this;
            ver.oc = lblOC.getText();
            try{ver.txtNotas.setText(Tabla1.getValueAt(fila, 9).toString());}catch(Exception e){ver.txtNotas.setText("");}
            try{ver.txtUbicacion.setText(Tabla1.getValueAt(fila, 10).toString());}catch(Exception e){ver.txtUbicacion.setText("");}
            boolean band = ver.getGuardado();
            if(band) {
                limpiarTabla();
                verRequisiciones();
                MensajeriaWhatsapp mensaje = new MensajeriaWhatsapp();
                String numero = getNumero(requi);
                mensaje.sendMessage(numero, lblOC.getText(), requi, getToken());
            }
        }
    }//GEN-LAST:event_Tabla1MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if(Tabla1.getSelectedRows().length > 0){
            boolean band, band2 = false;
            String requi = "";
            for (int i = 0; i < Tabla1.getSelectedRows().length; i++) {
                JFrame f = (JFrame) JOptionPane.getFrameForComponent(this);
                altaArticulos ver = new altaArticulos(f,true);
                ver.setLocationRelativeTo(f);
                int fila = Tabla1.getSelectedRows()[i];
                ver.cantidad = Double.parseDouble(Tabla1.getValueAt(fila, 4).toString());
                ver.cantidadRecibida = Double.parseDouble(Tabla1.getValueAt(fila, 8).toString());
                ver.txtID.setText(Tabla1.getValueAt(fila, 0).toString());
                ver.txtRequisicion.setText(Tabla1.getValueAt(fila, 1).toString());
                requi = Tabla1.getValueAt(fila, 1).toString();
                ver.txtCodigo.setText(Tabla1.getValueAt(fila, 2).toString());
                ver.txtDescripcion.setText(Tabla1.getValueAt(fila, 3).toString());
                ver.txtCantidadR.setText(Tabla1.getValueAt(fila, 8).toString());
                ver.almacen = this;
                try{ver.txtNotas.setText(Tabla1.getValueAt(fila, 9).toString());}catch(Exception e){ver.txtNotas.setText("");}
                try{ver.txtUbicacion.setText(Tabla1.getValueAt(fila, 10).toString());}catch(Exception e){ver.txtUbicacion.setText("");}
                band = ver.getGuardado();
                if(band != false){
                    band2 = band;
                }
            }
            if(band2){
                limpiarTabla();
                verRequisiciones();
                MensajeriaWhatsapp mensaje = new MensajeriaWhatsapp();
                String numero = getNumero(requi);
                mensaje.sendMessage(numero, lblOC.getText(), requi, getToken());
            }
        }else{
            JOptionPane.showMessageDialog(this, "Debes seleccionar 1 o mas articulos","Advertencia",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelX;
    private javax.swing.JTable Tabla1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblOC;
    private javax.swing.JLabel lblX;
    private javax.swing.JTextField txtOC;
    // End of variables declaration//GEN-END:variables
}
