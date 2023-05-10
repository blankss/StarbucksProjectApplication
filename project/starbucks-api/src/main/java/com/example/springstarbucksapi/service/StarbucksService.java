package com.example.springstarbucksapi.service;

import com.example.springstarbucksapi.model.StarbucksCard;
import com.example.springstarbucksapi.model.StarbucksOrder;
import com.example.springstarbucksapi.model.StarbucksDrink;
import com.example.springstarbucksapi.repository.StarbucksCardRepository;
import com.example.springstarbucksapi.repository.StarbucksOrderRepository;
import com.example.springstarbucksapi.repository.StarbucksDrinkRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Random;

import java.util.*;

@Service("StarbucksService")
public class StarbucksService {

    // REF: https://www.moreofless.co.uk/spring-mvc-java-autowired-component-null-repository-service
    @Autowired private StarbucksOrderRepository ordersRepository;
    @Autowired private StarbucksCardRepository cardsRepository;
    @Autowired private StarbucksDrinkRepository drinksRepository;

    @Autowired
    private RabbitTemplate rabbit;

    @Autowired
    private Queue queue;

    /* https://docs.spring.io/spring-data/jpa/docs/2.4.5/api/ */

    /* Fetch Drink status */
    public StarbucksDrink getStatus(String orderNum) {
        List<StarbucksDrink> list = drinksRepository.findStarbucksDrinkByOrderNum( orderNum ) ;
        if ( !list.isEmpty() ) {
            StarbucksDrink drink = list.get(0) ;
            return drink;
        } 
        else {
            return null;
        } 
    }

    /* Create a New Starbucks Card */
    public StarbucksCard newCard() {
        StarbucksCard newcard = new StarbucksCard();
        Random random = new Random();
        int num = random.nextInt(900000000) + 100000000;
        int code = random.nextInt(900) + 100;
        newcard.setCardNumber(String.valueOf(num));
        newcard.setCardCode(String.valueOf(code));
        newcard.setBalance(20.00);
        newcard.setActivated(false);
        newcard.setStatus("New Card");
        return cardsRepository.save(newcard);
    }

    /* Get List of Starbucks Cards */
    public List<StarbucksCard> allCards() {
        return cardsRepository.findAll();
    }

    /* Delete All Starbucks Cards (Cleanup for Unit Testing) */
    public void deleteAllCards() {
        cardsRepository.deleteAllInBatch();
    }

    /* Get Details of a Starbucks Card */
    public StarbucksCard findCard( String num ) {
        return cardsRepository.findByCardNumber(num);
    }

    /* Activate a Starbucks Card */
    public void activateCard(String num, String code) {
        StarbucksCard card = cardsRepository.findByCardNumber(num) ;
        if (card != null && card.getCardCode().equals(code)) {
            card.setActivated(true);
            cardsRepository.save(card);
        }
    }

    // https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html
    private HashMap<String, StarbucksOrder> orders = new HashMap<>();

    /* https://docs.spring.io/spring-data/jpa/docs/2.4.5/api/ */

    /* Get List of Starbucks Orders */
    public List<StarbucksOrder> allOrders() {
        return ordersRepository.findAll();
    }

    /* Delete All Starbucks Orders (Cleanup for Unit Testing) */
    public void deleteAllOrders() {
        ordersRepository.deleteAllInBatch();
        orders.clear();
    }

    public StarbucksOrder findActive(String regid) throws ResponseStatusException {
        List<StarbucksOrder> orderExist = ordersRepository.findStarbucksOrderByRegister(regid);
        // StarbucksOrder activeOrder = null;
        for (StarbucksOrder o : orderExist) {
            if (o.getActive()) {
                return o;
            }
        }
        return null;
    }

    /* Create a New Starbucks Order */
    public StarbucksOrder newOrder(String regid, StarbucksOrder order) throws ResponseStatusException {
        System.out.println("Placing Order (Reg ID = " + regid + ") => " + order);

        StarbucksOrder orderExist = findActive(regid);

        if (orderExist != null && orderExist.getActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Active Order Exists!");
        }   

        // check input
        if (order.getDrink().equals("") || order.getMilk().equals("") || order.getSize().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Order Request!");
        }
        // check for active order
        // StarbucksOrder active = orders.get(regid);
        // if (active != null) {
        //     System.out.println("Active Order (Reg ID = " + regid + ") => " + active);
        //     if (active.getStatus().equals("Ready for Payment."))
        //         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Active Order Exists!");
        // }
        // set price
        double price = 0.0;
        String drink = order.getDrink();
        String size = order.getSize();
        if (drink.equals("Caffe Latte")) {
            if (size.equals("tall")) {
                price = 2.95;
            }
            else if (size.equals("grande")) {
                price = 3.65;
            }
            else if (size.equals("venti")) {
                price = 3.95;
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            } 
        }
        else if (drink.equals("Caffe Americano")) {
            if (size.equals("tall")) {
                price = 2.25;
            }
            else if (size.equals("grande")) {
                price = 2.65;
            }   
            else if (size.equals("venti")) {
                price = 2.95;
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            } 
        }
        else if (drink.equals("Caffe Mocha")) {
            if (size.equals("tall")) {
                price = 3.45;
            }
            else if (size.equals("grande")) {
                price = 4.15;
            }
            else if (size.equals("venti")) {
                price = 4.45;
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            } 
        }
        else if (drink.equals("Cappuccino")) {
            if (size.equals("tall")) {
                price = 2.95;
            }
            else if (size.equals("grande")) {
                price = 3.65;
            }
            else if (size.equals("venti")) {
                price = 3.95;
            }   
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            } 
        }
        else if (drink.equals("Caffe Latte")) {
            if (size.equals("tall")) {
                price = 2.75;
            }
            else if (size.equals("grande")) {
                price = 3.25;
            }
            else if (size.equals("venti")) {
                price = 3.45;
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            }   
        }
        else if (drink.equals("Hot Chocolate")) {
            if (size.equals("tall")) {
                price = 2.75;
            }
            else if (size.equals("grande")) {
                price = 3.25;
            }
            else if (size.equals("venti")) {
                price = 3.45;
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Size!");
            }
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Drink!");
        }
        double tax = 0.0725;
        double total = price + (price * tax);
        double scale = Math.pow(10, 2);
        double rounded = Math.round(total * scale) / scale;
        order.activate();
        order.setTotal(rounded);
        order.setPrice("$" + price);
        // save order
        order.setRegister(regid);
        order.setStatus("Ready for Payment.");
        StarbucksOrder new_order = ordersRepository.save(order);
        // orders.put(regid, new_order);
        return new_order;
    }

    /* Get Details of a Starbucks Order */
    public StarbucksOrder getActiveOrder(String regid) {
        StarbucksOrder order = findActive(regid);
        if (order != null) {
            return order;
        }
        return orders.get(regid);
    }

    /* Clear Active Order */
    public void clearActiveOrder(String regid) {
        StarbucksOrder active = findActive(regid);
        active.setStatus("Cleared");
        active.clear();
        ordersRepository.save(active);
    }

    /*  Process payment for the "active" Order.
        REF:  https://www.baeldung.com/transaction-configuration-with-jpa-and-spring
     */
    @Transactional
    public StarbucksCard processOrder(String regid, String cardnum) throws ResponseStatusException {
        System.out.println("Pay for Order: Reg ID = " + regid + " Using Card = " + cardnum);
        
        StarbucksOrder active = findActive(regid);

        if (active == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order Not Found!");
        }
        if (cardnum.equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Number Not Provided!");
        }
        if (active.getStatus().startsWith("Paid with Card")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Clear Paid Active Order!");
        }
        StarbucksCard card = cardsRepository.findByCardNumber(cardnum);
        if (card == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Not Found!");
        }
        if (!card.isActivated()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Card Not Activated.");
        }
        double price = active.getTotal();
        double balance = card.getBalance();
        if (balance - price < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient Funds on Card.");
        }


        double new_balance = balance - price;
        card.setBalance(new_balance);
        String status = "Paid with Card: " + cardnum + " Balance: $" + new_balance + ".";
        active.setStatus(status);
        active.clear();
        cardsRepository.save(card);
        active.setCard(card);
        ordersRepository.save(active);

        StarbucksDrink drink = new StarbucksDrink(active);
        drink.setStatus("Currently making drink...");
        String orderNum = UUID.randomUUID().toString();
        drink.setOrderNum(orderNum);
        drinksRepository.save(drink);

        rabbit.convertAndSend(queue.getName(), orderNum);


        return card;
    }

}

/*

https://priceqube.com/menu-prices/%E2%98%95-starbucks

var DRINK_OPTIONS = [ "Caffe Latte", "Caffe Americano", "Caffe Mocha", "Espresso", "Cappuccino" ];
var MILK_OPTIONS  = [ "Whole Milk", "2% Milk", "Nonfat Milk", "Almond Milk", "Soy Milk" ];
var SIZE_OPTIONS  = [ "Short", "Tall", "Grande", "Venti", "Your Own Cup" ];

Caffè Latte
=============
tall 	$2.95
grande 	$3.65
venti 	$3.95 (Your Own Cup)

Caffè Americano
===============
tall 	$2.25
grande 	$2.65
venti 	$2.95 (Your Own Cup)

Caffè Mocha
=============
tall 	$3.45
grande 	$4.15
venti 	$4.45 (Your Own Cup)

Cappuccino
==========
tall 	$2.95
grande 	$3.65
venti 	$3.95 (Your Own Cup)

Espresso
========
short 	$1.75
tall 	$1.95

 */
