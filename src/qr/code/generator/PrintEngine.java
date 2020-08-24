package qr.code.generator;

import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.print.PrintQuality;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrinterName;

/**
 *
 * @author Spongebob
 */
public class PrintEngine {

    private AttributeSet aset;
    private PrintService[] services;
    private PrintService printService;

    public static void main(String[] args) {
        PrintEngine pe = new PrintEngine();
        pe.print();
    }
    
    public void print() {
        aset = new HashAttributeSet();
        String printerName = "hi";
        aset.add(new PrinterName(printerName, null));
        /* locate a print service that can handle the request */
        services = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.PNG, aset);

        printService = PrintServiceLookup.lookupDefaultPrintService();
        System.out.println("Printer Name: " + printService);
        Media[] res = (Media[]) printService.getSupportedAttributeValues(Media.class, null, null);
        for (Media media : res) {
            if (media instanceof MediaSizeName) {
                MediaSizeName msn = (MediaSizeName) media;
                MediaSize ms = MediaSize.getMediaSizeForName(msn);
                float width = ms.getX(MediaSize.INCH);
                float height = ms.getY(MediaSize.INCH);
                System.out.println(media + ": width = " + width + "; height = " + height);
            }
        }
        System.out.println("service length: " + services.length);
        if (services.length >= 0) {
            /* create a print job for the chosen service */
            DocPrintJob pj = services[0].createPrintJob();

            DocAttributeSet das = new HashDocAttributeSet();
            //das.add(PrintQuality.HIGH);
            das.add(MediaSizeName.ISO_A5); // I know the problem is here somewhere. This Media size seems to work best currently

            try {
                /* 
            * Create a Doc object to hold the print data.
                 */
                FileInputStream imageByteIs = new FileInputStream("src/resources/Mahindra_QR.png");
                Doc doc = new SimpleDoc(imageByteIs, DocFlavor.INPUT_STREAM.PNG, das);

                /* print the doc as specified */
                pj.print(doc, null);
                System.out.println("Done?");

            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    public void printQRCode() throws PrintException {
        PrintService ps = PrintServiceLookup.lookupDefaultPrintService();
        DocPrintJob job = ps.createPrintJob();
        String cmd = "^XA~TA000~JSN^LT0^MNW^MTT^PON^PMN^LH0,0^JMA^PR6,6~SD15^JUS^LRN^CI0^XZ\n"
                + "^XA\n"
                + "^MMT\n"
                + "^PW831\n"
                + "^LL0406\n"
                + "^LS0\n"
                + "^FT100,150^BQN,2,3\n"     //1st no: x axis distance, 2nd no: y axis distance, 3rd no: width, 4th No: height
                + "^FH\\^FDLA,Tractor Sr: ABCDAJXAAA12345\\0D\\0AEngine Sr: KJHDKJVK687685FG\\0D\\0ATrans Sr: ABCDAJXAAA12345\\0D\\0APump Sr: KJHDKJVK687685FG^FS\n"
                + "^FT255,90^A0N,28,28^FH\\^FDThis is a Test Text^FS\n"
                + "^PQ1,0,1,Y^XZ";
        DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
        Doc doc = new SimpleDoc(cmd.getBytes(), flavor, null);
        job.print(doc, null);
    }
}
