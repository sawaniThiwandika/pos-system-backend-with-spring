package lk.ijse.possystembackendwithspring.controller;

import lk.ijse.possystembackendwithspring.dto.impl.ItemDto;
import lk.ijse.possystembackendwithspring.exception.ItemNotFoundException;
import lk.ijse.possystembackendwithspring.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@CrossOrigin(origins = "http://localhost:63342")
@RestController
@RequestMapping("api/v1/item")
public class ItemController {
    @Autowired
    ItemService itemService;
    static Logger logger= LoggerFactory.getLogger(ItemController.class);
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveItem(@RequestPart("_itemCode") String itemCode,
                                               @RequestPart("_unitPrice") String unitPrice,
                                               @RequestPart("_category")String category,
                                               @RequestPart("_itemName")String itemName,
                                               @RequestPart("_itemQty") String itemQty){

        String unitPricePattern = "^\\d+(\\.\\d{1,2})?$";
        String categoryPattern = "^[a-zA-Z0-9\\s]+$";
        String itemNamePattern = "^[a-zA-Z\\s]+$";
        String itemQtyPattern = "^\\d+$";


        if (unitPrice == null || !unitPrice.matches(unitPricePattern)) {
            return ResponseEntity.badRequest().body("Invalid unit price");
        }
        if (category == null || !category.matches(categoryPattern)) {
            return ResponseEntity.badRequest().body("Invalid category");
        }
        if (itemName == null || !itemName.matches(itemNamePattern)) {
            return ResponseEntity.badRequest().body("Invalid item name");
        }
        if (itemQty == null || !itemQty.matches(itemQtyPattern)) {
            return ResponseEntity.badRequest().body("Invalid item quantity");
        }

        try {
            int qty;
            double price;
            try {
                qty = Integer.valueOf(itemQty);
                price = Double.valueOf(unitPrice);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            ItemDto itemDto = new ItemDto();
            itemDto.setItemName(itemName);
            itemDto.setItemCode(itemCode);
            itemDto.setItemQty(qty);
            itemDto.setCategory(category);
            itemDto.setUnitPrice(price);
            System.out.println(itemDto);
            itemService.saveItem(itemDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping(value = "/{itemCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDto getItem(@PathVariable ("itemCode") String itemCode) {
        String regexForUserID = "^Item[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        var regexMatcher = regexPattern.matcher(itemCode);
        try {
            if (!regexMatcher.matches()) {
                new ItemNotFoundException("Invalid Item code "+itemCode);
                return null;
            }
            ItemDto item = itemService.getItem(itemCode);
            return item;

        }
        catch (Exception e){
            e.printStackTrace();
           return null;
        }


    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDto> getItemList() {
        List<ItemDto> itemList = itemService.getItemList();
        return itemList;


    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> updateItem(@RequestPart("_itemCode") String itemCode,
                                             @RequestPart("_unitPrice") String unitPrice,
                                             @RequestPart("_category") String category,
                                             @RequestPart("_itemName") String itemName,
                                             @RequestPart("_itemQty") String itemQty) {

        String regexForUserID = "^Item[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        String unitPricePattern = "^\\d+(\\.\\d{1,2})?$";
        String categoryPattern = "^[a-zA-Z0-9\\s]+$";
        String itemNamePattern = "^[a-zA-Z\\s]+$";
        String itemQtyPattern = "^\\d+$";

        Pattern regexPattern = Pattern.compile(regexForUserID);
        var regexMatcher = regexPattern.matcher(itemCode);
        if (!regexMatcher.matches()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (unitPrice == null || !unitPrice.matches(unitPricePattern)) {
            return ResponseEntity.badRequest().body("Invalid unit price");
        }
        if (category == null || !category.matches(categoryPattern)) {
            return ResponseEntity.badRequest().body("Invalid category");
        }
        if (itemName == null || !itemName.matches(itemNamePattern)) {
            return ResponseEntity.badRequest().body("Invalid item name");
        }
        if (itemQty == null || !itemQty.matches(itemQtyPattern)) {
            return ResponseEntity.badRequest().body("Invalid item quantity");
        }

        ItemDto itemDto = new ItemDto();
        itemDto.setItemName(itemName);
        itemDto.setItemCode(itemCode);
        itemDto.setItemQty(Integer.valueOf(itemQty));
        itemDto.setCategory(category);
        itemDto.setUnitPrice(Double.valueOf(unitPrice));
        System.out.println(itemDto);

        try {
            itemService.updateItem(itemCode, itemDto);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(value = "/{itemCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteItem(@PathVariable("itemCode") String itemCode) {

        String regexForUserID = "^Item[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        Pattern regexPattern = Pattern.compile(regexForUserID);
        var regexMatcher = regexPattern.matcher(itemCode);

        try {
            if (itemCode==null||!regexMatcher.matches()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            itemService.deleteItem(itemCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ItemNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
