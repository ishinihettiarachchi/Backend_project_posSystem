package com.practicespringboot.pointofsale.service.impl;

import com.practicespringboot.pointofsale.DTO.QueryInterfaces.OrderDetailsInterface;
import com.practicespringboot.pointofsale.DTO.paginated.PaginatedOrderResponseOrderDetails;
import com.practicespringboot.pointofsale.DTO.paginated.PaginatedResponseItemDTO;
import com.practicespringboot.pointofsale.DTO.request.RequestOrderSaveDTO;
import com.practicespringboot.pointofsale.DTO.response.ResponseOrderDetailsDTO;
import com.practicespringboot.pointofsale.entity.Order;
import com.practicespringboot.pointofsale.entity.OrderDetails;
import com.practicespringboot.pointofsale.repo.CustomerRepo;
import com.practicespringboot.pointofsale.repo.ItemRepo;
import com.practicespringboot.pointofsale.repo.OrderDetailRepo;
import com.practicespringboot.pointofsale.repo.OrderRepo;
import com.practicespringboot.pointofsale.service.OrderService;
import com.practicespringboot.pointofsale.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Autowired
    private ItemRepo itemRepo;
    @Override
    @Transactional
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO) {
        Order order = new Order(
                customerRepo.getById(requestOrderSaveDTO.getCustomer()),
                requestOrderSaveDTO.getDate(),
                requestOrderSaveDTO.getTotal()
        );
        orderRepo.save(order);

        if(orderRepo.existsById(order.getOrderId())){
            List<OrderDetails> orderDetails = modelMapper.
                    map(requestOrderSaveDTO.getOrderDetails(), new TypeToken<List<OrderDetails>>(){
            }.getType());
            for(int i=0;i<orderDetails.size();i++){
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepo.getById(requestOrderSaveDTO.getOrderDetails().get(i).getItems()));
            }
            if(orderDetails.size()>0){
                orderDetailRepo.saveAll(orderDetails);
            }
            return "saved";

        }else{
            return null;
        }

    }

    @Override
    public PaginatedOrderResponseOrderDetails getAllOrderDetails(boolean state, int page, int size) {
        List<OrderDetailsInterface> orderDetailsInterfaces = orderRepo.getAllOrderDetails(state, PageRequest.of(page,size));

        List<ResponseOrderDetailsDTO> list = new ArrayList<>();
        for( OrderDetailsInterface o : orderDetailsInterfaces){
//            ResponseOrderDetailsDTO responseOrderDetailsDTO = new ResponseOrderDetailsDTO(
//                    o.getCustomerName(),o.getCustomerAddress(),o.getDate(),o.getTotal()
//            );
//            list.add(responseOrderDetailsDTO);
            list.add(
                    new ResponseOrderDetailsDTO(
//                    o.getCustomerName(),o.getCustomerAddress(),o.getDate(),o.getTotal()
                 )
            );
        }

        PaginatedOrderResponseOrderDetails paginatedOrderResponseOrderDetails = new PaginatedOrderResponseOrderDetails(
                list,
                orderRepo.countAllOrderDetails(state)
        );
        return paginatedOrderResponseOrderDetails;

    }
}
