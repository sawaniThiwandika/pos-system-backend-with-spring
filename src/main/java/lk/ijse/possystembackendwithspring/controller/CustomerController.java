package lk.ijse.possystembackendwithspring.controller;

import lk.ijse.possystembackendwithspring.dto.impl.CustomerDto;
import lk.ijse.possystembackendwithspring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin(origins = "http://localhost:63342")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveCustomer(@RequestBody CustomerDto dto){
        System.out.println(dto);
        customerService.saveCustomer(dto);

        return new ResponseEntity<>(HttpStatus.CREATED);


    }
    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCustomer(@PathVariable ("customerId") String cusId){
        return "Success";
    }
    @GetMapping()
    public void getCustomerList(@RequestBody CustomerDto dto){

    }
    @PutMapping(value ="/{customerId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateCustomer(@PathVariable ("customerId") String cusId,
                               @RequestBody CustomerDto dto){

    }
    @DeleteMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCustomer(@PathVariable("customerId") String cusId){

    }
}
