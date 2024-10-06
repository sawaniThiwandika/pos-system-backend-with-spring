package lk.ijse.possystembackendwithspring.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private String _customer;
    private String _date;
    private double _total;
    private List<OrderDetailsDto> _itemListOrder;
    private String _id;


}
