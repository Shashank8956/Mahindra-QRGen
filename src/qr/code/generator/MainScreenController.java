/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qr.code.generator;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.print.PrintException;

/**
 *
 * @author Spongebob
 */
public class MainScreenController implements Initializable {
    
    @FXML
    private JFXTextField txt_tractorSerialNo;
    @FXML
    private JFXTextField txt_engineSerialNo;
    @FXML
    private JFXTextField txt_trasnmissionSerialNo;
    @FXML
    private JFXTextField txt_fipSerialNo;
    @FXML
    private JFXTextField txt_hydraulicSerialNo;
    @FXML
    private JFXTextField txt_pumpSerialNo;
    @FXML
    private JFXTextField txt_chassisColour;
    @FXML
    private JFXTextField txt_exportDomestic;
    @FXML
    private JFXTextField txt_model;
    @FXML
    private JFXTextField txt_tyre;
    @FXML
    private JFXButton btn_reset;
    @FXML
    private JFXButton btn_generate;
    @FXML
    private ImageView img_QR, img_userBack;
    
    ModelHolder model = null;
    DatabaseHandler DBhandler = new DatabaseHandler();
    QREngine qrgen = new QREngine();
    
    public void resetButtonHandler() {
        txt_tractorSerialNo.setText("");
        txt_engineSerialNo.setText("");
        txt_trasnmissionSerialNo.setText("");
        txt_fipSerialNo.setText("");
        txt_hydraulicSerialNo.setText("");
        txt_pumpSerialNo.setText("");
        txt_chassisColour.setText("");
        txt_exportDomestic.setText("");
        txt_model.setText("");
        txt_tyre.setText("");
        
        File file = new File("src/resources/empty_image.jpg");
        Image image = new Image(file.toURI().toString()) {};
        img_QR.setImage(image);
    }
    
    public String extractTractorSeriesNo(String tractorNo){
        return tractorNo.substring(3, 9);
    }
    
    public void populateFields(){
        ModelHolder modelObj = null;
        String tractorSeriesNo = extractTractorSeriesNo(txt_tractorSerialNo.getText());
        modelObj = DBhandler.getModelData(tractorSeriesNo);
        System.out.println(modelObj.getChassis_color());
        System.out.println(modelObj.getExport_domestic());
        System.out.println(modelObj.getModel());
        txt_chassisColour.setText(modelObj.getChassis_color());
        txt_exportDomestic.setText(modelObj.getExport_domestic());
        txt_model.setText(String.valueOf(modelObj.getModel()));
        txt_tyre.setText("#######");
    }
    
    public void generateButtonHandler() {
      
        model.setTractor_Series_No(txt_tractorSerialNo.getText());
        model.setEngine_series_no(txt_engineSerialNo.getText());
        model.setTransmission_series_no(txt_trasnmissionSerialNo.getText());
        model.setFip_series_no(txt_fipSerialNo.getText());
        model.setHydraulic_series_no(txt_hydraulicSerialNo.getText());
        model.setPump_series_no(txt_pumpSerialNo.getText());
        
        populateFields();
        
        model.setChassis_color(txt_chassisColour.getText());
        model.setExport_domestic(txt_exportDomestic.getText());
        model.setModel(Integer.parseInt(txt_model.getText()));
        model.setTyre(txt_tyre.getText());
        
        qrgen.generateQR(model);
        
        File file = new File("src/resources/Mahindra_QR.png");
        Image image = new Image(file.toURI().toString()) {};
        img_QR.setImage(image);
    }
    
    public void printQRCode(){
        PrintEngine print = new PrintEngine();
        try{
            print.printQRCode();
            DatabaseHandler dbHandler = new DatabaseHandler();
            dbHandler.insertHistory(model);
        }catch(PrintException pe){
            System.out.println("Some shit must have happened while printing (PrintEngine.printQRCode)");
            System.out.println(pe.getStackTrace());
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DBhandler.createTables();
        model = new ModelHolder();
        File file = new File("src/resources/empty_image.jpg");
        Image image = new Image(file.toURI().toString()) {};
        img_QR.setImage(image);
    }    
    
}
