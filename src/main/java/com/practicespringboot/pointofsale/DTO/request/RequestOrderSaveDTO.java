package com.practicespringboot.pointofsale.DTO.request;

import com.practicespringboot.pointofsale.entity.Customer;
import com.practicespringboot.pointofsale.entity.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderSaveDTO {

    private int customer;
    private Date date;
    private Double total;
    private List<RequestOrderDetailSaveDTO> orderDetails;
}
