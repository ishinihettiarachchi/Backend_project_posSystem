package com.practicespringboot.pointofsale.controller;

import com.practicespringboot.pointofsale.DTO.paginated.PaginatedOrderResponseOrderDetails;
import com.practicespringboot.pointofsale.DTO.request.RequestOrderSaveDTO;
import com.practicespringboot.pointofsale.DTO.request.RequestSaveItemDTO;
import com.practicespringboot.pointofsale.service.OrderService;
import com.practicespringboot.pointofsale.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;

@RestController
@RequestMapping(path = "api/v1/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

     @PostMapping(path = "/save")
    public RequestEntity<StandardResponse> saveItem(@RequestBody RequestOrderSaveDTO requestOrderSaveDTO){
         String id= orderService.addOrder(requestOrderSaveDTO);
//         return new RequestEntity<StandardResponse>(
//                 new StandardResponse(201,"item successsfully saved",id ),
//                 HttpStatus.CREATED
//         );
         return null;
     }

     @GetMapping(
             params = {"stateType", "page","size"},
             path = {"/get-order-default"}
     )
    public ResponseEntity<StandardResponse> getAllOrderDetails(
            @RequestParam(value = "stateType") String stateType,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size")@Max(50) int size
     ){
         PaginatedOrderResponseOrderDetails paginatedOrderResponseOrderDetails = null;
         if(stateType.equalsIgnoreCase("active") | stateType.equalsIgnoreCase("inactive")){
                 boolean state =stateType.equalsIgnoreCase("active")?true:false;
                 paginatedOrderResponseOrderDetails = orderService.getAllOrderDetails(state,page,size);
//             if(stateType.equalsIgnoreCase("active")){
//                    state = true;
//
                    }
             return new ResponseEntity<StandardResponse>(
                     new StandardResponse(200,"Success",paginatedOrderResponseOrderDetails),
                     HttpStatus.OK
             );

         }


}
