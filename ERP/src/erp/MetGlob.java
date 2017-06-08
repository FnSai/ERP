/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erp;

import static erp.ERPMain.db;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

/**
 *
 * @author Dani
 * Esta clase contiene metodos globales que se utilizan en los distintos jPanel del proyecto
 */
public class MetGlob {
    
    
    public ResultSet consultaTodo(String tabla){
        ResultSet rs;
        rs = db.getValues(tabla);
        return rs;
    }
   
    public ResultSet buscarConsulta(String tabla, String Query){
        ResultSet rs;
        rs = db.searchValues(tabla,Query);
        return rs;
    }
   
    public DefaultTableModel resetTableModel(DefaultTableModel modelo, JTable jTable){
        modelo = new JTModelo();
        jTable.setModel(modelo);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable.getTableHeader().setReorderingAllowed(false);
        return modelo;
    }
    
    public DefaultTableModel clearRows(DefaultTableModel modelo){
        int rows = modelo.getRowCount();
        for(int i= rows-1;i>=0;i--){
            modelo.removeRow(i);
        }
        return modelo;
    }
    
    public void setTableHeader(ResultSet rs, DefaultTableModel modelo,JTable jTable){
        try{
            ResultSetMetaData cabecera = rs.getMetaData();
            modelo.setColumnCount(cabecera.getColumnCount());
            int numCol = modelo.getColumnCount();

            String[] labels = new String[numCol];

                for(int i=0;i<numCol;i++){
                    labels[i] = cabecera.getColumnLabel(i+1);
                }
                modelo.setColumnIdentifiers(labels);
        }
        catch(Exception sqle){
            System.out.println("Error sql1: "+ sqle);
        }
    }
    
    public void setTableData(ResultSet rs, JTable jTable,DefaultTableModel modelo){
        if(modelo.getRowCount()!=0){
            modelo=clearRows(modelo);
        }
        try{
            while(rs.next()){
                int numCol = rs.getMetaData().getColumnCount();
                Object [] fila = new Object[numCol];

                for(int i=0; i<numCol; i++){
                fila[i] = rs.getObject(i+1);
                }
                modelo.addRow(fila);
            }
        }
        catch(Exception sqle){
            System.out.println("Error sql2: "+ sqle);
        }
    }
    
    public void setComboBox(ResultSet rs,javax.swing.JComboBox<String> jComboBox1,DefaultComboBoxModel CBModel){
        try{
            ResultSetMetaData cabecera = rs.getMetaData();
            int numCol = cabecera.getColumnCount();

            String[] labels = new String[numCol];

                for(int i=0;i<numCol;i++){
                    labels[i] = cabecera.getColumnLabel(i+1);
                }
                CBModel=new DefaultComboBoxModel(labels);
                jComboBox1.setModel(CBModel);
        }
        catch(Exception sqle){
            System.out.println("Error sql3: "+ sqle);
        }
    }
    
   /* public void consultar(ResultSet rs, javax.swing.JTable jTable){
        modelo = new JTModelo();
        jTable.setModel(modelo);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable.getTableHeader().setReorderingAllowed(false);
        
        try{
            ResultSetMetaData cabecera = rs.getMetaData();
            modelo.setColumnCount(cabecera.getColumnCount());
            int numCol = modelo.getColumnCount();
            String[] labels = new String[numCol];
            
            for(int i=0;i<numCol;i++){
                labels[i] = cabecera.getColumnLabel(i+1);
            }
            
            modelo.setColumnIdentifiers(labels);
            CBModel=new DefaultComboBoxModel(labels);
            jComboBox1.setModel(CBModel);
            
            while(rs.next()){
                Object [] fila = new Object[numCol];
                
                for(int i=0; i<numCol; i++){
                    fila[i] = rs.getObject(i+1);
                }
                modelo.addRow(fila);
            }
            rs.close();
        }
        catch(Exception sqle){
            System.out.println("Error sql: "+ sqle);
        }
    }*/
    public class JTModelo extends DefaultTableModel{
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    }
}
