package com.practicespringboot.pointofsale.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Table(name = "order_detail")
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
//@ToString
@Data

public class OrderDetails  {
    @Id
    @Column(name = "order_detail_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderDetailId;

    @Column(name = "item_name", length = 100, nullable = false)
    private String itemName;

    @Column(name = "qty", length = 100, nullable = false)
    private String qty;

    @Column(name = "amount",nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false)
    private Order orders;

    @ManyToOne
    @JoinColumn(name = "item_id",nullable = false)
    private Item items;

}


