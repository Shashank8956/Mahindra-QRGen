/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qr.code.generator;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    private ImageView img_QR;
    
    ObservableDataModel model = null;
    
    
    
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
    
    public void generateButtonHandler() {
        model.setTractorSerialNo(txt_tractorSerialNo.getText());
        model.setEngineSerialNo(txt_engineSerialNo.getText());
        model.setTransmissionSerialNo(txt_trasnmissionSerialNo.getText());
        model.setFipSerialNo(txt_fipSerialNo.getText());
        model.setHydraulicSerialNo(txt_hydraulicSerialNo.getText());
        model.setPumpSerialNo(txt_pumpSerialNo.getText());
        txt_chassisColour.setText("XXXXXX");
        txt_exportDomestic.setText("YYYYYY");
        txt_model.setText("ZZZZZZZ");
        txt_tyre.setText("#######");
        model.setChassisColour(txt_chassisColour.getText());
        model.setExportDomestic(txt_exportDomestic.getText());
        model.setModel(txt_model.getText());
        model.setTyre(txt_tyre.getText());
        
        model.changeData();
        File file = new File("src/resources/Mahindra_QR.png");
        Image image = new Image(file.toURI().toString()) {};
        img_QR.setImage(image);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        QREngine qrgen = new QREngine();
        model = ObservableDataModel.getObservableDataModelInstance();
        model.addObserver(qrgen);
        
        File file = new File("src/resources/empty_image.jpg");
        Image image = new Image(file.toURI().toString()) {};
        img_QR.setImage(image);
        
       
        
    }    
    
}
