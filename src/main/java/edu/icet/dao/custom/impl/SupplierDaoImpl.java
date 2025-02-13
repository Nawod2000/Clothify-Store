package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.SupplierDao;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.HibernateUtill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

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
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE supplier SET supName =:supName,companyName =:companyName,email =:email WHERE id =:id");
        query.setParameter("supName",supplierEntity.getSupName());
        query.setParameter("companyName",supplierEntity.getCompanyName());
        query.setParameter("email",supplierEntity.getEmail());
        query.setParameter("id",supplierEntity.getId());

        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();

        return i>0;
    }

    @Override
    public ObservableList<SupplierEntity> findAll() {
        Session session = HibernateUtill.getSession();
        Transaction transaction = session.getTransaction();

        List<SupplierEntity> supplierList = session.createQuery("FROM supplier").list();
        ObservableList<SupplierEntity> list = FXCollections.observableArrayList();
        session.close();

        supplierList.forEach(supplierEntity -> {
            list.add(supplierEntity);
        });
        return list;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM supplier WHERE id=:id");
        query.setParameter("id",id);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    @Override
    public String getLastId() {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("SELECT id FROM supplier ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    public SupplierEntity searchById(String id) {

        Session session = HibernateUtill.getSession();
        session.getTransaction();

        Query query = session.createQuery("FROM supplier WHERE id=:id");
        query.setParameter("id",id);
        SupplierEntity supplierEntity = (SupplierEntity) query.uniqueResult();
        session.close();
        return supplierEntity;

    }

    public ObservableList<String> getAllIds() {
        Session session = HibernateUtill.getSession();
        session.getTransaction();
        List<String> list = session.createQuery("SELECT id FROM supplier").list();
        session.close();

        ObservableList<String> observableList = FXCollections.observableArrayList();
        list.forEach(s -> {
            observableList.add(s);
        });

        return observableList;

    }
}
