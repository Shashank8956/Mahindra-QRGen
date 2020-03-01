/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qr.code.generator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Spongebob
 */
public class QREngine implements Observer{
    @Override
    public void update(Observable obs, Object dataObject){
        ObservableDataModel dataModel = (ObservableDataModel) dataObject;
        System.out.println("Observer Notified!");
        generateQR(dataModel);
    }

    public void generateQR(ObservableDataModel dataModel){

        try {//ADD MORE CONTENT TO THE QR!!!!!
            String qrCodeText = "Tractor Sr: " + dataModel.getTractorSerialNo() + 
                                "\nEngine Sr: " + dataModel.getEngineSerialNo() + 
                                "\nTransmission Sr: " + dataModel.getTransmissionSerialNo() +
                                "\nFIP Sr: " + dataModel.getFipSerialNo() + 
                                "\nHydraulic Sr: " + dataModel.getHydraulicSerialNo() + 
                                "\nPump Sr: " + dataModel.getPumpSerialNo() +
                                "\nChassis colour: " + dataModel.getChassisColour() + 
                                "\nEx/Dom Sr: " + dataModel.getExportDomestic() + 
                                "\nModel Sr: " + dataModel.getModel()+
                                "\nTyre: " + dataModel.getTyre();
            
            String filePath = "src/resources/Mahindra_QR.png";
            int size = 225;
            String fileType = "png";
            File qrFile = new File(filePath);
            createQRImage(qrFile, qrCodeText, size, fileType);
            System.out.println("QR Generated!");
            addQRText(dataModel.getTractorSerialNo());
            System.out.print("Text added!");
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    private static void createQRImage(File qrFile, String qrCodeText, int size, String fileType)
                    throws WriterException, IOException {
            // Create the ByteMatrix for the QR-Code that encodes the given String
            Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
            // Make the BufferedImage that are to hold the QRCode
            int matrixWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(matrixWidth + 150, matrixWidth, BufferedImage.TYPE_INT_RGB);                                          // Line 1
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, matrixWidth + 150, matrixWidth);                                                                                      // Line 2
            // Paint and save the image using the ByteMatrix
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < matrixWidth; i++) {
                    for (int j = 0; j < matrixWidth; j++) {
                            if (byteMatrix.get(i, j)) {
                                    graphics.fillRect(i + 75, j - 10, 1, 1);                                                   //If we increase X axis in line 1 and 2 then increas it here too
                            }
                    }
            }
            ImageIO.write(image, fileType, qrFile);
    }
    
    public void addQRText(String tractorNo){
        try {
            final BufferedImage image = ImageIO.read(new File("src/resources/Mahindra_QR.png"));
            int sizeY = image.getHeight();
            int sizeX = image.getWidth();
            Graphics g = image.getGraphics();
            g.setFont(new Font("Roboto", Font.BOLD, 21));
            g.setColor(Color.BLACK);
            g.drawString("Tractor Sr: " + tractorNo, sizeX-355, sizeY-10);
            g.dispose();
            
            ImageIO.write(image, "png", new File("src/resources/Mahindra_QR.png"));
        } catch (Exception ex) {
            Logger.getLogger(QREngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
