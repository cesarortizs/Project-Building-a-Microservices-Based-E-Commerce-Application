package com.activitysix.user.controllers;

import com.activitysix.user.feign.OrderInterface;
import com.activitysix.user.feign.PaymentInterface;
import com.activitysix.user.models.Order;
import com.activitysix.user.models.Payment;
import com.activitysix.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderInterface orderInterface;

    @Autowired
    PaymentInterface paymentInterface;

    @PostMapping("/newOrder")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void createNewOrder(@RequestBody Order newOrder, Authentication authentication)
    {
        orderInterface.addOrder(newOrder);
    }

    @PostMapping("/newPayment")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void createNewPayment(@RequestBody Payment newPayment, Authentication authentication)
    {
        paymentInterface.addPayment(newPayment);
    }
}
