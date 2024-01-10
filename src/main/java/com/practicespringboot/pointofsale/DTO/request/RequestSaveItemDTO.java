package com.practicespringboot.pointofsale.DTO.request;

import com.practicespringboot.pointofsale.entity.enums.MeasuringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestSaveItemDTO {
    private String itemName;
    private MeasuringType measuringUnitType;
    private double balanceQty;
    private String supplierPrice;
    private String sellingPrice;
}
