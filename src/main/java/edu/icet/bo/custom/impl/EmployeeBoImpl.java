package edu.icet.bo.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.bo.custom.EmployeeBo;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.custom.impl.EmployeeDaoImpl;
import edu.icet.dto.Employee;
import edu.icet.entity.EmployeeEntity;
import edu.icet.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class EmployeeBoImpl implements EmployeeBo {

    private final EmployeeDaoImpl employeeDao= DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);

    public String localDate(){
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(f.format(date));
        return (f.format(date));
    }

    public String passwordEncrypt(String password){
        return new String(Base64.getEncoder().encode(password.getBytes(StandardCharsets.UTF_8)));
    }
    public boolean save(Employee employee){
        return employeeDao.save(new ObjectMapper().convertValue(employee, EmployeeEntity.class));

    }
    public boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }


    public String generateId(){
        if (employeeDao.getLastId()==null){
            return "E0001";
        }
        int num = Integer.parseInt(employeeDao.getLastId().split("E")[1]);
        num++;
        return String.format("E%04d",num);
    }

    public ObservableList<Employee> getAllEmployees() {

        ObservableList<EmployeeEntity> list = employeeDao.findAll();
        ObservableList<Employee> userList = FXCollections.observableArrayList();

        list.forEach(userEntity -> {
            userList.add(new ObjectMapper().convertValue(userEntity, Employee.class));
        });
        return userList;
    }

    public Employee getEmployeeById(String id) {
        EmployeeEntity employeeEntity = employeeDao.SearchByEmployeeId(id);
        return new ObjectMapper().convertValue(employeeEntity, Employee.class);
    }

    public boolean update(Employee employee){
        EmployeeEntity employeeEntity = new ObjectMapper().convertValue(employee, EmployeeEntity.class);
        return employeeDao.update(employeeEntity);
    }


    public boolean delete(String text) {
        return employeeDao.delete(text);
    }


    public EmployeeEntity getEmployeeByEmail(String text) {
        return employeeDao.search(text);
    }

    public String passwordDecrypt(String password) {
        return new String(Base64.getDecoder().decode(password));
    }
}
