package com.example.springcashier;


import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/starbucks")
public class SpringCashierController {

    @Autowired
    private OrderModelRepository mysql;

    @Autowired
    private OrderQueryRepository query;

    // private String[] size = {"short", "tall", "grande", "venti"};
    // private String[] milk = {"whole", "non-fat", "2%", "soy"};
    // private String[] drinks = {"mocha", "latte", "macchiato", "cappuccino"};
    // private String[] prices = {"$2.45","$2.95", "$3.65", "$4.45"};

    public static String priceCalculation(String drink, String size) {
        if (drink.equals("Caffe Latte")) {
            if (size.equals("tall")) {
                return "$2.95";
            }
            else if (size.equals("grande")) {
                return "$3.65";
            }
            else if (size.equals("venti")) {
                return "$3.95";
            }
        }
        else if (drink.equals("Caffe Americano")) {
            if (size.equals("tall")) {
                return "$2.25";
            }
            else if (size.equals("grande")) {
                return "$2.65";
            }   
            else if (size.equals("venti")) {
                return "$2.95";
            }
        }
        else if (drink.equals("Caffe Mocha")) {
            if (size.equals("tall")) {
                return "$3.45";
            }
            else if (size.equals("grande")) {
                return "$4.15";
            }
            else if (size.equals("venti")) {
                return "$4.45";
            }
        }
        else if (drink.equals("Cappuccino")) {
            if (size.equals("tall")) {
                return "$2.95";
            }
            else if (size.equals("grande")) {
                return "$3.65";
            }
            else if (size.equals("venti")) {
                return "$3.95";
            }   
        }
        else if (drink.equals("Caffe Latte")) {
            if (size.equals("tall")) {
                return "$2.75";
            }
            else if (size.equals("grande")) {
                return "$3.25";
            }
            else if (size.equals("venti")) {
                return "$3.45";
            }   
        }
        return null;
    }

    @GetMapping
    public String getAction( @ModelAttribute("command") Command command, 
                            Model model) {

    	String message = "" ;

        command.setRegister( "5012349" ) ;
        message = "Starbucks Reserved Order" + "\n\n" +
            "Register: " + command.getRegister() + "\n" +
            "Status:   " + "Ready for New Order"+ "\n" ;

        model.addAttribute("message", message);

        return "starbucks" ;

    }

    @PostMapping
    @Transactional
    public String postAction(@Valid @ModelAttribute("command") Command command,  
                            @RequestParam(value="action", required=true) String action,
                            Errors errors, Model model, HttpServletRequest request) {

        String message = "" ;

        log.info( "Action: " + action ) ;
        command.setRegister( command.getStores() ) ;
        log.info( "Command: " + command ) ;

        boolean isActive = false;
        List<OrderModel> list = mysql.findOrderModelByRegister(command.getRegister());
        OrderModel activeOrder = null;
        for (OrderModel o : list) {
            if (o.getActiveOrder()) {
                isActive = true;
                activeOrder = o;
            }
        }

        if ( action.equals("Place Order") ) {

            if (isActive) {
                message = "Starbucks Reserved Order" + "\n\n" +
                        "Register: " + command.getRegister() + "\n" +
                        "Status:   " + "Please Clear Active Order First" + "\n";
            }
            else {
                OrderModel order = new OrderModel() ;
                order.setDrink(command.getDrink());
                order.setMilk(command.getMilk());
                order.setSize(command.getSize());
                order.setStatus("Ready for Payment");
                order.setRegister( command.getRegister() ) ;
                order.setPrice(priceCalculation(order.getDrink(), order.getSize()));

                message = "Starbucks Reserved Order" + "\n\n" +
                    "Drink: " + order.getDrink() + "\n" +
                    "Milk:  " + order.getMilk() + "\n" +
                    "Size:  " + order.getSize() + "\n" +
                    "Total: " + order.getPrice() + "\n" +
                    "\n" +
                    "Register: " + order.getRegister() + "\n" +
                    "Status:   " + order.getStatus() + "\n" ;
                mysql.save(order);
            }
        }
        else if ( action.equals("Get Order") ) {

            if (isActive) {
                message = "Starbucks Reserved Order" + "\n\n" +
                    "Register: " + activeOrder.getRegister() + "\n" +
                    "Status:   " + activeOrder.getStatus() + "\n\n" +
                    "Drink: " + activeOrder.getDrink() + "\n" +
                    "Milk:  " + activeOrder.getMilk() + "\n" +
                    "Size:  " + activeOrder.getSize() + "\n" +
                    "Total: " + activeOrder.getPrice() + "\n" +
                    "\n";
            }
            else {
                message = "Starbucks Reserved Order" + "\n\n" + 
                    "Register: " + command.getRegister() + "\n" +
                    "Status:   " + "Ready for New Order" + "\n";
            }
        } 
        else if ( action.equals("Clear Order") ) {
            if (activeOrder == null) {
                message = "Starbucks Reserved Order" + "\n\n" +
                    "Register: " + command.getRegister() + "\n" +
                    "Status:   " + "No Active Order to Clear"+ "\n" ;
            }
            else {
                activeOrder.setActiveOrder(false);
                activeOrder.setStatus("Cleared");
                mysql.save(activeOrder);
                message = "Starbucks Reserved Order" + "\n\n" +
                    "Register: " + command.getRegister() + "\n" +
                    "Status:   " + "Ready for New Order"+ "\n" ;
            }        
        }         
        model.addAttribute("message", message);

        // OrderModel tmp;

        // String store = order.getStores();

        // OrderModel findOrderModelByStore = new OrderModel();
        // findOrderModelByStore.setStore(store);
        // Example<OrderModel> findOrderModelByStoreExample = Example.of(findOrderModelByStore);
        // Optional<OrderModel> findOrderModelByStoreRes = query.findOne(findOrderModelByStoreExample);

        // if (findOrderModelByStoreRes.isPresent() && action.equals("Place Order")) {
        //     model.addAttribute("orderPlaced", "Order placed for this register already, please clear order first");
        //     return "starbucks";
        // }
        // else if (findOrderModelByStoreRes.isPresent() && action.equals("Get Order")) {
        //     tmp = findOrderModelByStoreRes.get();
        //     String result =
        //         tmp.getDrink() + "\n" + 
        //         tmp.getSize() + "\n" +
        //         tmp.getMilk() + "\n" + 
        //         tmp.getPrice() + "\n" + 
        //         tmp.getStore();
        //     model.addAttribute("orderPlaced", result);
        //     return "starbucks";
        // }
        // else if (findOrderModelByStoreRes.isPresent() && action.equals("Clear Order")) {
        //     mysql.deleteByStore(store);
        //     model.addAttribute("orderPlaced", "Order cleared");
        //     return "starbucks";
        // }
        // else if (!(findOrderModelByStoreRes.isPresent()) && action.equals("Get Order") || action.equals("Clear Order")) {
        //     // System.out.println(!findOrderModelByStoreRes.isPresent());
        //     // System.out.println(store);
        //     model.addAttribute("orderPlaced", "Please place an order first");
        //     return "starbucks";
        // }
        
        // Random rand = new Random();
        // int randomInt = rand.nextInt(4);
        // String orderSize = size[randomInt];
        // String orderMilk = milk[rand.nextInt(4)];
        // String orderDrink = drinks[rand.nextInt(4)];
        // String orderPrice = prices[randomInt];

        // OrderModel orderModel = new OrderModel();
        // orderModel.setSize(orderSize);
        // orderModel.setMilk(orderMilk);
        // orderModel.setDrink(orderDrink);
        // orderModel.setPrice(orderPrice);
        // orderModel.setStore(order.getStores());

        // String result =
        //     orderDrink + "\n" + 
        //     orderSize + "\n" +
        //     orderMilk + "\n" + 
        //     orderPrice + "\n" + 
        //     order.getStores();

        // model.addAttribute("orderPlaced", result);

        // mysql.save(orderModel);

        return "starbucks";

    }

}