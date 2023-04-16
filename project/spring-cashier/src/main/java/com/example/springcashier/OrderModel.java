package com.example.springcashier;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@RequiredArgsConstructor
class OrderModel {
    
    @Id @GeneratedValue 
    private Long id;

    private String size;
    private String milk;
    private String drink;
    private String price;
    private String store;
    private String register;
    private String status;
    private boolean activeOrder = true;

    public boolean getActiveOrder() {return activeOrder;}

    // public String size() { return size; }
    // public String milk() { return milk; }
    // public String drink() { return drink; }
    // public String store() { return store; }

}
