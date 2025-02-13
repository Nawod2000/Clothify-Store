package edu.icet.controller.emp;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.impl.ProductBoImpl;
import edu.icet.bo.custom.impl.SupplierBoImpl;
import edu.icet.controller.SceneSwitchController;
import edu.icet.dto.Product;
import edu.icet.util.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageProductViewFromController implements Initializable {


    public JFXTextField txtPduId;
    public JFXTextField txtPduName;
    public ComboBox comType;
    public JFXTextField txtPrice;
    public ComboBox comSubId;
    public ComboBox comSize;
    public JFXTextField txtQty;
    public AnchorPane mangePdctAnchorPane;
    public TableView tblProductTable;
    public TableColumn colId;
    public TableColumn colType;
    public TableColumn colName;
    public TableColumn colSize;
    public TableColumn colQty;
    public TableColumn colPrice;
    public TableColumn colSupId;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;

    ProductBoImpl productBoImpl = BoFactory.getInstance().getBo(BoType.PRODUCT);
    SceneSwitchController sceneSwitch = SceneSwitchController.getInstance();

    String category,supplierId,size;
    boolean isAction = true,isMouseClick,isPriceValid,isSupplierSelect,isCategorySelect,isSizeSelect;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resetComboBoxes();
        initializeComboBoxes();
        txtPduId.setText(productBoImpl.generateProductId());
        refreshProductTable();

        comSubId.setItems(new SupplierBoImpl().getAllIds());

        isSupplierSelect = true;
        isPriceValid = true;
        isMouseClick = false;
        isCategorySelect = false;
        isSizeSelect = false;
        txtPduId.setText(productBoImpl.generateProductId());
        btnUpdate.setVisible(true);
        btnDelete.setVisible(true);

        comType.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> {
            category = (String) newValue;
            isCategorySelect=true;
            System.out.println(category);
        });

        comSize.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> {
            size = (String) newValue;
            isSizeSelect=true;
            System.out.println(size);
        });
        comSubId.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> {
            isSupplierSelect = true;
            supplierId = (String) newValue;

        });
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("category"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supId"));

        tblProductTable.setItems(productBoImpl.getAllProducts());
    }

    private ObservableList<String> setCategoryComboBoxValues(){
        ObservableList<String> option= FXCollections.observableArrayList("Gents","Ladies","Kids");
        return option;
    }

    private ObservableList<String> setSizeComboBoxValues(){
        ObservableList<String> list = FXCollections.observableArrayList("XS", "S", "M", "L", "XL", "XXL");
        return list;
    }

    private void getSupIds(){
        productBoImpl.getSupIds();
    }

    private void clearFields() {
        txtPduName.setText("");
        txtQty.setText("");
        txtPrice.setText("");
        comSize.setValue(null);
        comSubId.setValue(null);
        comType.setValue(null);
    }

    private void initializeComboBoxes() {
        comType.setItems(setCategoryComboBoxValues());
        comSize.setItems(setSizeComboBoxValues());
        comSubId.setItems(FXCollections.observableArrayList(supplierId));
    }

    private void resetComboBoxes() {
        comType.getSelectionModel().clearSelection();
        comSize.getSelectionModel().clearSelection();
        comSubId.getSelectionModel().clearSelection();
    }

    private void refreshProductTable() {
        tblProductTable.setItems(FXCollections.observableArrayList(productBoImpl.getAllProducts()));
    }

    public void addBtnOnAction(ActionEvent actionEvent) {
        if (isSupplierSelect && isPriceValid && !txtPduName.getText().equals("") && !txtQty.getText().equals("")) {
            Product product = new Product(
                    txtPduId.getText(),
                    txtPduName.getText(),
                    Integer.parseInt(txtQty.getText()),
                    category,
                    size,
                    Double.parseDouble(txtPrice.getText()),
                    supplierId);

            boolean isInsert = productBoImpl.insertProduct(product);

            if (isInsert) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Product Added");
                alert.setContentText("Product Added successfully");
                alert.showAndWait();
                clearFields();
                txtPduId.setText(productBoImpl.generateProductId());
                tblProductTable.setItems(productBoImpl.getAllProducts());
                isSupplierSelect = false;
            }
        }
    }

    public void updateBtnOnAction(ActionEvent actionEvent) {
        if (isSupplierSelect && isCategorySelect && isPriceValid && isMouseClick && !txtPduName.getText().equals("") && !txtQty.getText().equals("")) {
            Product product = new Product(
                    txtPduId.getText(),
                    txtPduName.getText(),
                    Integer.parseInt(txtQty.getText()),
                    category,
                    size,
                    Double.parseDouble(txtPrice.getText()),
                    supplierId);
            boolean isUpdate = productBoImpl.updateProduct(product);

            if (isUpdate){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Product Updated");
                alert.setContentText("Product Updated successfully");
                alert.showAndWait();
                clearFields();
                txtPduId.setText(productBoImpl.generateProductId());
                tblProductTable.setItems(productBoImpl.getAllProducts());
                isSupplierSelect = false;
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Something Missing").show();
        }
    }

    public void deleteBtnOnAction(ActionEvent actionEvent) {
        if (isMouseClick) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Deleting");
            alert.setContentText("Are you sure you want to delete this Product?");

            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean isDelete = productBoImpl.deleteProductById(txtPduId.getText());

                if (isDelete) {
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Product Deleted");
                    alert1.setContentText("Product Deleted Successfully");
                    alert1.showAndWait();
                    clearFields();
                    txtPduId.setText(productBoImpl.generateProductId());
                    tblProductTable.setItems(productBoImpl.getAllProducts());
                    isSupplierSelect = false;

                } else {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Deletion Failed");
                    alert1.setContentText("Failed to delete the product.");
                    alert1.showAndWait();
                }
            }
        }
    }


    public void productMenuOnAction(ActionEvent actionEvent) {
    }


    public void manageProductOnAction(ActionEvent actionEvent) {
    }

    public void rectangleMouseClick(MouseEvent mouseEvent) {
        btnAdd.setDisable(false);
        clearFields();
        txtPduId.setText(productBoImpl.generateProductId());
    }

    public void tableMouseClickAction(MouseEvent mouseEvent) {
        int index = tblProductTable.getSelectionModel().getSelectedIndex();


        if(index < 0){
            return;
        }
        String id = colId.getCellData(index).toString();

        if (isAction){
            isPriceValid = true;
            Product product = productBoImpl.getProductById(id);
            txtPduId.setText(product.getId());
            txtPduName.setText(product.getName());
            txtPrice.setText(Double.toString(product.getPrice()));
            txtQty.setText(Integer.toString(product.getQty()));
            comType.getSelectionModel().select(product.getCategory());
            comSize.getSelectionModel().select(product.getSize());
            comSubId.getSelectionModel().select(product.getSupId());
            btnAdd.setDisable(true);
            byte[] data;

            if (!product.getId().equals("")){
                isMouseClick = true;
            }
        }
    }
}
