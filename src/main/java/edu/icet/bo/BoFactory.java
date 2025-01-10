package edu.icet.bo;

import edu.icet.bo.custom.impl.EmployeeBoImpl;
import edu.icet.bo.custom.impl.ProductBoImpl;
import edu.icet.bo.custom.impl.SupplierBoImpl;
import edu.icet.util.BoType;

public class BoFactory {
    private static BoFactory instance;
    private BoFactory(){}
    public static BoFactory getInstance(){
        return instance!=null?instance:(instance=new BoFactory());
    }
    public <T extends SuperBo>T getBo(BoType type){
        switch (type){
           // case CUSTOMER:return (T) new CustomerBoImpl();
            case EMPLOYEE:return (T) new EmployeeBoImpl();
           // case ORDER:return (T)new OrderBoImpl();
            case PRODUCT:return (T)new ProductBoImpl();
            case SUPPLIER:return (T)new SupplierBoImpl();
          //  case ORDER_DETAIL:return (T)new OrderDetailBoImpl();
        }
        return null;
    }
}
