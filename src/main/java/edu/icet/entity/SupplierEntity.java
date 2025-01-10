package edu.icet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "supplier")
@Table(name = "supplier")
public class SupplierEntity {

    @Id
    private String id;
    private String supName;
    private String email;
    private String companyName;
}
