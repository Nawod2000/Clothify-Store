package edu.icet.bo.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.bo.custom.SupplierBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.custom.impl.SupplierDaoImpl;
import edu.icet.dto.Supplier;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.DaoType;

public class SupplierBoImpl implements SupplierBo {

    private final SupplierDaoImpl supplierDaoImpl = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);

    public String genarateId(){

        String lastSupId = supplierDaoImpl.getLastId();
        if (lastSupId == null){
            return "S0001";
        }

        int number = Integer.parseInt(lastSupId.split("S")[1]);
        number++;
        return String.format("S%04d",number);
    }

    public boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public boolean saveSupplier(Supplier supplier) {

        SupplierEntity supplierEntity = new ObjectMapper().convertValue(supplier, SupplierEntity.class);
        return  supplierDaoImpl.save(supplierEntity);
    }
}
