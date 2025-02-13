package edu.icet.controller.emp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.impl.SupplierBoImpl;
import edu.icet.controller.SceneSwitchController;
import edu.icet.dto.Employee;
import edu.icet.dto.Supplier;
import edu.icet.util.BoType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SupplierManageViewFromController implements Initializable {

    public AnchorPane empManage;
    public Label lblTime;
    public Label lblDate;
    public TextField txtId;
    public JFXTextField txtEmail;
    public JFXTextField txtName;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXTextField txtCompany;
    public TableView tblSupllierTable;
    public TableColumn colSupId;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colCompany;


    private SupplierBoImpl supplierBo = BoFactory.getInstance().getBo(BoType.SUPPLIER);

    private SceneSwitchController switchController = SceneSwitchController.getInstance();

    boolean isAction = true,isEmailValid,isMouseClick;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colSupId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("supName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("companyName"));

        txtId.setText(supplierBo.genarateId());
        txtId.setDisable(true);
        tblSupllierTable.setItems(supplierBo.getAllSupllier());

    }

    public void manageEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        switchController.switchScene(empManage, "admin/emp-manage-view-form.fxml");
    }

    public void manageProductsOnAction(ActionEvent actionEvent) throws IOException {
        switchController.switchScene(empManage, "admin/product-manage-view-form.fxml");
    }

    public void ordersViewOnAction(ActionEvent actionEvent) throws IOException {
        switchController.switchScene(empManage, "admin/Order-view-tab-form.fxml");
    }

    public void customerViewOnAction(ActionEvent actionEvent) throws IOException {
        switchController.switchScene(empManage, "admin/Customer-view-tab-form.fxml");
    }

    public void dashBroadOnAction(ActionEvent actionEvent) throws IOException {
        switchController.switchScene(empManage, "admin/admin-dashboard-view-form.fxml");
    }

    public void rectangleMouseClick(MouseEvent mouseEvent) {
        btnAdd.setDisable(false);
        clearFeild();
        txtId.setText(supplierBo.genarateId());
    }

    public void tableMouseClickAction(MouseEvent mouseEvent) {
        int index = tblSupllierTable.getSelectionModel().getSelectedIndex();


        if(index < 0){
            return;
        }
        String id = colSupId.getCellData(index).toString();


        if (isAction){
            isEmailValid = true;
            Supplier supplier = supplierBo.getSupplierById(id);
            txtId.setText(supplier.getId());
            txtName.setText(supplier.getSupName());
            txtEmail.setText(supplier.getEmail());
            txtCompany.setText(supplier.getCompanyName());
            btnAdd.setDisable(true);
            byte[] data;

            if (!supplier.getId().equals("")){
                isMouseClick = true;
            }
        }
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
                tblSupllierTable.setItems(supplierBo.getAllSupllier());
                clearFeild();
                txtId.setText(supplierBo.genarateId());
            }else {
                new Alert(Alert.AlertType.ERROR, "Failed to add supplier. Please try again.").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Please fill in all fields with valid data.").show();
        }
    }

    public void updateButtonOnAction(ActionEvent actionEvent) {
        if (!txtId.getText().isEmpty() && !txtEmail.getText().isEmpty() && !txtCompany.getText().isEmpty()) {
            Supplier supplier = new Supplier(
                    txtId.getText(),
                    txtName.getText(),
                    txtEmail.getText(),
                    txtCompany.getText()
            );

            boolean isUpdate = supplierBo.updateSupplier(supplier);

            if (isUpdate) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Supplier Update");
                alert.setContentText("Supplier updated successfully.");
                alert.showAndWait();
                clearFeild();
                txtId.setText(supplierBo.genarateId());
                tblSupllierTable.setItems(FXCollections.observableArrayList(supplierBo.getAllSupllier()));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Update Failed");
                alert.setContentText("Failed to update supplier. Please try again.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Information");
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
        }
    }

    public void deleteButtonOnAction(ActionEvent actionEvent) {
        if (!txtId.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting");
            alert.setContentText("Are you sure you want to delete this Supplier?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean isDeleted = supplierBo.deleteId(txtId.getText());
                if (isDeleted) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Suppplier Deleted");
                    alert2.setContentText("Supplier deleted successfully.");
                    alert2.showAndWait();
                    clearFeild();
                    txtId.setText(supplierBo.genarateId());
                    tblSupllierTable.setItems(FXCollections.observableArrayList(supplierBo.getAllSupllier()));
                } else {
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Deletion Failed");
                    alert2.setContentText("Failed to delete employee. Please try again.");
                    alert2.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Information");
            alert.setContentText("Please enter a valid Supplier ID.");
            alert.showAndWait();
        }
    }

    private void clearFeild(){
        txtId.clear();
        txtName.clear();
        txtEmail.clear();
        txtCompany.clear();
    }


    public void manageProductOnAction(ActionEvent actionEvent) {
    }

    public void productMenuOnAction(ActionEvent actionEvent) {
    }
}
