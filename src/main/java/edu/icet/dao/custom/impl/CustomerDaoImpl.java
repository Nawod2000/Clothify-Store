package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.CustomerDao;
import edu.icet.entity.CustomerEntity;
import edu.icet.entity.EmployeeEntity;
import edu.icet.util.HibernateUtill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(CustomerEntity entity) {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(CustomerEntity entity) {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE customer SET name =:name,address =:address,email =:email WHERE id =:id");
        query.setParameter("name",entity.getName());
        query.setParameter("address",entity.getAddress());
        query.setParameter("email",entity.getEmail());
        query.setParameter("id",entity.getId());

        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();

        return i>0;
    }

    @Override
    public ObservableList<CustomerEntity> findAll() {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        List<CustomerEntity> list = session.createQuery("FROM customer").list();
        session.close();

        ObservableList<CustomerEntity> customerEntities = FXCollections.observableArrayList();

        list.forEach(custEntity -> {
            customerEntities.add(custEntity);
        });
        return customerEntities;
    }

    @Override
    public boolean delete(String s) {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM customer WHERE id=:id");
        query.setParameter("id",s);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    @Override
    public String getLastId() {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("SELECT id FROM customer ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();

        session.close();

        return id;
    }


    public CustomerEntity SearchByCustomerId(String id) {
        Session session = HibernateUtill.getSession();
        session.getTransaction();

        Query query = session.createQuery("FROM customer WHERE id=:id");
        query.setParameter("id",id);
        CustomerEntity customerEntity = (CustomerEntity) query.uniqueResult();
        session.close();
        return customerEntity;
    }
}
