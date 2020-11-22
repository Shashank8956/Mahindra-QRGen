package qr.code.generator;

import java.sql.Connection;
import java.sql.DriverManager;
import javafx.scene.control.Alert;

/**
 * This class contains one static method named Connect which returns a Connection object
 * after establishing a connection with the database.
 * 
 * @author Spongebob
 */
public class DatabaseConnector {
 /**
 * This is the only method in this class. It's a static method and its job to 
 * establish a connection with the database using the JDBC connector. It returns
 * the java.sql.Connection object if a connection has been established, else it 
 * returns null if some error occurs.
 * 
 * @return Connection
 */
    
    public static Connection Connect(){
        try {
            // db parameters
            String urlSqlite = "jdbc:sqlite:QRGenMaster.db";
            //String urlMySql = "jdbc:mysql://localhost/mysql?autoReconnect=true&useSSL=false";
            String username = "root";
            String password = "root";
            Class.forName("org.sqlite.JDBC");
            //Class.forName("com.mysql.jdbc.Driver");
            
            // create a connection to the database
            Connection conn = DriverManager.getConnection(urlSqlite/*, username, password*/);
            return conn;
            
        } catch (Exception e) {
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Error connecting to the database!");
            String msg = "Cannot establish connection to database!\n\n"+
                         "Possible causes: \n"+
                         "1. DBMS not installed\n"+
                         "2. Incorrect database name\n"+
                         "3. JDBC driver is unavailable\n"+
                         "4. Incorrect username or password (in case of MySql)\n"+
                         "5. Path of database mentioned in URL of the DatabaseConnection class is not valid";
            al.setContentText(msg);
            al.setHeaderText(null);
            al.show();
            
    /***For Console output********************************/    
            System.out.println("Cannot establish connection to database! ");
            System.out.println("Possible causes: ");
            System.out.println("1. Database not installed");
            System.out.println("2. Incorrect database name");
            System.out.println("3. JDBC is unavailable");
            System.out.println("4. Incorrect username or password (in case of MySql)");
            System.out.println("5. Path of database mentioned in URL of the DatabaseConnection class is not valid\n\n");
            e.printStackTrace();
            return null;
        }
    }
}
