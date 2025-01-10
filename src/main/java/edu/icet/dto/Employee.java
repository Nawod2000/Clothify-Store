package edu.icet.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dao.DaoFactory;
import edu.icet.dao.custom.impl.EmployeeDaoImpl;
import edu.icet.entity.EmployeeEntity;
import edu.icet.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private String id;
    private String name;
    private String address;
    private String email;
    private String password;
    private String role;


}
