package Conexiones;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conexion {
    
    Connection con = null;
    
    public Connection getConnection(){
        con = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://192.168.100.40:3306/connector?autoReconnect=true&useSSL=false","Jorge","123456789Aa.");

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos" + e,"Error",JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }
}

