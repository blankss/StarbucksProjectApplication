package com.example.starbucksworker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Component
@RabbitListener(queues = "starbucks")
public class StarbucksOrderWorker {

    private static final Logger log = LoggerFactory.getLogger(StarbucksOrderWorker.class);

    @Autowired
    private StarbucksDrinkRepository drinks ;

    @RabbitHandler
    public void processStarbucksOrders(String orderNum) {
        log.info( "Received  Order # " + orderNum) ;

        // Sleeping to simulate buzy work
        try {
            Thread.sleep(60000); // 60 seconds
        } catch (Exception e) {}


        List<StarbucksDrink> list = drinks.findStarbucksDrinkByOrderNum( orderNum ) ;
        if ( !list.isEmpty() ) {
            StarbucksDrink drink = list.get(0) ;
            drink.setStatus ( "FULFILLED" ) ;
            drinks.save(drink) ;
            log.info( "Processed Order # " + orderNum );
            log.info("Order stuff: " + drink.getDrink());
        } else {
            log.info( "[ERROR] Order # " + orderNum + " Not Found!" );
        } 

    }
}