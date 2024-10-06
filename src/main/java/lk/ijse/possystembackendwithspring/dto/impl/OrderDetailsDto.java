package lk.ijse.possystembackendwithspring.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto {
        private int id;
        private String orderId;
        private String itemCode;
        private int qty;
        private double unitPrice;
        private double total;
        private String itemName;


}
