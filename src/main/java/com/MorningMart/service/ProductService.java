package com.MorningMart.service;

import com.MorningMart.entity.Product;
import com.MorningMart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(null);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    public void deleteImageById(Integer id) {
        Optional<Product> optionalImage = productRepository.findById(id);
        if (optionalImage.isPresent()) {
            Product image = optionalImage.get();
            // Delete image file from folder
            File file = new File("src/main/resources/static/images/products/" + image.getImage());
            if (file.exists()) {
                file.delete();
            }
            // Delete from database
            productRepository.deleteById(id);
        } else {
            // Handle if image with given ID doesn't exist
            throw new IllegalArgumentException("Image not found with ID: " + id);
        }
    }

}
