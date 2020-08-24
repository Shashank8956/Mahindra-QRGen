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
    
//</editor-fold>
}
