package com.practicespringboot.pointofsale.service.impl;

import com.practicespringboot.pointofsale.DTO.ItemDTO;
import com.practicespringboot.pointofsale.DTO.paginated.PaginatedResponseItemDTO;
import com.practicespringboot.pointofsale.DTO.request.RequestSaveItemDTO;
import com.practicespringboot.pointofsale.entity.Item;
import com.practicespringboot.pointofsale.exception.NotFoundException;
import com.practicespringboot.pointofsale.repo.ItemRepo;
import com.practicespringboot.pointofsale.service.ItemService;
import com.practicespringboot.pointofsale.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public String addItem(RequestSaveItemDTO requestSaveItemDTO) {
        Item item = modelMapper.map(requestSaveItemDTO,Item.class);
        if(itemRepo.existsById(item.getItemId())){
            itemRepo.save(item);
            return "Saved successfully";
        }else{
            throw new DuplicateKeyException("Item already exists.");
        }
    }

    @Override
    public List<ItemDTO> getItemByNameAndActiveState(String itemName) {
        boolean b = true;
        List<Item> items = itemRepo.findAllByItemNameEqualsAndActiveStateEquals( itemName,b);
       // List<ItemDTO> itemDTOS = modelMapper.map(items, new TypeToken<List<ItemDTO>>(){}.getType());
        if(items.size()>0){
            List<ItemDTO> itemDTOS = itemMapper.entityListToDtoList(items);

            return itemDTOS;
        }else{
            throw new NotFoundException("Not found");
        }

    }

    @Override
    public PaginatedResponseItemDTO getAllItemsActive(int page, int size, boolean activeState) {
        Page<Item> items = itemRepo.findAllByActiveStateEquals(activeState, PageRequest.of(page,size));
        if(items.getSize()<1){
            throw new NotFoundException("No Data According To Your Request");
        }
        return new PaginatedResponseItemDTO(
                itemMapper.pageToList(items),
                itemRepo.countAllByActiveStateEquals(activeState)
        );
    }
}
