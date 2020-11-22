/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qr.code.generator;

import java.io.File;

/**
 *
 * @author HP
 */
public class FileHandler {
    public void changeFileName(int status){
        File oldName = null;
        File newName = null;
        
        if(status == 0){                            //Original to Duplicate
            oldName = new File("QRGenMaster.db"); 
            newName = new File("QRGenMaster1.db"); 
        }else if(status == 1){                       //Duplicate to Original
            oldName = new File("QRGenMaster1.db"); 
            newName = new File("QRGenMaster.db");
        }
        
        if (oldName.renameTo(newName))  
            System.out.println("Renamed successfully");
        else 
            System.out.println("Error");
    }
}
