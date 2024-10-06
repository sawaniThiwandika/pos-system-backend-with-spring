package lk.ijse.possystembackendwithspring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.possystembackendwithspring.dao.CustomerDao;
import lk.ijse.possystembackendwithspring.dto.impl.CustomerDto;
import lk.ijse.possystembackendwithspring.entity.impl.CustomerEntity;
import lk.ijse.possystembackendwithspring.entity.impl.OrderEntity;
import lk.ijse.possystembackendwithspring.service.CustomerService;
import lk.ijse.possystembackendwithspring.util.AppUtil;
import lk.ijse.possystembackendwithspring.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

        CustomerEntity customerEntity = mapping.toCustomerEntity(dto);
        customerEntity.setOrders(new ArrayList<>());
        customerEntity.setCusId(AppUtil.generateCustomerID());
        customerDao.save(customerEntity);
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
    }

    @Override
    public String deleteCustomer(String id) {
        return null;
    }

    @Override
    public CustomerDto getCustomer(String id) {
        return null;
    }

    @Override
    public List<CustomerDto> getCustomerList() {

        List<CustomerEntity> allCustomers = customerDao.findAll();
        return allCustomers.stream()
                .map(mapping::toCustomerDto) // Use method reference for clarity
                .collect(Collectors.toList()); // Collect to a list
    }

}
