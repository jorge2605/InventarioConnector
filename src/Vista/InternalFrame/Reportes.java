package Vista.InternalFrame;

import Conexiones.Conexion;
import Modelo.CabeceraReportes;
import com.itextpdf.text.BaseColor;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
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

public class Reportes extends javax.swing.JInternalFrame {

    public void setEnabledFalse() {
        pdfActualizacion.setEnabled(false);
        pdfEntradas.setEnabled(false);
        pdfRequi.setEnabled(false);
        pdfSalidas.setEnabled(false);
        excelActualizacion.setEnabled(false);
        excelEntradas.setEnabled(false);
        excelRequi.setEnabled(false);
        excelSalidas.setEnabled(false);
    }

    public final void setTable(JTable tabla, JScrollPane scroll) {
        tabla.getTableHeader().setFont(new java.awt.Font("Roboto", Font.BOLD, 14));
        tabla.getTableHeader().setOpaque(false);
        tabla.getTableHeader().setBackground(new Color(255, 222, 0));
        tabla.getTableHeader().setForeground(Color.gray);
        tabla.setRowHeight(25);
        tabla.setShowVerticalLines(false);
        tabla.setGridColor(new Color(240, 240, 240));

        scroll.getViewport().setBackground(new Color(255, 255, 255));
    }

    public final void limpiarTabla() {
        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Empleado", "Fecha", "Codigo", "Cantidad", "Almacenista", "Motivo"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    public final void limpiarTablaRequisiciones() {
        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Requisicion", "Codigo", "Descripcion", "Cantidad", "Requisitor", "UM", "Proveedor", "Precio", "Llego", "OC", "Fecha Recibo", "TE", "C. R.", "Ubicacion", "Notas", "Remision"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
    }

    public void verDatos(String tabla) {
        if(fecha1.getDatoFecha() == null){
            JOptionPane.showMessageDialog(this, "Debes seleccionar fecha de inicio","Advertencia",JOptionPane.WARNING_MESSAGE);
        }else if(fecha2.getDatoFecha() == null){
            JOptionPane.showMessageDialog(this, "Debes seleccionar fecha de termino","Advertencia",JOptionPane.WARNING_MESSAGE);
        }else{
            try {
                Connection con;
                Conexion con1 = new Conexion();
                con = con1.getConnection();
                Statement st = con.createStatement();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String fec1 = sdf.format(fecha1.getDatoFecha());
                String fec2 = sdf.format(fecha2.getDatoFecha());
                String sql = "select * from " + tabla + " where fecha between '" + fec1 + "' and '" + fec2 + "'";
                ResultSet rs = st.executeQuery(sql);
                DefaultTableModel miModelo = (DefaultTableModel) Tabla1.getModel();
                String datos[] = new String[20];
                while (rs.next()) {
                    datos[0] = rs.getString("empleado");
                    datos[1] = rs.getString("fecha");
                    datos[2] = rs.getString("codigo");
                    datos[3] = rs.getString("cantidad");
                    datos[4] = rs.getString("almacenista");
                    datos[5] = rs.getString("motivo");
                    miModelo.addRow(datos);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void verDatosRequisiciones() {
        try {
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fec1 = sdf.format(fecha1.getDatoFecha());
            String fec2 = sdf.format(fecha2.getDatoFecha());
            String sql = "select * from requisicion where fecha between '" + fec1 + "' and '" + fec2 + "'";
            ResultSet rs = st.executeQuery(sql);
            int inicio = 0, fin = 0, cont = 0;
            while (rs.next()) {
                int id = rs.getInt("idrequisicion");
                if (cont == 0) {
                    inicio = id;
                }
                fin = id;
                cont++;
            }
            String sql2 = "select * from requisiciones where NumRequisicion between " + inicio + " and " + fin;
            Statement st2 = con.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            DefaultTableModel miModelo = (DefaultTableModel) Tabla1.getModel();
            String datos[] = new String[20];
            while (rs2.next()) {
                datos[0] = rs2.getString("NumRequisicion");
                datos[1] = rs2.getString("codigo");
                datos[2] = rs2.getString("descripcion");
                datos[3] = rs2.getString("cantidad");
                datos[4] = rs2.getString("requisitor");
                datos[5] = rs2.getString("UM");
                datos[6] = rs2.getString("proveedor");
                datos[7] = rs2.getString("precio");
                datos[8] = rs2.getString("llego");
                datos[9] = rs2.getString("OC");
                datos[10] = rs2.getString("fechaRecibo");
                datos[11] = rs2.getString("TE");
                datos[12] = rs2.getString("cantidadRecibida");
                datos[13] = rs2.getString("ubicacion");
                datos[14] = rs2.getString("notas");
                datos[15] = rs2.getString("remision");
                miModelo.addRow(datos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void crearPDF(File filePath, String titulo, boolean horizontal) throws IOException, DocumentException {
        Rectangle pag = PageSize.A4;
        if (horizontal) {
            pag = PageSize.A4.rotate();
        }
        Document document = new Document(pag, 36, 36, 90, 36);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath + ".pdf"));
        CabeceraReportes encabezado = new CabeceraReportes();
        encabezado.setImg1x(30);
        encabezado.setImg1y(790);
        encabezado.setImg2x(510);
        encabezado.setImg2y(790);
        encabezado.setY(845);
        encabezado.setX(34);
        if (horizontal) {
            encabezado.setImg1x(230);
            encabezado.setImg1y(530);
            encabezado.setImg2x(550);
            encabezado.setImg2y(530);
            encabezado.setY(580);
            encabezado.setX(155);
        }
        encabezado.setEncabezado("ENCABEZADO DE REMISIONES");
        writer.setPageEvent(encabezado);
        document.open();

        com.itextpdf.text.Font fuente3 = new com.itextpdf.text.Font();
        fuente3.setSize(14);
        fuente3.setFamily("Roboto");
        fuente3.setColor(BaseColor.BLACK);
        fuente3.setStyle(com.itextpdf.text.Font.BOLD);

        PdfPTable pdfTable = new PdfPTable(Tabla1.getColumnCount());
        pdfTable.setWidthPercentage(100);

        PdfPTable tabTit = new PdfPTable(1);
        pdfTable.setWidthPercentage(100);

        PdfPCell cel = new PdfPCell(new Phrase(titulo, fuente3));
        cel.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel.setBorder(0);
        tabTit.addCell(cel);

        cel = new PdfPCell(new Phrase(" ", fuente3));
        cel.setHorizontalAlignment(Element.ALIGN_CENTER);
        cel.setBorder(0);
        tabTit.addCell(cel);

        Font headerFont = new Font(new Font(FontFamily.TIMES_ROMAN, 10, Font.BOLD));
        Font cellFont = new Font(Font.FontFamily.HELVETICA, 8);

        for (int col = 0; col < Tabla1.getColumnCount(); col++) {
            PdfPCell cell = new PdfPCell(new Phrase(Tabla1.getColumnName(col), headerFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfTable.addCell(cell);
        }

        for (int row = 0; row < Tabla1.getRowCount(); row++) {
            for (int col = 0; col < Tabla1.getColumnCount(); col++) {
                String tab;
                try {
                    tab = Tabla1.getValueAt(row, col).toString();
                } catch (Exception e) {
                    tab = "";
                }
                PdfPCell cell = new PdfPCell(new Phrase(tab, cellFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTable.addCell(cell);
            }
        }

        document.add(tabTit);
        document.add(pdfTable);
        document.close();
    }

    public void exportarPdf(String titulo, boolean horizontal) {
        if(fecha1.getDatoFecha() == null){
            JOptionPane.showMessageDialog(this, "Debes seleccionar fecha de inicio","Advertencia",JOptionPane.WARNING_MESSAGE);
        }else if(fecha2.getDatoFecha() == null){
            JOptionPane.showMessageDialog(this, "Debes seleccionar fecha de termino","Advertencia",JOptionPane.WARNING_MESSAGE);
        }else{
            JFileChooser fc = new JFileChooser();
            File archivo = null;
            fc.setFileFilter(new FileNameExtensionFilter("PDF (*.pdf)", "pdf"));
            int n = fc.showSaveDialog(this);

            if (n == JFileChooser.APPROVE_OPTION) {
                archivo = fc.getSelectedFile();
                try {
                    crearPDF(archivo, titulo, horizontal);
                    Desktop.getDesktop().open(new File(archivo.getAbsolutePath() + ".pdf"));
                } catch (IOException ex) {
                    Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void exportarExcel(JTable TablaDeDatos1, String tit) {
        if(fecha1.getDatoFecha() == null){
            JOptionPane.showMessageDialog(this, "Debes seleccionar fecha de inicio","Advertencia",JOptionPane.WARNING_MESSAGE);
        }else if(fecha2.getDatoFecha() == null){
            JOptionPane.showMessageDialog(this, "Debes seleccionar fecha de termino","Advertencia",JOptionPane.WARNING_MESSAGE);
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

    public Reportes(String numEmpleado, String nomEmpleado) {
        initComponents();
        setTable(Tabla1, jScrollPane1);
        limpiarTabla();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fecha1 = new rojeru_san.rsdate.RSDateChooser();
        fecha2 = new rojeru_san.rsdate.RSDateChooser();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        pnlSalidas = new javax.swing.JPanel();
        btnSalidas = new javax.swing.JButton();
        pdfSalidas = new javax.swing.JButton();
        excelSalidas = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        pnlEntradas = new javax.swing.JPanel();
        btnEntradas = new javax.swing.JButton();
        pdfEntradas = new javax.swing.JButton();
        excelEntradas = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        pnlActualizacion = new javax.swing.JPanel();
        btnActualizacion = new javax.swing.JButton();
        pdfActualizacion = new javax.swing.JButton();
        excelActualizacion = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        pnlRequisiciones = new javax.swing.JPanel();
        btnRequisiciones = new javax.swing.JButton();
        pdfRequi = new javax.swing.JButton();
        excelRequi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel11.setFont(new java.awt.Font("Lexend", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 204));
        jLabel11.setText("Reportes");
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
        jPanel6.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Fecha de inicio");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel6.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Seleccionar fechas de reporte");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        jPanel6.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Fecha de termino");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(9, 9, 9, 9);
        jPanel6.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(fecha1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(fecha2, gridBagConstraints);

        jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new java.awt.GridLayout(4, 0));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salidas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 1, 12))); // NOI18N
        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 30, 5));

        pnlSalidas.setBackground(new java.awt.Color(255, 255, 255));

        btnSalidas.setBackground(new java.awt.Color(255, 255, 255));
        btnSalidas.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnSalidas.setText("Ver datos");
        btnSalidas.setBorder(null);
        btnSalidas.setBorderPainted(false);
        btnSalidas.setContentAreaFilled(false);
        btnSalidas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalidas.setFocusPainted(false);
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

        jPanel9.add(pnlSalidas);

        pdfSalidas.setBackground(new java.awt.Color(255, 255, 255));
        pdfSalidas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/pdf_24.png"))); // NOI18N
        pdfSalidas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        pdfSalidas.setBorderPainted(false);
        pdfSalidas.setContentAreaFilled(false);
        pdfSalidas.setEnabled(false);
        pdfSalidas.setFocusPainted(false);
        pdfSalidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfSalidasActionPerformed(evt);
            }
        });
        jPanel9.add(pdfSalidas);

        excelSalidas.setBackground(new java.awt.Color(255, 255, 255));
        excelSalidas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/excel_24.png"))); // NOI18N
        excelSalidas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        excelSalidas.setBorderPainted(false);
        excelSalidas.setContentAreaFilled(false);
        excelSalidas.setEnabled(false);
        excelSalidas.setFocusPainted(false);
        excelSalidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excelSalidasActionPerformed(evt);
            }
        });
        jPanel9.add(excelSalidas);

        jPanel8.add(jPanel9);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Entradas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 1, 12))); // NOI18N
        jPanel13.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 30, 5));

        pnlEntradas.setBackground(new java.awt.Color(255, 255, 255));

        btnEntradas.setBackground(new java.awt.Color(255, 255, 255));
        btnEntradas.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnEntradas.setText("Ver datos");
        btnEntradas.setBorder(null);
        btnEntradas.setBorderPainted(false);
        btnEntradas.setContentAreaFilled(false);
        btnEntradas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEntradas.setFocusPainted(false);
        btnEntradas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEntradasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEntradasMouseExited(evt);
            }
        });
        btnEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntradasActionPerformed(evt);
            }
        });
        pnlEntradas.add(btnEntradas);

        jPanel13.add(pnlEntradas);

        pdfEntradas.setBackground(new java.awt.Color(255, 255, 255));
        pdfEntradas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/pdf_24.png"))); // NOI18N
        pdfEntradas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        pdfEntradas.setBorderPainted(false);
        pdfEntradas.setContentAreaFilled(false);
        pdfEntradas.setEnabled(false);
        pdfEntradas.setFocusPainted(false);
        pdfEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfEntradasActionPerformed(evt);
            }
        });
        jPanel13.add(pdfEntradas);

        excelEntradas.setBackground(new java.awt.Color(255, 255, 255));
        excelEntradas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/excel_24.png"))); // NOI18N
        excelEntradas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        excelEntradas.setBorderPainted(false);
        excelEntradas.setContentAreaFilled(false);
        excelEntradas.setEnabled(false);
        excelEntradas.setFocusPainted(false);
        excelEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excelEntradasActionPerformed(evt);
            }
        });
        jPanel13.add(excelEntradas);

        jPanel8.add(jPanel13);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Actualizacion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 1, 12))); // NOI18N
        jPanel14.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 30, 5));

        pnlActualizacion.setBackground(new java.awt.Color(255, 255, 255));

        btnActualizacion.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizacion.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnActualizacion.setText("Ver datos");
        btnActualizacion.setBorder(null);
        btnActualizacion.setBorderPainted(false);
        btnActualizacion.setContentAreaFilled(false);
        btnActualizacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnActualizacion.setFocusPainted(false);
        btnActualizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnActualizacionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActualizacionMouseExited(evt);
            }
        });
        btnActualizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizacionActionPerformed(evt);
            }
        });
        pnlActualizacion.add(btnActualizacion);

        jPanel14.add(pnlActualizacion);

        pdfActualizacion.setBackground(new java.awt.Color(255, 255, 255));
        pdfActualizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/pdf_24.png"))); // NOI18N
        pdfActualizacion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        pdfActualizacion.setBorderPainted(false);
        pdfActualizacion.setContentAreaFilled(false);
        pdfActualizacion.setEnabled(false);
        pdfActualizacion.setFocusPainted(false);
        pdfActualizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfActualizacionActionPerformed(evt);
            }
        });
        jPanel14.add(pdfActualizacion);

        excelActualizacion.setBackground(new java.awt.Color(255, 255, 255));
        excelActualizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/excel_24.png"))); // NOI18N
        excelActualizacion.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        excelActualizacion.setBorderPainted(false);
        excelActualizacion.setContentAreaFilled(false);
        excelActualizacion.setEnabled(false);
        excelActualizacion.setFocusPainted(false);
        excelActualizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excelActualizacionActionPerformed(evt);
            }
        });
        jPanel14.add(excelActualizacion);

        jPanel8.add(jPanel14);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Requisiciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Roboto", 1, 12))); // NOI18N
        jPanel15.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 30, 5));

        pnlRequisiciones.setBackground(new java.awt.Color(255, 255, 255));

        btnRequisiciones.setBackground(new java.awt.Color(255, 255, 255));
        btnRequisiciones.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        btnRequisiciones.setText("Ver datos");
        btnRequisiciones.setBorder(null);
        btnRequisiciones.setBorderPainted(false);
        btnRequisiciones.setContentAreaFilled(false);
        btnRequisiciones.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRequisiciones.setFocusPainted(false);
        btnRequisiciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRequisicionesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRequisicionesMouseExited(evt);
            }
        });
        btnRequisiciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRequisicionesActionPerformed(evt);
            }
        });
        pnlRequisiciones.add(btnRequisiciones);

        jPanel15.add(pnlRequisiciones);

        pdfRequi.setBackground(new java.awt.Color(255, 255, 255));
        pdfRequi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/pdf_24.png"))); // NOI18N
        pdfRequi.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        pdfRequi.setBorderPainted(false);
        pdfRequi.setContentAreaFilled(false);
        pdfRequi.setEnabled(false);
        pdfRequi.setFocusPainted(false);
        pdfRequi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfRequiActionPerformed(evt);
            }
        });
        jPanel15.add(pdfRequi);

        excelRequi.setBackground(new java.awt.Color(255, 255, 255));
        excelRequi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/excel_24.png"))); // NOI18N
        excelRequi.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        excelRequi.setBorderPainted(false);
        excelRequi.setContentAreaFilled(false);
        excelRequi.setEnabled(false);
        excelRequi.setFocusPainted(false);
        excelRequi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excelRequiActionPerformed(evt);
            }
        });
        jPanel15.add(excelRequi);

        jPanel8.add(jPanel15);

        jPanel7.add(jPanel8, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Requisicion", "Codigo", "Descripcion", "Cantidad", "Requisitor", "UM", "Proveedor", "Precio", "Llego", "OC", "Fecha Recibo", "TE", "C. R.", "Ubicacion", "Notas", "Remision"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tabla1);

        jPanel7.add(jScrollPane1, java.awt.BorderLayout.CENTER);

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

    private void btnSalidasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalidasMouseEntered
        pnlSalidas.setBackground(new Color(255, 222, 0));
        btnSalidas.setForeground(Color.gray);
    }//GEN-LAST:event_btnSalidasMouseEntered

    private void btnSalidasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalidasMouseExited
        pnlSalidas.setBackground(Color.white);
        btnSalidas.setForeground(Color.black);
    }//GEN-LAST:event_btnSalidasMouseExited

    private void btnSalidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalidasActionPerformed
        setEnabledFalse();
        pdfSalidas.setEnabled(true);
        excelSalidas.setEnabled(true);
        limpiarTabla();
        verDatos("salidamaterial");
    }//GEN-LAST:event_btnSalidasActionPerformed

    private void btnEntradasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEntradasMouseEntered
        pnlEntradas.setBackground(new Color(255, 222, 0));
        btnEntradas.setForeground(Color.gray);
    }//GEN-LAST:event_btnEntradasMouseEntered

    private void btnEntradasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEntradasMouseExited
        pnlEntradas.setBackground(Color.white);
        btnEntradas.setForeground(Color.black);
    }//GEN-LAST:event_btnEntradasMouseExited

    private void btnEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntradasActionPerformed
        setEnabledFalse();
        pdfEntradas.setEnabled(true);
        excelEntradas.setEnabled(true);
        limpiarTabla();
        verDatos("entradamaterial");
    }//GEN-LAST:event_btnEntradasActionPerformed

    private void btnActualizacionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizacionMouseEntered
        pnlActualizacion.setBackground(new Color(255, 222, 0));
        btnActualizacion.setForeground(Color.gray);
    }//GEN-LAST:event_btnActualizacionMouseEntered

    private void btnActualizacionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizacionMouseExited
        pnlActualizacion.setBackground(Color.white);
        btnActualizacion.setForeground(Color.black);
    }//GEN-LAST:event_btnActualizacionMouseExited

    private void btnActualizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizacionActionPerformed
        setEnabledFalse();
        pdfActualizacion.setEnabled(true);
        excelActualizacion.setEnabled(true);
        limpiarTabla();
        verDatos("actualizarmaterial");
    }//GEN-LAST:event_btnActualizacionActionPerformed

    private void btnRequisicionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRequisicionesMouseEntered
        pnlRequisiciones.setBackground(new Color(255, 222, 0));
        btnRequisiciones.setForeground(Color.gray);
    }//GEN-LAST:event_btnRequisicionesMouseEntered

    private void btnRequisicionesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRequisicionesMouseExited
        pnlRequisiciones.setBackground(Color.white);
        btnRequisiciones.setForeground(Color.black);
    }//GEN-LAST:event_btnRequisicionesMouseExited

    private void btnRequisicionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRequisicionesActionPerformed
        setEnabledFalse();
        pdfRequi.setEnabled(true);
        excelRequi.setEnabled(true);
        limpiarTablaRequisiciones();
        verDatosRequisiciones();
    }//GEN-LAST:event_btnRequisicionesActionPerformed

    private void pdfSalidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfSalidasActionPerformed
        exportarPdf("Salida de material", false);
    }//GEN-LAST:event_pdfSalidasActionPerformed

    private void pdfRequiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfRequiActionPerformed
        exportarPdf("Requisiciones", true);
    }//GEN-LAST:event_pdfRequiActionPerformed

    private void pdfEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfEntradasActionPerformed
        exportarPdf("Entrada de material", false);
    }//GEN-LAST:event_pdfEntradasActionPerformed

    private void pdfActualizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfActualizacionActionPerformed
        exportarPdf("Actualizacion de material", false);
    }//GEN-LAST:event_pdfActualizacionActionPerformed

    private void excelSalidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excelSalidasActionPerformed
        exportarExcel(Tabla1, "salidas");
    }//GEN-LAST:event_excelSalidasActionPerformed

    private void excelEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excelEntradasActionPerformed
        exportarExcel(Tabla1, "entradas");
    }//GEN-LAST:event_excelEntradasActionPerformed

    private void excelActualizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excelActualizacionActionPerformed
        exportarExcel(Tabla1, "actualizacion");
    }//GEN-LAST:event_excelActualizacionActionPerformed

    private void excelRequiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excelRequiActionPerformed
        exportarExcel(Tabla1, "requisiciones");
    }//GEN-LAST:event_excelRequiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelX;
    private javax.swing.JTable Tabla1;
    public javax.swing.JButton btnActualizacion;
    public javax.swing.JButton btnEntradas;
    public javax.swing.JButton btnRequisiciones;
    public javax.swing.JButton btnSalidas;
    private javax.swing.JButton excelActualizacion;
    private javax.swing.JButton excelEntradas;
    private javax.swing.JButton excelRequi;
    private javax.swing.JButton excelSalidas;
    private rojeru_san.rsdate.RSDateChooser fecha1;
    private rojeru_san.rsdate.RSDateChooser fecha2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
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
    private javax.swing.JButton pdfActualizacion;
    private javax.swing.JButton pdfEntradas;
    private javax.swing.JButton pdfRequi;
    private javax.swing.JButton pdfSalidas;
    public javax.swing.JPanel pnlActualizacion;
    public javax.swing.JPanel pnlEntradas;
    public javax.swing.JPanel pnlRequisiciones;
    public javax.swing.JPanel pnlSalidas;
    // End of variables declaration//GEN-END:variables
}
