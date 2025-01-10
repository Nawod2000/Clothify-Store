package edu.icet.bo.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.bo.custom.SupplierBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.custom.impl.SupplierDaoImpl;
import edu.icet.dto.Supplier;
import edu.icet.entity.SupplierEntity;
import edu.icet.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SupplierBoImpl implements SupplierBo {

    private final SupplierDaoImpl supplierDaoImpl = DaoFactory.getInstance().getDao(DaoType.SUPPLIER);

    public String genarateId(){
        String lastSupplierId = supplierDaoImpl.getLastId();
        if (lastSupplierId==null){
            return "S0001";
        }

        int number = Integer.parseInt(lastSupplierId.split("S")[1]);
        number++;
        return String.format("S%04d", number);
    }

    public boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public boolean saveSupplier(Supplier supplier) {

        SupplierEntity supplierEntity = new ObjectMapper().convertValue(supplier, SupplierEntity.class);
        return  supplierDaoImpl.save(supplierEntity);
    }

    public ObservableList<Supplier> getAllSupllier() {

        ObservableList<SupplierEntity> list = supplierDaoImpl.findAll();
        ObservableList<Supplier> suppliersList= FXCollections.observableArrayList();

        list.forEach(supplierEntity -> {
            suppliersList.add(new ObjectMapper().convertValue(supplierEntity, Supplier.class));
        });
        return suppliersList;
    }

    public boolean updateSupplier(Supplier supplier) {

        SupplierEntity supplierEntity = new ObjectMapper().convertValue(supplier, SupplierEntity.class);
        return supplierDaoImpl.update(supplierEntity);
    }

    public Supplier getSupplierById(String id) {
        SupplierEntity supplierEntity = supplierDaoImpl.searchById(id);
        return new ObjectMapper().convertValue(supplierEntity, Supplier.class);
    }

    public boolean deleteId(String text) {
        return supplierDaoImpl.delete(text);
    }
}
