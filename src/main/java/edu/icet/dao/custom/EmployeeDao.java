package edu.icet.dao.custom;

import edu.icet.dao.CrudDao;
import edu.icet.entity.EmployeeEntity;
import javafx.collections.ObservableList;

public interface EmployeeDao extends CrudDao<EmployeeEntity,String> {

    EmployeeEntity search(String s);
}
