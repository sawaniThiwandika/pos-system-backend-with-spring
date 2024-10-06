package lk.ijse.possystembackendwithspring.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private String itemCode;
    private double unitPrice;
    private String category;
    private String itemName;
    private int itemQty;

}
