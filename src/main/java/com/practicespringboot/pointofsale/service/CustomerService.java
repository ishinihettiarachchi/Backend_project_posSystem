package com.practicespringboot.pointofsale.service;

import com.practicespringboot.pointofsale.DTO.CustomerDTO;
import com.practicespringboot.pointofsale.DTO.request.RequestUpdateCustomerDTO;

import java.util.List;

public interface CustomerService{
    public String saveCustomer(CustomerDTO customerDTO);


    String updateCustomer(RequestUpdateCustomerDTO customerDTO);

    CustomerDTO getCustomerById(int customerId);

    List<CustomerDTO> getAllCustomers();

    String deleteCustomer(int customerId);

    CustomerDTO getCustomerByNic(String nic);

    List<CustomerDTO> getAllCustomersByStatus(boolean status);
}
