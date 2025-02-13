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
    private int qty;
    private String category;
    private String size;
    @Column(name = "price")
    private double price;
    @Column(name = "supId")
    private String supId;
}