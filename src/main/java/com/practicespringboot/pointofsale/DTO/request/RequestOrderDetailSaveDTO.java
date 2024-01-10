package com.practicespringboot.pointofsale.DTO.request;

import com.practicespringboot.pointofsale.entity.Item;
import com.practicespringboot.pointofsale.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestOrderDetailSaveDTO {

    private String itemName;
    private String qty;
    private Double amount;
    private int orders;
    private int items;
}
