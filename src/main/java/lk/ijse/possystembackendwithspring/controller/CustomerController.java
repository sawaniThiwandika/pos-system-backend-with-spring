package lk.ijse.possystembackendwithspring.controller;

import lk.ijse.possystembackendwithspring.dto.impl.CustomerDto;
import lk.ijse.possystembackendwithspring.service.CustomerService;
import org.hibernate.usertype.LoggableUserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@CrossOrigin(origins = "http://localhost:63342")
public class CustomerController {
    @Autowired
    CustomerService customerService;
   static Logger logger=LoggerFactory.getLogger(CustomerController.class);
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveCustomer(@RequestPart("_cusId") String cusId,
                                               @RequestPart("_cusName") String cusName,
                                               @RequestPart("_cusEmail")String cusEmail,
                                               @RequestPart("_cusAddress")String cusAddress,
                                               @RequestPart("_cusContact") String cusContact,
                                               @RequestPart("_addCusDate") String addCusDate ){
       logger.info("Run the Post method in customer controller");
        logger.trace("init");

        CustomerDto customerDto = new CustomerDto();
        customerDto.setAddCusDate(String.valueOf(LocalDate.now()));
        customerDto.setCusAddress(cusAddress);
        customerDto.setCusContact(cusContact);
        customerDto.setCusEmail(cusEmail);
        customerDto.setCusName(cusName);
        customerDto.setCusId(cusId);
        System.out.println(customerDto);
        customerService.saveCustomer(customerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);


    }
    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCustomer(@PathVariable ("customerId") String cusId){

        return "Success";
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDto> getCustomerList(){
        List<CustomerDto> customerList = customerService.getCustomerList();
        logger.info("Run the Get All method in customer controller");
        logger.trace("init");
        return customerList;

    }
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateCustomer(@RequestPart("_cusId") String cusId,
                               @RequestPart("_cusName") String cusName,
                               @RequestPart("_cusEmail")String cusEmail,
                               @RequestPart("_cusAddress")String cusAddress,
                               @RequestPart("_cusContact") String cusContact,
                               @RequestPart("_addCusDate") String addCusDate ){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setAddCusDate(String.valueOf(LocalDate.now()));
        customerDto.setCusAddress(cusAddress);
        customerDto.setCusContact(cusContact);
        customerDto.setCusEmail(cusEmail);
        customerDto.setCusName(cusName);
        customerDto.setCusId(cusId);
        customerDto.setAddCusDate(addCusDate);
        System.out.println(customerDto);
        customerService.updateCustomer(cusId,customerDto);


    }
    @DeleteMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteCustomer(@PathVariable("customerId") String cusId){
        customerService.deleteCustomer(cusId);

    }
}
