package lk.ijse.possystembackendwithspring.dao;

import lk.ijse.possystembackendwithspring.entity.impl.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<OrderEntity,String> {
}
