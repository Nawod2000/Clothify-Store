package edu.icet.dao.custom.impl;

import edu.icet.dao.custom.EmployeeDao;
import edu.icet.entity.EmployeeEntity;
import javafx.collections.ObservableList;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public EmployeeEntity search(String s) {
        return null;
    }

    @Override
    public ObservableList<EmployeeEntity> searchAll() {
        return null;
    }

    @Override
    public boolean insert(EmployeeEntity employeeEntity) {
        return false;
    }

    @Override
    public boolean update(EmployeeEntity employeeEntity) {
        return false;
    }

    @Override
    public boolean delete(EmployeeEntity employeeEntity) {
        return false;
    }
}
