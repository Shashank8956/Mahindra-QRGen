package qr.code.generator;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 * This class contains all the basic SQL operation methods that are required for
 * this system. It contains methods for creating table, inserting data, deleting data,
 * filtering, selecting, etc.
 * <p>
 * Its constructor establishes a connection to the database and creates the required tables.
 * Other methods are invoked as and when needed.
 * 
 * @author Spongebob
 */
public class DatabaseHandler {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement ps =null;
    ResultSet res = null;
    ArrayList<ModelHolder> mod = new ArrayList<ModelHolder>();
    
/**
 * Constructor of DataBase class. Tries to establish connection with the database
 * by calling the static connect() method of the DatabaseConnection class and storing
 * the returned java.sql.Connection object in this class's java.sql.Connection reference.
 * 
 * <p>
 * 
 * It creates four tables (personal, academic, profession and contact) by calling the
 * createTables() method of this class. If the tables are already created, it does not
 * override them.
 */    
    DatabaseHandler(){
        conn = DatabaseConnector.Connect();
        if(conn != null){
            System.out.println("Database connection established!");
            createTables();
        }else{
            System.out.println("Connection could not be established!");
        }
    }
    
/**
 * createTables() method creates four tables namely personal, academic, profession
 * and contacts. Personal table has a primary key column named _id, all other columns
 * use it as their foreign key.
 * <p>
 * The method contains four 'CREATE TABLE' queries in four different strings and
 * each string query is executed in its own try-catch block so as to isolate one's
 * error from another.
 * INSERT INTO tractor_series (tractor_series_no, variant, model, material, export_domestic, chassis_color) VALUES ('AAABAE', 'BP', 255, 'TR255PBPLCO3A', 'Domestic', 'Red');
 */    
    public void createTables(){
        String tractorSeriesTable = "CREATE TABLE IF NOT EXISTS tractor_series("+
                               "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                               "tractor_series_no VARCHAR(10) NOT NULL, "+
                               "variant  VARCHAR(10) NOT NULL, "+
                               "model INT NOT NULL, "+
                               "material VARCHAR(30) NOT NULL, "+
                               "export_domestic VARCHAR(10) NOT NULL, "+
                               "chassis_color VARCHAR(10) NOT NULL); ";
        
        
        String modelSettingsTable = "CREATE TABLE IF NOT EXISTS model_settings("+
                               "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                               "tractor_Series_No VARCHAR(10) NOT NULL, "+
                               "variant  VARCHAR(10) NOT NULL, "+
                               "model INT NOT NULL, "+
                               "material VARCHAR(30) NOT NULL, "+
                               "export_domestic VARCHAR(10) NOT NULL, "+
                               "chassis_color VARCHAR(10) NOT NULL); ";
        
        String historyTable = "CREATE TABLE IF NOT EXISTS history("+
                              "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                              "tractor_serial_no VARCHAR(10) NOT NULL, "+
                              "engine_serial_no VARCHAR(20), "+
                              "transmission_serial_no VARCHAR(320) NOT NULL, "+
                              "fip_serial_no VARCHAR(150), "+
                              "hydraulic_serial_no VARCHAR(150), "+
                              "pump_serial_no VARCHAR(150), "+
                              "chassis_colour VARCHAR(320) NOT NULL, "+
                              "export_domestic VARCHAR(150), "+
                              "model VARCHAR(150), "+
                              "tyre VARCHAR(150), "+
                              "material VARCHAR(30),"+
                              "variant VARCHAR(30),"+
                              "DateTime TEXT);";
        
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(tractorSeriesTable);
            System.out.println("Tractor_series table created (or already exits)!");
        }catch(SQLException e){
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Error creating the tractor_series table");
            al.setContentText("Error executing create tractor_series table in class DataBaseHandler.java!");
            al.setHeaderText(null);
            al.show();
            e.printStackTrace();
        }
        
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(historyTable);
            System.out.println("History table created (or already exits)!");
        }catch(SQLException e){
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Error creating the history table");
            al.setContentText("Error executing create history table in class DatabaseHandler.java!");
            al.setHeaderText(null);
            al.show();
            e.printStackTrace();
        }
        
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(modelSettingsTable);
            System.out.println("Settings table created (or already exits)!");
        }catch(SQLException e){
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setTitle("Error creating the Settings table");
            al.setContentText("Error executing create Settings table in class DataBase.java!");
            al.setHeaderText(null);
            al.show();
            e.printStackTrace();
        }
    }
  
    public void insertHistory(ModelHolder modelObj){
        try {
            
            String modelInsert =   "INSERT INTO history"+
                    "(tractor_serial_no, engine_serial_no, transmission_serial_no, fip_serial_no, "
                    + "hydraulic_serial_no, pump_serial_no, chassis_colour, export_domestic, model, tyre, material, variant, DateTime)"+
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?, datetime('now','localtime'));";
            
            System.out.println("************************\nInside Insert history settings\nSize of model: " + modelObj.getTractor_Series_No());
        
            ps = conn.prepareStatement(modelInsert);
            ps.setString(1, modelObj.getTractor_Series_No());
            ps.setString(2, modelObj.getEngine_series_no());
            ps.setString(3, modelObj.getTransmission_series_no());
            ps.setString(4, modelObj.getFip_series_no());
            ps.setString(5, modelObj.getHydraulic_series_no());
            ps.setString(6, modelObj.getPump_series_no());
            ps.setString(7, modelObj.getChassis_color());
            ps.setString(8, modelObj.getExport_domestic());
            ps.setInt(9, modelObj.getModel());
            ps.setString(10, modelObj.getTyre());
            ps.setString(11, modelObj.getMaterial());
            ps.setString(12, modelObj.getVariant());
            
            ps.execute();
            ps = null;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void insertModel(ModelHolder modelObj){
        try {
            
            String modelInsert =   "INSERT INTO model_settings"+
                    "(tractor_Series_No, variant, model, material, export_domestic, chassis_color)"+
                    "VALUES(?,?,?,?,?,?);";
            
            ps = conn.prepareStatement(modelInsert);
            ps.setString(1, modelObj.getTractor_Series_No());
            ps.setString(2, modelObj.getVariant());
            ps.setInt(3, modelObj.getModel());
            ps.setString(4, modelObj.getMaterial());
            ps.setString(5, modelObj.getExport_domestic());
            ps.setString(6, modelObj.getChassis_color());
            
            ps.execute();
            ps = null;
            System.out.println("************************\nInside Insert model settings\nSize of model: " + modelObj.getTractor_Series_No());
        
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public ModelHolder getModelData(String tractorSeriesNo){
        ModelHolder modelObj = new ModelHolder();
        try {
            
            String querySelect = "SELECT * FROM model_settings WHERE tractor_series_no = '" + tractorSeriesNo + "'; ";
            System.out.println(querySelect);
            ps = conn.prepareStatement(querySelect);
            res = ps.executeQuery();
            
            while(res.next()){
                modelObj.setTractor_Series_No(res.getString("tractor_series_no"));
                modelObj.setVariant(res.getString("variant"));
                modelObj.setModel(res.getInt("model"));
                modelObj.setMaterial(res.getString("material")); 
                modelObj.setExport_domestic(res.getString("export_domestic")); 
                modelObj.setChassis_color(res.getString("chassis_color"));
                System.out.println("Company Name: = " + modelObj.getTractor_Series_No());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelObj;
    }
    
    public ArrayList<ModelHolder> getModelSettingsData(){
        
        try {
            
            String querySelect = "SELECT * FROM model_settings; ";
            System.out.println(querySelect);
            ps = conn.prepareStatement(querySelect);
            res = ps.executeQuery();
            
            while(res.next()){
                ModelHolder modelObj = new ModelHolder();
                modelObj.setTractor_Series_No(res.getString("tractor_series_no"));
                modelObj.setVariant(res.getString("variant"));
                modelObj.setModel(res.getInt("model"));
                modelObj.setMaterial(res.getString("material")); 
                modelObj.setExport_domestic(res.getString("export_domestic")); 
                modelObj.setChassis_color(res.getString("chassis_color"));
                
                mod.add(modelObj);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("Tractor No: = " + mod.get(0).getTractor_Series_No());
        return mod;
    }
    
    public ArrayList<ModelHolder> getHistoryData(){
        
        try {
            
            String querySelect = "SELECT * FROM history ORDER BY _id DESC; ";
            System.out.println(querySelect);
            ps = conn.prepareStatement(querySelect);
            res = ps.executeQuery();
            
            String modelInsert =   "INSERT INTO history"+
                    "(tractor_serial_no, engine_serial_no, transmission_serial_no, fip_serial_no, "
                    + "hydraulic_serial_no, pump_serial_no, chassis_colour, export_domestic, model, tyre, material, variant, DateTime)"+
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
            
            while(res.next()){
                ModelHolder modelObj = new ModelHolder();
                modelObj.setTractor_Series_No(res.getString("tractor_serial_no"));
                modelObj.setEngine_series_no(res.getString("engine_serial_no"));
                modelObj.setTransmission_series_no("transmission_serial_no");
                modelObj.setFip_series_no(res.getString("fip_serial_no")); 
                modelObj.setHydraulic_series_no(res.getString("hydraulic_serial_no")); 
                modelObj.setPump_series_no(res.getString("pump_serial_no"));
                modelObj.setChassis_color(res.getString("chassis_colour"));
                modelObj.setExport_domestic(res.getString("export_domestic"));
                modelObj.setModel(res.getInt("model"));
                modelObj.setTyre(res.getString("tyre")); 
                modelObj.setMaterial(res.getString("material")); 
                modelObj.setVariant(res.getString("variant"));
                modelObj.setDateTime(res.getString("DateTime"));
                
                mod.add(modelObj);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("Tractor No: = " + mod.get(0).getTractor_Series_No());
        return mod;
    }
    
    public void clearAll(){
        try {
            String query1 = "DROP TABLE tractor_series;";
            String query2 = "DROP TABLE history;";
            String query3 = "DROP TABLE model_settings;";
            
            stmt = conn.createStatement();
            stmt.executeUpdate(query3);
            stmt.executeUpdate(query2);
            stmt.executeUpdate(query1);
            
            createTables();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
