package com.activitysix.order.Controllers;

import com.activitysix.order.Feign.ProductInterface;
import com.activitysix.order.Models.Order;
import com.activitysix.order.Models.PaymentDTO;
import com.activitysix.order.Models.Product;
import com.activitysix.order.Models.Status;
import com.activitysix.order.Repositories.OrderRepository;
import com.activitysix.order.Repositories.StatusRepository;
import com.activitysix.order.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public List<Order> getAllOrders()
    {
        return orderService.getAllOrders();
    }

    @PostMapping("/orders")
    public void addOrder(@RequestBody Order newOrder)
    {
        orderService.addOrder(newOrder);
    }

    @PutMapping("/updateOrderStatus")
    public void updateOrderStatus(@RequestBody PaymentDTO paymentDTO)
    {
        orderService.updateOrderStatus(paymentDTO);
    }
}
