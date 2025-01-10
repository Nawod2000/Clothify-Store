package edu.icet.dao;

import edu.icet.dao.custom.impl.EmployeeDaoImpl;
import edu.icet.dao.custom.impl.SupplierDaoImpl;
import edu.icet.util.DaoType;

public class DaoFactory {

    private static DaoFactory instance;
    private DaoFactory(){}
    public static DaoFactory getInstance() {
        return instance!=null?instance:(instance=new DaoFactory());
    }
    public <T extends SuperDao>T getDao(DaoType type){
        switch (type){
           // case CUSTOMER:return (T) new CustomerDaoImpl();
           // case ORDER:return (T)new OrderDaoImpl();
           // case PRODUCT:return (T)new ProductDaoImpl();
            case SUPPLIER:return (T)new SupplierDaoImpl();
            case EMPLOYEE :return (T)new EmployeeDaoImpl();
           // case ORDER_DETAIL:return (T) new OrderDetailDaoImpl();
        }
        return null;
    }
}
