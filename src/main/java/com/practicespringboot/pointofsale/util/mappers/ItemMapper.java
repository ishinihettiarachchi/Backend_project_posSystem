package com.practicespringboot.pointofsale.util.mappers;

import com.practicespringboot.pointofsale.DTO.ItemDTO;
import com.practicespringboot.pointofsale.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    List<ItemDTO> entityListToDtoList(List<Item> item);

    List<ItemDTO> pageToList(Page<Item> items);
}
