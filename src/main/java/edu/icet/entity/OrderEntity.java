package edu.icet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "order_table")
@Table(name = "order_table")
public class OrderEntity {
    @Id
    private String id;
    private String cusId;
    private Date date;
    private double amount;
    private String empId;
}
