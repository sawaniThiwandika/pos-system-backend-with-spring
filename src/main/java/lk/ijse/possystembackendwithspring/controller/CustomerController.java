package lk.ijse.possystembackendwithspring.controller;

import lk.ijse.possystembackendwithspring.dto.impl.CustomerDto;
import lk.ijse.possystembackendwithspring.service.CustomerService;
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

        if (cusName == null || cusName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Customer name is missing");
        }
        if (cusEmail == null || cusEmail.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Customer email is missing");
        }
        if (cusAddress == null || cusAddress.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Customer address is missing");
        }
        if (cusContact == null || cusContact.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Customer contact is missing");
        }
        String namePattern = "^[a-zA-Z\\s]+$";
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String addressPattern = "^[\\w\\s.,#-]+$";
        String contactPattern = "^\\+?[0-9\\s()-]+$";


        if ( !cusName.matches(namePattern)) {
            return ResponseEntity.badRequest().body("Invalid customer name");
        }
        if ( !cusEmail.matches(emailPattern)) {
            return ResponseEntity.badRequest().body("Invalid customer email");
        }
        if ( !cusAddress.matches(addressPattern)) {
            return ResponseEntity.badRequest().body("Invalid customer address");
        }
        if (!cusContact.matches(contactPattern)) {
            return ResponseEntity.badRequest().body("Invalid customer contact number");
        }

        logger.info("Run the Post method in customer controller");
        logger.trace("init");
        try {
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
       catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }




    }
    @GetMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCustomer(@PathVariable ("customerId") String cusId){
        if (cusId == null || cusId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Customer ID is missing");
        }
        String cusIdPattern="^C[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        if ( !cusId.matches(cusIdPattern)) {
            return ResponseEntity.badRequest().body("Invalid customer ID");
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomerDto> getCustomerList(){
        List<CustomerDto> customerList = customerService.getCustomerList();
        logger.info("Run the Get All method in customer controller");
        logger.trace("init");
        return customerList;

    }
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateCustomer(@RequestPart("_cusId") String cusId,
                                                 @RequestPart("_cusName") String cusName,
                                                 @RequestPart("_cusEmail")String cusEmail,
                                                 @RequestPart("_cusAddress")String cusAddress,
                                                 @RequestPart("_cusContact") String cusContact,
                                                 @RequestPart("_addCusDate") String addCusDate ){
        if (cusName == null || cusName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Customer name is missing");
        }
        if (cusEmail == null || cusEmail.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Customer email is missing");
        }
        if (cusAddress == null || cusAddress.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Customer address is missing");
        }
        if (cusContact == null || cusContact.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Customer contact is missing");
        }
        if (cusId == null || cusContact.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Customer ID is missing");
        }
        String namePattern = "^[a-zA-Z\\s]+$";
        String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String addressPattern = "^[\\w\\s.,#-]+$";
        String contactPattern = "^\\+?[0-9\\s()-]+$";
        String cusIdPattern="^C[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

        if ( !cusId.matches(cusIdPattern)) {
            return ResponseEntity.badRequest().body("Invalid customer ID");
        }
        if ( !cusName.matches(namePattern)) {
            return ResponseEntity.badRequest().body("Invalid customer name");
        }
        if ( !cusEmail.matches(emailPattern)) {
            return ResponseEntity.badRequest().body("Invalid customer email");
        }
        if ( !cusAddress.matches(addressPattern)) {
            return ResponseEntity.badRequest().body("Invalid customer address");
        }
        if (!cusContact.matches(contactPattern)) {
            return ResponseEntity.badRequest().body("Invalid customer contact number");
        }

        try {
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
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
       catch (Exception e){
       e.printStackTrace();
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }



    }
    @DeleteMapping(value = "/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") String cusId){
        if (cusId == null || cusId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Customer ID is missing");
        }
        String cusIdPattern="^C[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        if ( !cusId.matches(cusIdPattern)) {
            return ResponseEntity.badRequest().body("Invalid customer ID");
        }
        try {
            customerService.deleteCustomer(cusId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }
}
