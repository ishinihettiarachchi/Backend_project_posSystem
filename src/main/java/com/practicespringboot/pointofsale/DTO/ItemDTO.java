package com.practicespringboot.pointofsale.DTO;

import com.practicespringboot.pointofsale.entity.enums.MeasuringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ItemDTO {

    private int itemId;
    private String itemName;
    private MeasuringType measuringUnitType;
    private double balanceQty;
    private String supplierPrice;
    private String sellingPrice;
    private boolean activeState;


}
