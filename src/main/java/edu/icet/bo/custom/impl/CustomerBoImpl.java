package edu.icet.bo.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.bo.custom.CustomerBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.custom.impl.CustomerDaoImpl;
import edu.icet.dto.Customer;
import edu.icet.dto.Employee;
import edu.icet.entity.CustomerEntity;
import edu.icet.entity.EmployeeEntity;
import edu.icet.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerBoImpl implements CustomerBo {

    private CustomerDaoImpl customerDao = DaoFactory.getInstance().getDao(DaoType.CUSTOMER);




    public boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }


    public String generateId(){
        String lastProductId = customerDao.getLastId();
        if (lastProductId==null){
            return "C0001";
        }

        int number = Integer.parseInt(lastProductId.split("C")[1]);
        number++;
        return String.format("C%04d", number);
    }

    public boolean save(Customer customer) {
        return customerDao.save(new ObjectMapper().convertValue(customer, CustomerEntity.class));
    }


    public ObservableList<Customer> getAllCustomer() {

        ObservableList<CustomerEntity> list = customerDao.findAll();
        ObservableList<Customer> custList = FXCollections.observableArrayList();

        list.forEach(custEntity -> {
            custList.add(new ObjectMapper().convertValue(custEntity, Customer.class));
        });
        return custList;
    }

    public boolean update(Customer customer) {
        CustomerEntity customerEntity = new ObjectMapper().convertValue(customer, CustomerEntity.class);
        return customerDao.update(customerEntity);
    }

    public boolean delete(String text) {
        return customerDao.delete(text);
    }

    public Customer getCustomerById(String id) {
        CustomerEntity customerEntity = customerDao.SearchByCustomerId(id);
        return new ObjectMapper().convertValue(customerEntity, Customer.class);
    }
}
