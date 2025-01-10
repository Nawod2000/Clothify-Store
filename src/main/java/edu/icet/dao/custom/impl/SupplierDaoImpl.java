package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.SupplierDao;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.HibernateUtill;
import javafx.collections.ObservableList;
import org.hibernate.Session;

public class SupplierDaoImpl implements SupplierDao {
    @Override
    public boolean save(SupplierEntity supplierEntity) {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();

        session.persist(supplierEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(SupplierEntity supplierEntity) {
        return false;
    }

    @Override
    public ObservableList<SupplierEntity> findAll() {
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
}
