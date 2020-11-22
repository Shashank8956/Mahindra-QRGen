
package qr.code.generator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Spongebob
 */
public class LoginScreenController implements Initializable {

    @FXML
    private Label lbl_close;
    @FXML
    private AnchorPane login_rootPane;
    @FXML
    private TextField txt_username;
    @FXML
    private TextField txt_password;
    @FXML
    private ImageView img_loginBackground;
    private DatabaseHandler db;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        db = new DatabaseHandler();
        
        
        File file = new File("resources/tractor.jpg");
        Image image = new Image(file.toURI().toString()) {
        };
        img_loginBackground.setImage(image);
        //img_loginBackground.setEffect(new GaussianBlur());

    }

    public void loginEventHandler() {
        try {
            //txt_username = new TextField();
            //txt_password = new TextField();
            //FileHandler fh = new FileHandler();
            //fh.changeFileName(0);
        
            AnchorPane userPane = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            AnchorPane adminPane = FXMLLoader.load(getClass().getResource("AdminScreen.fxml"));
            String username = txt_username.getText();
            String password = txt_password.getText();
            System.out.println(username + "\t" + password);
            if (username.equals("user") && password.equals("pass")) {
                login_rootPane.getChildren().setAll(userPane);
            } else if (username.equals("admin") && password.equals("admin")) {
                login_rootPane.getChildren().setAll(adminPane);
            } else {
                System.out.println("Incorrect username or password!");
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeApp() {
        System.exit(0);
    }
}
