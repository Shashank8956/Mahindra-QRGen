/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qr.code.generator;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
    private JFXListView history_table;
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
        model.clear();
        
        
        File file = new File("resources/empty_image.jpg");
        Image image = new Image(file.toURI().toString()) {};
        img_QR.setImage(image);
    }
    
    public String extractTractorSeriesNo(String tractorNo){
        return tractorNo.substring(3, 9);
    }
    
    public void populateFields(){
        ModelHolder modelObj = new ModelHolder();
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
      
        if(txt_tractorSerialNo.getText().length() < 1 || txt_engineSerialNo.getText().length() < 1 || txt_trasnmissionSerialNo.getText().length() < 1 ||
                txt_fipSerialNo.getText().length() < 1 || txt_hydraulicSerialNo.getText().length() < 1 || txt_pumpSerialNo.getText().length() < 1 ||
                txt_chassisColour.getText().length() < 1 || txt_exportDomestic.getText().length() < 1 || txt_model.getText().length() < 1 || txt_tyre.getText().length() < 1){
                    Alert al = new Alert(Alert.AlertType.WARNING);
                    al.setTitle("Incomplete details");
                    String msg = "Please fill all the fields";
                    al.setContentText(msg);
                    al.setHeaderText(null);
                    al.show();
        }else{  
            model.setTractor_Series_No(txt_tractorSerialNo.getText());
            model.setEngine_series_no(txt_engineSerialNo.getText());
            model.setTransmission_series_no(txt_trasnmissionSerialNo.getText());
            model.setFip_series_no(txt_fipSerialNo.getText());
            model.setHydraulic_series_no(txt_hydraulicSerialNo.getText());
            model.setPump_series_no(txt_pumpSerialNo.getText());

            model.setChassis_color(txt_chassisColour.getText());
            model.setExport_domestic(txt_exportDomestic.getText());
            model.setModel(Integer.parseInt(txt_model.getText()));
            model.setTyre(txt_tyre.getText());

            qrgen.generateQR(model);

            File file = new File("resources/Mahindra_QR.png");
            Image image = new Image(file.toURI().toString()) {};
            img_QR.setImage(image);
        }

    }
    
    public void printQRCode(){
        PrintEngine print = new PrintEngine();
        try{
            if(txt_tractorSerialNo.getText().length() < 1 || txt_engineSerialNo.getText().length() < 1 || txt_trasnmissionSerialNo.getText().length() < 1 ||
                txt_fipSerialNo.getText().length() < 1 || txt_hydraulicSerialNo.getText().length() < 1 || txt_pumpSerialNo.getText().length() < 1 ||
                txt_chassisColour.getText().length() < 1 || txt_exportDomestic.getText().length() < 1 || txt_model.getText().length() < 1 || txt_tyre.getText().length() < 1){
                    Alert al = new Alert(Alert.AlertType.WARNING);
                    al.setTitle("Incomplete details!");
                    String msg = "Please fill all the fields and generate the QR code before printing!";
                    al.setContentText(msg);
                    al.setHeaderText(null);
                    al.show();
        }else{  
            //print.printQRCode(model);
            DatabaseHandler dbHandler = new DatabaseHandler();
            dbHandler.insertHistory(model);
            resetButtonHandler();
            populateList();
            
            //clear Input
        }
        }catch(Exception pe){
            System.out.println("Some shit must have happened while printing (PrintEngine.printQRCode)");
            System.out.println(pe.getStackTrace());
        }
    }
    
    public void populateList(){
        ArrayList<ModelHolder> historyModel = new ArrayList<ModelHolder>(); 
        DatabaseHandler dbHandler = new DatabaseHandler();
        historyModel = dbHandler.getHistoryData();
        
        history_table.getItems().clear();
        history_table.getItems().add(getRowHeader());
        
        int i=0;
        while(i<historyModel.size()){
            history_table.getItems().add(getRow(historyModel.get(i)));
            i++;
        }
    }
    
    public HBox getRow(ModelHolder modelSettings){
        HBox row = new HBox();
        Label tractorSeriesNo = new Label();
        Label dateTime = new Label();
        Label color = new Label();
        Label expDom = new Label();
        
        tractorSeriesNo.setText(modelSettings.getTractor_Series_No());
        tractorSeriesNo.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        tractorSeriesNo.setAlignment(Pos.CENTER);
        tractorSeriesNo.setPrefWidth(110);
        tractorSeriesNo.setMinWidth(110);
        tractorSeriesNo.setMaxWidth(110);
        
        dateTime.setText(modelSettings.getDateTime());
        dateTime.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        dateTime.setAlignment(Pos.CENTER);
        dateTime.setPrefWidth(120);
        dateTime.setMinWidth(120);
        dateTime.setMaxWidth(120);
        
        color.setText(modelSettings.getChassis_color());
        color.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        color.setAlignment(Pos.CENTER);
        color.setPrefWidth(80);
        color.setMinWidth(80);
        color.setMaxWidth(80);
        
        expDom.setText(modelSettings.getExport_domestic());
        expDom.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        expDom.setAlignment(Pos.CENTER);
        expDom.setPrefWidth(80);
        expDom.setMinWidth(80);
        expDom.setMaxWidth(80);
        
        row.setSpacing(25);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(0, 0, 0, 0));                               //new Insets(Top, Right, Bottom, Left)
        row.getChildren().addAll(tractorSeriesNo, dateTime, color, expDom);
        
        return row;
    }
    
    public HBox getRowHeader(){
        HBox row = new HBox();
        Label tractorSeriesNo = new Label();
        Label variant = new Label();
        Label model = new Label();
        Label material = new Label();
        Label export_domestic = new Label();
        Label chassis_color = new Label();
        
        tractorSeriesNo.setText("Tractor Series");
        tractorSeriesNo.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));
        tractorSeriesNo.setAlignment(Pos.CENTER);
        tractorSeriesNo.setPrefWidth(110);
        tractorSeriesNo.setMinWidth(110);
        tractorSeriesNo.setMaxWidth(110);
        
        variant.setText("Created On");
        variant.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));
        variant.setAlignment(Pos.CENTER);
        variant.setPrefWidth(110);
        variant.setMinWidth(110);
        variant.setMaxWidth(110);
        
        model.setText("Color");
        model.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));
        model.setAlignment(Pos.CENTER);
        model.setPrefWidth(100);
        model.setMinWidth(100);
        model.setMaxWidth(100);
        
        material.setText("Exp/Dom");
        material.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));
        material.setAlignment(Pos.CENTER);
        material.setPrefWidth(55);
        material.setMinWidth(55);
        material.setMaxWidth(55);
        
        row.setStyle("-fx-border-color: transparent transparent Black transparent;");
        //row.setStyle("-fx-background-color: red;");
        row.setSpacing(25);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(5, 0, 10, 0));                               //new Insets(Top, Right, Bottom, Left)
        row.getChildren().addAll(tractorSeriesNo, variant, model, material, export_domestic, chassis_color);
        
        return row;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBhandler.clearAll();
        DBhandler.createTables();
        
        model = new ModelHolder();
        history_table.getItems().add(getRowHeader());
        populateList();
        
        File file = new File("resources/empty_image.jpg");
        Image image = new Image(file.toURI().toString()) {};
        img_QR.setImage(image);
        
        txt_tractorSerialNo.focusedProperty().addListener((ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue, Boolean newPropertyValue) -> {
            if (newPropertyValue){
                System.out.println("Textfield on focus");
            }
            else{
                System.out.println("Textfield out focus");
                if(txt_tractorSerialNo.getText().length() >= 9){
                    populateFields();
                }else{
                    Alert al = new Alert(Alert.AlertType.WARNING);
                    al.setTitle("Invalid Tractor Series No");
                    String msg = "Enter correct Tractor Series No";
                    al.setContentText(msg);
                    al.setHeaderText(null);
                    al.show();
                }
            }
        });
    }    
    
}
