package com.activitysix.payment.Feign;

import com.activitysix.payment.Models.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("order")
public interface OrderInterface {

    @PutMapping("/updateOrderStatus")
    public void updateOrderStatus(@RequestBody PaymentDTO paymentDTO);
}
