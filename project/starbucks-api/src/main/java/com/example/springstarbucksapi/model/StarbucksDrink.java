package com.example.springstarbucksapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;
// import com.example.springstarbucksapi.model.StarbucksOrder;

import javax.persistence.*;

/* 
    https://www.baeldung.com/intro-to-project-lombok
    https://www.baeldung.com/lombok-ide
            
    Spring JPA References:

    https://docs.spring.io/spring-data/jpa/docs/2.5.1/reference/html
    https://docs.spring.io/spring-data/jpa/docs/2.5.1/api
    https://www.baeldung.com/spring-data-rest-relationships
    https://www.baeldung.com/hibernate-one-to-many
    https://www.baeldung.com/jpa-one-to-one
    https://www.baeldung.com/jpa-cascade-types
    https://www.baeldung.com/category/persistence/tag/jpa

*/

@Entity
@Table(name = "STARBUCKS_DRINK")
@Data
@RequiredArgsConstructor
public class StarbucksDrink {

    private @Id
    @GeneratedValue
    @JsonIgnore  /* https://www.baeldung.com/jackson-ignore-properties-on-serialization */
    Long id;
    @Column(nullable = false)
    private String drink;
    @Column(nullable = false)
    private String milk;
    @Column(nullable = false)
    private String size;
    private double total;
    private String status;
    private String register;
    private String price;

    private String orderNum;

    public StarbucksDrink(StarbucksOrder order) {
        this.setDrink(order.getDrink());
        this.setMilk(order.getMilk());
        this.setSize(order.getSize());
        this.setTotal(order.getTotal());
        this.setStatus(order.getStatus());
        this.setRegister(order.getRegister());
        this.setPrice(order.getPrice());
    }

}