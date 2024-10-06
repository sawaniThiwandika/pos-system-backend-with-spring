package lk.ijse.possystembackendwithspring.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.possystembackendwithspring.dto.impl.CustomerDto;
import lk.ijse.possystembackendwithspring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {


    @Override
    public void saveCustomer(CustomerDto dto) {

    }

    @Override
    public void updateCustomer(String cusId, CustomerDto dto) {

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
        return null;
    }
}
