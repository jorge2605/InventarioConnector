package Vista.InternalFrame;

import Conexiones.Conexion;
import emergente.admin.AgregarCorreo;
import emergente.almacen.EditarRequisicion;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.Stack;
import javax.swing.JFrame;

public class Admin extends javax.swing.JInternalFrame {

    int idSeleccionado;
    Stack<JLabel> lblAdd;
    
    public void addLabel(int i, String id, String nombre, String correo){
        lblAdd.push(new javax.swing.JLabel());
        lblAdd.get(i).setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblAdd.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/outlook_16.png"))); // NOI18N
        lblAdd.get(i).setText(nombre);
        lblAdd.get(i).setName(id);
        lblAdd.get(i).setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblAdd.get(i).setToolTipText(correo);
        lblAdd.get(i).setComponentPopupMenu(jPopupMenu1);
        lblAdd.get(i).addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                idSeleccionado = i;
            }
        });

        panelCorreos.add(lblAdd.get(i));
    }
    
    public void limpiar(){
        panelCorreos.removeAll();
        revalidate();
        repaint();
    }
    
    public final void addCorreos(){
        lblAdd = new Stack<>();
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            String sql = "select * from correo";
            ResultSet rs = st.executeQuery(sql);
            int cont = 0;
            while(rs.next()){
                String id = rs.getString("idcorreo");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                addLabel(cont, id, nombre, correo);
                cont++;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error: "+e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Admin(String numEmpleado, String nomEmpleado) {
        initComponents();
        addCorreos();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPopupMenu1 = new javax.swing.JPopupMenu();
        editar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        eliminar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        PanelX = new javax.swing.JPanel();
        lblX = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        pnlSalidas = new javax.swing.JPanel();
        btnSalidas = new javax.swing.JButton();
        panelCorreos = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        pblEditarRequi = new javax.swing.JPanel();
        btnEditarRequi = new javax.swing.JButton();

        jPopupMenu1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jPopupMenu1PopupMenuWillBecomeVisible(evt);
            }
        });

        editar.setText("Editar correo de");
        editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(editar);
        jPopupMenu1.add(jSeparator1);

        eliminar.setText("Eliminar correo de ");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(eliminar);

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel11.setFont(new java.awt.Font("Lexend", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 204));
        jLabel11.setText("Admin");
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
        jPanel5Layout.columnWeights = new double[] {1.0};
        jPanel5.setLayout(jPanel5Layout);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Configuracion correo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 1, 12))); // NOI18N
        jPanel6.setLayout(new java.awt.BorderLayout(20, 5));

        pnlSalidas.setBackground(new java.awt.Color(255, 255, 255));
        pnlSalidas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 115, 198), 1, true));

        btnSalidas.setBackground(new java.awt.Color(255, 255, 255));
        btnSalidas.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnSalidas.setForeground(new java.awt.Color(0, 115, 198));
        btnSalidas.setText("Añadir CC a envio de correos");
        btnSalidas.setBorder(null);
        btnSalidas.setBorderPainted(false);
        btnSalidas.setContentAreaFilled(false);
        btnSalidas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalidas.setFocusPainted(false);
        btnSalidas.setPreferredSize(new java.awt.Dimension(154, 25));
        btnSalidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalidasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalidasMouseExited(evt);
            }
        });
        btnSalidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalidasActionPerformed(evt);
            }
        });
        pnlSalidas.add(btnSalidas);

        jPanel6.add(pnlSalidas, java.awt.BorderLayout.WEST);

        panelCorreos.setBackground(new java.awt.Color(255, 255, 255));
        panelCorreos.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 25, 5));
        jPanel6.add(panelCorreos, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(17, 0, 17, 0);
        jPanel5.add(jPanel6, gridBagConstraints);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Configuracion requisiciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 1, 12))); // NOI18N
        jPanel7.setLayout(new java.awt.BorderLayout());

        pblEditarRequi.setBackground(new java.awt.Color(255, 255, 255));
        pblEditarRequi.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 115, 198), 1, true));

        btnEditarRequi.setBackground(new java.awt.Color(255, 255, 255));
        btnEditarRequi.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnEditarRequi.setForeground(new java.awt.Color(0, 115, 198));
        btnEditarRequi.setText("Editar requisicion");
        btnEditarRequi.setBorder(null);
        btnEditarRequi.setBorderPainted(false);
        btnEditarRequi.setContentAreaFilled(false);
        btnEditarRequi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditarRequi.setFocusPainted(false);
        btnEditarRequi.setPreferredSize(new java.awt.Dimension(154, 25));
        btnEditarRequi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditarRequiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditarRequiMouseExited(evt);
            }
        });
        btnEditarRequi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarRequiActionPerformed(evt);
            }
        });
        pblEditarRequi.add(btnEditarRequi);

        jPanel7.add(pblEditarRequi, java.awt.BorderLayout.WEST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(17, 0, 17, 0);
        jPanel5.add(jPanel7, gridBagConstraints);

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

    private void btnSalidasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalidasMouseEntered
        pnlSalidas.setBackground(new Color(0,115,198));
        btnSalidas.setForeground(Color.white);
    }//GEN-LAST:event_btnSalidasMouseEntered

    private void btnSalidasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalidasMouseExited
        pnlSalidas.setBackground(Color.white);
        btnSalidas.setForeground(new Color(0,115,198));
    }//GEN-LAST:event_btnSalidasMouseExited

    private void btnSalidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalidasActionPerformed
        JFrame f = (JFrame) JOptionPane.getFrameForComponent(this);
        AgregarCorreo add = new AgregarCorreo(f, true);
        add.setLocationRelativeTo(f);
        boolean band = add.guardado();
        if(band){
            limpiar();
            addCorreos();
        }
    }//GEN-LAST:event_btnSalidasActionPerformed

    private void btnEditarRequiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarRequiMouseEntered
        pblEditarRequi.setBackground(new Color(0,115,198));
        btnEditarRequi.setForeground(Color.white);
    }//GEN-LAST:event_btnEditarRequiMouseEntered

    private void btnEditarRequiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarRequiMouseExited
        pblEditarRequi.setBackground(Color.white);
        btnEditarRequi.setForeground(new Color(0,115,198));
    }//GEN-LAST:event_btnEditarRequiMouseExited

    private void btnEditarRequiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarRequiActionPerformed
        JFrame f = (JFrame) JOptionPane.getFrameForComponent(this);
        EditarRequisicion editar = new EditarRequisicion(f, true);
        editar.setLocationRelativeTo(f);
        editar.setVisible(true);
    }//GEN-LAST:event_btnEditarRequiActionPerformed

    private void jPopupMenu1PopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jPopupMenu1PopupMenuWillBecomeVisible
        editar.setText("Editar correo de " + lblAdd.get(idSeleccionado).getText() + "                       ");
        eliminar.setText("Eliminar correo de  " + lblAdd.get(idSeleccionado).getText() + "                       ");
    }//GEN-LAST:event_jPopupMenu1PopupMenuWillBecomeVisible

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        int opc = JOptionPane.showConfirmDialog(this, "¿Estas seguro de eliminar el correo de "+lblAdd.get(idSeleccionado).getText() + "?");
        if(opc == 0){
            try{
                Connection con;
                Conexion con1 = new Conexion();
                con = con1.getConnection();
                PreparedStatement pst = con.prepareStatement("delete from correo where idcorreo = ?");
                
                pst.setString(1, lblAdd.get(idSeleccionado).getName());
                
                int n = pst.executeUpdate();
                
                if(n > 0){
                    JOptionPane.showMessageDialog(this, "Correo eliminado correctamente");
                    limpiar();
                    addCorreos();
                }
                
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, "Error al eliminar correo: "+e,"Error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarActionPerformed
        JFrame f = (JFrame) JOptionPane.getFrameForComponent(this);
        AgregarCorreo add = new AgregarCorreo(f, true);
        add.setLocationRelativeTo(f);
        add.verDatos(lblAdd.get(idSeleccionado).getName());
        boolean band = add.guardado();
        if(band){
            limpiar();
            addCorreos();
        }
    }//GEN-LAST:event_editarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelX;
    public javax.swing.JButton btnEditarRequi;
    public javax.swing.JButton btnSalidas;
    private javax.swing.JMenuItem editar;
    private javax.swing.JMenuItem eliminar;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lblX;
    private javax.swing.JPanel panelCorreos;
    public javax.swing.JPanel pblEditarRequi;
    public javax.swing.JPanel pnlSalidas;
    // End of variables declaration//GEN-END:variables
}
