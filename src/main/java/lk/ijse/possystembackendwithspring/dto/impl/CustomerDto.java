package lk.ijse.possystembackendwithspring.dto.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String _cusId;
    private String _cusName;
    private String _cusEmail;
    private String _cusAddress;
    private String _cusContact;
    private String _addCusDate;
}
