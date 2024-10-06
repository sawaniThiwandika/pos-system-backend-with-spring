package lk.ijse.possystembackendwithspring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.possystembackendwithspring.dao.ItemDao;
import lk.ijse.possystembackendwithspring.dto.impl.ItemDto;
import lk.ijse.possystembackendwithspring.entity.impl.ItemEntity;
import lk.ijse.possystembackendwithspring.service.ItemService;
import lk.ijse.possystembackendwithspring.util.AppUtil;
import lk.ijse.possystembackendwithspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemDao itemDao;
    @Autowired
    Mapping mapping;
    @Override
    public void saveItem(ItemDto dto) {
        ItemEntity itemEntity = mapping.toItemEntity(dto);
        itemEntity.setItemOrderDetails(new ArrayList<>());
        itemEntity.setItemCode(AppUtil.generateItemCode());
        System.out.println("Item Entity :"+itemEntity);
        itemDao.save(itemEntity);

    }

    @Override
    public void updateItem(String itemCode, ItemDto dto) {


    }

    @Override
    public void deleteItem(String itemCode) {

    }

    @Override
    public ItemDto getItem(String itemCode) {
        return null;
    }

    @Override
    public List<ItemDto> getItemList() {
        List<ItemEntity> all = itemDao.findAll();
        return mapping.asItemDTOList(all);
    }
}
