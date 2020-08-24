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
    ArrayList<ObservableDataModel> mod = new ArrayList<ObservableDataModel>();
    
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
                              "material VARCHAR(30));";
        
//        String settingsTable = "CREATE TABLE IF NOT EXISTS settings("+
//                              "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
//                              "UG INT NOT NULL, "+
//                              "Umarks VARCHAR(10), "+
//                              "Ucollege VARCHAR(100), "+
//                              "Ubatch CHAR(5), "+
//                              "Ubranch VARCHAR(30), "+
//                              "Ucourse VARCHAR(30), "+
//                
//                              "Pmarks VARCHAR(10), "+
//                              "Pcollege VARCHAR(100), "+
//                              "Pbatch VARCHAR(5) , "+
//                              "Pbranch VARCHAR(30), "+
//                              "Pcourse VARCHAR(30));";
        
        
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
//<editor-fold defaultstate="collapsed" desc="Commented section">

/**
 * This method returns a list of all the entries from the three tables (personal,
 * profession, academic) so as to assist populating the main JFXListView when the
 * application starts.
 * 
 * @return ArrayList<AlumniModel>
 */ /*   
    public ArrayList<AlumniModel> getRowsData(){
        try {
            String querySelect = "SELECT * FROM personal "+
                                  "JOIN academic ON personal._id = academic._id "+
                                  "JOIN profession ON personal._id = profession._id "+
                                  "JOIN contact ON personal._id = contact._id;";
            
            ps = conn.prepareStatement(querySelect);
            res = ps.executeQuery();
            ResultSet resJob = null;
            //res.beforeFirst();
            while(res.next()){
                querySelect = "SELECT * FROM profession "+
                              "WHERE _id = "+res.getInt("_id")+";";
                ps = conn.prepareStatement(querySelect);
                resJob = ps.executeQuery();
                
                while(resJob.next()){
                    jobmod.add(new JobModel(res.getInt("_Id"), resJob.getString("CompanyName"), resJob.getString("Position"), resJob.getString("Location"), 
                                            resJob.getString("DOJ"), resJob.getString("DOL"), Double.valueOf(resJob.getString("Package"))));
                    //System.out.println("Company Name: = " + jobmod.get(0).getCompanyName());
                }
                
                mod.add(new AlumniModel(res.getInt("_id"), res.getString("name"), res.getString("name"), res.getString("pic"),res.getString("Nationality"),
                    res.getString("address"), res.getString("city"),res.getString("state"), res.getString("gender"), res.getString("dob"),
                    res.getString("pincode"),res.getString("Ucollege"),res.getString("Ucourse"),res.getString("Ubranch"),res.getString("Umarks"),
                    res.getInt("Ubatch"),res.getString("Pcollege"),res.getString("Pcourse"),res.getString("Pbranch"),
                    res.getString("Pmarks"),res.getInt("Pbatch"),res.getInt("Placed"),res.getString("Remark"),res.getString("Mobile"),
                    res.getString("Landline"),res.getString("Email"),res.getString("Linkedin"),res.getString("Facebook"),res.getString("Instagram"), jobmod, res.getInt("UG")));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mod;
    }
    */
    
/**
 * This method is used to insert a new entry in the four tables (personal, contact, academic profession)
 * upon clicking the Main SAVE button. Initially the the entries of the personal
 * tab are inserted into the personal table. The value for _id column is not inserted
 * explicitly instead it is AUTO_INCREMENTed with a starting value of 1.
 * <p>
 * Once the data is inserted in the personal table, a SELECT query is run on the
 * personal table to get the _id of the last entry added. The extracted _id is then
 * added to the remaining tables to ensure that the FOREIGN KEY constraint is satisfied.
 * <p>
 * This function takes a single object of the AlumniModel class which contains all
 * the required fields entered by the user.
 * @param insertMod
 * @throws SQLException 
 */    
//    public void insertData(AlumniModel insertMod) throws SQLException{
//        int id=1;
//        String personalInsert =   "INSERT INTO personal"+
//                                  "(name, gender, dob, pic, nationality, address, city, state, pincode)"+
//                                  "VALUES(?,?,?,?,?,?,?,?,?);";
//        
//        String contactInsert =    "INSERT INTO contact"+
//                                  "(_id, mobile, landline, email, facebook, linkedin, instagram)"+
//                                  "VALUES(?,?,?,?,?,?,?);";
//        
//        String academicInsert =   "INSERT INTO academic"+
//                                  "(_id, Umarks, Ucollege, Ubatch, Ubranch, Ucourse,"+
//                                  " Pmarks, Pcollege, Pbatch, Pbranch, Pcourse, UG)"+
//                                  "VALUES(?,?,?,?,?,?,?,?,?,?,?, ?);";
//        
//        String professionInsert = "INSERT INTO profession"+
//                                  "(_id, Placed, Companyname, Position, DOJ, Location, Package, Remark, DOL)"+
//                                  "VALUES(?,?,?,?,?,?,?,?,?);";
//
//    /***Personal*****************************************/        
//        ps = conn.prepareStatement(personalInsert);
//        String name = insertMod.getFirstname() + " " + insertMod.getLastname();
//        ps.setString(1, name);
//        ps.setString(2, String.valueOf(insertMod.getGender()));
//        ps.setString(3, insertMod.getDob());
//        ps.setString(4, insertMod.getPic());
//        ps.setString(5, insertMod.getNationality());
//        ps.setString(6, insertMod.getAddress());
//        ps.setString(7, insertMod.getCity());
//        ps.setString(8, insertMod.getState());
//        ps.setString(9, insertMod.getPincode());
//        
//        ps.execute();
//        ps = null;
//    /***************************************************/
//        
//        String querySelectID = "SELECT _id FROM personal;";
//        stmt = conn.createStatement();
//        res = stmt.executeQuery(querySelectID);
//        res.last();
//        id = res.getInt("_id");
//        
//    /***Contact*****************************************/ 
//        ps = conn.prepareStatement(contactInsert);
//        ps.setInt(1, id);
//        ps.setString(2, String.valueOf(insertMod.getMobile()));
//        ps.setString(3, String.valueOf(insertMod.getLandline()));
//        ps.setString(4, insertMod.getEmail());
//        ps.setString(5, insertMod.getFacebook());
//        ps.setString(6, insertMod.getLinkedin());
//        ps.setString(7, insertMod.getInstagram());
//        
//        ps.execute();
//        ps = null;
//        
//    /***Academic****************************************/    
//        ps = conn.prepareStatement(academicInsert);
//        ps.setInt(1, id);
//        ps.setString(2, insertMod.getPassUG());
//        ps.setString(3, insertMod.getCollegeNameUG());
//        ps.setInt(4, insertMod.getBatchUG());
//        ps.setString(5, insertMod.getBranchUG());
//        ps.setString(6, insertMod.getCourseUG());
//        ps.setString(7, insertMod.getPassPG());
//        ps.setString(8, insertMod.getCollegeNamePG());
//        ps.setInt(9, insertMod.getBatchPG());
//        ps.setString(10, insertMod.getBranchPG());
//        ps.setString(11, insertMod.getCoursePG());
//        ps.setInt(12, insertMod.getEducationLevel());
//        
//        ps.execute();
//        ps = null;
//        
//    /***Profession**************************************/
//        if(insertMod.getJobmod().size()>0){
//            for(int i=0; i<insertMod.getJobmod().size(); i++){
//                ps = conn.prepareStatement(professionInsert);
//                ps.setInt(1, id);
//                ps.setInt(2, insertMod.getPlaced());
//                ps.setString(3, insertMod.getJobmod().get(i).getCompanyName());
//                ps.setString(4, insertMod.getJobmod().get(i).getDesignation());
//                ps.setString(5, insertMod.getJobmod().get(i).getDoj());
//                ps.setString(6, insertMod.getJobmod().get(i).getLocation());
//                ps.setDouble(7, insertMod.getJobmod().get(i).getAnnualPackage());
//                ps.setString(8, insertMod.getRemark());
//                ps.setString(9, insertMod.getJobmod().get(i).getDol());
//
//                ps.execute();
//                ps = null;
//            }
//        }else{
//                ps = conn.prepareStatement(professionInsert);
//                ps.setInt(1, id);
//                ps.setInt(2, insertMod.getPlaced());
//                ps.setString(3, null);
//                ps.setString(4, null);
//                ps.setString(5, null);
//                ps.setString(6, null);
//                ps.setDouble(7, 0.0);
//                ps.setString(8, insertMod.getRemark());
//                ps.setString(9, null);
//
//                ps.execute();
//                ps = null;
//        }
//    }
//    
//    public void updateRow(String updateQuery){
//        try {
//            ps = conn.prepareStatement(updateQuery);
//            ps.execute();
//        } catch (SQLException ex) {
//            Alert al = new Alert(Alert.AlertType.ERROR);
//            al.setTitle("Update Error!");
//            al.setContentText("Unable to update record!");
//            al.setHeaderText(null);
//            al.show();
//            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    public void deleteRow(int id){
//        
//        String personalTable = "DELETE from personal "+
//                               "WHERE _id='"+id+"';";
//
//        String contactTable = "DELETE from contact "+
//                              "WHERE _id='"+id+"';";
//
//        String academicTable = "DELETE from academic "+
//                               "WHERE _id='"+id+"';";
//
//        String professionTable = "DELETE from profession "+
//                                 "WHERE _id='"+id+"';";
//        try {  
//            stmt = conn.createStatement();
//            stmt.executeUpdate(contactTable);
//            
//            stmt = conn.createStatement();
//            stmt.executeUpdate(academicTable);
//            
//            stmt = conn.createStatement();
//            stmt.executeUpdate(professionTable);
//            
//            stmt = conn.createStatement();
//            stmt.executeUpdate(personalTable);
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
        
//</editor-fold>
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
                    //System.out.println("Company Name: = " + jobmod.get(0).getCompanyName());
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelObj;
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
