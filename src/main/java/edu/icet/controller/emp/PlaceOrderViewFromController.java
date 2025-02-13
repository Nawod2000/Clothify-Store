package edu.icet.controller.emp;

import com.jfoenix.controls.JFXTextField;
import edu.icet.bo.BoFactory;
import edu.icet.bo.custom.impl.CustomerBoImpl;
import edu.icet.bo.custom.impl.OrderBoImpl;
import edu.icet.bo.custom.impl.OrderDetailsBoImpl;
import edu.icet.bo.custom.impl.ProductBoImpl;
import edu.icet.controller.EmployeeData;
import edu.icet.dto.*;
import edu.icet.util.BoType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class PlaceOrderViewFromController implements Initializable {


    public Label lblTime;
    public Label lblDate;
    public TextField txtOrderId;
    public ComboBox comCustId;
    public JFXTextField txtCustName;
    public JFXTextField txtEmail;
    public ComboBox comItemId;
    public JFXTextField txtAdress;
    public JFXTextField txtItemName;
    public JFXTextField txtCatagory;
    public JFXTextField txtPrice;
    public JFXTextField txtSize;
    public TextField txtQty;
    
    public TableColumn colItemCode;
    public TableColumn colDescrtiption;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotal;
    public TextField txtNetValue;
    public TableView tblOrderTable;

    private String empId;

    OrderBoImpl orderBoImpl = BoFactory.getInstance().getBo(BoType.ORDER);
    OrderDetailsBoImpl orderDetailsBoImpl = BoFactory.getInstance().getBo(BoType.ORDER_DETAIL);
    ProductBoImpl productBoImpl = BoFactory.getInstance().getBo(BoType.PRODUCT);
    CustomerBoImpl customerBoImpl = BoFactory.getInstance().getBo(BoType.CUSTOMER);



    ObservableList<Product> productList = FXCollections.observableArrayList();

    ObservableList<CartTable> orderList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAndTime();
        loadCustomerIDs();
        loadItemCodes();

        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescrtiption.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        comCustId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setCustomerDataFroLbl((String) newValue);
        });
        comItemId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setItemDataFroLbl((String) newValue);
        });

        txtOrderId.setText(orderDetailsBoImpl.generateOrderId());
        productList = orderDetailsBoImpl.getAllProducts();

    }

    private void setItemDataFroLbl(String ItemCode) {

        Product product = productBoImpl.getProductById(ItemCode);
        txtItemName.setText(product.getName());
        txtCatagory.setText(product.getCategory());
        txtPrice.setText(String.valueOf(product.getPrice()));
        txtSize.setText(product.getSize());

    }

    private void setCustomerDataFroLbl(String newValue) {

        Customer customer = customerBoImpl.getCustomerById(newValue);
        txtCustName.setText(customer.getName());
        txtEmail.setText(customer.getEmail());
        txtAdress.setText(customer.getAddress());
        empId = EmployeeData.getInstance().getId();
    }

    private void loadItemCodes() {

        ObservableList<Product> allSupplier = productBoImpl.getAllProducts();
        ObservableList ids = FXCollections.observableArrayList();

        allSupplier.forEach(supplier -> {
            ids.add(supplier.getId());

        });
        comItemId.setItems(ids);
    }

    private void loadCustomerIDs() {

        ObservableList<Customer> allSupplier = customerBoImpl.getAllCustomer();
        ObservableList ids = FXCollections.observableArrayList();

        allSupplier.forEach(supplier -> {
            ids.add(supplier.getId());

        });
        comCustId.setItems(ids);

    }

    public void singOutOnAction(ActionEvent actionEvent) {
    }

    public void manageProductOnAction(ActionEvent actionEvent) {
    }

    public void reportOnAction(ActionEvent actionEvent) {
    }

    public void manageCustomerOnAction(ActionEvent actionEvent) {
    }

    public void manageSupplierOnAction(ActionEvent actionEvent) {
    }

    public void manageOrderOnAction(ActionEvent actionEvent) {
    }

    public void addBtnOnAction(ActionEvent actionEvent) {
        String itemCode = (String) comItemId.getValue();
        String desc = txtItemName.getText();
        Integer qtyFroCus = Integer.parseInt(txtQty.getText());
        Double unitPrice = Double.valueOf(txtPrice.getText());
        Double total = qtyFroCus * unitPrice;
        CartTable cartTable = new CartTable(itemCode, desc, qtyFroCus, unitPrice, total);
        System.out.println(cartTable);
        orderList.add(cartTable);
        tblOrderTable.setItems(orderList);
        calcNetTotal();
        itemClear();
    }



    private void clearFields() {
        txtCustName.setText("");
        txtEmail.setText("");
        txtAdress.setText("");
        txtItemName.setText("");
        txtCatagory.setText("");
        txtPrice.setText("");
        txtSize.setText("");
        txtQty.setText("");
        txtNetValue.setText("");
        txtOrderId.setText("");
        tblOrderTable.setItems(null);
    }


    double total = 0;
    private void calcNetTotal() {
        total = 0;
        for (CartTable orderObj : orderList) {
            total += orderObj.getTotal();
        }
        txtNetValue.setText(String.valueOf(total) + "/=");
    }

    public void clearBtnOnAction(ActionEvent actionEvent) {
        clearFields();
        txtOrderId.setText(orderDetailsBoImpl.generateOrderId());
    }

    public void tableMouseClickAction(MouseEvent mouseEvent) {
    }

    public void placeOrderOnAction(ActionEvent actionEvent) throws ParseException {
        String id = txtOrderId.getText();
        String cusId =comCustId.getValue().toString();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date orderDate = format.parse(lblDate.getText());
        double amount = total;

        ObservableList<OrderDetails> orderDetailsObservableList =FXCollections.observableArrayList();

        for (CartTable cartTable : orderList) {
            String oId = txtOrderId.getText();
            String itemCode = cartTable.getItemCode();
            Integer qty = cartTable.getQty();
            String itemName = cartTable.getDesc();
            Double tamount = cartTable.getTotal();
            orderDetailsObservableList.add(new OrderDetails(null,oId,itemName,qty,tamount,itemCode));
        }

        orderDetailsBoImpl.saveOrderDetails(orderDetailsObservableList);

        Order order = new Order(id,cusId,orderDate,amount,empId);

        boolean isInsert = orderBoImpl.saveOrder(order);
        if (isInsert) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Order Placed");
            alert.setContentText("Order Placed Successfully..!");
            alert.showAndWait();
            clearFields();
            txtOrderId.setText(orderDetailsBoImpl.generateOrderId());
            productList = orderDetailsBoImpl.getAllProducts();
            orderList.clear();

        } else {
            new Alert(Alert.AlertType.ERROR, "Somthing Wrong..!!!").show();
        }
    }

    private void loadDateAndTime() {

        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));


        //Time
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime time = LocalTime.now();
            lblTime.setText(
                    time.getHour() + " : " + time.getMinute() + " : " + time.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void itemClear(){
        txtItemName.clear();
        txtCatagory.clear();
        txtPrice.clear();
        txtSize.clear();
    }
}
