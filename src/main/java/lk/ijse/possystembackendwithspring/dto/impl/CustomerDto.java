package lk.ijse.possystembackendwithspring.dto.impl;

import lk.ijse.possystembackendwithspring.entity.impl.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto implements Serializable {
    private String cusId;
    private String cusName;
    private String cusEmail;
    private String cusAddress;
    private String cusContact;
    private String addCusDate;

}
