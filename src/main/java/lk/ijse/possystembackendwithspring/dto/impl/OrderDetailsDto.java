package lk.ijse.possystembackendwithspring.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto {
        private String _orderId ;
        private String _itemCode;
        private int _qty;
        private double _unitPrice;
        private double _total;
        private String _itemName;


}
