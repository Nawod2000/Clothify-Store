package edu.icet.bo.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.bo.custom.OrderDetailsBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.custom.impl.OrderDaoImpl;
import edu.icet.dao.custom.impl.OrderDetailsDaoImpl;
import edu.icet.dao.custom.impl.ProductDaoImpl;
import edu.icet.dto.OrderDetails;
import edu.icet.dto.Product;
import edu.icet.entity.ProductEntity;
import edu.icet.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OrderDetailsBoImpl implements OrderDetailsBo {

    OrderDetailsDaoImpl orderDetailsDao = DaoFactory.getInstance().getDao(DaoType.ORDER_DETAIL);
    ProductDaoImpl productDao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);
    OrderDaoImpl orderDao = DaoFactory.getInstance().getDao(DaoType.ORDER);

    public boolean saveOrderDetails(ObservableList<OrderDetails> orderDetailsObservableList) {
        return orderDetailsDao.saveAll(orderDetailsObservableList);
    }

    public String generateOrderId() {
        String id = new OrderDaoImpl().getLatestOrderId();

        if (id==null){
            return "O0001";
        }
        int number = Integer.parseInt(id.split("O")[1]);
        number++;
        return String.format("O%04d", number);
    }

    public ObservableList<Product> getAllProducts() {
        ObservableList<ProductEntity> list = productDao.findAll();
        ObservableList<Product> products = FXCollections.observableArrayList();

        list.forEach(productEntity -> {
            products.add(new ObjectMapper().convertValue(productEntity, Product.class));
        });
        return products;
    }
}
