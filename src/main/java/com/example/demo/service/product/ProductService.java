package com.example.demo.service.product;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findALl() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Page<Product> findALl(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }


    @Override
    public Page<Product> findByCategory(Category category,Pageable pageable) {
        return productRepository.findAllByCategory(category,pageable);
    }

    @Override
    public Page<Product> findByName(String name,Pageable pageable) {
        return productRepository.findAllByName(name,pageable);
    }
}
