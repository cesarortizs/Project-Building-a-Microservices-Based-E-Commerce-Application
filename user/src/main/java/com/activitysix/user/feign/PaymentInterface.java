package com.activitysix.user.feign;

import com.activitysix.user.models.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("payment")
public interface PaymentInterface {

    @PostMapping("/payments")
    public void addPayment(@RequestBody Payment newPayment);
}
