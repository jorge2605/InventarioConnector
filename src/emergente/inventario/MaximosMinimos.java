package emergente.inventario;

import Conexiones.Conexion;
import Vista.InternalFrame.Reportes;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class MaximosMinimos extends javax.swing.JDialog {

    public void exportarExcel(JTable TablaDeDatos1, String tit) {
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
    
    public double getPendientes(Connection con, String codigo){
        double cant = 0;
        try{
            Statement st = con.createStatement();
            String sql = "select * from requisiciones where codigo like '" + codigo + "' and llego is null and OC != 'CANCELADO'";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                cant += rs.getDouble("cantidad");
            }
            return cant;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error: " + e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return cant;
    }
    
    public final void limpiarTabla(){
        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Codigo", "Maximos", "Minimos", "Cantidad", "Pendiente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
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
    
    public final void verDatos(){
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            String sql = "select * from inventario where (maximos != 0 and minimos != 0) and (cantidad <= minimos)";
            ResultSet rs = st.executeQuery(sql);
            Object datos[] = new Object[10];
            DefaultTableModel miModelo = (DefaultTableModel) Tabla1.getModel();
            while(rs.next()){
                datos[0] = rs.getString("codigo");
                datos[1] = rs.getString("maximos");
                datos[2] = rs.getString("minimos");
                datos[3] = rs.getString("cantidad");
                System.out.println("Codigo: "+datos[0] + "/ Maximos: "+datos[1] + "/ Minimos: "+ datos[2] + "/ cantidad: "+datos[3]);
                datos[4] = getPendientes(con, datos[0].toString());
                miModelo.addRow(datos);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error: "+e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public MaximosMinimos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        limpiarTabla();
        verDatos();
        setTable(Tabla1, jScrollPane2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        excelSalidas = new javax.swing.JButton();
        btnMaxMin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout(0, 20));

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 204));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Maximos y Minimos");
        jPanel1.add(jLabel11, java.awt.BorderLayout.NORTH);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.BorderLayout(10, 0));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Maximos", "Minimos", "Cantidad", "Pendiente"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        excelSalidas.setBackground(new java.awt.Color(255, 255, 255));
        excelSalidas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/excel_24.png"))); // NOI18N
        excelSalidas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        excelSalidas.setBorderPainted(false);
        excelSalidas.setContentAreaFilled(false);
        excelSalidas.setFocusPainted(false);
        excelSalidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excelSalidasActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 13, 13, 13);
        jPanel3.add(excelSalidas, gridBagConstraints);

        btnMaxMin.setBackground(new java.awt.Color(255, 255, 255));
        btnMaxMin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/minmax_24.png"))); // NOI18N
        btnMaxMin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        btnMaxMin.setBorderPainted(false);
        btnMaxMin.setContentAreaFilled(false);
        btnMaxMin.setFocusPainted(false);
        btnMaxMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMaxMinActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 13, 13, 13);
        jPanel3.add(btnMaxMin, gridBagConstraints);

        jPanel2.add(jPanel3, java.awt.BorderLayout.LINE_END);

        jPanel1.add(jPanel2, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabla1MouseClicked
        
    }//GEN-LAST:event_Tabla1MouseClicked

    private void excelSalidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excelSalidasActionPerformed
        exportarExcel(Tabla1, "maximos y minimos");
    }//GEN-LAST:event_excelSalidasActionPerformed

    private void btnMaxMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMaxMinActionPerformed
        JFrame f = (JFrame) JOptionPane.getFrameForComponent(this);
        AgregarMaxMIn max = new AgregarMaxMIn(f, true);
        max.setLocationRelativeTo(f);
        max.setVisible(true);
    }//GEN-LAST:event_btnMaxMinActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MaximosMinimos dialog = new MaximosMinimos(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnMaxMin;
    private javax.swing.JButton excelSalidas;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
