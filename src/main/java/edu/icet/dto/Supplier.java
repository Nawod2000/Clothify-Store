package edu.icet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    private String id;
    private String supName;
    private String email;
    private String companyName;

}
