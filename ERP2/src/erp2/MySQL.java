/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp2;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
/**
 *
 * @author Dani
 */


public class MySQL {
    
   private static Connection Conexion;
   
   public void MySQLConnection(String host,String puerto,String db_nombre, String usu, String pass ){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Conexion = DriverManager.getConnection("jdbc:mysql://"+host+":"+puerto+"/"+db_nombre,usu,pass);
            JOptionPane.showMessageDialog(null,"Conexion correcta");
        }
        catch(ClassNotFoundException ex){
            System.out.println("Error: "+ex);
        }
        catch(SQLException ex){
            System.out.println("Error: "+ex);
        }
    }
   
   public void closeConnection(){
       try{
           Conexion.close();
           Conexion=null;
           JOptionPane.showMessageDialog(null, "Desconexion correcta");
       }
       catch(SQLException ex){
           System.out.println("Error: "+ex);
       }
   }
   
   public ResultSet getValues(String table_name){
       ResultSet resultSet=null;
       try{
           String Query = "SELECT * FROM " + table_name;
           Statement st = Conexion.createStatement();
           resultSet = st.executeQuery(Query);
       }
       catch(SQLException ex){
           JOptionPane.showMessageDialog(null,"Error adquiriendo datos: " + ex);
       }
       return resultSet;
   }
   
   public boolean isConnected(){
       if(Conexion!=null){
           return true;
       }
       else{return false;}
   }
   
   
}
