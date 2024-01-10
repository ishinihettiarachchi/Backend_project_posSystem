package com.practicespringboot.pointofsale.DTO.QueryInterfaces;

import java.util.Date;


public interface OrderDetailsInterface {
    //customer
    String getCustomerName();
    String getCustomerAddress();

    //order
    Date getDate();
    Double getTotal();

}
