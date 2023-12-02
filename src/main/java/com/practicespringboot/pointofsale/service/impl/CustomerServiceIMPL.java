package com.practicespringboot.pointofsale.service.impl;

import com.practicespringboot.pointofsale.DTO.CustomerDTO;
import com.practicespringboot.pointofsale.entity.Customer;
import com.practicespringboot.pointofsale.repo.CustomerRepo;
import com.practicespringboot.pointofsale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerSalary(),
                customerDTO.getNic(),
                customerDTO.isActiveState()
        );

        if(!customerRepo.existsById(customer.getCustomerId())){
            customerRepo.save(customer);
            return customer.getCustomerName()+" Saved";
        }else{
            throw new DuplicateKeyException("Customer already exists.");
        }

    }
}
