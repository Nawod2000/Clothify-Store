package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.EmployeeDao;
import edu.icet.entity.EmployeeEntity;
import edu.icet.util.HibernateUtill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public EmployeeEntity search(String s) {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("FROM employee WHERE email=:email");
        query.setParameter("email",s);
        EmployeeEntity employeeEntity = (EmployeeEntity) query.uniqueResult();
        session.close();
        return employeeEntity;
    }

    @Override
    public boolean save(EmployeeEntity employeeEntity) {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        session.persist(employeeEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(EmployeeEntity employeeEntity) {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE employee SET name =:name,address =:address,email =:email WHERE id =:id");
        query.setParameter("name",employeeEntity.getName());
        query.setParameter("address",employeeEntity.getAddress());
        query.setParameter("email",employeeEntity.getEmail());
        query.setParameter("id",employeeEntity.getId());

        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();

        return i>0;
    }

    @Override
    public ObservableList<EmployeeEntity> findAll() {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        List<EmployeeEntity> list = session.createQuery("FROM employee").list();
        session.close();

        ObservableList<EmployeeEntity> employeeEntityList = FXCollections.observableArrayList();

        list.forEach(userEntity -> {
            employeeEntityList.add(userEntity);
        });
        return employeeEntityList;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM employee WHERE id=:id");
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

        Query query = session.createQuery("SELECT id FROM employee ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    public EmployeeEntity SearchByEmployeeId(String id) {
        Session session = HibernateUtill.getSession();
        session.getTransaction();

        Query query = session.createQuery("FROM employee WHERE id=:id");
        query.setParameter("id",id);
        EmployeeEntity employeeEntity = (EmployeeEntity) query.uniqueResult();
        session.close();
        return employeeEntity;
    }

}













