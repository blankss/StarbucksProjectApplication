package com.example.springstarbucksapi.repository;

/* https://docs.spring.io/spring-data/jpa/docs/2.4.6/reference/html/#repositories */

import com.example.springstarbucksapi.model.StarbucksDrink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface StarbucksDrinkRepository extends JpaRepository<StarbucksDrink, Long> {
	public List<StarbucksDrink> findStarbucksDrinkByOrderNum(String orderNum) ;
}


