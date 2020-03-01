/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qr.code.generator;

import java.util.Observable;

/**
 *
 * @author Spongebob
 */
public class ObservableDataModel extends Observable {
     
    private String tractorSerialNo;
    private String engineSerialNo;
    private String transmissionSerialNo;
    private String fipSerialNo;
    private String hydraulicSerialNo;
    private String pumpSerialNo;
    private String chassisColour;
    private String exportDomestic;
    private String model;
    private String tyre;
    private static ObservableDataModel dataModel = new ObservableDataModel();

    private ObservableDataModel(){
        tractorSerialNo = null;
        engineSerialNo = null;
        transmissionSerialNo = null;
        
    }
    
    public void changeData(){
        setChanged();
        notifyObservers(dataModel);
    }
    
    public static ObservableDataModel getObservableDataModelInstance(){
        return dataModel;
    }
    
//<editor-fold defaultstate="collapsed" desc="Setters">
    
    public void setTractorSerialNo(String tractorSerialNo) {
        this.tractorSerialNo = tractorSerialNo;
    }

    public void setEngineSerialNo(String engineSerialNo) {
        this.engineSerialNo = engineSerialNo;
    }

    public void setTransmissionSerialNo(String transmissionSerialNo) {
        this.transmissionSerialNo = transmissionSerialNo;
    }

    public void setFipSerialNo(String fipSerialNo) {
        this.fipSerialNo = fipSerialNo;
    }

    public void setHydraulicSerialNo(String hydraulicSerialNo) {
        this.hydraulicSerialNo = hydraulicSerialNo;
    }

    public void setPumpSerialNo(String pumpSerialNo) {
        this.pumpSerialNo = pumpSerialNo;
    }

    public void setChassisColour(String chassisColour) {
        this.chassisColour = chassisColour;
    }

    public void setExportDomestic(String exportDomestic) {
        this.exportDomestic = exportDomestic;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setTyre(String tyre) {
        this.tyre = tyre;
    }
    
//</editor-fold>
    
//<editor-fold defaultstate="collapsed" desc="Getters">

    public String getTractorSerialNo() {
        return tractorSerialNo;
    }
    
    public String getEngineSerialNo() {
        return engineSerialNo;
    }
    
    public String getTransmissionSerialNo() {
        return transmissionSerialNo;
    }
    
    public String getFipSerialNo() {
        return fipSerialNo;
    }
    
    public String getHydraulicSerialNo() {
        return hydraulicSerialNo;
    }
    
    public String getPumpSerialNo() {
        return pumpSerialNo;
    }
    
    public String getChassisColour() {
        return chassisColour;
    }

    public String getExportDomestic() {
        return exportDomestic;
    }
    
    public String getModel() {
        return model;
    }
    
    public String getTyre() {
        return tyre;
    }
    
//</editor-fold>
        
}
