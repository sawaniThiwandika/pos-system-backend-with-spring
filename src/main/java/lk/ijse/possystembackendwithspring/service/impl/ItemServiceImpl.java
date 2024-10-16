package lk.ijse.possystembackendwithspring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.possystembackendwithspring.dao.ItemDao;
import lk.ijse.possystembackendwithspring.dto.impl.ItemDto;
import lk.ijse.possystembackendwithspring.entity.impl.ItemEntity;
import lk.ijse.possystembackendwithspring.exception.CustomerNotFoundException;
import lk.ijse.possystembackendwithspring.exception.ItemNotFoundException;
import lk.ijse.possystembackendwithspring.service.ItemService;
import lk.ijse.possystembackendwithspring.util.AppUtil;
import lk.ijse.possystembackendwithspring.util.Mapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ItemDao itemDao;
    @Autowired
    Mapping mapping;
    static Logger logger= LoggerFactory.getLogger(ItemServiceImpl.class);
    @Override
    public void saveItem(ItemDto dto) {

        try {
            ItemEntity itemEntity = mapping.toItemEntity(dto);
            itemEntity.setItemOrderDetails(new ArrayList<>());
            itemEntity.setItemCode(AppUtil.generateItemCode());
            System.out.println("Item Entity :"+itemEntity);
            itemDao.save(itemEntity);
        }
        catch (DataAccessException e){
            System.err.println("Error saving Item to the database: " + e.getMessage());
            throw new RuntimeException("Failed to save Item. Please try again later.", e);
        }
        catch (Exception e){
            System.err.println("An unexpected error occurred: " + e.getMessage());
            throw new RuntimeException("An unexpected error occurred. Please contact support.", e);
        }



    }

    @Override
    public void updateItem(String itemCode, ItemDto dto) {
        Optional<ItemEntity> tmpUser = itemDao.findById(itemCode);
        if(tmpUser.isPresent()) {
            tmpUser.get().setItemName(dto.getItemName());
            tmpUser.get().setItemCategory(dto.getCategory());
            tmpUser.get().setItemQty(dto.getItemQty());
            tmpUser.get().setItemUnitPrice(dto.getUnitPrice());
        }
        else {
           throw new ItemNotFoundException(itemCode+ " item not found.");
        }

    }

    @Override
    public void deleteItem(String itemCode) {
        Optional<ItemEntity> existedUser = itemDao.findById(itemCode);
        if(!existedUser.isPresent()){
            throw new ItemNotFoundException(itemCode+ " item not found.");
        }else {
            itemDao.deleteById(itemCode);
        }

    }

    @Override
    public ItemDto getItem(String itemCode) {

        Optional<ItemEntity> itemEntityOptional = itemDao.findById(itemCode);

        if (itemEntityOptional.isPresent()) {
            return mapping.toItemDto(itemEntityOptional.get());
        } else {
            throw new ItemNotFoundException("Item with code " + itemCode + " not found");
        }
    }

    @Override
    public List<ItemDto> getItemList() {
        List<ItemEntity> all = itemDao.findAll();
        if(all.isEmpty()){
            new ItemNotFoundException("Failed to load");
        }
        return mapping.asItemDTOList(all);
    }
}
