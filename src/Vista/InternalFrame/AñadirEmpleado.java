package Vista.InternalFrame;

import Componentes.ComboBoxItem;
import Componentes.ComboBoxRenderer;
import Conexiones.Conexion;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Blob;
import javax.sql.rowset.serial.SerialBlob;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

public class AñadirEmpleado extends javax.swing.JInternalFrame implements FocusListener{

    String numeroEmpleado;
    JComboBox<ComboBoxItem> comboBox;
    JComboBox<ComboBoxItem> cmbTel1;
    
    public void setBordeA(JComponent field, JLabel label){
        field.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 222, 0)));
        label.setForeground(new Color(51,51,51));
    }
    
    public void setBordeG(JComponent field, JLabel label){
        field.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        label.setForeground(new Color(204,204,204));
        revalidate();
        repaint();
    }
    
    public void limpiarTabla(){
        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Nombre", "Apellido", "No. Reloj", "Rol", "Requisiciones", "Almacen", "Entradas", "Salidas", "Registro", "Activo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }
    
    public final void setTable(){
        Tabla1.getTableHeader().setFont(new Font("Roboto", Font.BOLD, 14));
        Tabla1.getTableHeader().setOpaque(false);
        Tabla1.getTableHeader().setBackground(new Color(255, 222, 0));
        Tabla1.getTableHeader().setForeground(Color.gray);
        Tabla1.setRowHeight(25);
        Tabla1.setShowVerticalLines(false);
        Tabla1.setGridColor(new Color(240,240,240));
        
        jScrollPane1.getViewport().setBackground(new Color(255,255,255));
    }
    
    public void quitarSelect(){
        requisiciones.setSelected(false);
        almacen.setSelected(false);
        entradas.setSelected(false);
        salidas.setSelected(false);
        empleados.setSelected(false);
    }
    
    public void quitarSelect2(){
        Erequisiciones.setSelected(false);
        Ealmacen.setSelected(false);
        Eentradas.setSelected(false);
        Esalidas.setSelected(false);
        Eempleados.setSelected(false);
    }
    
    public void blank(){
        quitarSelect();
        txtNombre.setText("");
        txtApellido.setText("");
        txtReloj.setText("");
        txtContra.setText("");
        txtTelefono.setText("");
        cmbRol.setSelectedIndex(0);
    }
    
    public void blankE(){
        quitarSelect2();
        txtENombre.setText("");
        txtEApellido.setText("");
        txtEReloj.setText("");
        txtEContra.setText("");
        txtETelefono.setText("");
        cmbERol.setSelectedIndex(0);
    }
    
    public final void verEmpleados(String sql){
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            Object datos[] = new Object[10];
            DefaultTableModel miModelo = (DefaultTableModel) Tabla1.getModel();
            while(rs.next()){
                datos[0] = rs.getString("name");
                datos[1] = rs.getString("lastname");
                datos[2] = rs.getString("employeeNumber");
                datos[3] = rs.getString("role");
                datos[4] = rs.getBoolean("requisiciones");
                datos[5] = rs.getBoolean("almacen");
                datos[6] = rs.getBoolean("entradas");
                datos[7] = rs.getBoolean("salidas");
                datos[8] = rs.getBoolean("registro");
                datos[9] = rs.getBoolean("active");
                miModelo.addRow(datos);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error: "+e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public final void addCmb(){
        comboBox = new JComboBox<>();
        comboBox.addItem(new ComboBoxItem("+52",new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/mex.png"))));
        comboBox.addItem(new ComboBoxItem("+1",new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/usa.png"))));
        comboBox.setRenderer(new ComboBoxRenderer());
        
        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 7, 10);
        jPanel6.add(comboBox, gridBagConstraints);
    }
    
    public final void addCmbTel(){
        cmbTel1 = new JComboBox<>();
        cmbTel1.addItem(new ComboBoxItem("+52",new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/mex.png"))));
        cmbTel1.addItem(new ComboBoxItem("+1",new javax.swing.ImageIcon(getClass().getResource("/Recursos/Iconos/usa.png"))));
        cmbTel1.setRenderer(new ComboBoxRenderer());
        
        java.awt.GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.insets = new java.awt.Insets(7, 0, 7, 10);
        jPanel8.add(cmbTel1, gridBagConstraints);
    }
    
    public String getLada(){
        return switch (comboBox.getSelectedIndex()) {
            case 0 -> "+52";
            case 1 -> "+1";
            default -> null;
        };
    }
    
    public AñadirEmpleado() {
        initComponents();
        setTable();
        verEmpleados("select * from users where active like '1'");
        txtApellido.addFocusListener(this);
        txtContra.addFocusListener(this);
        txtEApellido.addFocusListener(this);
        txtEContra.addFocusListener(this);
        txtENombre.addFocusListener(this);
        txtEReloj.addFocusListener(this);
        txtNombre.addFocusListener(this);
        txtReloj.addFocusListener(this);
        txtTelefono.addFocusListener(this);
        txtETelefono.addFocusListener(this);
        addCmb();
        addCmbTel();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        PanelX = new javax.swing.JPanel();
        lblX = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblApellido = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        lblReloj = new javax.swing.JLabel();
        txtReloj = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        cmbRol = new javax.swing.JComboBox<>();
        lblAcceso = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        requisiciones = new javax.swing.JCheckBox();
        almacen = new javax.swing.JCheckBox();
        entradas = new javax.swing.JCheckBox();
        salidas = new javax.swing.JCheckBox();
        empleados = new javax.swing.JCheckBox();
        pnlGuardar = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        lblPass = new javax.swing.JLabel();
        txtContra = new javax.swing.JPasswordField();
        lblRol = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JFormattedTextField();
        jPanel8 = new javax.swing.JPanel();
        lblEReloj = new javax.swing.JLabel();
        txtEReloj = new javax.swing.JTextField();
        lblENombre = new javax.swing.JLabel();
        txtENombre = new javax.swing.JTextField();
        lblEApellido = new javax.swing.JLabel();
        txtEApellido = new javax.swing.JTextField();
        cmbERol = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        Erequisiciones = new javax.swing.JCheckBox();
        Ealmacen = new javax.swing.JCheckBox();
        Eentradas = new javax.swing.JCheckBox();
        Esalidas = new javax.swing.JCheckBox();
        Eempleados = new javax.swing.JCheckBox();
        pnlEditar = new javax.swing.JPanel();
        btnEditar = new javax.swing.JButton();
        lblEPass = new javax.swing.JLabel();
        txtEContra = new javax.swing.JPasswordField();
        lblETelefono = new javax.swing.JLabel();
        txtETelefono = new javax.swing.JFormattedTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        boton1 = new Componentes.Boton();
        boton4 = new Componentes.Boton();
        boton2 = new Componentes.Boton();
        boton3 = new Componentes.Boton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
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

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel6Layout = new java.awt.GridBagLayout();
        jPanel6Layout.columnWidths = new int[] {1, 1};
        jPanel6Layout.columnWeights = new double[] {1.0, 1.0};
        jPanel6Layout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
        jPanel6.setLayout(jPanel6Layout);

        lblNombre.setBackground(new java.awt.Color(204, 204, 204));
        lblNombre.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(204, 204, 204));
        lblNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombre.setText("Nombre:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(7, 100, 7, 100);
        jPanel6.add(lblNombre, gridBagConstraints);

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombre.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(7, 100, 7, 100);
        jPanel6.add(txtNombre, gridBagConstraints);

        lblApellido.setBackground(new java.awt.Color(204, 204, 204));
        lblApellido.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblApellido.setForeground(new java.awt.Color(204, 204, 204));
        lblApellido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblApellido.setText("Apellido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(7, 100, 7, 100);
        jPanel6.add(lblApellido, gridBagConstraints);

        txtApellido.setBackground(new java.awt.Color(255, 255, 255));
        txtApellido.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtApellido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtApellido.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(7, 100, 7, 100);
        jPanel6.add(txtApellido, gridBagConstraints);

        lblReloj.setBackground(new java.awt.Color(204, 204, 204));
        lblReloj.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblReloj.setForeground(new java.awt.Color(204, 204, 204));
        lblReloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblReloj.setText("Numero de reloj:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(7, 100, 7, 100);
        jPanel6.add(lblReloj, gridBagConstraints);

        txtReloj.setBackground(new java.awt.Color(255, 255, 255));
        txtReloj.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtReloj.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtReloj.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        txtReloj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRelojActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(7, 100, 7, 100);
        jPanel6.add(txtReloj, gridBagConstraints);

        lblTelefono.setBackground(new java.awt.Color(204, 204, 204));
        lblTelefono.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblTelefono.setForeground(new java.awt.Color(204, 204, 204));
        lblTelefono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTelefono.setText("Telefono:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(7, 100, 7, 100);
        jPanel6.add(lblTelefono, gridBagConstraints);

        cmbRol.setBackground(new java.awt.Color(255, 255, 255));
        cmbRol.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cmbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Almacen", "Requisitor", "Admin" }));
        cmbRol.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        cmbRol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRolActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(7, 100, 7, 100);
        jPanel6.add(cmbRol, gridBagConstraints);

        lblAcceso.setBackground(new java.awt.Color(204, 204, 204));
        lblAcceso.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblAcceso.setForeground(new java.awt.Color(204, 204, 204));
        lblAcceso.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAcceso.setText("Accesos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(7, 100, 7, 100);
        jPanel6.add(lblAcceso, gridBagConstraints);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 5));

        requisiciones.setBackground(new java.awt.Color(255, 255, 255));
        requisiciones.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        requisiciones.setText("Requisiciones");
        jPanel5.add(requisiciones);

        almacen.setBackground(new java.awt.Color(255, 255, 255));
        almacen.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        almacen.setText("Almacen");
        jPanel5.add(almacen);

        entradas.setBackground(new java.awt.Color(255, 255, 255));
        entradas.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        entradas.setText("Entradas");
        jPanel5.add(entradas);

        salidas.setBackground(new java.awt.Color(255, 255, 255));
        salidas.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        salidas.setText("Salidas");
        jPanel5.add(salidas);

        empleados.setBackground(new java.awt.Color(255, 255, 255));
        empleados.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        empleados.setText("Añadir empleados");
        jPanel5.add(empleados);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(7, 100, 7, 100);
        jPanel6.add(jPanel5, gridBagConstraints);

        pnlGuardar.setBackground(new java.awt.Color(255, 255, 255));

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.gridwidth = 2;
        jPanel6.add(pnlGuardar, gridBagConstraints);

        lblPass.setBackground(new java.awt.Color(204, 204, 204));
        lblPass.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblPass.setForeground(new java.awt.Color(204, 204, 204));
        lblPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPass.setText("Contraseña:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(7, 100, 7, 100);
        jPanel6.add(lblPass, gridBagConstraints);

        txtContra.setBackground(new java.awt.Color(255, 255, 255));
        txtContra.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtContra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtContra.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(7, 100, 7, 100);
        jPanel6.add(txtContra, gridBagConstraints);

        lblRol.setBackground(new java.awt.Color(204, 204, 204));
        lblRol.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblRol.setForeground(new java.awt.Color(204, 204, 204));
        lblRol.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRol.setText("Rol:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(7, 100, 7, 100);
        jPanel6.add(lblRol, gridBagConstraints);

        txtTelefono.setBackground(new java.awt.Color(255, 255, 255));
        txtTelefono.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        try {
            txtTelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)-###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefono.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(7, 10, 7, 100);
        jPanel6.add(txtTelefono, gridBagConstraints);

        jTabbedPane1.addTab("Añadir nuevo empleado", jPanel6);

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        java.awt.GridBagLayout jPanel8Layout = new java.awt.GridBagLayout();
        jPanel8Layout.columnWidths = new int[] {1, 1};
        jPanel8Layout.columnWeights = new double[] {1.0, 1.0};
        jPanel8Layout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
        jPanel8.setLayout(jPanel8Layout);

        lblEReloj.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblEReloj.setForeground(new java.awt.Color(204, 204, 204));
        lblEReloj.setText("Ingresa numero de reloj");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(0, 200, 0, 200);
        jPanel8.add(lblEReloj, gridBagConstraints);

        txtEReloj.setBackground(new java.awt.Color(255, 255, 255));
        txtEReloj.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtEReloj.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        txtEReloj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtERelojActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(0, 200, 0, 200);
        jPanel8.add(txtEReloj, gridBagConstraints);

        lblENombre.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblENombre.setForeground(new java.awt.Color(204, 204, 204));
        lblENombre.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.insets = new java.awt.Insets(5, 200, 5, 10);
        jPanel8.add(lblENombre, gridBagConstraints);

        txtENombre.setBackground(new java.awt.Color(255, 255, 255));
        txtENombre.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtENombre.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(0, 200, 0, 10);
        jPanel8.add(txtENombre, gridBagConstraints);

        lblEApellido.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblEApellido.setForeground(new java.awt.Color(204, 204, 204));
        lblEApellido.setText("Apellido");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 12;
        gridBagConstraints.insets = new java.awt.Insets(5, 10, 5, 200);
        jPanel8.add(lblEApellido, gridBagConstraints);

        txtEApellido.setBackground(new java.awt.Color(255, 255, 255));
        txtEApellido.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtEApellido.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 200);
        jPanel8.add(txtEApellido, gridBagConstraints);

        cmbERol.setBackground(new java.awt.Color(255, 255, 255));
        cmbERol.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        cmbERol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Almacen", "Requisitor", "Admin" }));
        cmbERol.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        cmbERol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbERolActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new java.awt.Insets(10, 200, 10, 200);
        jPanel8.add(cmbERol, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Accesos:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(7, 100, 7, 100);
        jPanel8.add(jLabel10, gridBagConstraints);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 5));

        Erequisiciones.setBackground(new java.awt.Color(255, 255, 255));
        Erequisiciones.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        Erequisiciones.setText("Requisiciones");
        jPanel9.add(Erequisiciones);

        Ealmacen.setBackground(new java.awt.Color(255, 255, 255));
        Ealmacen.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        Ealmacen.setText("Almacen");
        jPanel9.add(Ealmacen);

        Eentradas.setBackground(new java.awt.Color(255, 255, 255));
        Eentradas.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        Eentradas.setText("Entradas");
        jPanel9.add(Eentradas);

        Esalidas.setBackground(new java.awt.Color(255, 255, 255));
        Esalidas.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        Esalidas.setText("Salidas");
        jPanel9.add(Esalidas);

        Eempleados.setBackground(new java.awt.Color(255, 255, 255));
        Eempleados.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        Eempleados.setText("Añadir empleados");
        jPanel9.add(Eempleados);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(7, 100, 7, 100);
        jPanel8.add(jPanel9, gridBagConstraints);

        pnlEditar.setBackground(new java.awt.Color(255, 255, 255));

        btnEditar.setBackground(new java.awt.Color(255, 255, 255));
        btnEditar.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setBorder(null);
        btnEditar.setBorderPainted(false);
        btnEditar.setContentAreaFilled(false);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEditar.setFocusPainted(false);
        btnEditar.setPreferredSize(new java.awt.Dimension(100, 25));
        btnEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditarMouseExited(evt);
            }
        });
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        pnlEditar.add(btnEditar);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(14, 14, 14, 14);
        jPanel8.add(pnlEditar, gridBagConstraints);

        lblEPass.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblEPass.setForeground(new java.awt.Color(204, 204, 204));
        lblEPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEPass.setText("Contraseña:");
        lblEPass.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(7, 200, 7, 10);
        jPanel8.add(lblEPass, gridBagConstraints);

        txtEContra.setBackground(new java.awt.Color(255, 255, 255));
        txtEContra.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtEContra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEContra.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(7, 10, 7, 200);
        jPanel8.add(txtEContra, gridBagConstraints);

        lblETelefono.setBackground(new java.awt.Color(204, 204, 204));
        lblETelefono.setFont(new java.awt.Font("Roboto", 1, 12)); // NOI18N
        lblETelefono.setForeground(new java.awt.Color(204, 204, 204));
        lblETelefono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblETelefono.setText("Telefono:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(7, 100, 7, 100);
        jPanel8.add(lblETelefono, gridBagConstraints);

        txtETelefono.setBackground(new java.awt.Color(255, 255, 255));
        txtETelefono.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 204, 204)));
        try {
            txtETelefono.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(###)-###-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtETelefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtETelefono.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 200);
        jPanel8.add(txtETelefono, gridBagConstraints);

        jTabbedPane1.addTab("Editar empelados", jPanel8);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setLayout(new java.awt.BorderLayout());

        jLabel12.setFont(new java.awt.Font("Lexend", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 102, 204));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Todos mis empleados");
        jPanel7.add(jLabel12, java.awt.BorderLayout.PAGE_START);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new java.awt.BorderLayout());

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 5));

        boton1.setBackground(new java.awt.Color(255, 255, 255));
        boton1.setForeground(new java.awt.Color(51, 153, 255));
        boton1.setText("Actualizar");
        boton1.setBorderColor(new java.awt.Color(51, 153, 255));
        boton1.setBorderColorSelected(new java.awt.Color(0, 102, 204));
        boton1.setColor(new java.awt.Color(51, 153, 255));
        boton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton1ActionPerformed(evt);
            }
        });
        jPanel11.add(boton1);

        boton4.setBackground(new java.awt.Color(255, 255, 255));
        boton4.setForeground(new java.awt.Color(102, 102, 255));
        boton4.setText("Ver todo");
        boton4.setBorderColor(new java.awt.Color(102, 102, 255));
        boton4.setBorderColorSelected(new java.awt.Color(51, 0, 204));
        boton4.setColor(new java.awt.Color(102, 102, 255));
        boton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton4ActionPerformed(evt);
            }
        });
        jPanel11.add(boton4);

        boton2.setBackground(new java.awt.Color(255, 255, 255));
        boton2.setForeground(new java.awt.Color(0, 102, 204));
        boton2.setText("Buscar");
        boton2.setBorderColor(new java.awt.Color(0, 102, 204));
        boton2.setBorderColorSelected(new java.awt.Color(0, 51, 102));
        boton2.setColor(new java.awt.Color(0, 102, 204));
        boton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton2ActionPerformed(evt);
            }
        });
        jPanel11.add(boton2);

        boton3.setBackground(new java.awt.Color(255, 255, 255));
        boton3.setForeground(new java.awt.Color(255, 0, 0));
        boton3.setText("Baja");
        boton3.setBorderColorSelected(new java.awt.Color(204, 0, 0));
        boton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton3ActionPerformed(evt);
            }
        });
        jPanel11.add(boton3);

        jPanel10.add(jPanel11, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        Tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido", "No. Reloj", "Rol", "Requisiciones", "Almacen", "Entradas", "Salidas", "Registro", "Activo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tabla1);

        jPanel10.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel7.add(jPanel10, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Ver empleados", jPanel7);

        jPanel1.add(jTabbedPane1, java.awt.BorderLayout.CENTER);

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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(txtNombre.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debes llenar el campo Nombre");
        }else if(txtApellido.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debes llenar el campo Apellido");
        }else if(txtReloj.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debes llenar el campo Numero de reloj");
        }else if(txtContra.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Debes llenar el campo Contraseña");
        }
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            String sql = "select * from users where employeeNumber like '" + txtReloj.getText() + "'";
            ResultSet rs = st.executeQuery(sql);
            String num = null;
            while(rs.next()){
                num = rs.getString("employeeNumber");
            }

            if(num == null){
                String sql2 = "insert into users (name, lastname, employeeNumber, role, requisiciones, almacen, entradas, salidas, registro, password) values(?,?,?,?,?,?,?,?,?,AES_ENCRYPT(?,'mi_llave'))";
                if(!txtTelefono.getText().equals("(   )-   -    ")){
                    sql2 = "insert into users (name, lastname, employeeNumber, role, requisiciones, almacen, entradas, salidas, registro, password, telefono) values(?,?,?,?,?,?,?,?,?,AES_ENCRYPT(?,'mi_llave'),?)";
                }
                PreparedStatement pst = con.prepareStatement(sql2);
                
                byte dato[] = txtContra.getText().getBytes();
                Blob blob= new SerialBlob(dato);
                
                String telefono = getLada() + (txtTelefono.getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
                
                pst.setString(1, txtNombre.getText());
                pst.setString(2, txtApellido.getText());
                pst.setString(3, txtReloj.getText());
                pst.setString(4, cmbRol.getSelectedItem().toString());
                pst.setBoolean(5, requisiciones.isSelected());
                pst.setBoolean(6, almacen.isSelected());
                pst.setBoolean(7, entradas.isSelected());
                pst.setBoolean(8, salidas.isSelected());
                pst.setBoolean(9, empleados.isSelected());
                pst.setBlob(10, blob);
                if(!txtTelefono.getText().equals("(   )-   -    ")){
                    pst.setString(11, telefono);
                }

                int n = pst.executeUpdate();

                if(n > 0){
                    JOptionPane.showMessageDialog(this, "Usuario creado correctamente");
                    blank();
                }

            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error al crear usuario: " + e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseExited
        pnlGuardar.setBackground(Color.white);
        btnGuardar.setForeground(Color.black);
    }//GEN-LAST:event_btnGuardarMouseExited

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        pnlGuardar.setBackground(new Color(255, 222, 0));
        btnGuardar.setForeground(Color.gray);
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void cmbRolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRolActionPerformed
        quitarSelect();
        if(cmbRol.getSelectedItem().equals("Almacen")){
            requisiciones.setSelected(true);
            almacen.setSelected(true);
            entradas.setSelected(true);
            salidas.setSelected(true);
        }else if(cmbRol.getSelectedItem().equals("Requisitor")){
            requisiciones.setSelected(true);
        }else if(cmbRol.getSelectedItem().equals("Admin")){
            requisiciones.setSelected(true);
            almacen.setSelected(true);
            entradas.setSelected(true);
            salidas.setSelected(true);
            empleados.setSelected(true);
        }
    }//GEN-LAST:event_cmbRolActionPerformed

    private void cmbERolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbERolActionPerformed
        quitarSelect2();
        if(cmbERol.getSelectedItem().equals("Almacen")){
            Erequisiciones.setSelected(true);
            Ealmacen.setSelected(true);
            Eentradas.setSelected(true);
            Esalidas.setSelected(true);
        }else if(cmbERol.getSelectedItem().equals("Requisitor")){
            Erequisiciones.setSelected(true);
        }else if(cmbRol.getSelectedItem().equals("Admin")){
            Erequisiciones.setSelected(true);
            Ealmacen.setSelected(true);
            Eentradas.setSelected(true);
            Esalidas.setSelected(true);
            Eempleados.setSelected(true);
        }
    }//GEN-LAST:event_cmbERolActionPerformed

    private void btnEditarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseEntered
        pnlEditar.setBackground(new Color(255, 222, 0));
        btnEditar.setForeground(Color.gray);
    }//GEN-LAST:event_btnEditarMouseEntered

    private void btnEditarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseExited
        pnlEditar.setBackground(Color.white);
        btnEditar.setForeground(Color.black);
    }//GEN-LAST:event_btnEditarMouseExited

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        try{
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            String sql = "update users set name = ?, lastname = ?, role = ?, requisiciones = ?, almacen = ?, entradas = ?, salidas = ?, registro = ?, password = AES_ENCRYPT(?,'mi_llave')"
                    + " where employeeNumber = ?";
            if(!txtETelefono.getText().equals("(   )-   -    ")){
                sql = "update users set name = ?, lastname = ?, role = ?, requisiciones = ?, almacen = ?, entradas = ?, salidas = ?, registro = ?, password = AES_ENCRYPT(?,'mi_llave')"
                    + ", telefono = ? where employeeNumber = ?";
            }
            PreparedStatement pst = con.prepareStatement(sql);
            
            byte dato[] = txtEContra.getText().getBytes();
            Blob blob= new SerialBlob(dato);
            
            String telefono = getLada() + (txtTelefono.getText().replace("(", "").replace(")", "").replace("-", "").replace(" ", ""));
            
            pst.setString(1, txtENombre.getText());
            pst.setString(2, txtEApellido.getText());
            pst.setString(3, cmbERol.getSelectedItem().toString());
            pst.setBoolean(4, Erequisiciones.isSelected());
            pst.setBoolean(5, Ealmacen.isSelected());
            pst.setBoolean(6, Eentradas.isSelected());
            pst.setBoolean(7, Esalidas.isSelected());
            pst.setBoolean(8, Eempleados.isSelected());
            pst.setBlob(9, blob);
            if(txtETelefono.getText().equals("(   )-   -    ")){
                pst.setString(10, numeroEmpleado);
            }else{
                pst.setString(10, telefono);
                pst.setString(11, numeroEmpleado);
            }
            int n = pst.executeUpdate();
            
            if(n > 0){
                JOptionPane.showMessageDialog(this, "Datos guardados correctamente");
                blankE();
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error: "+e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtERelojActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtERelojActionPerformed
        try{
            quitarSelect2();
            Connection con;
            Conexion con1 = new Conexion();
            con = con1.getConnection();
            Statement st = con.createStatement();
            String sql = "select * from users where employeeNumber like '" + txtEReloj.getText() + "'";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                txtENombre.setText(rs.getString("name"));
                String tel = rs.getString("telefono");
                if(tel != null){
                    String lada = tel.substring(0,3);
                    if(lada.equals("+52")){
                        cmbTel1.setSelectedIndex(0);
                        txtETelefono.setText(tel.substring(3, tel.length()));
                    }else{
                        cmbTel1.setSelectedIndex(1);
                        txtETelefono.setText(tel.substring(2, tel.length()));
                    }
                }
                txtEApellido.setText(rs.getString("lastname"));
                cmbERol.setSelectedItem(rs.getString("role"));
                if(rs.getBoolean("requisiciones")){
                    Erequisiciones.setSelected(true);
                }
                if(rs.getBoolean("almacen")){
                    Ealmacen.setSelected(true);
                }
                if(rs.getBoolean("entradas")){
                    Eentradas.setSelected(true);
                }
                if(rs.getBoolean("salidas")){
                    Esalidas.setSelected(true);
                }
                if(rs.getBoolean("registro")){
                    Eempleados.setSelected(true);
                }
                numeroEmpleado = txtEReloj.getText();
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(this, "Error: "+e,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_txtERelojActionPerformed

    private void boton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton1ActionPerformed
        limpiarTabla();
        verEmpleados("select * from users where active like '1'");
    }//GEN-LAST:event_boton1ActionPerformed

    private void boton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton4ActionPerformed
        limpiarTabla();
        verEmpleados("select * from users");
    }//GEN-LAST:event_boton4ActionPerformed

    private void boton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton2ActionPerformed
        String opc = JOptionPane.showInputDialog("Ingresa numero de empleado");
        if(opc != null){
            if(!opc.equals("")){
                limpiarTabla();
                verEmpleados("select * from users where employeeNumber like '" + txtEReloj.getText() + "'");
            }
        }
    }//GEN-LAST:event_boton2ActionPerformed

    private void boton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton3ActionPerformed
        int fila = Tabla1.getSelectedRow();
        if(fila >= 0){
            int opc = JOptionPane.showConfirmDialog(this, "Estas seguro en dar de baja a " + Tabla1.getValueAt(fila, 0) + " " + Tabla1.getValueAt(fila, 1));
            if(opc == 0){
                try{
                    Connection con;
                    Conexion con1 = new Conexion();
                    con = con1.getConnection();
                    String sql = "update users set active = ? where employeeNumber = ?";
                    PreparedStatement pst = con.prepareStatement(sql);
                    
                    pst.setBoolean(1, false);
                    pst.setString(2, Tabla1.getValueAt(fila, 2).toString());
                    
                    int n = pst.executeUpdate();
                    
                    if(n > 0){
                        JOptionPane.showMessageDialog(this, "Empleado dado de baja exitosamente");
                        limpiarTabla();
                        verEmpleados("select * from users where active like '1'");
                    }
                    
                }catch(SQLException e){
                    JOptionPane.showMessageDialog(this, "Error: "+e,"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_boton3ActionPerformed

    private void txtRelojActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRelojActionPerformed
        
    }//GEN-LAST:event_txtRelojActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Ealmacen;
    private javax.swing.JCheckBox Eempleados;
    private javax.swing.JCheckBox Eentradas;
    private javax.swing.JCheckBox Erequisiciones;
    private javax.swing.JCheckBox Esalidas;
    private javax.swing.JPanel PanelX;
    private javax.swing.JTable Tabla1;
    private javax.swing.JCheckBox almacen;
    private Componentes.Boton boton1;
    private Componentes.Boton boton2;
    private Componentes.Boton boton3;
    private Componentes.Boton boton4;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbERol;
    private javax.swing.JComboBox<String> cmbRol;
    private javax.swing.JCheckBox empleados;
    private javax.swing.JCheckBox entradas;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblAcceso;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblEApellido;
    private javax.swing.JLabel lblENombre;
    private javax.swing.JLabel lblEPass;
    private javax.swing.JLabel lblEReloj;
    private javax.swing.JLabel lblETelefono;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPass;
    private javax.swing.JLabel lblReloj;
    private javax.swing.JLabel lblRol;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblX;
    private javax.swing.JPanel pnlEditar;
    private javax.swing.JPanel pnlGuardar;
    private javax.swing.JCheckBox requisiciones;
    private javax.swing.JCheckBox salidas;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JPasswordField txtContra;
    private javax.swing.JTextField txtEApellido;
    private javax.swing.JPasswordField txtEContra;
    private javax.swing.JTextField txtENombre;
    private javax.swing.JTextField txtEReloj;
    private javax.swing.JFormattedTextField txtETelefono;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtReloj;
    private javax.swing.JFormattedTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    @Override
    public void focusGained(FocusEvent e) {
        if(e.getSource() == txtApellido){
            setBordeA(txtApellido,lblApellido);
        }else if(e.getSource() == txtContra){
            setBordeA(txtContra,lblPass);
        }else if(e.getSource() == txtEApellido){
            setBordeA(txtEApellido,lblEApellido);
        }else if(e.getSource() == txtENombre){
            setBordeA(txtENombre,lblENombre);
        }else if(e.getSource() == txtEReloj){
            setBordeA(txtEReloj, lblEReloj);
        }else if(e.getSource() == txtNombre){
            setBordeA(txtNombre, lblNombre);
        }else if(e.getSource() == txtReloj){
            setBordeA(txtReloj,lblReloj);
        }else if(e.getSource() == txtEContra){
            setBordeA(txtEContra,lblEPass);
        }else if(e.getSource() == txtTelefono){
            setBordeA(txtTelefono,lblTelefono);
        }else if(e.getSource() == txtETelefono){
            setBordeA(txtETelefono,lblETelefono);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if(e.getSource() == txtApellido){
            setBordeG(txtApellido,lblApellido);
        }else if(e.getSource() == txtContra){
            setBordeG(txtContra,lblPass);
        }else if(e.getSource() == txtEApellido){
            setBordeG(txtEApellido,lblEApellido);
        }else if(e.getSource() == txtENombre){
            setBordeG(txtENombre, lblENombre);
        }else if(e.getSource() == txtEReloj){
            setBordeG(txtEReloj, lblEReloj);
        }else if(e.getSource() == txtNombre){
            setBordeG(txtNombre, lblNombre);
        }else if(e.getSource() == txtReloj){
            setBordeG(txtReloj, lblReloj);
        }else if(e.getSource() == txtEContra){
            setBordeG(txtEContra, lblEPass);
        }else if(e.getSource() == txtTelefono){
            setBordeG(txtTelefono, lblTelefono);
        }else if(e.getSource() == txtETelefono){
            setBordeG(txtETelefono, lblETelefono);
        }
    }
}
