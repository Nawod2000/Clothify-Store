package edu.icet.controller.admin;


import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.custom.impl.ProductBoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageProductViewFromController implements Initializable {


    public JFXTextField txtPduId;
    public JFXTextField txtPduName;
    public ComboBox comType;
    public JFXTextField txtPrice;
    public ComboBox comSubId;
    public ComboBox comSize;
    public JFXTextField txtQty;

    ProductBoImpl productBo = new ProductBoImpl();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCategoryComboBoxValues();
        setSizeComboBoxValues();
    }

    private void setCategoryComboBoxValues(){
        ObservableList<String> option= FXCollections.observableArrayList("Gents","Ladies","Kids");
        comType.setItems(option);
    }

    private void setSizeComboBoxValues(){
        ObservableList<String> list = FXCollections.observableArrayList("XS", "S", "M", "L", "XL", "XXL");
        comSize.setValue(list);
    }
//
//    private ObservableList<String> sizeLoad(){
//        ObservableList<String> list = FXCollections.observableArrayList();
//        list.add("XS");
//        list.add("S");
//        list.add("M");
//        list.add("L");
//        list.add("XL");
//        list.add("XXL");
//        comSubId.setValue(list);
//        return list;
//    }
//
//    private void clearFields() {
//        txtPduId.setText("");
//        txtPduName.setText("");
//        comSize.setValue("");
//        comType.setValue("");
//        comSubId.setValue("");
//        txtQty.setText("");
//        txtPrice.setText("");
//    }
//
//    private void initializeComboBoxes() {
//        categoryCombo.setItems(categoryLoad());
//        sizeCombo.setItems(sizeLoad());
//        supIdCombo.setItems(FXCollections.observableArrayList(supplierId));
//    }
//
//    private void resetComboBoxes() {
//        categoryCombo.getSelectionModel().clearSelection();
//        sizeCombo.getSelectionModel().clearSelection();
//        supIdCombo.getSelectionModel().clearSelection();
//    }
//
//    private void refreshProductTable() {
//        itemTable.setItems(FXCollections.observableArrayList(productBoImpl.getAllProducts()));
//    }

    public void addBtnOnAction(ActionEvent actionEvent) {
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {
    }

    public void dashBroadOnAction(ActionEvent actionEvent) {
    }

    public void supplierViewOnAction(ActionEvent actionEvent) {
    }

    public void productMenuOnAction(ActionEvent actionEvent) {
    }

    public void customerViewOnAction(ActionEvent actionEvent) {
    }

    public void orderViewOnAction(ActionEvent actionEvent) {
    }

    public void manageProductOnAction(ActionEvent actionEvent) {
    }

    public void manageEmployeeOnAction(ActionEvent actionEvent) {
    }

}
