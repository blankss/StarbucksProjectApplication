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
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


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

   public static String format(String jsonString) {
        String res = jsonString.substring(jsonString.indexOf("\"size\""), jsonString.length() - 1);
        res = res.replace("\"", "");
        return res;

   }
    @Value("${starbucks.client.apikey}") String API_KEY ;
    @Value("${starbucks.client.apihost}") String API_HOST ;
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

        // boolean isActive = false;
        // List<OrderModel> list = mysql.findOrderModelByRegister(command.getRegister());
        // OrderModel activeOrder = null;
        // for (OrderModel o : list) {
        //     if (o.getActiveOrder()) {
        //         isActive = true;
        //         activeOrder = o;
        //     }
        // }

        String resourceUrl = "";

        //IP Address
        String ip = "http://" + API_HOST;
        //api key
        // String apiKey = "2H3fONTa8ugl1IcVS7CjLPnPIS2Hp9dJ";
        RestTemplate restTemplate = new RestTemplate();

        if ( action.equals("Place Order") ) {

            resourceUrl = ip + "order/register/" + command.getRegister() + "?apikey=" + API_KEY;
            // get response as POJO
            OrderModel order = new OrderModel() ;
            order.setDrink(command.getDrink());
            order.setMilk(command.getMilk());
            order.setSize(command.getSize());
            order.setStatus("Ready for Payment");
            order.setRegister( command.getRegister() ) ;
            // order.setPrice(priceCalculation(order.getDrink(), order.getSize()));

            // pretty print JSON
            try {
                HttpEntity<OrderModel> newOrderRequest = new HttpEntity<OrderModel>(order) ;
                ResponseEntity<OrderModel> newOrderResponse = restTemplate.postForEntity(resourceUrl, newOrderRequest, OrderModel.class);
                OrderModel newOrder = newOrderResponse.getBody();
                ObjectMapper objectMapper = new ObjectMapper() ;
                String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(newOrder);
                System.out.println( jsonString) ;
                int index = jsonString.indexOf("$");
                String price = jsonString.substring(index, jsonString.indexOf("\"", index));
                order.setPrice(price);
                String res = format(jsonString);
                System.out.println(price);
                message = "\n" + res ;
            }
            catch ( Exception e ) {
                message = "Starbucks Reserved Order" + "\n\n" +
                    "Register: " + command.getRegister() + "\n" +
                    "Status:   " + "Active Order Exists"+ "\n" ;
            }
        }
        else if ( action.equals("Get Order") ) {
            resourceUrl = ip + "order/register/" + command.getRegister() +"?apikey=" + API_KEY;
            // resourceUrl = ip + "ping/?apikey=" + API_KEY;
            // get response as string
            try {
                ResponseEntity<String> stringResponse = restTemplate.getForEntity(resourceUrl, String.class);
                message = stringResponse.getBody();
            }
            catch (Exception e) {
                message = "Starbucks Reserved Order" + "\n\n" +
                    "Register: " + command.getRegister() + "\n" +
                    "Status:   " + "Ready for New Order"+ "\n" ;
            }
            
        } 
        else if ( action.equals("Clear Order") ) {
            resourceUrl = ip + "order/register/" + command.getRegister() + "?apikey=" + API_KEY;
            restTemplate.delete(resourceUrl);
            try {
                message = "Starbucks Reserved Order" + "\n\n" +
                    "Register: " + command.getRegister() + "\n" +
                    "Status:   " + "Ready for New Order"+ "\n" ;
            }
            catch (Exception e) {
                message = "Starbucks Reserved Order" + "\n\n" +
                    "Register: " + command.getRegister() + "\n" +
                    "Status:   " + "No Active Order to Clear"+ "\n" ;
            }
            
            // if (activeOrder == null) {
            //     message = "Starbucks Reserved Order" + "\n\n" +
            //         "Register: " + command.getRegister() + "\n" +
            //         "Status:   " + "No Active Order to Clear"+ "\n" ;
            // }
            // else {
            //     activeOrder.setActiveOrder(false);
            //     activeOrder.setStatus("Cleared");
            //     mysql.save(activeOrder);
            //     message = "Starbucks Reserved Order" + "\n\n" +
            //         "Register: " + command.getRegister() + "\n" +
            //         "Status:   " + "Ready for New Order"+ "\n" ;
            // }        
        }         
        model.addAttribute("message", message);

        return "starbucks";

    }

}