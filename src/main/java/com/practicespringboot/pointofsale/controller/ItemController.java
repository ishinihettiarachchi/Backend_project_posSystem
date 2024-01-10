package com.practicespringboot.pointofsale.controller;

import com.practicespringboot.pointofsale.DTO.ItemDTO;
import com.practicespringboot.pointofsale.DTO.paginated.PaginatedResponseItemDTO;
import com.practicespringboot.pointofsale.DTO.request.RequestSaveItemDTO;
import com.practicespringboot.pointofsale.service.ItemService;
import com.practicespringboot.pointofsale.service.impl.ItemServiceIMPL;
import com.practicespringboot.pointofsale.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping(path = "/save")
    public String saveItem(@RequestBody RequestSaveItemDTO requestSaveItemDTO){
        String add = itemService.addItem(requestSaveItemDTO);
        return "saved";
    }

    @GetMapping(
            path = "/get-by-name",
            params = "name "
    )
    public ResponseEntity<StandardResponse> getItemByNameActiveAndState(@RequestParam(name = "name") String itemName){
        List<ItemDTO> itemDTOS = itemService.getItemByNameAndActiveState(itemName);
//        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
//                new StandardResponse(201,"success",itemDTOS), HttpStatus.OK
//        );
//        return response;

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"success",itemDTOS),
                HttpStatus.OK
        );
    }

    @GetMapping(
            path = {"/get-all-item-by-status"},
            params = {"page","size","activeState"}
    )
    public ResponseEntity<StandardResponse> getAllItemsActive(
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size,
            @RequestParam(value = "activeState") boolean activeState
    ){
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllItemsActive(page,size,activeState);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"success",paginatedResponseItemDTO),
                HttpStatus.OK
        );
    }
}
