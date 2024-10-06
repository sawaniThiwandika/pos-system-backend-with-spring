package lk.ijse.possystembackendwithspring.entity.impl;

import jakarta.persistence.*;
import lk.ijse.possystembackendwithspring.dto.impl.OrderDetailsDto;
import lk.ijse.possystembackendwithspring.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="item")
public class ItemEntity implements SuperEntity {
    @Id
    private String itemCode;
    private double itemUnitPrice;
    private String itemCategory;
    private String itemName;
    private int itemQty;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetailsEntity> itemOrderDetails;
}
