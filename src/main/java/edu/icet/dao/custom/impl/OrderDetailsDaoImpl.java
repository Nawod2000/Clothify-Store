package edu.icet.dao.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.custom.OrderDetailsDao;
import edu.icet.dto.OrderDetails;
import edu.icet.entity.OrderDetailsEntity;
import edu.icet.util.DaoType;
import edu.icet.util.HibernateUtill;
import javafx.collections.ObservableList;
import org.hibernate.Session;

public class OrderDetailsDaoImpl implements OrderDetailsDao {

    ProductDaoImpl productDao = DaoFactory.getInstance().getDao(DaoType.PRODUCT);

    @Override
    public boolean save(OrderDetailsEntity orderDetailsEntity) {
        return false;
    }

    @Override
    public boolean update(OrderDetailsEntity orderDetailsEntity) {
        return false;
    }

    @Override
    public ObservableList<OrderDetailsEntity> findAll() {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public String getLastId() {
        return null;
    }

    public boolean saveAll(ObservableList<OrderDetails> orderDetailsObservableList) {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();

        orderDetailsObservableList.forEach(orderDetails -> {
            OrderDetailsEntity orderDetailsEntity = new ObjectMapper().convertValue(orderDetails, OrderDetailsEntity.class);

            productDao.updateQty(orderDetailsEntity.getItemId(),orderDetailsEntity.getQty());
            session.persist(orderDetailsEntity);
        });
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
