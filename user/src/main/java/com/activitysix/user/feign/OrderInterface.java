package com.activitysix.user.feign;

import com.activitysix.user.models.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("order")
public interface OrderInterface {

    @PostMapping("/orders")
    public void addOrder(@RequestBody Order newOrder);
}
