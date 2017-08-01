/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author WAGEESHA-PC
 */
public class jdbc {
    
    String driver="com.mysql.jdbc.Driver";
    String url="jdbc:mysql://localhost:3306/inventory";
    
    Connection getconnection() throws Exception{
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url, "root", "");
        return con; 
    }
    
    void putdata(String sql){
        try {
            Statement state = getconnection().createStatement();
            state.executeUpdate(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    ResultSet getdata(String sql) throws Exception{
        Statement state = getconnection().createStatement();
        ResultSet r = state.executeQuery(sql);
        return r;
    }
    
}
