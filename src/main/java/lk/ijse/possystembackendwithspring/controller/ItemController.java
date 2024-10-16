package lk.ijse.possystembackendwithspring.controller;

import lk.ijse.possystembackendwithspring.dto.impl.ItemDto;
import lk.ijse.possystembackendwithspring.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("api/v1/item")
public class ItemController {
    @Autowired
    ItemService itemService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveItem(@RequestPart("_itemCode") String itemCode,
                                               @RequestPart("_unitPrice") String unitPrice,
                                               @RequestPart("_category")String category,
                                               @RequestPart("_itemName")String itemName,
                                               @RequestPart("_itemQty") String itemQty){
        try {
            ItemDto itemDto = new ItemDto();
            itemDto.setItemName(itemName);
            itemDto.setItemCode(itemCode);
            itemDto.setItemQty(Integer.valueOf(itemQty));
            itemDto.setCategory(category);
            itemDto.setUnitPrice(Double.valueOf(unitPrice));
            System.out.println(itemDto);
            itemService.saveItem(itemDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping(value = "/{itemCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getItem(@PathVariable ("itemCode") String itemCode){

        return "Success";
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDto> getItemList(){
        return itemService.getItemList();


    }
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateItem(@RequestPart("_itemCode") String itemCode,
                           @RequestPart("_unitPrice") String unitPrice,
                           @RequestPart("_category")String category,
                           @RequestPart("_itemName")String itemName,
                           @RequestPart("_itemQty") String itemQty){

        ItemDto itemDto = new ItemDto();
        itemDto.setItemName(itemName);
        itemDto.setItemCode(itemCode);
        itemDto.setItemQty(Integer.valueOf(itemQty));
        itemDto.setCategory(category);
        itemDto.setUnitPrice(Double.valueOf(unitPrice));
        System.out.println(itemDto);
        itemService.updateItem(itemCode,itemDto);



    }
    @DeleteMapping(value = "/{itemCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteItem(@PathVariable("itemCode") String itemCode){
        itemService.deleteItem(itemCode);
    }
}
