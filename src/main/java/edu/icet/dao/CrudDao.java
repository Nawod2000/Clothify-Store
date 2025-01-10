package edu.icet.dao;

import javafx.beans.Observable;
import javafx.collections.ObservableList;

public interface CrudDao<T,S> extends SuperDao{

    boolean save(T t);
    boolean update(T t);
    ObservableList<T>findAll();
    boolean delete (S s);
    String getLastId();
}
