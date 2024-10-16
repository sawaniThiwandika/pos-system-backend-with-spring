package lk.ijse.possystembackendwithspring.dao;

import lk.ijse.possystembackendwithspring.entity.impl.CustomerEntity;
import lk.ijse.possystembackendwithspring.service.impl.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<CustomerEntity,String> {

}
