package edu.icet.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "product")
@Table(name = "product")
public class ProductEntity {

    @Id
    private String id;
    private String name;
    private String type;
    private String size;
    private String supId;
    private int qty;
    private Double price;
}