package com.practicespringboot.pointofsale.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "orders")
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
//@ToString
@Data
public class Order {
    @Id
    @Column(name = "order_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;


    @Column(name = "order_date", columnDefinition = "DATETIME")
    private Date date;

    @Column(name = "total",nullable = false)
    private Double total;

    @Column(name="active_state",columnDefinition = "TINYINT default 1")
    private boolean activeState;

    @OneToMany(mappedBy = "orders")
    private Set<OrderDetails> orderDetails;

    public Order(Customer customer, Date date, Double total) {
        this.customer = customer;
        this.date = date;
        this.total = total;
    }
}
