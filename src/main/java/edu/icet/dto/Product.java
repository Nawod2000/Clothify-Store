package edu.icet.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    private String id;
    private String name;
    private String type;
    private String size;
    private String supId;
    private int qty;
    private Double price;
}