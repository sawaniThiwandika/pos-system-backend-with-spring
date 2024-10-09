package lk.ijse.possystembackendwithspring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.possystembackendwithspring.dao.CustomerDao;
import lk.ijse.possystembackendwithspring.dao.ItemDao;
import lk.ijse.possystembackendwithspring.dao.OrderDao;
import lk.ijse.possystembackendwithspring.dao.OrderDetailsDao;
import lk.ijse.possystembackendwithspring.dto.impl.ItemDto;
import lk.ijse.possystembackendwithspring.dto.impl.OrderDetailsDto;
import lk.ijse.possystembackendwithspring.dto.impl.OrderDto;
import lk.ijse.possystembackendwithspring.entity.impl.ItemEntity;
import lk.ijse.possystembackendwithspring.entity.impl.OrderDetailsEntity;
import lk.ijse.possystembackendwithspring.entity.impl.OrderEntity;
import lk.ijse.possystembackendwithspring.service.ItemService;
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
    @Autowired
    ItemService itemService;
    @Override
    public void placeOrder(OrderDto dto) {


        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(AppUtil.generateOrderId());
        orderEntity.setCustomer(mapping.toCustomerEntity(dto.getCustomer()));
        orderEntity.setDate(String.valueOf(LocalDate.now()));
        orderEntity.setTotal(dto.getTotal());

        orderDao.save(orderEntity);

        List<OrderDetailsEntity> orderDetailsEntities = new ArrayList<>();
        for (OrderDetailsDto orderDetailsDto : dto.getItemListOrder()) {

            OrderDetailsEntity orderDetailsEntity = mapping.toOrderDetailsEntity(orderDetailsDto);
            orderDetailsEntity.setOrder(orderEntity);

            ItemDto item = itemService.getItem(orderDetailsEntity.getItem().getItemCode());

            if (item.getItemQty() >= orderDetailsEntity.getQty()) {
                item.setItemQty(item.getItemQty() - orderDetailsEntity.getQty());

                itemService.updateItem(item.getItemCode(),item);
                orderDetailsEntities.add(orderDetailsEntity);
                orderDetailsDao.save(orderDetailsEntity);

            } else {
                throw new RuntimeException("Not enough stock for item: " + item.getItemName());
            }
        }

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
        return optionalOrderEntity.orElse(null);
    }
}
