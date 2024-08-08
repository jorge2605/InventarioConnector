package Vista.InternalFrame;

import Conexiones.Conexion;
import com.mxrck.autocompleter.TextAutoCompleter;
import emergente.inventario.AgregarArticulo;
import emergente.inventario.MaximosMinimos;
import emergente.inventario.infoInventario;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Inventario extends javax.swing.JInternalFrame {

    public String numEmpleado;
    public String nombre;
    int seleccionado = 1;
    TextAutoCompleter text;
    String rol;
    boolean entradas;
    boolean salidas;
    
    public final void text(){
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            String sql = "select codigo from inventario order by codigo asc";
            ResultSet rs = st.executeQuery(sql);
            text = new TextAutoCompleter(txtCodigo);
            while(rs.next()){
                text.addItem(rs.getString("codigo"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error: "+e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public final void limpiarTabla(){
        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "CANTIDAD", "PROVEEDOR", "UBICACION", "SAL.", "ENT.", "ACT."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false,true,true,true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        TableColumn col;
        col = Tabla1.getColumnModel().getColumn(5);
        col.setCellEditor(new myeditor(Tabla1,this,1));
        col.setCellRenderer(new renderer(false,1));
        
        col = Tabla1.getColumnModel().getColumn(6);
        col.setCellEditor(new myeditor(Tabla1,this,2));
        col.setCellRenderer(new renderer(false,2));
        
        col = Tabla1.getColumnModel().getColumn(7);
        col.setCellEditor(new myeditor(Tabla1,this,3));
        col.setCellRenderer(new renderer(false,3));
        
        Tabla1.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 12));
        Tabla1.getTableHeader().setOpaque(false);
        Tabla1.getTableHeader().setBackground(new Color(255,222,0));
        Tabla1.getTableHeader().setForeground(Color.gray);
        Tabla1.setRowHeight(25);
        Tabla1.setShowVerticalLines(false);
        Tabla1.setGridColor(new Color(240,240,240));
        
        jScrollPane1.getViewport().setBackground(new Color(255,255,255));
        
        if (Tabla1.getColumnModel().getColumnCount() > 0) {
            Tabla1.getColumnModel().getColumn(1).setMinWidth(300);
            Tabla1.getColumnModel().getColumn(1).setPreferredWidth(600);
            Tabla1.getColumnModel().getColumn(5).setMinWidth(50);
            Tabla1.getColumnModel().getColumn(5).setPreferredWidth(50);
            Tabla1.getColumnModel().getColumn(5).setMaxWidth(50);
            Tabla1.getColumnModel().getColumn(6).setMinWidth(50);
            Tabla1.getColumnModel().getColumn(6).setPreferredWidth(50);
            Tabla1.getColumnModel().getColumn(6).setMaxWidth(50);
            Tabla1.getColumnModel().getColumn(7).setMinWidth(50);
            Tabla1.getColumnModel().getColumn(7).setPreferredWidth(50);
            Tabla1.getColumnModel().getColumn(7).setMaxWidth(50);
        }
    }
    
    public final void limpiarTablaSalidas(){
        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMPLEADO", "FECHA", "CODIGO", "CANTIDAD", "ALMACENISTA", "MOTIVO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false,false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        Tabla1.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 12));
        Tabla1.getTableHeader().setOpaque(false);
        Tabla1.getTableHeader().setBackground(new Color(255,222,0));
        Tabla1.getTableHeader().setForeground(Color.gray);
        Tabla1.setRowHeight(25);
        Tabla1.setShowVerticalLines(false);
        Tabla1.setGridColor(new Color(240,240,240));
        
        jScrollPane1.getViewport().setBackground(new Color(255,255,255));
    }
    
    public void setWhite(JPanel panel, JButton boton){
        panelActualizar.setBackground(Color.white);
        btnActualizar.setForeground(Color.black);
        panelEntrada.setBackground(Color.white);
        btnEntrada.setForeground(Color.black);
        panelInicio.setBackground(Color.white);
        btnInicio.setForeground(Color.black);
        panelSalida.setBackground(Color.white);
        btnSalida.setForeground(Color.black);
        panel.setBackground(new Color(255,222,0));
        boton.setForeground(Color.gray);
    }
    
    public final void verDatos(){
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            String sql = "select * from inventario";
            ResultSet rs = st.executeQuery(sql);
            String datos[] = new String[10];
            DefaultTableModel miModelo = (DefaultTableModel) Tabla1.getModel();
            while(rs.next()){
                datos[0] = rs.getString("codigo");
                datos[1] = rs.getString("descripcion");
                datos[2] = rs.getString("cantidad");
                datos[3] = rs.getString("proveedor");
                datos[4] = rs.getString("ubicacion");
                miModelo.addRow(datos);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error al ver inventario: "+e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void verDatosESA(String sql){
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            DefaultTableModel miModelo = (DefaultTableModel) Tabla1.getModel();
            String datos[] = new String[10];
            while(rs.next()){
                datos[0] = rs.getString("empleado");
                datos[1] = rs.getString("fecha");
                datos[2] = rs.getString("codigo");
                datos[3] = rs.getString("cantidad");
                datos[4] = rs.getString("almacenista");
                datos[5] = rs.getString("motivo");
                miModelo.addRow(datos);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error: "+e,"error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public final void agregarInventario(String sql){
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            String datos[] = new String[10];
            ResultSet rs = st.executeQuery(sql);
            DefaultTableModel miModelo = (DefaultTableModel) Tabla1.getModel();
            while(rs.next()){
                datos[0] = rs.getString("codigo");
                datos[1] = rs.getString("descripcion");
                datos[2] = rs.getString("cantidad");
                datos[3] = rs.getString("proveedor");
                datos[4] = rs.getString("ubicacion");
                if(datos[0] != null){
                    if(!datos[0].equals("")){
                        miModelo.addRow(datos);
                    }
                }
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "ERROR: "+e,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public final void setDatos(){
        txtAlmacenista.setText(nombre);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        txtFecha.setText(sdf.format(d));
    }
    
    public final void getRol(){
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            String sql = "select * from users where employeeNumber like '" + numEmpleado + "'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                rol = rs.getString("role");
                entradas = rs.getBoolean("entradas");
                salidas = rs.getBoolean("salidas");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error: "+e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Inventario(String numEmpleado, String nombre) {
        initComponents();
        this.numEmpleado = numEmpleado;
        this.nombre = nombre;
        getRol();
        setDatos();
        limpiarTabla();
        text();
        verDatos();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        PanelX = new javax.swing.JPanel();
        lblX = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        panelInicio = new javax.swing.JPanel();
        btnInicio = new javax.swing.JButton();
        panelSalida = new javax.swing.JPanel();
        btnSalida = new javax.swing.JButton();
        panelEntrada = new javax.swing.JPanel();
        btnEntrada = new javax.swing.JButton();
        panelActualizar = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();
        panelCard = new javax.swing.JPanel();
        Salida = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtAlmacenista = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtRequisitor = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnAgregar = new Componentes.Boton();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtProyecto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtDesc = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        btnAgregar1 = new Componentes.Boton();

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel11.setFont(new java.awt.Font("Lexend", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 204));
        jLabel11.setText("Inventario");
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
        jPanel6.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        panelInicio.setBackground(new java.awt.Color(255, 222, 0));

        btnInicio.setFont(new java.awt.Font("Lexend", 1, 12)); // NOI18N
        btnInicio.setForeground(new java.awt.Color(255, 255, 255));
        btnInicio.setText("Inicio");
        btnInicio.setBorder(null);
        btnInicio.setBorderPainted(false);
        btnInicio.setContentAreaFilled(false);
        btnInicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnInicio.setFocusPainted(false);
        btnInicio.setPreferredSize(new java.awt.Dimension(92, 25));
        btnInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnInicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnInicioMouseExited(evt);
            }
        });
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });
        panelInicio.add(btnInicio);

        jPanel6.add(panelInicio);

        panelSalida.setBackground(new java.awt.Color(255, 255, 255));

        btnSalida.setFont(new java.awt.Font("Lexend", 1, 12)); // NOI18N
        btnSalida.setForeground(new java.awt.Color(51, 51, 51));
        btnSalida.setText("Salida");
        btnSalida.setBorder(null);
        btnSalida.setBorderPainted(false);
        btnSalida.setContentAreaFilled(false);
        btnSalida.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalida.setFocusPainted(false);
        btnSalida.setPreferredSize(new java.awt.Dimension(50, 25));
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

        jPanel6.add(panelSalida);

        panelEntrada.setBackground(new java.awt.Color(255, 255, 255));

        btnEntrada.setFont(new java.awt.Font("Lexend", 1, 12)); // NOI18N
        btnEntrada.setForeground(new java.awt.Color(51, 51, 51));
        btnEntrada.setText("Entrada");
        btnEntrada.setBorder(null);
        btnEntrada.setBorderPainted(false);
        btnEntrada.setContentAreaFilled(false);
        btnEntrada.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEntrada.setFocusPainted(false);
        btnEntrada.setPreferredSize(new java.awt.Dimension(60, 25));
        btnEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEntradaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEntradaMouseExited(evt);
            }
        });
        btnEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntradaActionPerformed(evt);
            }
        });
        panelEntrada.add(btnEntrada);

        jPanel6.add(panelEntrada);

        panelActualizar.setBackground(new java.awt.Color(255, 255, 255));

        btnActualizar.setFont(new java.awt.Font("Lexend", 1, 12)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(51, 51, 51));
        btnActualizar.setText("Actualizacion");
        btnActualizar.setBorder(null);
        btnActualizar.setBorderPainted(false);
        btnActualizar.setContentAreaFilled(false);
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizar.setFocusPainted(false);
        btnActualizar.setPreferredSize(new java.awt.Dimension(92, 25));
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnActualizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActualizarMouseExited(evt);
            }
        });
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        panelActualizar.add(btnActualizar);

        jPanel6.add(panelActualizar);

        jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CODIGO", "DESCRIPCION", "CANTIDAD", "PROVEEDOR", "UBICACION", "Salida", "Entrada", "Act."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tabla1);

        jPanel9.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel9, java.awt.BorderLayout.CENTER);

        panelCard.setBackground(new java.awt.Color(255, 255, 255));
        panelCard.setPreferredSize(new java.awt.Dimension(1173, 150));
        panelCard.setLayout(new java.awt.CardLayout());

        Salida.setBackground(new java.awt.Color(255, 255, 255));
        Salida.setLayout(new java.awt.GridLayout(1, 0));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new java.awt.GridLayout(4, 0));

        jLabel1.setFont(new java.awt.Font("Lexend", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Almacenista:  ");
        jPanel10.add(jLabel1);

        txtAlmacenista.setEditable(false);
        txtAlmacenista.setBackground(new java.awt.Color(255, 255, 255));
        txtAlmacenista.setFont(new java.awt.Font("Lexend", 1, 12)); // NOI18N
        txtAlmacenista.setForeground(new java.awt.Color(51, 51, 51));
        txtAlmacenista.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAlmacenista.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        jPanel10.add(txtAlmacenista);

        jLabel2.setFont(new java.awt.Font("Lexend", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Fecha:  ");
        jPanel10.add(jLabel2);

        txtFecha.setEditable(false);
        txtFecha.setBackground(new java.awt.Color(255, 255, 255));
        txtFecha.setFont(new java.awt.Font("Lexend", 1, 12)); // NOI18N
        txtFecha.setForeground(new java.awt.Color(51, 51, 51));
        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFecha.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        jPanel10.add(txtFecha);

        jLabel3.setFont(new java.awt.Font("Lexend", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Requisitor:  ");
        jPanel10.add(jLabel3);

        txtRequisitor.setBackground(new java.awt.Color(255, 255, 255));
        txtRequisitor.setFont(new java.awt.Font("Lexend", 0, 12)); // NOI18N
        txtRequisitor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRequisitor.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        txtRequisitor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtRequisitorFocusGained(evt);
            }
        });
        txtRequisitor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRequisitorMouseClicked(evt);
            }
        });
        txtRequisitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRequisitorActionPerformed(evt);
            }
        });
        jPanel10.add(txtRequisitor);
        jPanel10.add(jLabel15);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        btnAgregar.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar.setForeground(new java.awt.Color(0, 102, 255));
        btnAgregar.setText("Agregar Material");
        btnAgregar.setBorderColor(new java.awt.Color(0, 102, 255));
        btnAgregar.setBorderColorSelected(new java.awt.Color(0, 51, 153));
        btnAgregar.setColor(new java.awt.Color(0, 102, 255));
        btnAgregar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel8.add(btnAgregar);

        jPanel10.add(jPanel8);

        Salida.add(jPanel10);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new java.awt.GridLayout(4, 0));

        jLabel6.setFont(new java.awt.Font("Lexend", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Motivo:  ");
        jPanel11.add(jLabel6);

        txtProyecto.setBackground(new java.awt.Color(255, 255, 255));
        txtProyecto.setFont(new java.awt.Font("Lexend", 0, 12)); // NOI18N
        txtProyecto.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtProyecto.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
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
        jPanel11.add(txtProyecto);

        jLabel7.setFont(new java.awt.Font("Lexend", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Codigo:  ");
        jPanel11.add(jLabel7);

        txtCodigo.setBackground(new java.awt.Color(255, 255, 255));
        txtCodigo.setFont(new java.awt.Font("Lexend", 0, 12)); // NOI18N
        txtCodigo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtCodigo.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        jPanel11.add(txtCodigo);

        jLabel18.setFont(new java.awt.Font("Lexend", 1, 12)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Descripcion:  ");
        jPanel11.add(jLabel18);

        txtDesc.setBackground(new java.awt.Color(255, 255, 255));
        txtDesc.setFont(new java.awt.Font("Lexend", 0, 12)); // NOI18N
        txtDesc.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtDesc.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        txtDesc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescActionPerformed(evt);
            }
        });
        jPanel11.add(txtDesc);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        btnAgregar1.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar1.setForeground(new java.awt.Color(204, 153, 255));
        btnAgregar1.setText("Maximos y minimos");
        btnAgregar1.setBorderColor(new java.awt.Color(204, 153, 255));
        btnAgregar1.setBorderColorSelected(new java.awt.Color(153, 0, 255));
        btnAgregar1.setColor(new java.awt.Color(204, 153, 255));
        btnAgregar1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar1ActionPerformed(evt);
            }
        });
        jPanel12.add(btnAgregar1);

        jPanel11.add(jPanel12);

        Salida.add(jPanel11);

        panelCard.add(Salida, "card2");

        jPanel7.add(panelCard, java.awt.BorderLayout.PAGE_START);

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

    private void btnInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseEntered
        panelInicio.setBackground(new Color(255,222,0));
        btnInicio.setForeground(Color.white);
    }//GEN-LAST:event_btnInicioMouseEntered

    private void btnInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInicioMouseExited
        if(seleccionado != 1){
            panelInicio.setBackground(Color.white);
            btnInicio.setForeground(new Color(54,54,54));
        }
    }//GEN-LAST:event_btnInicioMouseExited

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        seleccionado = 1;
        setWhite(panelInicio, btnInicio);
        panelCard.setVisible(true);
        limpiarTabla();
        verDatos();
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnSalidaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalidaMouseEntered
        panelSalida.setBackground(new Color(255,222,0));
        btnSalida.setForeground(Color.white);
    }//GEN-LAST:event_btnSalidaMouseEntered

    private void btnSalidaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalidaMouseExited
        if(seleccionado != 2){
            panelSalida.setBackground(Color.white);
            btnSalida.setForeground(new Color(54,54,54));
        }
    }//GEN-LAST:event_btnSalidaMouseExited

    private void btnSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalidaActionPerformed
        seleccionado = 2;
        setWhite(panelSalida, btnSalida);
        panelCard.setVisible(false);
        limpiarTablaSalidas();
        verDatosESA("select * from salidamaterial order by idsalidamaterial desc");
    }//GEN-LAST:event_btnSalidaActionPerformed

    private void btnEntradaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEntradaMouseEntered
        panelEntrada.setBackground(new Color(255,222,0));
        btnEntrada.setForeground(Color.white);
    }//GEN-LAST:event_btnEntradaMouseEntered

    private void btnEntradaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEntradaMouseExited
        if(seleccionado != 3){
            panelEntrada.setBackground(Color.white);
            btnEntrada.setForeground(new Color(54,54,54));
        }
    }//GEN-LAST:event_btnEntradaMouseExited

    private void btnEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntradaActionPerformed
        seleccionado = 3;
        setWhite(panelEntrada, btnEntrada);
        panelCard.setVisible(false);
        limpiarTablaSalidas();
        verDatosESA("select * from entradamaterial order by identradamaterial desc");
    }//GEN-LAST:event_btnEntradaActionPerformed

    private void btnActualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseEntered
        panelActualizar.setBackground(new Color(255,222,0));
        btnActualizar.setForeground(Color.white);
    }//GEN-LAST:event_btnActualizarMouseEntered

    private void btnActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseExited
        if(seleccionado != 4){
            panelActualizar.setBackground(Color.white);
            btnActualizar.setForeground(new Color(54,54,54));
        }
    }//GEN-LAST:event_btnActualizarMouseExited

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        seleccionado = 4;
        setWhite(panelActualizar, btnActualizar);
        panelCard.setVisible(false);
        limpiarTablaSalidas();
        verDatosESA("select * from actualizarmaterial order by idactualizarmaterial desc");
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txtRequisitorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtRequisitorFocusGained

    }//GEN-LAST:event_txtRequisitorFocusGained

    private void txtRequisitorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRequisitorMouseClicked
        txtRequisitor.setText("");
        txtRequisitor.setEnabled(true);
    }//GEN-LAST:event_txtRequisitorMouseClicked

    private void txtRequisitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRequisitorActionPerformed
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            String slq = "select * from users where employeeNumber like '"+txtRequisitor.getText()+"'";
            ResultSet rs = st.executeQuery(slq);
            String empleado = null;
            while(rs.next()){
                empleado = rs.getString("name") + " " + rs.getString("lastname");
            }
            if(empleado != null){
                txtRequisitor.setText(empleado);
                txtRequisitor.setEnabled(false);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "ERROR: "+e,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtRequisitorActionPerformed

    private void txtProyectoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtProyectoMouseClicked
        txtProyecto.setEnabled(true);
        txtProyecto.setText("");
    }//GEN-LAST:event_txtProyectoMouseClicked

    private void txtProyectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProyectoActionPerformed
//        boolean band = false;
//        for (int i = 0; i < proyectos.size(); i++) {
//            if(proyectos.get(i).equals(txtProyecto.getText())){
//                band = true;
//            }
//        }
//        if(band){
//            txtProyecto.setEnabled(false);
//        }else{
//            JOptionPane.showMessageDialog(this, "Este proyecto no existe","Error",JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event_txtProyectoActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        limpiarTabla();
        agregarInventario("select codigo, cantidad, proveedor, ubicacion,descripcion from inventario where codigo like '" + txtCodigo.getText() + "' order by codigo asc");
        if(Tabla1.getRowCount() == 0){
            DefaultTableModel miModelo = (DefaultTableModel) Tabla1.getModel();
            String datos[] = new String[10];
            datos[0] = txtCodigo.getText();
            datos[1] = "Nuevo";
            datos[2] = "Nuevo";
            datos[3] = "Nuevo";
            datos[4] = "Nuevo";
            miModelo.addRow(datos);
        }
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtDescActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescActionPerformed
        limpiarTabla();
        agregarInventario("codigo, cantidad, proveedor, ubicacion,descripcion from inventario where descripcion like '%" + txtDesc.getText() + "%' order by codigo asc");
        if(Tabla1.getRowCount() == 0){
            DefaultTableModel miModelo = (DefaultTableModel) Tabla1.getModel();
            String datos[] = new String[10];
            datos[0] = txtCodigo.getText();
            datos[1] = "Nuevo";
            datos[2] = "Nuevo";
            datos[3] = "Nuevo";
            datos[4] = "Nuevo";
            miModelo.addRow(datos);
        }
    }//GEN-LAST:event_txtDescActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        JFrame f = (JFrame) JOptionPane.getFrameForComponent(this);
        AgregarArticulo agregar = new AgregarArticulo(f, true);
        agregar.setLocationRelativeTo(f);
        agregar.setVisible(true);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar1ActionPerformed
        JFrame f = (JFrame) JOptionPane.getFrameForComponent(this);
        MaximosMinimos max = new MaximosMinimos(f, true);
        max.setLocationRelativeTo(f);
        max.setVisible(true);
    }//GEN-LAST:event_btnAgregar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelX;
    private javax.swing.JPanel Salida;
    public javax.swing.JTable Tabla1;
    private javax.swing.JButton btnActualizar;
    private Componentes.Boton btnAgregar;
    private Componentes.Boton btnAgregar1;
    private javax.swing.JButton btnEntrada;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblX;
    private javax.swing.JPanel panelActualizar;
    private javax.swing.JPanel panelCard;
    private javax.swing.JPanel panelEntrada;
    private javax.swing.JPanel panelInicio;
    private javax.swing.JPanel panelSalida;
    public javax.swing.JTextField txtAlmacenista;
    public javax.swing.JTextField txtCodigo;
    public javax.swing.JTextField txtDesc;
    public javax.swing.JTextField txtFecha;
    public javax.swing.JTextField txtProyecto;
    public javax.swing.JTextField txtRequisitor;
    // End of variables declaration//GEN-END:variables
}
class renderer extends JLabel implements TableCellRenderer {

    boolean isBordered = true;
    int seleccionado;

    public renderer(boolean isBordered, int sel) {
        this.isBordered = isBordered;
        setOpaque(true);
        seleccionado = sel;
    }

    public Component getTableCellRendererComponent(JTable table, Object color, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton boton = new JButton();
            boton.setBackground(new java.awt.Color(255, 255, 255));
        switch (seleccionado) {
            case 1:
                boton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Iconos/salida_16.png"))); // NOI18N
                break;
            case 2:
                boton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Iconos/entrada_16.png"))); // NOI18N
                break;
            case 3:
                boton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Iconos/act_16.png"))); // NOI18N
                break;
            default:
                break;
        }
            boton.setBorder(null);
            boton.setBorderPainted(false);
            boton.setContentAreaFilled(false);
            boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            boton.setFocusPainted(false);
            return boton;
    }
}

class myeditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

    Boolean currentValue;
    JButton button;
    Inventario cxp;
    protected static final String EDIT = "edit";
    private JTable tabla1;
    int seleccionado;

    public myeditor(JTable jTable1, Inventario cxp, int sel) {
        button = new JButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);
        this.tabla1 = jTable1;
        this.cxp = cxp;
        seleccionado = sel;
    }

    public void actionPerformed(ActionEvent e) {
        JFrame f = (JFrame) JOptionPane.getFrameForComponent(cxp);
        infoInventario info = new infoInventario(f,true);
        info.setLocationRelativeTo(cxp);
        boolean puede = true;
        switch (seleccionado) {
            case 1:
                info.lblTitulo.setText("Salida");
                if(cxp.salidas == false){
                    puede = false;
                }
                break;
            case 2:
                info.lblTitulo.setText("Entrada");
                info.lblCantidad.setText("Cantidad a ingresar:");
                if(cxp.entradas == false){
                    puede = false;
                }
                break;
            case 3:
                info.lblTitulo.setText("Actualizar");
                info.lblCantidad.setText("Cantidad:");
                if(!cxp.rol.equals("Admin")){
                    puede = false;
                }
                break;
            default:
                break;
        }
        if(puede == false){
            JOptionPane.showMessageDialog(cxp, "No tienes permiso de realizar este moviento","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            info.txtAlmacenista.setText(cxp.txtAlmacenista.getText());
            info.txtEmpleado.setText(cxp.txtRequisitor.getText());
            int row = cxp.Tabla1.getSelectedRow();
            info.txtCodigo.setText(cxp.Tabla1.getValueAt(row, 0).toString());
            info.txtDescripcion.setText(cxp.Tabla1.getValueAt(row, 1).toString());
            info.txtCantStock.setText(cxp.Tabla1.getValueAt(row, 2).toString());
            info.txtProyecto.setText(cxp.txtProyecto.getText());
            boolean band = info.guardado();
            if(band){
                cxp.limpiarTabla();
                cxp.verDatos();
            }
            fireEditingStopped();
        }
    }

    public Object getCellEditorValue() {
        return currentValue;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            currentValue = (Boolean) value;
            return button;
    }
}