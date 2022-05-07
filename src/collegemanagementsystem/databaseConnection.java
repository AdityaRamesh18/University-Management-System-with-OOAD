/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collegemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class databaseConnection {
    
    /*
    Connect() is a function that connects NetBeans to the MySQL database.
    Pre-condition: NetBeans isn't connected to the specified MySQL server where
    the database is located.
    Post-condition: NetBeans is connected to the specified MySQL server and
    the database can be accessed & modified.
    */
	
	private static Connection conn = null;
	
	static
	{
		try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/student_management_system",
                                               "root", "aditya");
        }
        catch (ClassNotFoundException | SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex);
        }
	}
	
    public static Connection Connect()
    {
        return conn;
    }
}
