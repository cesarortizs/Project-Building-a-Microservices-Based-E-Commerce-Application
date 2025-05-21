package com.activitysix.payment.Controllers;

import com.activitysix.payment.Feign.OrderInterface;
import com.activitysix.payment.Models.Payment;
import com.activitysix.payment.Models.PaymentDTO;
import com.activitysix.payment.Repositories.PaymentRepository;
import com.activitysix.payment.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/payments")
    public void addPayment(@RequestBody Payment newPayment)
    {
        paymentService.addPayment(newPayment, 2);
    }
}
