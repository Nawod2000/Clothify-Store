package edu.icet.controller;


import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminDashBoardViewFromController {

    public AnchorPane adminDashbordAnchorPane;

    SceneSwitchController switchController = SceneSwitchController.getInstance();

    public void manageEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        switchController.switchScene(adminDashbordAnchorPane, "admin/emp-manage-view-form.fxml");
    }

    public void manageProductOnAction(ActionEvent actionEvent) throws IOException {
        switchController.switchScene(adminDashbordAnchorPane, "admin/manage-product-view-form.fxml");
    }

    public void orderViewOnAction(ActionEvent actionEvent) throws IOException {
        switchController.switchScene(adminDashbordAnchorPane, "admin/Order-view-tab-form.fxml");
    }

    public void customerViewOnAction(ActionEvent actionEvent) throws IOException {
        switchController.switchScene(adminDashbordAnchorPane, "admin/Customer-view-tab-form.fxml");
    }

    public void productMenuOnAction(ActionEvent actionEvent) throws IOException {
        switchController.switchScene(adminDashbordAnchorPane, "admin/product-menu-view-form.fxml");
    }

    public void supplierManageOnAction(ActionEvent actionEvent) throws IOException {
        switchController.switchScene(adminDashbordAnchorPane, "admin/supllier-manage-view-form.fxml");
    }

    public void dashBordeOnAction(ActionEvent actionEvent) throws IOException {
        switchController.switchScene(adminDashbordAnchorPane, "admin/admin-dashboard-view-form.fxml");
    }
}
