package Vista.InternalFrame;

import Conexiones.Conexion;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class VerRequisiciones extends javax.swing.JInternalFrame {

    String numEmpleado;
    
    public String getCampo(int opc){
        switch(opc){
            case 0 -> {
                return "NumRequisicion";
            }
            case 1 -> {
                return "codigo";
            }
            case 2 -> {
                return "descripcion";
            }
            case 3 -> {
                return "requisitor";
            }
            case 4 -> {
                return "proveedor";
            }
        }
        return null;
    }
    
    public void limpiarTabla1(){
        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "No. Requisicion", "Progreso", "Completado", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    public void limpiarTabla2(){
        Tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "No. Requisicion", "Codigo", "Descripcion", "Cantidad", "UM", "Proveedor", "Precio", "Llego", "OC", "Remision", "Cant. Rec."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    public final void verRequisicion(String sql){
        try{
            limpiarTabla1();
            DefaultTableModel miModelo = (DefaultTableModel) Tabla1.getModel();
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String datos[] = new String[10];
            while(rs.next()){
                datos[0] = rs.getString("idrequisicion");
                datos[1] = rs.getString("progreso");
                datos[2] = rs.getString("completado");
                datos[3] = rs.getString("fecha");
                miModelo.addRow(datos);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error al ver requisiciones: "+e,"error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void verRequisiciones(String sql){
        try{
            limpiarTabla2();
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String datos[] = new String[15];
            DefaultTableModel miModelo = (DefaultTableModel) Tabla2.getModel();
            while(rs.next()){
                datos[0] = rs.getString("NumRequisicion");
                datos[1] = rs.getString("codigo");
                datos[2] = rs.getString("descripcion");
                datos[3] = rs.getString("cantidad");
                datos[4] = rs.getString("UM");
                datos[5] = rs.getString("proveedor");
                datos[6] = rs.getString("precio");
                datos[7] = rs.getString("llego");
                datos[8] = rs.getString("OC");
                datos[9] = rs.getString("remision");
                datos[10] = rs.getString("cantidadRecibida");
                miModelo.addRow(datos);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error al ver requisicion: "+e,"Error",JOptionPane.ERROR_MESSAGE);
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
    
    public final void rendercmb(){
        cmbBuscar.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
                @Override
                protected JButton createArrowButton() {
                    JButton button = super.createArrowButton();
                    button.setBackground(Color.white);
                    return button;
                }
            });
        cmbBuscar.setBackground(Color.white);
        cmbBuscar.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                    // Establecer color de fondo
                    if (isSelected) {
                        label.setBackground(new Color(255,222,0)); // Color de fondo cuando se selecciona
                        label.setForeground(Color.gray); // Color de letra cuando se selecciona
                    } else {
                        label.setBackground(Color.WHITE); // Color de fondo cuando no está seleccionado
                        label.setForeground(Color.BLACK); // Color de letra cuando no está seleccionado
                    }
                    
                    return label;
                }
            });
    }
    
    public VerRequisiciones(String numEmpleado) {
        initComponents();
        verRequisicion("select * from requisicion order by idrequisicion desc");
        this.numEmpleado = numEmpleado; 
        rendercmb();
        setTable(Tabla1, jScrollPane2);
        setTable(Tabla2, jScrollPane3);
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        PanelX = new javax.swing.JPanel();
        lblX = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbBuscar = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        misRequisiciones = new javax.swing.JRadioButton();
        todas = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabla2 = new javax.swing.JTable();

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel11.setFont(new java.awt.Font("Lexend", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 204));
        jLabel11.setText("Ver Requisiciones");
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
        jPanel5.setLayout(new java.awt.BorderLayout(0, 10));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel6Layout = new java.awt.GridBagLayout();
        jPanel6Layout.columnWeights = new double[] {0.0, 0.0, 1.0};
        jPanel6.setLayout(jPanel6Layout);

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Buscar por:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel6.add(jLabel1, gridBagConstraints);

        cmbBuscar.setBackground(new java.awt.Color(255, 255, 255));
        cmbBuscar.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cmbBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Numero de requisicion", "Codigo", "Descripcion", "Requisitor", "Proveedor" }));
        cmbBuscar.setBorder(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 1;
        gridBagConstraints.insets = new java.awt.Insets(3, 10, 3, 10);
        jPanel6.add(cmbBuscar, gridBagConstraints);

        txtBuscar.setBackground(new java.awt.Color(255, 255, 255));
        txtBuscar.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtBuscar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        txtBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBuscarFocusLost(evt);
            }
        });
        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.insets = new java.awt.Insets(3, 30, 3, 30);
        jPanel6.add(txtBuscar, gridBagConstraints);

        jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.BorderLayout(0, 10));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 40, 5));

        misRequisiciones.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(misRequisiciones);
        misRequisiciones.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        misRequisiciones.setForeground(new java.awt.Color(51, 51, 51));
        misRequisiciones.setText("Mis requisiciones");
        misRequisiciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                misRequisicionesActionPerformed(evt);
            }
        });
        jPanel8.add(misRequisiciones);

        todas.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(todas);
        todas.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        todas.setForeground(new java.awt.Color(51, 51, 51));
        todas.setSelected(true);
        todas.setText("Todas las requisiciones");
        todas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todasActionPerformed(evt);
            }
        });
        jPanel8.add(todas);

        jPanel7.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.GridLayout(2, 0, 0, 20));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Requisicion", "Progreso", "Completado", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabla1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Tabla1);

        jPanel9.add(jScrollPane2);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        Tabla2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No. Requisicion", "Codigo", "Descripcion", "Cantidad", "UM", "Proveedor", "Precio", "Llego", "OC", "Remision", "Cant. Rec."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(Tabla2);

        jPanel9.add(jScrollPane3);

        jPanel7.add(jPanel9, java.awt.BorderLayout.CENTER);

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

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        limpiarTabla1();
        verRequisiciones("select * from requisiciones where " + getCampo(cmbBuscar.getSelectedIndex()) + " like '%" + txtBuscar.getText() + "%'");
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusGained
        txtBuscar.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 222, 0)));
    }//GEN-LAST:event_txtBuscarFocusGained

    private void txtBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBuscarFocusLost
        txtBuscar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
    }//GEN-LAST:event_txtBuscarFocusLost

    private void todasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todasActionPerformed
        verRequisicion("select * from requisicion order by idrequisicion desc");
    }//GEN-LAST:event_todasActionPerformed

    private void Tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla1MouseClicked
        verRequisiciones("select * from requisiciones where NumRequisicion like '" + Tabla1.getValueAt(Tabla1.getSelectedRow(), 0).toString() + "'");
    }//GEN-LAST:event_Tabla1MouseClicked

    private void misRequisicionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_misRequisicionesActionPerformed
        verRequisicion("select * from requisicion where empleado like '" + numEmpleado + "' order by idrequisicion desc");
    }//GEN-LAST:event_misRequisicionesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelX;
    private javax.swing.JTable Tabla1;
    private javax.swing.JTable Tabla2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblX;
    private javax.swing.JRadioButton misRequisiciones;
    private javax.swing.JRadioButton todas;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
