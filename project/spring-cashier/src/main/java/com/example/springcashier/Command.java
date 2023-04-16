package com.example.springcashier;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
class Command {

    private String action ;
    private String message ;
    private String stores ;
    private String register;

    //Order details
    private String size;
    private String milk;
    private String drink;
    
}