package lk.ijse.possystembackendwithspring.util;

import lk.ijse.possystembackendwithspring.dto.impl.*;
import lk.ijse.possystembackendwithspring.entity.impl.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapping {

    @Autowired
    private ModelMapper modelMapper;

    public UserEntity toUserEntity (UserDto userDTO){
        return modelMapper.map(userDTO,UserEntity.class);
    }
    public UserDto toUserDto (UserEntity userEntity){
        return modelMapper.map(userEntity,UserDto.class);
    }
    public CustomerEntity toCustomerEntity (CustomerDto customerDTO){
        return modelMapper.map(customerDTO,CustomerEntity.class);
    }
    public CustomerDto toCustomerDto(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerDto.class);
    }


    public OrderEntity toOrderEntity (OrderDto orderDTO){
        return modelMapper.map(orderDTO,OrderEntity.class);
    }
    public OrderDto toOrderDto (OrderEntity orderEntity){
        return modelMapper.map(orderEntity,OrderDto.class);
    }
    public OrderDetailsEntity toOrderDetailsEntity (OrderDetailsDto orderDetailsDTO){
        return modelMapper.map(orderDetailsDTO, OrderDetailsEntity.class);
    }
    public OrderDetailsDto toOrderDetailsDto (OrderDetailsEntity orderDetailsEntity){
        return modelMapper.map(orderDetailsEntity,OrderDetailsDto.class);
    }
    public ItemEntity toItemEntity (ItemDto itemDTO){

        return modelMapper.map(itemDTO, ItemEntity.class);
    }
    public ItemDto toItemDto (ItemEntity itemEntity){
        return modelMapper.map(itemEntity, ItemDto.class);
    }
}
