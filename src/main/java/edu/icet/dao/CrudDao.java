package edu.icet.dao;

import javafx.beans.Observable;
import javafx.collections.ObservableList;

public interface CrudDao<T,S> extends SuperDao{
    T search(S s);

    ObservableList<T> searchAll();

    boolean insert(T t);

    boolean update(T t);

    boolean delete(T t);
}
