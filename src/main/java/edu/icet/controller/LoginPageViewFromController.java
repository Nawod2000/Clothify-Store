package edu.icet.controller;

import com.jfoenix.controls.JFXButton;
import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.impl.EmployeeBoImpl;
import edu.icet.entity.EmployeeEntity;
import edu.icet.util.BoType;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageViewFromController implements Initializable {


    public AnchorPane welcomeAnchorPane;
    public PasswordField passwordField;
    public Hyperlink needHelpHyperlink;
    public TextField UserNameField;
    public JFXButton btnSing;

    private boolean isShow;

    SceneSwitchController sceneSwitch = SceneSwitchController.getInstance();
    EmployeeBoImpl employeeBo = new EmployeeBoImpl();
    EmployeeData employeeData = EmployeeData.getInstance();

    public void ForgetPassBtn(ActionEvent actionEvent) throws IOException {
        sceneSwitch.switchScene(welcomeAnchorPane,"view/admin/password-reset-view-form.fxml");
    }


    public void emailKeyReleasedAction(KeyEvent keyEvent) {
        if (isShow){
            new Alert(Alert.AlertType.INFORMATION,"If your first time to sign in to this, Please reset your password clicked forgot password button").show();
            isShow=false;
        }
    }

    public void SingInOnAction(ActionEvent actionEvent) throws IOException {
        employeeBo= BoFactory.getInstance().getBo(BoType.EMPLOYEE);
        EmployeeEntity employeeEntity = employeeBo.getEmployeeByEmail(UserNameField.getText());

        if (employeeEntity==null){
            new Alert(Alert.AlertType.ERROR,"Invalid Email Address or Password").show();
            return;
        }

        String password = employeeBo.passwordDecrypt(employeeEntity.getPassword());
        employeeData.setId(employeeEntity.getId());

        if (employeeEntity.getRole().equals("admin") && password.equals(passwordField.getText())){
            System.out.println("Logged");
            try {
                SceneSwitchController.getInstance().switchScene(welcomeAnchorPane,"view/admin/admin-welcome-view-form.fxml");
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        } else if (employeeEntity.getRole().equals("employee") && password.equals(passwordField.getText())) {
            EmployeeData instance = EmployeeData.getInstance();
            instance.setId(employeeEntity.getId());
            instance.setName(employeeEntity.getName());
            instance.setEmail(employeeEntity.getEmail());
            SceneSwitchController.getInstance().switchScene(welcomeAnchorPane,"view/emp/emp-welcome-view-form.fxml");
        } else if (employeeEntity.getId()==null) {
            System.out.println("Null");
        } else {
            new Alert(Alert.AlertType.ERROR,"Invalid Password").show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isShow=true;
    }


    public void handleNeedHelpBtn(ActionEvent actionEvent) {
    }
}
