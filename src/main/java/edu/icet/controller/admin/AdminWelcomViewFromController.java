package edu.icet.controller.admin;

import edu.icet.controller.SceneSwitchController;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminWelcomViewFromController {

    public AnchorPane welcomeAnchorPane;
    SceneSwitchController switchController = SceneSwitchController.getInstance();

    public void manageEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        switchController.switchScene(welcomeAnchorPane, "admin/emp-manage-view-form.fxml");
    }

    public void manageProductOnAction(ActionEvent actionEvent) throws IOException {
        switchController.switchScene(welcomeAnchorPane, "admin/manage-product-view-form.fxml");
    }

    public void orderViewOnAction(ActionEvent actionEvent) throws IOException {
        switchController.switchScene(welcomeAnchorPane, "admin/Order-view-tab-form.fxml");
    }

    public void customerViewOnAction(ActionEvent actionEvent) throws IOException {
        switchController.switchScene(welcomeAnchorPane, "admin/Customer-view-tab-form.fxml");
    }

    public void productMenuOnAction(ActionEvent actionEvent) throws IOException {
        switchController.switchScene(welcomeAnchorPane, "admin/product-menu-view-form.fxml");
    }

    public void supplierManageOnAction(ActionEvent actionEvent) throws IOException {
        switchController.switchScene(welcomeAnchorPane, "admin/supllier-manage-view-form.fxml");
    }

    public void dashBordOnAction(ActionEvent actionEvent) throws IOException {
        switchController.switchScene(welcomeAnchorPane, "admin/admin-dashboard-view-form.fxml");
    }
}
