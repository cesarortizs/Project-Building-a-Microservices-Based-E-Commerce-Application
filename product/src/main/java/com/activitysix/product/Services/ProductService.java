package com.activitysix.product.Services;

import com.activitysix.product.Models.Product;
import com.activitysix.product.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    public void addNewProduct(Product newTask)
    {
        productRepository.save(newTask);
    }

    public void updateProduct(Product updatedProduct, int id)
    {
        Product existingProduct = productRepository.findById(id).get();

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setStock(updatedProduct.getStock());
        existingProduct.setPrice(updatedProduct.getPrice());
        productRepository.save(existingProduct);
    }

    public void deleteProduct(int id)
    {
        productRepository.deleteById(id);
    }

    public List<Product> getOrderProducts(List<Integer> productIds)
    {
        List<Product> products = new ArrayList<Product>();

        for(int id : productIds)
        {
            products.add(productRepository.findById(id).get());
        }

        return products;
    }

}
