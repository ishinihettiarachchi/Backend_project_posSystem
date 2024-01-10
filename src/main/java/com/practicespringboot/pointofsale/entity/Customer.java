package com.practicespringboot.pointofsale.entity;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.annotations.Type;


import javax.persistence.*;
import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "customer")

public class Customer {

    @Id
    @Column(name="customer_id",length = 45)
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int customerId;

    @Column(name="customer_name", length=100,nullable = false)
    private String customerName;

    @Column(name="customer_address")
    private String customerAddress;

    @Column(name = "customer_salary")
    private double customerSalary;

    @Column(name="nic")
    private String nic;


    @Column(name="active_state", columnDefinition = "TINYINT default 1")
    private boolean activeState;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;

    public Customer() {
    }

    public Customer(int customerId, String customerName, String customerAddress, double customerSalary, String nic, boolean activeState) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerSalary = customerSalary;
        this.nic = nic;
        this.activeState = activeState;
    }

    public Customer(String customerName, String customerAddress, double customerSalary, String nic, boolean activeState) {
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerSalary = customerSalary;
        this.nic = nic;
        this.activeState = activeState;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public double getCustomerSalary() {
        return customerSalary;
    }

    public void setCustomerSalary(double customerSalary) {
        this.customerSalary = customerSalary;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public boolean isActiveState() {
        return activeState;
    }

    public void setActiveState(boolean activeState) {
        this.activeState = activeState;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerSalary=" + customerSalary +
                ", nic='" + nic + '\'' +
                ", activeState=" + activeState +
                '}';
    }
}
