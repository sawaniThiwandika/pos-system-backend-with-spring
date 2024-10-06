package lk.ijse.possystembackendwithspring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.possystembackendwithspring.dao.CustomerDao;
import lk.ijse.possystembackendwithspring.dao.OrderDao;
import lk.ijse.possystembackendwithspring.dao.OrderDetailsDao;
import lk.ijse.possystembackendwithspring.dto.impl.OrderDetailsDto;
import lk.ijse.possystembackendwithspring.dto.impl.OrderDto;
import lk.ijse.possystembackendwithspring.entity.impl.ItemEntity;
import lk.ijse.possystembackendwithspring.entity.impl.OrderDetailsEntity;
import lk.ijse.possystembackendwithspring.entity.impl.OrderEntity;
import lk.ijse.possystembackendwithspring.service.OrderService;
import lk.ijse.possystembackendwithspring.util.AppUtil;
import lk.ijse.possystembackendwithspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderDetailsDao orderDetailsDao;
    @Autowired
    Mapping mapping;
    @Autowired
    CustomerDao customerDao;

    @Override
    public void placeOrder(OrderDto dto) {
        // Create the order entity and set its properties
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(AppUtil.generateOrderId());
        orderEntity.setCustomer(mapping.toCustomerEntity(dto.getCustomer()));
        orderEntity.setDate(String.valueOf(LocalDate.now()));
        orderEntity.setTotal(dto.getTotal());

        // Save the OrderEntity first
        orderDao.save(orderEntity); // Save first to generate an ID
        List<OrderDetailsEntity> orderDetailsEntities = new ArrayList<>();
        for (OrderDetailsDto orderDetailsDto : dto.getItemListOrder()) {

            orderDetailsEntities.add(mapping.toOrderDetailsEntity(orderDetailsDto));
        }
        // Now map item list
        orderDetailsEntities = mapping.asOrderDetailsList(dto.getItemListOrder());

        // Set the reference to the saved order entity
        for (OrderDetailsEntity orderDetail : orderDetailsEntities) {
            orderDetail.setOrder(orderEntity); // Set the order reference
        }

        // Now save all order details entities
        for (OrderDetailsEntity orderDetail : orderDetailsEntities) {

            orderDetail.setOrder(orderEntity); // Ensure order reference is set
            orderDetailsDao.save(orderDetail); // Save order detail
        }

        // Optional: Log to confirm save operation
        System.out.println("OrderEntity saved: " + orderEntity);
        System.out.println("OrderDetailsEntities saved: " + orderDetailsEntities);
    }

    @Override
    public List<OrderDto> getOrderList() {

        List<OrderEntity> all = orderDao.findAll();
        return mapping.asOrderDtoList(all);




    }

    @Override
    public OrderEntity findOrderEntityById(String orderId) {
        Optional<OrderEntity> optionalOrderEntity = orderDao.findById(orderId);
        return optionalOrderEntity.orElse(null); // Return the found entity or null if not found
    }
}
