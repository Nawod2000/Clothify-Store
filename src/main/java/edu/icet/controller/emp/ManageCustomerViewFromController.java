package edu.icet.controller.emp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.impl.CustomerBoImpl;
import edu.icet.controller.SceneSwitchController;
import edu.icet.dto.Customer;
import edu.icet.dto.Employee;
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

public class ManageCustomerViewFromController implements Initializable {

    public AnchorPane custManage;
    public Label lblTime;
    public Label lblDate;
    public TextField txtId;
    public JFXTextField txtEmail;
    public TableColumn colCustId;
    public TableColumn colCustEmail;
    public TableColumn colCustName;
    public TableColumn colCustAddress;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXButton btnAdd;
    public TableView tblCustomerTable;

    private CustomerBoImpl customerBo = BoFactory.getInstance().getBo(BoType.CUSTOMER);

    boolean isAction = true,isEmailValid,isMouseClick;

    SceneSwitchController sceneswitch = SceneSwitchController.getInstance();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCustId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCustEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colCustAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        tblCustomerTable.setItems(customerBo.getAllCustomer());

        txtId.setText(customerBo.generateId());
    }

    public void addButtonOnAction(ActionEvent actionEvent) {
        Customer customer = new Customer(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtEmail.getText()
        );
        if (!txtName.getText().isEmpty() && !txtAddress.getText().isEmpty() && customerBo.isValidEmail(txtEmail.getText())){

            boolean isSave = customerBo.save(customer);

            if (isSave){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Employee Added");
                alert.setContentText("Employee added successfully!");
                alert.showAndWait();
                tblCustomerTable.setItems(FXCollections.observableArrayList(customerBo.getAllCustomer()));
                txtClear();
                txtId.setText(customerBo.generateId());
            }else {
                new Alert(Alert.AlertType.ERROR,"Failed to add employee. Please try again.").show();
            }

        }else {
            new Alert(Alert.AlertType.ERROR,"Please fill in valid data with fields").show();
        }
    }

    private void txtClear() {
        txtAddress.setText("");
        txtEmail.setText("");
        txtName.setText("");
    }

    public void updateButtonOnAction(ActionEvent actionEvent) {
        if (!txtName.getText().isEmpty() && !txtAddress.getText().isEmpty() && !txtEmail.getText().isEmpty()){
            Customer customer = new Customer(
                    txtId.getId(),
                    txtName.getText(),
                    txtAddress.getText(),
                    txtEmail.getText()
            );

            boolean isUpdate = customerBo.update(customer);

            if (isUpdate){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Employee Update");
                alert.setContentText("Employee Update Successfully.");
                alert.showAndWait();
                txtClear();
                txtId.setText(customerBo.generateId());
                tblCustomerTable.setItems(FXCollections.observableArrayList(customerBo.getAllCustomer()));

            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Employee Failed");
                alert.setContentText("Failed to update employee. Please try again.");
                alert.showAndWait();

            }

        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Information");
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
        }
    }

    public void deleteButtonOnAction(ActionEvent actionEvent) {
        if (!txtId.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting");
            alert.setContentText("Are you sure you want to delete this Employee?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK){
                boolean isDelete = customerBo.delete(txtId.getText());
                if (isDelete){
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Employee Deleted");
                    alert2.setContentText("Employee deleted successfully.");
                    alert2.showAndWait();
                    txtClear();
                    txtId.setText(customerBo.generateId());
                    tblCustomerTable.setItems(FXCollections.observableArrayList(customerBo.getAllCustomer()));
                } else {
                    Alert alert2 = new Alert(Alert.AlertType.ERROR);
                    alert2.setTitle("Deletion Failed");
                    alert2.setContentText("Failed to delete employee. Please try again.");
                    alert2.showAndWait();
                }
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing Information");
            alert.setContentText("Please enter a valid Employee ID.");
            alert.showAndWait();
        }
    }

    public void singOutOnAction(ActionEvent actionEvent) {
    }

    public void rectangleMouseClick(MouseEvent mouseEvent) {
        btnAdd.setDisable(false);
        txtClear();
        txtId.setText(customerBo.generateId());
    }

    public void tableMouseClickAction(MouseEvent mouseEvent) {
        int index = tblCustomerTable.getSelectionModel().getSelectedIndex();

        if (index < 0){
            return;
        }

        String id = colCustId.getCellData(index).toString();
        if (isAction){
            isEmailValid = true;
            Customer customerById = customerBo.getCustomerById(id);
            txtId.setText(customerById.getId());
            txtName.setText(customerById.getName());
            txtEmail.setText(customerById.getEmail());
            txtAddress.setText(customerById.getAddress());
            btnAdd.setDisable(true);
            byte [] data;

            if (!customerById.getId().equals("")){
                isMouseClick = true;
            }
        }
    }


    public void manageProductOnAction(ActionEvent actionEvent) throws IOException {
        sceneswitch.switchScene(custManage, "view/emp/product-manage-view-form.fxml");
    }

    public void reportOnAction(ActionEvent actionEvent) {

    }

    public void manageSupplierOnAction(ActionEvent actionEvent) {
    }

    public void manageOrderOnAction(ActionEvent actionEvent) {
    }


    public void manageCustomerOnAction(ActionEvent actionEvent) {
    }
}
