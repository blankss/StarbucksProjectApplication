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

    private String[] size = {"short", "tall", "grande", "venti"};
    private String[] milk = {"whole", "non-fat", "2%", "soy"};
    private String[] drinks = {"mocha", "latte", "macchiato", "cappuccino"};
    private String[] prices = {"$2.45","$2.95", "$3.65", "$4.45"};

    @GetMapping
    public String getAction( @ModelAttribute("command") Command command, 
                            Model model) {

    	String message = "Hello Starbucks!" ;

        model.addAttribute("message", message);

        return "starbucks" ;

    }

    @PostMapping
    @Transactional
    public String postAction(@Valid @ModelAttribute("command") Command order,  
                            @RequestParam(value="action", required=true) String action,
                            Errors errors, Model model, HttpServletRequest request) {
        OrderModel tmp;

        String store = order.getStores();

        OrderModel findOrderModelByStore = new OrderModel();
        findOrderModelByStore.setStore(store);
        Example<OrderModel> findOrderModelByStoreExample = Example.of(findOrderModelByStore);
        Optional<OrderModel> findOrderModelByStoreRes = query.findOne(findOrderModelByStoreExample);

        if (findOrderModelByStoreRes.isPresent() && action.equals("Place Order")) {
            model.addAttribute("orderPlaced", "Order placed for this register already, please clear order first");
            return "starbucks";
        }
        else if (findOrderModelByStoreRes.isPresent() && action.equals("Get Order")) {
            tmp = findOrderModelByStoreRes.get();
            String result =
                tmp.getDrink() + "\n" + 
                tmp.getSize() + "\n" +
                tmp.getMilk() + "\n" + 
                tmp.getPrice() + "\n" + 
                tmp.getStore();
            model.addAttribute("orderPlaced", result);
            return "starbucks";
        }
        else if (findOrderModelByStoreRes.isPresent() && action.equals("Clear Order")) {
            mysql.deleteByStore(store);
            model.addAttribute("orderPlaced", "Order cleared");
            return "starbucks";
        }
        else if (!(findOrderModelByStoreRes.isPresent()) && action.equals("Get Order") || action.equals("Clear Order")) {
            // System.out.println(!findOrderModelByStoreRes.isPresent());
            // System.out.println(store);
            model.addAttribute("orderPlaced", "Please place an order first");
            return "starbucks";
        }
        
        Random rand = new Random();
        int randomInt = rand.nextInt(4);
        String orderSize = size[randomInt];
        String orderMilk = milk[rand.nextInt(4)];
        String orderDrink = drinks[rand.nextInt(4)];
        String orderPrice = prices[randomInt];

        OrderModel orderModel = new OrderModel();
        orderModel.setSize(orderSize);
        orderModel.setMilk(orderMilk);
        orderModel.setDrink(orderDrink);
        orderModel.setPrice(orderPrice);
        orderModel.setStore(order.getStores());

        String result =
            orderDrink + "\n" + 
            orderSize + "\n" +
            orderMilk + "\n" + 
            orderPrice + "\n" + 
            order.getStores();

        model.addAttribute("orderPlaced", result);

        mysql.save(orderModel);

        return "starbucks";

    }

}