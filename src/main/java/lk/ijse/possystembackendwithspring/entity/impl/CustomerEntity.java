package lk.ijse.possystembackendwithspring.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lk.ijse.possystembackendwithspring.entity.SuperEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity implements SuperEntity {

    @Id
    private String cusId;
    private String cusName;
    private String cusEmail;
    private String cusAddress;
    private String cusContact;
    private String addCusDate;

    @OneToMany(mappedBy = "customer")
    private List<OrderEntity> orders;

}
