/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qr.code.generator;

/**
 *
 * @author Spongebob
 */
public class ModelHolder {
    int model;
    String tractor_Series_No;
    String variant;
    String material;
    String export_domestic;
    String chassis_color;
    String engine_series_no;
    String transmission_series_no;
    String fip_series_no;
    String hydraulic_series_no;
    String pump_series_no;
    String tyre;
    
    ModelHolder(){}
    
    ModelHolder(String tractor_Series_No, String variant, int model, String material, String export_domestic, String chassis_color){
        this.model = model;
        this.tractor_Series_No = tractor_Series_No;
        this.variant = variant;
        this.material = material;
        this.export_domestic = export_domestic;
        this.chassis_color = chassis_color;
    }
    
//<editor-fold defaultstate="collapsed" desc="Getters">
    
    public int getModel() {
        return model;
    }

    public String getTractor_Series_No() {
        return tractor_Series_No;
    }

    public String getVariant() {
        return variant;
    }

    public String getMaterial() {
        return material;
    }

    public String getExport_domestic() {
        return export_domestic;
    }

    public String getChassis_color() {
        return chassis_color;
    }

    public String getEngine_series_no() {
        return engine_series_no;
    }

    public String getTransmission_series_no() {
        return transmission_series_no;
    }

    public String getFip_series_no() {
        return fip_series_no;
    }

    public String getHydraulic_series_no() {
        return hydraulic_series_no;
    }

    public String getPump_series_no() {
        return pump_series_no;
    }

    public String getTyre() {
        return tyre;
    }
    
    
    
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Setter">

    public void setModel(int model) {
        this.model = model;
    }

    public void setTractor_Series_No(String tractor_Series_No) {
        this.tractor_Series_No = tractor_Series_No;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setExport_domestic(String export_domestic) {
        this.export_domestic = export_domestic;
    }

    public void setChassis_color(String chassis_color) {
        this.chassis_color = chassis_color;
    }
    
    
    public void setEngine_series_no(String engine_series_no) {
        this.engine_series_no = engine_series_no;
    }

    public void setTransmission_series_no(String transmission_series_no) {
        this.transmission_series_no = transmission_series_no;
    }

    public void setFip_series_no(String fip_series_no) {
        this.fip_series_no = fip_series_no;
    }

    public void setHydraulic_series_no(String hydraulic_series_no) {
        this.hydraulic_series_no = hydraulic_series_no;
    }

    public void setPump_series_no(String pump_series_no) {
        this.pump_series_no = pump_series_no;
    }

    public void setTyre(String tyre) {
        this.tyre = tyre;
    }
}

    
//</editor-fold>
