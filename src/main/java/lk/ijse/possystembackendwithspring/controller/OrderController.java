package lk.ijse.possystembackendwithspring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lk.ijse.possystembackendwithspring.dto.impl.CustomerDto;
import lk.ijse.possystembackendwithspring.dto.impl.OrderDetailsDto;
import lk.ijse.possystembackendwithspring.dto.impl.OrderDto;
import lk.ijse.possystembackendwithspring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/v1/order")
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    private final ObjectMapper objectMapper = new ObjectMapper();  // Jackson ObjectMapper for parsing JSON

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void placeOrder(@RequestPart("_customer") String customerJson,
                           @RequestPart("_date") String date,
                           @RequestPart("_total") String total,
                           @RequestPart("_itemListOrder") String itemListJson,
                           @RequestPart("_id") String id) throws IOException {

        // Parse the JSON strings back into the corresponding DTO objects
        CustomerDto customer = objectMapper.readValue(customerJson, CustomerDto.class);
        ArrayList<OrderDetailsDto> itemList = objectMapper.readValue(itemListJson,
                objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, OrderDetailsDto.class));
        System.out.println("Json"+customerJson);
        // Process the order details here...
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
    @GetMapping(produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public MultipartFile getAllOrders(){
        List<OrderDto> orderList = orderService.getOrderList();

    }

}
