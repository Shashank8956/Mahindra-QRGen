/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qr.code.generator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Spongebob
 */
public class QRCodeGenerator extends Application {
    
    private Stage window;
    private Scene loginScene, mainScene;
    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
        loginScene = new Scene(root);
        window.initStyle(StageStyle.UNDECORATED);
        window.setScene(loginScene);
        window.show();
    }

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
