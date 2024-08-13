package Vista.InternalFrame;

import Conexiones.Conexion;
import com.mxrck.autocompleter.TextAutoCompleter;
import emergente.inventario.AgregarArticulo;
import emergente.inventario.MaximosMinimos;
import emergente.inventario.infoInventario;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import static org.apache.poi.ss.usermodel.FillPatternType.SOLID_FOREGROUND;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
                "CODIGO", "DESCRIPCION", "CANTIDAD", "PROVEEDOR", "UBICACION", "MAXIMOS", "MINIMOS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        
        if (Tabla1.getColumnModel().getColumnCount() > 0) {
            Tabla1.getColumnModel().getColumn(1).setMinWidth(300);
            Tabla1.getColumnModel().getColumn(1).setPreferredWidth(600);
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
                String codigo = rs.getString("codigo");
                datos[0] =  (codigo == null) ? "" : codigo;
                String descripcion = rs.getString("descripcion");
                datos[1] = (descripcion == null) ? "" : descripcion;
                String cantidad = rs.getString("cantidad");
                datos[2] = (cantidad == null) ? "" : cantidad;
                String proveedor = rs.getString("proveedor");
                datos[3] = (proveedor == null) ? "" : proveedor;
                String ubicacion = rs.getString("ubicacion");
                datos[4] = (ubicacion == null) ? "" : ubicacion;
                String maximos = rs.getString("maximos");
                datos[5] = (maximos == null) ? "" : maximos;
                String minimos = rs.getString("minimos");
                datos[6] = (minimos == null) ? "" : minimos;
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
    
    public void exportarExcel(JTable TablaDeDatos1, String tit) {
        if(TablaDeDatos1.getRowCount() <= 0){
            JOptionPane.showMessageDialog(this, "Existir al menos un articulo para generar Excel","Advertencia",JOptionPane.WARNING_MESSAGE);
        }else{
            Workbook book;
            int columna = TablaDeDatos1.getColumnCount();
            try {
                JFileChooser fc = new JFileChooser();
                File archivo = null;
                fc.setFileFilter(new FileNameExtensionFilter("EXCEL (*.xlsx)", "xlsx"));
                int n = fc.showSaveDialog(this);

                if (n == JFileChooser.APPROVE_OPTION) {
                    archivo = fc.getSelectedFile();
                    String a = "" + archivo;
                    if (a.endsWith("xls")) {
                        book = new HSSFWorkbook();
                    } else {
                        book = new XSSFWorkbook();
                        a = archivo + ".xlsx";
                    }

                    Sheet hoja = book.createSheet("Reporte de " + tit);
                    Row fila = hoja.createRow(2);
                    Cell col = fila.createCell(2);

                    //-------------------------------ESTILOS
                    org.apache.poi.ss.usermodel.Font font = book.createFont();
                    CellStyle estilo1 = book.createCellStyle();

                    org.apache.poi.ss.usermodel.Font font3 = book.createFont();
                    CellStyle estilo3 = book.createCellStyle();

                    font.setBold(true);
                    font.setColor(IndexedColors.BLACK.getIndex());
                    font.setFontHeightInPoints((short) 12);
                    estilo1.setFont(font);

                    estilo1.setAlignment(HorizontalAlignment.LEFT);

                    font3.setBold(false);
                    font3.setColor(IndexedColors.BLACK.getIndex());
                    font3.setFontHeightInPoints((short) 15);
                    estilo3.setFont(font3);

                    estilo3.setAlignment(HorizontalAlignment.CENTER);
                    estilo3.setWrapText(true);

                    //--------------------------------------
                    //        hoja.setColumnWidth(2, 5000);
                    //---------------------------------------
                    hoja.setColumnWidth(2, 4000);
                    hoja.setColumnWidth(3, 6500);
                    hoja.setColumnWidth(4, 6500);
                    hoja.setColumnWidth(5, 8200);
                    hoja.setColumnWidth(7, 8200);
                    hoja.setColumnWidth(13, 8200);

                    org.apache.poi.ss.usermodel.Font font1 = book.createFont();
                    CellStyle style = book.createCellStyle();

                    font1.setBold(true);
                    font1.setColor(IndexedColors.WHITE.getIndex());
                    font1.setFontHeightInPoints((short) 16);
                    style.setFont(font1);

                    style.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                    style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                    style.setFillPattern(SOLID_FOREGROUND);
                    style.setVerticalAlignment(VerticalAlignment.BOTTOM);
                    style.setAlignment(HorizontalAlignment.CENTER);
                    style.setWrapText(true);

                    hoja.addMergedRegion(new CellRangeAddress(
                            2,
                            2,
                            2,
                            columna + 1
                    ));

                    Map<String, Object> properties = new HashMap<String, Object>();
                    properties.put(CellUtil.BORDER_TOP, BorderStyle.MEDIUM);
                    properties.put(CellUtil.BORDER_BOTTOM, BorderStyle.MEDIUM);
                    properties.put(CellUtil.BORDER_LEFT, BorderStyle.MEDIUM);
                    properties.put(CellUtil.BORDER_RIGHT, BorderStyle.MEDIUM);

                    properties.put(CellUtil.TOP_BORDER_COLOR, IndexedColors.BLACK.getIndex());
                    properties.put(CellUtil.BOTTOM_BORDER_COLOR, IndexedColors.BLACK.getIndex());
                    properties.put(CellUtil.LEFT_BORDER_COLOR, IndexedColors.BLACK.getIndex());
                    properties.put(CellUtil.RIGHT_BORDER_COLOR, IndexedColors.BLACK.getIndex());

                    col.setCellStyle(style);
                    col.setCellValue("Reporte de " + tit);

                    for (int i = -1; i < TablaDeDatos1.getRowCount(); i++) {
                        Row fila10 = hoja.createRow(i + 7);
                        for (int j = 0; j < columna; j++) {
                            Cell celda = fila10.createCell(j + 2);
                            if (i == -1 && (j >= 0 && j <= columna + 1)) {
                                CellStyle s = book.createCellStyle();
                                org.apache.poi.ss.usermodel.Font f = book.createFont();
                                f.setBold(true);
                                f.setColor(IndexedColors.WHITE.getIndex());
                                s.setFont(f);
                                s.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
                                s.setFillPattern(SOLID_FOREGROUND);
                                celda.setCellStyle(s);
                            }
                            if (i > -1 && (j > -1 && j <= columna) && (i % 2 == 0)) {
                                CellStyle s = book.createCellStyle();
                                s.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                                s.setFillPattern(SOLID_FOREGROUND);
                                celda.setCellStyle(s);
                            }

                            if (i == -1) {
                                celda.setCellValue(String.valueOf(TablaDeDatos1.getColumnName(j)));
                                //                        CellUtil.setCellStyleProperties(celda, properties);
                            } else {
                                if (j == 3) {
                                    CellStyle ss = book.createCellStyle();
                                    ss.setWrapText(true);

                                    if (i % 2 == 0) {
                                        ss.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                                        ss.setFillPattern(SOLID_FOREGROUND);

                                    }
                                    celda.setCellStyle(ss);
                                }
                                celda.setCellValue(String.valueOf(TablaDeDatos1.getValueAt(i, j)));
                            }
                            File ad = new File(a);
                            book.write(new FileOutputStream(a));
                        }
                    }
                    book.close();
                    Desktop.getDesktop().open(new File(a));
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void esa(int seleccionado){
        JFrame f = (JFrame) JOptionPane.getFrameForComponent(this);
        infoInventario info = new infoInventario(f,true);
        info.setLocationRelativeTo(this);
        boolean puede = true;
        switch (seleccionado) {
            case 1:
                info.lblTitulo.setText("Salida");
                if(this.salidas == false){
                    puede = false;
                }
                break;
            case 2:
                info.lblTitulo.setText("Entrada");
                info.lblCantidad.setText("Cantidad a ingresar:");
                if(this.entradas == false){
                    puede = false;
                }
                break;
            case 3:
                info.lblTitulo.setText("Actualizar");
                info.lblCantidad.setText("Cantidad:");
                if(!this.rol.equals("Admin")){
                    puede = false;
                }
                break;
            default:
                break;
        }
        if(puede == false){
            JOptionPane.showMessageDialog(this, "No tienes permiso de realizar este moviento","Error",JOptionPane.ERROR_MESSAGE);
        }else{
            info.txtAlmacenista.setText(txtAlmacenista.getText());
            info.txtEmpleado.setText(txtRequisitor.getText());
            int row = Tabla1.getSelectedRow();
            info.txtCodigo.setText(Tabla1.getValueAt(row, 0).toString());
            info.txtDescripcion.setText(Tabla1.getValueAt(row, 1).toString());
            info.txtCantStock.setText(Tabla1.getValueAt(row, 2).toString());
            info.txtProyecto.setText(txtProyecto.getText());
            boolean band = info.guardado();
            if(band){
                limpiarTabla();
                verDatos();
            }
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        salida = new javax.swing.JMenuItem();
        entrada = new javax.swing.JMenuItem();
        axtualizacion = new javax.swing.JMenuItem();
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
        jPanel13 = new javax.swing.JPanel();
        btnAgregar = new Componentes.Boton();
        btnAgregar1 = new Componentes.Boton();
        btnExcel = new Componentes.Boton();
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
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtProyecto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtDesc = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();

        jPopupMenu1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                jPopupMenu1PopupMenuWillBecomeVisible(evt);
            }
        });

        salida.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        salida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/salida_16.png"))); // NOI18N
        salida.setText("Dar salida de material ");
        salida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salidaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(salida);

        entrada.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        entrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/entrada_16.png"))); // NOI18N
        entrada.setText("Dar entrada de material ");
        entrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entradaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(entrada);

        axtualizacion.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        axtualizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/act_16.png"))); // NOI18N
        axtualizacion.setText("Actualizacion de material ");
        axtualizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                axtualizacionActionPerformed(evt);
            }
        });
        jPopupMenu1.add(axtualizacion);

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
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "CODIGO", "DESCRIPCION", "CANTIDAD", "PROVEEDOR", "UBICACION", "MAXIMOS", "MINIMOS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

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
        jScrollPane1.setViewportView(Tabla1);

        jPanel9.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 5));

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
        jPanel13.add(btnAgregar);

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
        jPanel13.add(btnAgregar1);

        btnExcel.setBackground(new java.awt.Color(255, 255, 255));
        btnExcel.setForeground(new java.awt.Color(0, 153, 0));
        btnExcel.setText("Exportar Excel");
        btnExcel.setBorderColor(new java.awt.Color(0, 153, 0));
        btnExcel.setBorderColorSelected(new java.awt.Color(0, 102, 0));
        btnExcel.setColor(new java.awt.Color(0, 153, 0));
        btnExcel.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });
        jPanel13.add(btnExcel);

        jPanel9.add(jPanel13, java.awt.BorderLayout.PAGE_START);

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

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        exportarExcel(Tabla1, "Inventario");
    }//GEN-LAST:event_btnExcelActionPerformed

    private void Tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla1MouseClicked
        
    }//GEN-LAST:event_Tabla1MouseClicked

    private void salidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salidaActionPerformed
        esa(1);
    }//GEN-LAST:event_salidaActionPerformed

    private void entradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entradaActionPerformed
        esa(2);
    }//GEN-LAST:event_entradaActionPerformed

    private void axtualizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_axtualizacionActionPerformed
        esa(3);
    }//GEN-LAST:event_axtualizacionActionPerformed

    private void jPopupMenu1PopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jPopupMenu1PopupMenuWillBecomeVisible
        if(Tabla1.getSelectedRow() >= 0){
            salida.setEnabled(true);
            entrada.setEnabled(true);
            axtualizacion.setEnabled(true);
            salida.setText("Salida de material " + Tabla1.getValueAt(Tabla1.getSelectedRow(), 0));
            entrada.setText("Entrada de material " + Tabla1.getValueAt(Tabla1.getSelectedRow(), 0));
            axtualizacion.setText("Actualizacion de material " + Tabla1.getValueAt(Tabla1.getSelectedRow(), 0));
        }else{
            salida.setEnabled(false);
            entrada.setEnabled(false);
            axtualizacion.setEnabled(false);
        }
    }//GEN-LAST:event_jPopupMenu1PopupMenuWillBecomeVisible


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelX;
    private javax.swing.JPanel Salida;
    public javax.swing.JTable Tabla1;
    private javax.swing.JMenuItem axtualizacion;
    private javax.swing.JButton btnActualizar;
    private Componentes.Boton btnAgregar;
    private Componentes.Boton btnAgregar1;
    private javax.swing.JButton btnEntrada;
    private Componentes.Boton btnExcel;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnSalida;
    private javax.swing.JMenuItem entrada;
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
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblX;
    private javax.swing.JPanel panelActualizar;
    private javax.swing.JPanel panelCard;
    private javax.swing.JPanel panelEntrada;
    private javax.swing.JPanel panelInicio;
    private javax.swing.JPanel panelSalida;
    private javax.swing.JMenuItem salida;
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
                JOptionPane.showMessageDialog(this,getClass().getResource("/recursos/Iconos/salida_16.png"));
//                boton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Iconos/salida_16.png"))); // NOI18N
                break;
            case 2:
//                boton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Iconos/entrada_16.png"))); // NOI18N
                break;
            case 3:
//                boton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Iconos/act_16.png"))); // NOI18N
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
        
    }

    public Object getCellEditorValue() {
        return currentValue;
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            currentValue = (Boolean) value;
            return button;
    }
}