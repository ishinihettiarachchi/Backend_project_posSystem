package com.practicespringboot.pointofsale.service;

import com.practicespringboot.pointofsale.DTO.paginated.PaginatedOrderResponseOrderDetails;
import com.practicespringboot.pointofsale.DTO.request.RequestOrderSaveDTO;

public interface OrderService {
    String addOrder(RequestOrderSaveDTO requestOrderSaveDTO);

    PaginatedOrderResponseOrderDetails getAllOrderDetails(boolean state, int page, int size);
}
