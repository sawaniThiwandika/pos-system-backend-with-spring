package lk.ijse.possystembackendwithspring.service;

import lk.ijse.possystembackendwithspring.dto.impl.OrderDto;
import lk.ijse.possystembackendwithspring.entity.impl.OrderEntity;

import java.util.List;

public interface OrderService {
    public void placeOrder(OrderDto dto);
    public List<OrderDto> getOrderList();
    OrderEntity findOrderEntityById(String orderId);
}
