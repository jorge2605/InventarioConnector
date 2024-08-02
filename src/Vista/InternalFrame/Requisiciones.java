package Vista.InternalFrame;

import Componentes.DoubleFilter;
import Conexiones.Conexion;
import com.mxrck.autocompleter.TextAutoCompleter;
import emergente.requisiciones.verID;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

public class Requisiciones extends javax.swing.JInternalFrame implements FocusListener{

    String nomEmpleado;
    String numEmpleado;
    TextAutoCompleter au;
    
    public final void addPartes(){
        try{
            au = new TextAutoCompleter(txtCodigo);
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            String sql = "select codigo from inventario";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                au.addItem(rs.getString("codigo"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error: "+e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void limpiarDatos(){
        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtUM.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtProveedor.setText("");
    }
    
    public void setBordeA(JComponent field){
        field.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 222, 0)));
    }
    
    public void setBordeG(JComponent field){
        field.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
    }
    
    public final void setTable(){
        Tabla1.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 14));
        Tabla1.getTableHeader().setOpaque(false);
        Tabla1.getTableHeader().setBackground(new Color(255, 222, 0));
        Tabla1.getTableHeader().setForeground(Color.gray);
        Tabla1.setRowHeight(25);
        Tabla1.setShowVerticalLines(false);
        Tabla1.setGridColor(new Color(240,240,240));
        
        jScrollPane2.getViewport().setBackground(new Color(255,255,255));
        
        ((AbstractDocument) txtCantidad.getDocument()).setDocumentFilter(new DoubleFilter());
        ((AbstractDocument) txtPrecio.getDocument()).setDocumentFilter(new DoubleFilter());
    }
    
    public final void limpiarTabla(){
        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Codigo", "Descripcion", "Cantidad", "Precio", "Total", "Proveedor", "PO", "UM", "Remision"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    public Requisiciones(String nombre, String numEmpleado) {
        initComponents();
        setTable();
        limpiarTabla();
        txtCantidad.addFocusListener(this);
        txtCodigo.addFocusListener(this);
        txtDescripcion.addFocusListener(this);
        txtPO.addFocusListener(this);
        txtPrecio.addFocusListener(this);
        txtProveedor.addFocusListener(this);
        txtUM.addFocusListener(this);
        txtRemision.addFocusListener(this);
        addPartes();
        this.nomEmpleado = nombre;
        this.numEmpleado = numEmpleado;
        Tabla1.setShowVerticalLines(false);
        txtRequisitor.setText(nomEmpleado);
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
        lblCodigo = new javax.swing.JLabel();
        txtRequisitor = new javax.swing.JTextField();
        lblRequisitor = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lblDesc = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        lblum = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        lblCantidad = new javax.swing.JLabel();
        txtUM = new javax.swing.JTextField();
        lblProveedor = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        lblPrecio = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        lblPO = new javax.swing.JLabel();
        txtPO = new javax.swing.JTextField();
        btnAgregar = new Componentes.Boton();
        lblRemision = new javax.swing.JLabel();
        txtRemision = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        btnAgregar1 = new Componentes.Boton();
        jLabel9 = new javax.swing.JLabel();
        btnAgregar2 = new Componentes.Boton();
        jLabel10 = new javax.swing.JLabel();
        btnAgregar3 = new Componentes.Boton();

        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout(10, 10));

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel11.setFont(new java.awt.Font("Lexend", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 102, 204));
        jLabel11.setText("Requisiciones");
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
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel6Layout = new java.awt.GridBagLayout();
        jPanel6Layout.columnWidths = new int[] {1, 1, 1, 1, 1};
        jPanel6Layout.columnWeights = new double[] {1.0, 1.0, 1.0, 1.0, 1.0};
        jPanel6.setLayout(jPanel6Layout);

        lblCodigo.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblCodigo.setForeground(new java.awt.Color(153, 153, 153));
        lblCodigo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCodigo.setText("Codigo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(lblCodigo, gridBagConstraints);

        txtRequisitor.setEditable(false);
        txtRequisitor.setBackground(new java.awt.Color(255, 255, 255));
        txtRequisitor.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtRequisitor.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        txtRequisitor.setEnabled(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(txtRequisitor, gridBagConstraints);

        lblRequisitor.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblRequisitor.setForeground(new java.awt.Color(153, 153, 153));
        lblRequisitor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblRequisitor.setText("Requisitor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(lblRequisitor, gridBagConstraints);

        txtCodigo.setBackground(new java.awt.Color(255, 255, 255));
        txtCodigo.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCodigo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(txtCodigo, gridBagConstraints);

        lblDesc.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblDesc.setForeground(new java.awt.Color(153, 153, 153));
        lblDesc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblDesc.setText("Descripcion");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 20);
        jPanel6.add(lblDesc, gridBagConstraints);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(jScrollPane1, gridBagConstraints);

        lblum.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblum.setForeground(new java.awt.Color(153, 153, 153));
        lblum.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblum.setText("UM");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(lblum, gridBagConstraints);

        txtPrecio.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecio.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtPrecio.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(txtPrecio, gridBagConstraints);

        lblCantidad.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblCantidad.setForeground(new java.awt.Color(153, 153, 153));
        lblCantidad.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCantidad.setText("Cantidad");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(lblCantidad, gridBagConstraints);

        txtUM.setBackground(new java.awt.Color(255, 255, 255));
        txtUM.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtUM.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(txtUM, gridBagConstraints);

        lblProveedor.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblProveedor.setForeground(new java.awt.Color(153, 153, 153));
        lblProveedor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblProveedor.setText("Proveedor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(lblProveedor, gridBagConstraints);

        txtCantidad.setBackground(new java.awt.Color(255, 255, 255));
        txtCantidad.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtCantidad.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(txtCantidad, gridBagConstraints);

        lblPrecio.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblPrecio.setForeground(new java.awt.Color(153, 153, 153));
        lblPrecio.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPrecio.setText("Precio");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 20);
        jPanel6.add(lblPrecio, gridBagConstraints);

        txtProveedor.setBackground(new java.awt.Color(255, 255, 255));
        txtProveedor.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtProveedor.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(txtProveedor, gridBagConstraints);

        lblPO.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblPO.setForeground(new java.awt.Color(153, 153, 153));
        lblPO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblPO.setText("PO");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 20);
        jPanel6.add(lblPO, gridBagConstraints);

        txtPO.setBackground(new java.awt.Color(255, 255, 255));
        txtPO.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtPO.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        txtPO.setNextFocusableComponent(txtRemision);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(txtPO, gridBagConstraints);

        btnAgregar.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar.setForeground(new java.awt.Color(0, 102, 255));
        btnAgregar.setText("Agregar");
        btnAgregar.setBorderColor(new java.awt.Color(0, 102, 255));
        btnAgregar.setBorderColorSelected(new java.awt.Color(0, 51, 153));
        btnAgregar.setColor(new java.awt.Color(0, 102, 255));
        btnAgregar.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 27;
        gridBagConstraints.ipady = 2;
        jPanel6.add(btnAgregar, gridBagConstraints);

        lblRemision.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblRemision.setForeground(new java.awt.Color(153, 153, 153));
        lblRemision.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblRemision.setText("Remision");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 0, 20);
        jPanel6.add(lblRemision, gridBagConstraints);

        txtRemision.setBackground(new java.awt.Color(255, 255, 255));
        txtRemision.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtRemision.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)));
        txtRemision.setNextFocusableComponent(btnAgregar);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 4;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel6.add(txtRemision, gridBagConstraints);

        jPanel5.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.BorderLayout(15, 0));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Cantidad", "Precio", "Total", "Proveedor", "PO", "UM", "Remision"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(Tabla1);

        jPanel7.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.Y_AXIS));

        btnAgregar1.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar1.setForeground(new java.awt.Color(0, 204, 0));
        btnAgregar1.setText("Guardar");
        btnAgregar1.setBorderColor(new java.awt.Color(0, 204, 0));
        btnAgregar1.setBorderColorSelected(new java.awt.Color(0, 102, 0));
        btnAgregar1.setColor(new java.awt.Color(0, 204, 0));
        btnAgregar1.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnAgregar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar1ActionPerformed(evt);
            }
        });
        jPanel8.add(btnAgregar1);

        jLabel9.setText(" ");
        jPanel8.add(jLabel9);

        btnAgregar2.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar2.setForeground(new java.awt.Color(153, 153, 255));
        btnAgregar2.setText("Borrar articulo");
        btnAgregar2.setBorderColor(new java.awt.Color(153, 153, 255));
        btnAgregar2.setBorderColorSelected(new java.awt.Color(153, 51, 255));
        btnAgregar2.setColor(new java.awt.Color(153, 153, 255));
        btnAgregar2.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnAgregar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar2ActionPerformed(evt);
            }
        });
        jPanel8.add(btnAgregar2);

        jLabel10.setText(" ");
        jPanel8.add(jLabel10);

        btnAgregar3.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar3.setForeground(new java.awt.Color(255, 0, 0));
        btnAgregar3.setText("Borrar tabla");
        btnAgregar3.setBorderColorSelected(new java.awt.Color(102, 0, 0));
        btnAgregar3.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        btnAgregar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregar3ActionPerformed(evt);
            }
        });
        jPanel8.add(btnAgregar3);

        jPanel7.add(jPanel8, java.awt.BorderLayout.LINE_END);

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

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if(txtCodigo.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debes completar el campo Codigo","Advertencia",JOptionPane.WARNING_MESSAGE);
        }else if(txtDescripcion.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debes completar el campo Descripcion","Advertencia",JOptionPane.WARNING_MESSAGE);
        }else if(txtUM.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debes completar el campo Unidad de medida","Advertencia",JOptionPane.WARNING_MESSAGE);
        }else if(txtCantidad.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debes completar el campo Cantidad","Advertencia",JOptionPane.WARNING_MESSAGE);
        }else if(txtProveedor.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debes completar el campo Proveedor","Advertencia",JOptionPane.WARNING_MESSAGE);
        }else if(txtPrecio.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debes completar el campo Precio","Advertencia",JOptionPane.WARNING_MESSAGE);
        }else if(txtPO.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debes completar el campo Orden de compra","Advertencia",JOptionPane.WARNING_MESSAGE);
        }else{
            try{
                DefaultTableModel miModelo = (DefaultTableModel) Tabla1.getModel();
                Connection con;
                Conexion con1 = new Conexion();
                con = con1.getConnection();
                Statement st = con.createStatement();
                String sql = "select * from inventario where codigo like '" + txtCodigo.getText() + "'";
                ResultSet rs = st.executeQuery(sql);
                String cod = null;
                while(rs.next()){
                    cod = rs.getString("codigo");
                }
                if(cod == null){
                    String sql2 = "insert into inventario (codigo, descripcion, cantidad, UM, proveedor) values(?,?,?,?,?)";
                    PreparedStatement pst = con.prepareStatement(sql2);
                    
                    pst.setString(1, txtCodigo.getText());
                    pst.setString(2, txtDescripcion.getText());
                    pst.setString(3, "0");
                    pst.setString(4, txtUM.getText());
                    pst.setString(5, txtProveedor.getText());
                    
                    int n = pst.executeUpdate();
                    if(n <= 0){
                        JOptionPane.showMessageDialog(this, "No se guardo numerod de parte","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                
                double precio, cantidad, total;
                try{precio = Double.parseDouble(txtPrecio.getText());}catch(Exception e){precio = 0;}
                try{cantidad = Double.parseDouble(txtCantidad.getText());}catch(Exception e){cantidad = 0;}
                
                total = precio * cantidad;
                
                String datos[] = new String[10];
                datos[0] = txtCodigo.getText();
                datos[1] = txtDescripcion.getText();
                datos[2] = txtCantidad.getText();
                datos[3] = txtPrecio.getText();
                datos[4] = String.valueOf(total);
                datos[5] = txtProveedor.getText();
                datos[6] = txtPO.getText();
                datos[7] = txtUM.getText();
                datos[8] = txtRemision.getText();
                
                miModelo.addRow(datos);
                limpiarDatos();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, "Error: "+e,"error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnAgregar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar2ActionPerformed
        int fila = Tabla1.getSelectedRow();
        if(fila >= 0){
            int opc = JOptionPane.showConfirmDialog(this, "¿Estas seguro de eliminar este articulo?");
            if(opc == 0){
                DefaultTableModel miModelo = (DefaultTableModel) Tabla1.getModel();
                miModelo.removeRow(fila);
            }
        }
    }//GEN-LAST:event_btnAgregar2ActionPerformed

    private void btnAgregar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar3ActionPerformed
        int opc = JOptionPane.showConfirmDialog(this, "¿Estas seguro de eliminar toda la tabla?");
            if(opc == 0){
                limpiarTabla();
            }
    }//GEN-LAST:event_btnAgregar3ActionPerformed

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            String sql = "select * from inventario where codigo like '" + txtCodigo.getText() + "'";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                txtDescripcion.setText(rs.getString("descripcion"));
                txtProveedor.setText(rs.getString("proveedor"));
                txtUM.setText(rs.getString("UM"));
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error: "+e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void btnAgregar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregar1ActionPerformed
        if(Tabla1.getRowCount() <= 0){
            JOptionPane.showMessageDialog(this, "Debes agregar 1 o mas filas para poder guardar esta requisicion","Advertencia",JOptionPane.ERROR_MESSAGE);
        }else{
            try{
                Connection con;
                Conexion con1 = new Conexion();
                con = con1.getConnection();
                String sql = "insert into requisicion (empleado, progreso, completado, fecha) values(?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = null;
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date d = new Date();
                
                pst.setString(1, numEmpleado);
                pst.setString(2, "NUEVO");
                pst.setString(3, "NO");
                pst.setString(4, sdf.format(d));
                
                int n = pst.executeUpdate();
                
                if(n > 0){
                    rs = pst.getGeneratedKeys();
                    if(rs.next()){
                        long gen = rs.getLong(1);
                        String sql2 = "insert into requisiciones (NumRequisicion, codigo, descripcion, cantidad, requisitor, UM, proveedor, precio, OC, remision) values (?,?,?,?,?,?,?,?,?,?)";
                        PreparedStatement pst2 = con.prepareStatement(sql2);
                        
                        int n1 = 0;
                        for (int i = 0; i < Tabla1.getRowCount(); i++) {
                            pst2.setString(1, String.valueOf(gen));//Requisicion
                            pst2.setString(2, Tabla1.getValueAt(i, 0).toString());//Codigo
                            pst2.setString(3, Tabla1.getValueAt(i, 1).toString());//descripcion
                            pst2.setString(4, Tabla1.getValueAt(i, 2).toString());//cantidad
                            pst2.setString(5, txtRequisitor.getText());//requisitor
                            pst2.setString(6, Tabla1.getValueAt(i, 7).toString());//um
                            pst2.setString(7, Tabla1.getValueAt(i, 5).toString());//proveedor
                            pst2.setString(8, Tabla1.getValueAt(i, 3).toString());//precio
                            pst2.setString(9, Tabla1.getValueAt(i, 6).toString());//oc
                            pst2.setString(10, Tabla1.getValueAt(i, 8).toString());//remision
                            
                            n1 = pst2.executeUpdate();
                        }
                        
                        if(n1 > 0){
                            JFrame f = (JFrame) JOptionPane.getFrameForComponent(this);
                            verID ver = new verID(f,true);
                            ver.lblNumero.setText(String.valueOf(gen));
                            ver.setLocationRelativeTo(f);
                            limpiarTabla();
                            ver.setVisible(true);
                        }
                    }
                }
                
            }catch(SQLException e){
                JOptionPane.showMessageDialog(this, "Error: "+e,"error",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAgregar1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelX;
    private javax.swing.JTable Tabla1;
    private Componentes.Boton btnAgregar;
    private Componentes.Boton btnAgregar1;
    private Componentes.Boton btnAgregar2;
    private Componentes.Boton btnAgregar3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblPO;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JLabel lblRemision;
    private javax.swing.JLabel lblRequisitor;
    private javax.swing.JLabel lblX;
    private javax.swing.JLabel lblum;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtPO;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProveedor;
    private javax.swing.JTextField txtRemision;
    private javax.swing.JTextField txtRequisitor;
    private javax.swing.JTextField txtUM;
    // End of variables declaration//GEN-END:variables

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource() == txtCantidad){
            setBordeA(txtCantidad);
            lblCantidad.setForeground(new Color(52,52,52));
        }else if(e.getSource() == txtCodigo){
            setBordeA(txtCodigo);
            lblCodigo.setForeground(new Color(52,52,52));
        }else if(e.getSource() == txtDescripcion){
            setBordeA(txtDescripcion);
            lblDesc.setForeground(new Color(52,52,52));
        }else if(e.getSource() == txtPO){
            setBordeA(txtPO);
            lblPO.setForeground(new Color(52,52,52));
        }else if(e.getSource() == txtPrecio){
            setBordeA(txtPrecio);
            lblPrecio.setForeground(new Color(52,52,52));
        }else if(e.getSource() == txtProveedor){
            setBordeA(txtProveedor);
            lblProveedor.setForeground(new Color(52,52,52));
        }else if(e.getSource() == txtRequisitor){
            setBordeA(txtRequisitor);
            lblRequisitor.setForeground(new Color(52,52,52));
        }else if(e.getSource() == txtUM){
            setBordeA(txtUM);
            lblum.setForeground(new Color(52,52,52));
        }else if(e.getSource() == txtRemision){
            setBordeA(txtRemision);
            lblRemision.setForeground(new Color(52,52,52));
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource() == txtCantidad){
            setBordeG(txtCantidad);
            lblCantidad.setForeground(new Color(153,153,153));
        }else if(e.getSource() == txtCodigo){
            setBordeG(txtCodigo);
            lblCodigo.setForeground(new Color(153,153,153));
        }else if(e.getSource() == txtDescripcion){
            setBordeG(txtDescripcion);
            lblDesc.setForeground(new Color(153,153,153));
        }else if(e.getSource() == txtPO){
            setBordeG(txtPO);
            lblPO.setForeground(new Color(153,153,153));
        }else if(e.getSource() == txtPrecio){
            setBordeG(txtPrecio);
            lblPrecio.setForeground(new Color(153,153,153));
        }else if(e.getSource() == txtProveedor){
            setBordeG(txtProveedor);
            lblProveedor.setForeground(new Color(153,153,153));
        }else if(e.getSource() == txtRequisitor){
            setBordeG(txtRequisitor);
            lblRequisitor.setForeground(new Color(153,153,153));
        }else if(e.getSource() == txtUM){
            setBordeG(txtUM);
            lblum.setForeground(new Color(153,153,153));
        }else if(e.getSource() == txtRemision){
            setBordeG(txtRemision);
            lblRemision.setForeground(new Color(153,153,153));
        }
        revalidate();
        repaint();
    }
}
