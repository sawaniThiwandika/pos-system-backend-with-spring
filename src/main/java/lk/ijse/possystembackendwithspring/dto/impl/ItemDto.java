package lk.ijse.possystembackendwithspring.dto.impl;

import lk.ijse.possystembackendwithspring.entity.impl.OrderDetailsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private String _itemCode;
    private String _unitPrice;
    private String _category;
    private String _itemName;
    private String _itemQty;
    private List<OrderDetailsDto> _orderDetails;
}
