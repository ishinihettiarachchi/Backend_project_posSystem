package com.practicespringboot.pointofsale.controller;

import com.practicespringboot.pointofsale.DTO.CustomerDTO;
import com.practicespringboot.pointofsale.DTO.request.RequestUpdateCustomerDTO;
import com.practicespringboot.pointofsale.entity.Customer;
import com.practicespringboot.pointofsale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(path="/save")
    public String saveCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.saveCustomer(customerDTO);
        return "saved";
    }

    @PutMapping(path = "/update")
    public String updateCustomer(@RequestBody RequestUpdateCustomerDTO customerDTO){
        String updated = customerService.updateCustomer(customerDTO);
        return updated;
    }

    @GetMapping (
            path = "/get-by-id",
            params = "id"
    )
    public CustomerDTO getCustomerById(@RequestParam(value = "id") int customerId){
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        return customerDTO;
    }

    @GetMapping(path = "/get-all-customers")
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> allCustomer = customerService.getAllCustomers();
        return allCustomer;
    }

    @DeleteMapping(path = "/delete-customer/{id}")
    public String deleteCustomer(@PathVariable(value = "id") int customerId){
        String deleted = customerService.deleteCustomer(customerId);
        return deleted;
    }

    @GetMapping(
            path = "/get-by-nic",
            params = "nic"
    )
    public CustomerDTO getCustomerByNic(@RequestParam(value = "nic") String nic){
        CustomerDTO customerDTO = customerService.getCustomerByNic(nic);
        return customerDTO;

    }

    @GetMapping(path = "/get-all-customers/{status}")
    public List<CustomerDTO> getAllCustomers(@PathVariable(value = "status") boolean status){
        List<CustomerDTO> allCustomer = customerService.getAllCustomersByStatus(status);
        return allCustomer;
    }


}
