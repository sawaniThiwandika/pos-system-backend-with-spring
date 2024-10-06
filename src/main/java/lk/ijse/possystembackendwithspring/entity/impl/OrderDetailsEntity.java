package lk.ijse.possystembackendwithspring.entity.impl;

import jakarta.persistence.*;
import lk.ijse.possystembackendwithspring.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "order_details")
public class OrderDetailsEntity implements SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "itemCode", nullable = false)
    private ItemEntity item;
    private double unitPrice;
    private double total;
    private String itemName;
    private int qty;
}
