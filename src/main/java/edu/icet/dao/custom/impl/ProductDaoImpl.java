package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.ProductDao;
import edu.icet.dto.OrderDetails;
import edu.icet.entity.ProductEntity;
import edu.icet.util.HibernateUtill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class ProductDaoImpl implements ProductDao {


    @Override
    public boolean save(ProductEntity productEntity) {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        session.persist(productEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(ProductEntity productEntity) {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE product SET name =:name,qty =:qty,size =:size,category= :category,price= :price,supId= :supId WHERE id =:id");
        query.setParameter("id",productEntity.getId());
        query.setParameter("name",productEntity.getName());
        query.setParameter("qty",productEntity.getQty());
        query.setParameter("size",productEntity.getSize());
        query.setParameter("category",productEntity.getCategory());
        query.setParameter("price",productEntity.getPrice());
        query.setParameter("supId",productEntity.getSupId());

        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }

    @Override
    public ObservableList<ProductEntity> findAll() {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        List<ProductEntity> list = session.createQuery("FROM product").list();
        session.close();

        ObservableList<ProductEntity> productEntities = FXCollections.observableArrayList();
        list.forEach(productEntity -> {
            productEntities.add(productEntity);
        });

        return productEntities;
    }

    @Override
    public boolean delete(String s) {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM product WHERE id= :id");
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

        Query query = session.createQuery("SELECT id FROM product ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();

        session.close();

        return id;
    }
    public ProductEntity search(String s) {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("FROM product WHERE id=:id");
        query.setParameter("id",s);
        ProductEntity product = (ProductEntity) query.uniqueResult();
        session.close();

        return product;
    }

    public ObservableList<String> searchAllIds() {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        List<String> list = session.createQuery("SELECT id FROM product").list();
        session.close();
        ObservableList<String> idList = FXCollections.observableArrayList();

        list.forEach(s -> {
            idList.add(s);
        });
        return idList;
    }

    public void updateQty(String itemId, int qty) {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE product SET qty=qty-:qty WHERE id=:id");
        query.setParameter("qty",qty);
        query.setParameter("id",itemId);

        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();

    }

    public boolean increseQty(ObservableList<OrderDetails> itemIdList) {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE product SET qty=qty+:qty WHERE id=:id");

        itemIdList.forEach(orderDetails -> {
            query.setParameter("qty",orderDetails.getQty());
            query.setParameter("id",orderDetails.getItemId());
            query.executeUpdate();
        });
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public ObservableList<ProductEntity> getProductBysID(String id) {
        Session session = HibernateUtill.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("FROM product WHERE supId=:id");
        query.setParameter("id",id);
        List<ProductEntity> list = query.list();

        ObservableList<ProductEntity> productEntities = FXCollections.observableArrayList();

        list.forEach(productEntity -> {
            productEntities.add(productEntity);
        });
        return productEntities;

    }

    public boolean updateQtyOfProduct(String id, int qty) {
        Session session = HibernateUtill.getSession();
        Query query = session.createQuery("UPDATE product SET qty=qty+:qty WHERE id=:id");
        query.setParameter("qty",qty);
        query.setParameter("id",id);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i>0;
    }



}
