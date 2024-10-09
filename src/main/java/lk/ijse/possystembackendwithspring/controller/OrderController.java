package lk.ijse.possystembackendwithspring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.possystembackendwithspring.dto.impl.CustomerDto;
import lk.ijse.possystembackendwithspring.dto.impl.OrderDetailsDto;
import lk.ijse.possystembackendwithspring.dto.impl.OrderDto;
import lk.ijse.possystembackendwithspring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/v1/order")
@RestController
@CrossOrigin(origins = "http://localhost:63342")
public class OrderController {
    @Autowired
    OrderService orderService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void placeOrder(@RequestPart("_customer") String customerJson,
                           @RequestPart("_date") String date,
                           @RequestPart("_total") String total,
                           @RequestPart("_itemListOrder") String itemListJson,
                           @RequestPart("_id") String id) throws IOException {


        CustomerDto customer = objectMapper.readValue(customerJson, CustomerDto.class);
        ArrayList<OrderDetailsDto> itemList = objectMapper.readValue(itemListJson,
                objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, OrderDetailsDto.class));
        System.out.println("Json"+customerJson);

        System.out.println("Order ID: " + id);
        System.out.println("Customer: " + customer);
        System.out.println("Date: " + date);
        System.out.println("Total: " + total);
        System.out.println("Item List: " + itemList);

        OrderDto orderDto = new OrderDto();
        orderDto.setId(id);
        orderDto.setItemListOrder(itemList);
        orderDto.setCustomer(customer);
        orderDto.setDate(date);
        orderDto.setTotal(Double.valueOf(total));
        try {
            orderService.placeOrder(orderDto);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orderList = orderService.getOrderList();

        if (orderList != null && !orderList.isEmpty()) {
            return ResponseEntity.ok(orderList);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
    }

}
