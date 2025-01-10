package edu.icet.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "employee")
@Table(name ="employee")
public class EmployeeEntity {

    @Id
    private String id;
    private String name;
    private String address;
    private String email;
    private String password;
    private String role;

}
