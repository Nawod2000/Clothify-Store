package edu.icet.controller.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.impl.EmployeeBoImpl;
import edu.icet.controller.SceneSwitchController;
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
import java.util.Random;
import java.util.ResourceBundle;

public class EmpManageViewFromController implements Initializable {
    public JFXTextField txtEmail;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public Label lblTime;
    public Label lblDate;
    public TableView tblEmployeeTable;
    public TableColumn colEmpId;
    public TableColumn colEmpName;
    public TableColumn colEmpEmail;
    public TableColumn colEmpAddress;
    public TextField txtId;
    public JFXButton btnAdd;
    public AnchorPane empManage;

    private EmployeeBoImpl employeeBo = BoFactory.getInstance().getBo(BoType.EMPLOYEE);

    SceneSwitchController sceneswitch = SceneSwitchController.getInstance();

    boolean isAction = true,isEmailValid,isMouseClick;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmpEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmpAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        tblEmployeeTable.setItems(employeeBo.getAllEmployees());


        lblDate.setText(employeeBo.localDate());
        //lblTime.setText(employeeBo.localTime());

        txtId.setText(employeeBo.generateId());
        txtId.setDisable(true);

    }


    public void dashBroadOnAction(ActionEvent actionEvent) throws IOException {
        sceneswitch.switchScene(empManage, "admin/admin-dashboard-view-form.fxml");
    }

    public void manageEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        sceneswitch.switchScene(empManage, "admin/emp-manage-view-form.fxml");
    }

    public void manageProductsOnAction(ActionEvent actionEvent) {

    }

    public void ordersViewOnAction(ActionEvent actionEvent) {
    }

    public void customerViewOnAction(ActionEvent actionEvent) {
    }

    public void addButtonOnAction(ActionEvent actionEvent) {

        Random random = new Random();
        int digit = random.nextInt(90000000) + 10000000; // genarate 8 digit
        String encrypt = Integer.toString(digit);
        String password = employeeBo.passwordEncrypt(encrypt);

        Employee employee = new Employee(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtEmail.getText(),
                password,
                "employee"
        );
       if (!txtName.getText().isEmpty() && !txtAddress.getText().isEmpty() && employeeBo.isValidEmail(txtEmail.getText())){

           boolean isSave = employeeBo.save(employee);

           if (isSave){
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Employee Added");
               alert.setContentText("Employee added successfully!");
               alert.showAndWait();
               tblEmployeeTable.setItems(FXCollections.observableArrayList(employeeBo.getAllEmployees()));
               txtClear();
               txtId.setText(employeeBo.generateId());
           }else {
               new Alert(Alert.AlertType.ERROR,"Failed to add employee. Please try again.").show();
           }

       }else {
           new Alert(Alert.AlertType.ERROR,"Please fill in valid data with fields").show();
       }
    }

    public void updateButtonOnAction(ActionEvent actionEvent) {
        if (!txtName.getText().isEmpty() && !txtAddress.getText().isEmpty() && !txtEmail.getText().isEmpty()){
            Employee employee = new Employee(
                    txtId.getId(),
                    txtName.getText(),
                    txtAddress.getText(),
                    txtEmail.getText(),
                    null,
                    null
            );

            boolean isUpdate = employeeBo.update(employee);

            if (isUpdate){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Employee Update");
                alert.setContentText("Employee Update Successfully.");
                alert.showAndWait();
                txtClear();
                txtId.setText(employeeBo.generateId());
                tblEmployeeTable.setItems(FXCollections.observableArrayList(employeeBo.getAllEmployees()));

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
                boolean isDelete = employeeBo.delete(txtId.getText());
                if (isDelete){
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Employee Deleted");
                    alert2.setContentText("Employee deleted successfully.");
                    alert2.showAndWait();
                    txtClear();
                    txtId.setText(employeeBo.generateId());
                    tblEmployeeTable.setItems(FXCollections.observableArrayList(employeeBo.getAllEmployees()));
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

    public void txtClear(){
        txtAddress.clear();
        txtEmail.clear();
        txtName.clear();
    }

    public void tableMouseClickAction(MouseEvent mouseEvent) {
        int index = tblEmployeeTable.getSelectionModel().getSelectedIndex();

        if (index < 0){
            return;
        }

        String id = colEmpId.getCellData(index).toString();
        if (isAction){
            isEmailValid = true;
            Employee employeeById = employeeBo.getEmployeeById(id);
            txtId.setText(employeeById.getId());
            txtName.setText(employeeById.getName());
            txtEmail.setText(employeeById.getEmail());
            txtAddress.setText(employeeById.getAddress());
            btnAdd.setDisable(true);
            byte [] data;

            if (!employeeById.getId().equals("")){
                isMouseClick = true;
            }
        }
    }

    public void rectangleMouseClick(MouseEvent mouseEvent) {
        btnAdd.setDisable(false);
        txtClear();
        txtId.setText(employeeBo.generateId());
    }
}
