package com.practicespringboot.pointofsale.controller;

import com.practicespringboot.pointofsale.DTO.CustomerDTO;
import com.practicespringboot.pointofsale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public String saveCustomer(@RequestBody CustomerDTO customerDTO){
        customerService.saveCustomer(customerDTO);
        return "Ishini";
    }
}
