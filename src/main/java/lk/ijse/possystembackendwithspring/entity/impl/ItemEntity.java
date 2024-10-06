package lk.ijse.possystembackendwithspring.entity.impl;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="item")
public class ItemEntity {
    @Id
    private String itemCode;

    private String name;
    private String category;
    private String unitPrice;
    private int qty;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetailsEntity> orderDetails;
}
