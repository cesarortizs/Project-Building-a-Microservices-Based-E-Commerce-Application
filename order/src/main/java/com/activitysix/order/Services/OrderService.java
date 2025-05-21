package com.activitysix.order.Services;

import com.activitysix.order.Feign.ProductInterface;
import com.activitysix.order.Models.Order;
import com.activitysix.order.Models.PaymentDTO;
import com.activitysix.order.Models.Product;
import com.activitysix.order.Models.Status;
import com.activitysix.order.Repositories.OrderRepository;
import com.activitysix.order.Repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    ProductInterface productInterface;

    public List<Order> getAllOrders()
    {
        return orderRepository.findAll();
    }

    public void addOrder(Order newOrder)
    {
        if (validateOrderProducts(newOrder.getProductsId()))
        {
            orderRepository.save(newOrder);
        }
    }

    public void updateOrderStatus(PaymentDTO paymentDTO)
    {
        Order existingOrder = orderRepository.findById(paymentDTO.getOrderId()).get();

        Status newStatus = statusRepository.findById(paymentDTO.getStatusId()).get();

        existingOrder.setStatus(newStatus);

        orderRepository.save(existingOrder);
    }

    public boolean validateOrderProducts(List<Integer> productsId)
    {
        boolean validOrder = true;

        List<Product> products = productInterface.getOrderProducts(productsId);

        for(Product p : products)
        {
            if (p.getStock() <= 0)
            {
                validOrder = false;
            }
        }

        return validOrder;
    }
}
