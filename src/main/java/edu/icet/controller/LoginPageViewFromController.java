package edu.icet.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.custom.impl.EmployeeBoImpl;
import edu.icet.dto.Employee;
import edu.icet.entity.EmployeeEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LoginPageViewFromController {

    public AnchorPane loginAnchorPane;
    EmployeeBoImpl employeeBo = new EmployeeBoImpl();

    @FXML
    private JFXTextField loginEmailtxt;

    @FXML
    private JFXPasswordField loginpasswordtxt;

    @FXML
    void loginAction(ActionEvent event) throws IOException {

//        EmployeeEntity employee = employeeBo.findEmployee(loginEmailtxt.getText(), loginpasswordtxt.getText());
//        if (employee!=null){
//            if (employee.getRole().equals("Admin")){
//                //SceneSwitchController.getInstance().switchScene(loginAnchorPane, "");
//            }
//        }else{
//            System.out.println("Null");
//        }

    }

}
