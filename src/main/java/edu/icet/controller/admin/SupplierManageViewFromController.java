package edu.icet.controller.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.impl.SupplierBoImpl;
import edu.icet.controller.SceneSwitchController;
import edu.icet.dto.Supplier;
import edu.icet.util.BoType;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierManageViewFromController implements Initializable {

    public AnchorPane empManage;
    public Label lblTime;
    public Label lblDate;
    public TextField txtId;
    public JFXTextField txtEmail;
    public TableView tblEmployeeTable;
    public TableColumn colEmpId;
    public TableColumn colEmpName;
    public TableColumn colEmpEmail;
    public TableColumn colEmpAddress;
    public JFXTextField txtName;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXTextField txtCompany;


    private SupplierBoImpl supplierBo = BoFactory.getInstance().getBo(BoType.SUPPLIER);

    private SceneSwitchController switchController = SceneSwitchController.getInstance();

    boolean isAction = true,isEmailValid,isMouseClick;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtId.setText(supplierBo.genarateId());

    }

    public void manageEmployeeOnAction(ActionEvent actionEvent) {
    }

    public void manageProductsOnAction(ActionEvent actionEvent) {
    }

    public void ordersViewOnAction(ActionEvent actionEvent) {
    }

    public void customerViewOnAction(ActionEvent actionEvent) {
    }

    public void dashBroadOnAction(ActionEvent actionEvent) {
    }

    public void rectangleMouseClick(MouseEvent mouseEvent) {
    }

    public void tableMouseClickAction(MouseEvent mouseEvent) {
    }

    public void addButtonOnAction(ActionEvent actionEvent) {
        Supplier supplier = new Supplier(
                txtId.getText(),
                txtName.getText(),
                txtEmail.getText(),
                txtCompany.getText()
        );

        if (!txtId.getText().isEmpty() && supplierBo.isValidEmail(txtEmail.getText()) && !txtCompany.getText().isEmpty()){
            boolean isSave = false;

            try {
                isSave = supplierBo.saveSupplier(supplier);
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Supplier Id");
                alert.setContentText("jhjdjfcdc");
                alert.showAndWait();
                txtId.setText(supplierBo.genarateId());
                txtName.clear();
                txtEmail.clear();
                txtCompany.clear();
                return;
            }

            if (isSave){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Supplier Added");
                alert.setContentText("Supplier added successfully!");
                alert.showAndWait();
            }
        }

    }

    public void updateButtonOnAction(ActionEvent actionEvent) {
    }

    public void deleteButtonOnAction(ActionEvent actionEvent) {
    }


}
