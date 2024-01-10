package com.practicespringboot.pointofsale.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseOrderDetailsDTO {
    //customer
    private String customerName;
    private String customerAddress;

    //order
    private Date date;
    private Double total;

}
