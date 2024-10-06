package lk.ijse.possystembackendwithspring.dao;

import lk.ijse.possystembackendwithspring.entity.impl.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDao extends JpaRepository<ItemEntity,String> {

}
