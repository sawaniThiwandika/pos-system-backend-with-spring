package lk.ijse.possystembackendwithspring.service;

import lk.ijse.possystembackendwithspring.dto.impl.CustomerDto;
import lk.ijse.possystembackendwithspring.dto.impl.ItemDto;

import java.util.List;

public interface ItemService {
    public void saveItem(ItemDto dto);
    public void updateItem(String itemCode,ItemDto dto);
    public void deleteItem(String itemCode);
    public ItemDto getItem(String itemCode);
    public List<ItemDto> getItemList();
}
