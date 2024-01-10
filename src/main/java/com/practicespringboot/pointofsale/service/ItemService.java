package com.practicespringboot.pointofsale.service;

import com.practicespringboot.pointofsale.DTO.ItemDTO;
import com.practicespringboot.pointofsale.DTO.paginated.PaginatedResponseItemDTO;
import com.practicespringboot.pointofsale.DTO.request.RequestSaveItemDTO;

import java.util.List;

public interface ItemService {

    String addItem(RequestSaveItemDTO requestSaveItemDTO);

    List<ItemDTO> getItemByNameAndActiveState(String itemName);

    PaginatedResponseItemDTO getAllItemsActive(int page, int size, boolean activeState);
}
