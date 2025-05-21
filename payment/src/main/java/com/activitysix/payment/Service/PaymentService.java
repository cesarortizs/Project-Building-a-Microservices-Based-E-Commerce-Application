package com.activitysix.payment.Service;

import com.activitysix.payment.Feign.OrderInterface;
import com.activitysix.payment.Models.Payment;
import com.activitysix.payment.Models.PaymentDTO;
import com.activitysix.payment.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    OrderInterface orderInterface;

    public void addPayment(Payment newPayment, int statusId)
    {
        paymentRepository.save(newPayment);

        PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setOrderId(newPayment.getOrderId());
        paymentDTO.setStatusId(statusId);

        orderInterface.updateOrderStatus(paymentDTO);
    }
}
