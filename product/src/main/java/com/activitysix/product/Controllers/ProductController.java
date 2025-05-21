package com.activitysix.product.Controllers;

import com.activitysix.product.Models.Product;
import com.activitysix.product.Repositories.ProductRepository;
import com.activitysix.product.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @PostMapping("/products")
    public void addNewProduct(@RequestBody Product newTask)
    {
        productService.addNewProduct(newTask);
    }

    @PutMapping("/products/{id}")
    public void updateProduct(@RequestBody Product updatedProduct, @PathVariable("id") int id)
    {
        productService.updateProduct(updatedProduct, id);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") int id)
    {
        productService.deleteProduct(id);
    }

    @PostMapping("/orderProducts")
    public List<Product> getOrderProducts(@RequestBody List<Integer> productIds)
    {
        return productService.getOrderProducts(productIds);
    }
}
