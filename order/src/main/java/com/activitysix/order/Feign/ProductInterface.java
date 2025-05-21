package com.activitysix.order.Feign;

import com.activitysix.order.Models.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("product")
public interface ProductInterface {

    @PostMapping("/orderProducts")
    public List<Product> getOrderProducts(@RequestBody List<Integer> productIds);
}
