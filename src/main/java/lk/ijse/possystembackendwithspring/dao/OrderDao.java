package lk.ijse.possystembackendwithspring.dao;

import lk.ijse.possystembackendwithspring.entity.impl.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends JpaRepository<OrderEntity,String> {

}
