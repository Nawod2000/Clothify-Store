package edu.icet.dto;


import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

    private String id;
    private String name;
    private int qty;
    private String category;
    private String size;
    private double price;
    private String supId;
}