package lk.ijse.possystembackendwithspring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.possystembackendwithspring.dao.CustomerDao;
import lk.ijse.possystembackendwithspring.dto.impl.CustomerDto;
import lk.ijse.possystembackendwithspring.entity.impl.CustomerEntity;
import lk.ijse.possystembackendwithspring.entity.impl.OrderEntity;
import lk.ijse.possystembackendwithspring.exception.CustomerNotFoundException;
import lk.ijse.possystembackendwithspring.service.CustomerService;
import lk.ijse.possystembackendwithspring.util.AppUtil;
import lk.ijse.possystembackendwithspring.util.Mapping;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerDao customerDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveCustomer(CustomerDto dto) {
        try {

            CustomerEntity customerEntity = mapping.toCustomerEntity(dto);
            customerEntity.setOrders(new ArrayList<>());
            customerEntity.setCusId(AppUtil.generateCustomerID());
            customerDao.save(customerEntity);

        } catch (DataAccessException e) {

            System.err.println("Error saving customer to the database: " + e.getMessage());
            throw new RuntimeException("Failed to save customer. Please try again later.", e);
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            throw new RuntimeException("An unexpected error occurred. Please contact support.", e);
        }
    }


    @Override
    public void updateCustomer(String cusId, CustomerDto dto) {
        Optional<CustomerEntity> tmpUser = customerDao.findById(cusId);
        if(tmpUser.isPresent()) {
            tmpUser.get().setCusName(dto.getCusName());
            tmpUser.get().setCusContact(dto.getCusContact());
            tmpUser.get().setCusEmail(dto.getCusEmail());
            tmpUser.get().setCusAddress(dto.getCusAddress());
            tmpUser.get().setAddCusDate(dto.getAddCusDate());
        }
        else {
            throw new CustomerNotFoundException("Customer with id " + cusId + " not found");
        }
    }

    @Override
    public void deleteCustomer(String id) {
        Optional<CustomerEntity> existedUser = customerDao.findById(id);
        if(!existedUser.isPresent()){
            throw new CustomerNotFoundException("Customer with id " + id + " not found");
        }else {
            customerDao.deleteById(id);
        }

    }

    @Override
    public CustomerDto getCustomer(String id) {
        return null;
    }

    @Override
    public List<CustomerDto> getCustomerList() {

        List<CustomerEntity> allCustomers = customerDao.findAll();
        if(allCustomers.isEmpty()){
            new CustomerNotFoundException("Failed to load");
        }
        return allCustomers.stream()
                .map(mapping::toCustomerDto)
                .collect(Collectors.toList());
    }

}
