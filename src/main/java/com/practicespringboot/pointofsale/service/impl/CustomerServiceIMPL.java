package com.practicespringboot.pointofsale.service.impl;

import com.practicespringboot.pointofsale.DTO.CustomerDTO;
import com.practicespringboot.pointofsale.DTO.request.RequestUpdateCustomerDTO;
import com.practicespringboot.pointofsale.entity.Customer;
import com.practicespringboot.pointofsale.repo.CustomerRepo;
import com.practicespringboot.pointofsale.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(

                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerSalary(),
                customerDTO.getNic(),
                customerDTO.isActiveState()
        );

        if(customerRepo.existsById(customer.getCustomerId())){
            throw new DuplicateKeyException("Customer already exists.");
        }else{
            customerRepo.save(customer);
            return customer.getCustomerName()+" Saved";
        }

    }

    @Override
    public String updateCustomer(RequestUpdateCustomerDTO customerDTO) {
        if(customerRepo.existsById(customerDTO.getCustomerId())){
              Customer customer = customerRepo.getById(customerDTO.getCustomerId());

              customer.setCustomerName(customerDTO.getCustomerName());
              customer.setCustomerAddress(customerDTO.getCustomerAddress());
              customer.setCustomerSalary(customerDTO.getCustomerSalary());

              customerRepo.save(customer);
              
              return "saved" + customer.getCustomerId()+" "+customer.getCustomerName();
        }else{
            throw new RuntimeException("Customer Not In Database");
        }
    }


    @Override
    public CustomerDTO getCustomerById(int customerId) {
        System.out.println("hello");
        Customer customer = customerRepo.getById(customerId);
        System.out.println(customer);
        if (customer != null) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setCustomerName(customer.getCustomerName());
            customerDTO.setCustomerAddress(customer.getCustomerAddress());
            customerDTO.setCustomerSalary(customer.getCustomerSalary());
            customerDTO.setNic(customer.getNic());
            customerDTO.setActiveState(customer.isActiveState());

            System.out.println(customerDTO.getCustomerAddress());
            return customerDTO;
        } else {
            throw new RuntimeException("Customer not found for that id " + customerId);
        }
    }

//    @Override
//    public CustomerDTO getCustomerById(int customerId) {
//        Optional<Customer> customer = customerRepo.findById(customerId);
//        if (customer.isPresent()) {
//            CustomerDTO customerDTO = new CustomerDTO(
//                    customer.get().getCustomerId(),
//                    customer.get().getCustomerName(),
//                    customer.get().getCustomerAddress(),
//                    customer.get().getCustomerSalary(),
//                    customer.get().getNic(),
//                    customer.get().isActiveState()
//            );
//
//            return customerDTO;
//        } else {
//            throw new RuntimeException("Customer not found for that id " + customerId);
//        }
//    }


    public List<CustomerDTO> getAllCustomers() {
        List<Customer> getCustomer = customerRepo.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : getCustomer){
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setCustomerName(customer.getCustomerName());
            customerDTO.setCustomerAddress(customer.getCustomerAddress());
            customerDTO.setCustomerSalary(customer.getCustomerSalary());
            customerDTO.setNic(customer.getNic());
            customerDTO.setActiveState(customer.isActiveState());

            customerDTOList.add(customerDTO);
        }
        return customerDTOList ;
    }

    @Override
    public String deleteCustomer(int customerId) {
        if(customerRepo.existsById(customerId)){
            customerRepo.deleteById(customerId);
            return "deleted successfully";
        }else{
            return "Not found customer";
        }

    }

    @Override
    public CustomerDTO getCustomerByNic(String nic) {
        Optional<Customer> customer = customerRepo.findByNicEquals(nic);
        if (customer.isPresent()) {
           CustomerDTO customerDTO = new CustomerDTO(
                   customer.get().getCustomerId(),
                   customer.get().getCustomerName(),
                    customer.get().getCustomerAddress(),
                    customer.get().getCustomerSalary(),
                    customer.get().getNic(),
                    customer.get().isActiveState()
           );

            return customerDTO;
        } else {
            throw new RuntimeException("Customer not found for that id " + nic);
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomersByStatus(boolean status) {
        List<Customer> getCustomer = customerRepo.findAllByActiveState(status);
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer : getCustomer){
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomerId(customer.getCustomerId());
            customerDTO.setCustomerName(customer.getCustomerName());
            customerDTO.setCustomerAddress(customer.getCustomerAddress());
            customerDTO.setCustomerSalary(customer.getCustomerSalary());
            customerDTO.setNic(customer.getNic());
            customerDTO.setActiveState(customer.isActiveState());

            customerDTOList.add(customerDTO);
        }
        return customerDTOList ;
    }


}

