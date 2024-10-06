package lk.ijse.possystembackendwithspring.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private CustomerDto customer;
    private String date;
    private double total;
    private List<OrderDetailsDto> itemListOrder;
    private String id;


}
