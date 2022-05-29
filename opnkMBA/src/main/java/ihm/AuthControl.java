package ihm;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthControl {

    @FXML
    private Button connexionbutton;
    @FXML
    private Label incorrect;
    @FXML
    private TextField identifiant;
    @FXML
    private PasswordField password;



    public void userLogIn(ActionEvent event) throws IOException {
        checkLogin();

    }

    private void checkLogin() throws IOException {
        AuthGUI m = new AuthGUI();
        if(identifiant.getText().toString().equals("javacoding") && password.getText().toString().equals("123")) {
            incorrect.setText("Success!");

            m.changeScene("info_perso.fxml");
        }

        else if(identifiant.getText().isEmpty() && password.getText().isEmpty()) {
            incorrect.setText("Please enter your data.");
        }


        else {
            incorrect.setText("Wrong username or password!");
        }
    }


}