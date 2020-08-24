/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qr.code.generator;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Spongebob
 */
public class AdminScreenController implements Initializable {

    @FXML
    private JFXTextField txt_tractor_series_no;
    
    @FXML
    private JFXTextField txt_modelNo;
    
    @FXML
    private JFXTextField txt_material;
    
    @FXML
    private JFXTextField txt_variant;
    
    @FXML
    private JFXTextField txt_chassisColor;
    
    @FXML
    private JFXListView masterData_Listview;
    
    @FXML
    private JFXTextField txt_export;
    DatabaseHandler DBhandler = new DatabaseHandler();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
    
    public void addModelDataButtonHandler(){
        String model = txt_modelNo.getText();
        String material = txt_material.getText();
        String variant = txt_variant.getText();
        String chassisColor = txt_chassisColor.getText();
        String export = txt_export.getText();
        String tractor_series_no = txt_tractor_series_no.getText();
        ModelHolder modelObj = new ModelHolder();
        
        if(model!=null && material!=null && variant!=null && chassisColor!=null && export!=null && tractor_series_no!=null){
            if(tractor_series_no.length()!=0 && model.length()!=0 && material.length()!=0 && variant.length()!=0 && chassisColor.length()!=0 && export.length()!=0){
                modelObj.setModel(Integer.parseInt(model));
                modelObj.setChassis_color(chassisColor);
                modelObj.setExport_domestic(export);
                modelObj.setMaterial(material);
                modelObj.setTractor_Series_No(tractor_series_no);
                modelObj.setVariant(variant);
            }
        }
        if(modelObj.getMaterial().length()!=0){
            DBhandler.createTables();
            DBhandler.insertModel(modelObj);
            System.out.print("Model entry added");
        }    
        
    }
}

