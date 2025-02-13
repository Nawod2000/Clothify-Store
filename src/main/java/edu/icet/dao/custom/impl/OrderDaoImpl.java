package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.OrderDao;
import edu.icet.entity.OrderEntity;
import edu.icet.util.HibernateUtill;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderEntity orderEntity) {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        session.persist((OrderEntity)orderEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(OrderEntity orderEntity) {
        return false;
    }

    @Override
    public ObservableList<OrderEntity> findAll() {
        return null;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }

    @Override
    public String getLastId() {
        return null;
    }

    public String getLatestOrderId() {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("SELECT id FROM order_table ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();

        session.close();

        return id;
    }
}
