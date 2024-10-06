package lk.ijse.possystembackendwithspring.dao;

import lk.ijse.possystembackendwithspring.entity.impl.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao extends JpaRepository<CustomerEntity,String> {

}
