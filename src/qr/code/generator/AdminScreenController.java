/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qr.code.generator;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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
        populateList();
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
                
                //DBhandler.clearAll();
                DBhandler.createTables();
                DBhandler.insertModel(modelObj);
                System.out.println(modelObj.getTractor_Series_No());
                System.out.println(modelObj.getVariant());
                System.out.println(modelObj.getModel());
                System.out.println(modelObj.getExport_domestic());
                System.out.println(modelObj.getMaterial());
                clearInputs();
                populateList();
            }else{
                Alert al = new Alert(Alert.AlertType.WARNING);
                al.setTitle("Incomplete data");
                String msg = "Please fill all the fields";
                al.setContentText(msg);
                al.setHeaderText(null);
                al.show();
            }
        }
    }
    
    public void clearInputs(){
        txt_modelNo.clear();
        txt_material.clear();
        txt_variant.clear();
        txt_chassisColor.clear();
        txt_export.clear();
        txt_tractor_series_no.clear();
    }
    
    public void populateList(){
        ArrayList<ModelHolder> model = new ArrayList<ModelHolder>();
        DatabaseHandler dbHandler = new DatabaseHandler();
        model = dbHandler.getModelSettingsData();
        
        masterData_Listview.getItems().clear();
        masterData_Listview.getItems().add(getRowHeader());
        
        int i=0;
        while(i<model.size()){
            masterData_Listview.getItems().add(getRow(model.get(i)));
            i++;
        }
    }
    
    public HBox getRow(ModelHolder modelSettings){
        HBox row = new HBox();
        Label tractorSeriesNo = new Label();
        Label variant = new Label();
        Label model = new Label();
        Label material = new Label();
        Label export_domestic = new Label();
        Label chassis_color = new Label();

        
        tractorSeriesNo.setText(modelSettings.getTractor_Series_No());
        tractorSeriesNo.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        tractorSeriesNo.setAlignment(Pos.CENTER);
        tractorSeriesNo.setPrefWidth(97);
        tractorSeriesNo.setMinWidth(97);
        tractorSeriesNo.setMaxWidth(97);
        
        variant.setText(modelSettings.getVariant());
        variant.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        variant.setAlignment(Pos.CENTER);
        variant.setPrefWidth(70);
        variant.setMinWidth(70);
        variant.setMaxWidth(70);
        
        model.setText(Integer.toString(modelSettings.getModel()));
        model.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        model.setAlignment(Pos.CENTER);
        model.setPrefWidth(60);
        model.setMinWidth(60);
        model.setMaxWidth(60);
        
        material.setText(modelSettings.getMaterial());
        material.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        material.setAlignment(Pos.CENTER);
        material.setPrefWidth(50);
        material.setMinWidth(50);
        material.setMaxWidth(50);
        
        export_domestic.setText(modelSettings.getExport_domestic());
        export_domestic.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        export_domestic.setAlignment(Pos.CENTER);
        export_domestic.setPrefWidth(50);
        export_domestic.setMinWidth(50);
        export_domestic.setMaxWidth(50);
        
        chassis_color.setText(modelSettings.getChassis_color());
        chassis_color.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        chassis_color.setAlignment(Pos.CENTER);
        chassis_color.setPrefWidth(50);
        chassis_color.setMinWidth(50);
        chassis_color.setMaxWidth(50);
        
        row.setSpacing(25);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(0, 0, 0, 0));                               //new Insets(Top, Right, Bottom, Left)
        row.getChildren().addAll(tractorSeriesNo, variant, model, material, export_domestic, chassis_color);
        
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
        tractorSeriesNo.setPrefWidth(97);
        tractorSeriesNo.setMinWidth(97);
        tractorSeriesNo.setMaxWidth(97);
        
        variant.setText("Variant");
        variant.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));
        variant.setAlignment(Pos.CENTER);
        variant.setPrefWidth(70);
        variant.setMinWidth(70);
        variant.setMaxWidth(70);
        
        model.setText("Model");
        model.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));
        model.setAlignment(Pos.CENTER);
        model.setPrefWidth(60);
        model.setMinWidth(60);
        model.setMaxWidth(60);
        
        material.setText("Material");
        material.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));
        material.setAlignment(Pos.CENTER);
        material.setPrefWidth(50);
        material.setMinWidth(50);
        material.setMaxWidth(50);
        
        export_domestic.setText("Export/Dom");
        export_domestic.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));
        export_domestic.setAlignment(Pos.CENTER);
        export_domestic.setPrefWidth(50);
        export_domestic.setMinWidth(50);
        export_domestic.setMaxWidth(50);
        
        chassis_color.setText("Color");
        chassis_color.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 12));
        chassis_color.setAlignment(Pos.CENTER);
        chassis_color.setPrefWidth(50);
        chassis_color.setMinWidth(50);
        chassis_color.setMaxWidth(50);
        
        row.setStyle("-fx-border-color: transparent transparent Black transparent;");
        //row.setStyle("-fx-background-color: red;");
        row.setSpacing(25);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(0, 0, 10, 0));                               //new Insets(Top, Right, Bottom, Left)
        row.getChildren().addAll(tractorSeriesNo, variant, model, material, export_domestic, chassis_color);
        
        return row;
    }
}

