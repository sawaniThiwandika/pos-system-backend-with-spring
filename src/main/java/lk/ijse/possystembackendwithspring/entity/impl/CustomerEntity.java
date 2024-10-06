package lk.ijse.possystembackendwithspring.entity.impl;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {

    @Id
    private String customerId;
    private String name;
    private String email;
    private String address;
    private String contact;
    private String date;
    @OneToMany(mappedBy = "customer")
    private List<OrderEntity> orders;

}
