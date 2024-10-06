package lk.ijse.possystembackendwithspring.service;

import lk.ijse.possystembackendwithspring.dto.impl.CustomerDto;

import java.util.List;

public interface CustomerService {
    public void saveCustomer(CustomerDto dto);
    public void updateCustomer(String cusId,CustomerDto dto);
    public String deleteCustomer(String id);
    public CustomerDto getCustomer(String id);
    public List<CustomerDto> getCustomerList();
}
