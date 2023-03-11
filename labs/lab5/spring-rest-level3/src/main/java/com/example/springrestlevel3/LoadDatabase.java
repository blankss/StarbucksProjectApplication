package com.example.springrestlevel3.payroll;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(com.example.springrestlevel3.payroll.EmployeeRepository employeeRepository, com.example.springrestlevel3.order.OrderRepository orderRepository) {

    return args -> {
      log.info("Preloading " + employeeRepository.save(new com.example.springrestlevel3.payroll.Employee("Bilbo", "Baggins", "burglar")));
      log.info("Preloading " + employeeRepository.save(new com.example.springrestlevel3.payroll.Employee("Frodo", "Baggins", "thief")));

      log.info("Preloading " + orderRepository.save(new com.example.springrestlevel3.order.Order("MacBook Pro", com.example.springrestlevel3.order.Status.COMPLETED)));
      log.info("Preloading " + orderRepository.save(new com.example.springrestlevel3.order.Order("iPhone", com.example.springrestlevel3.order.Status.IN_PROGRESS)));
      
    };
  }
}