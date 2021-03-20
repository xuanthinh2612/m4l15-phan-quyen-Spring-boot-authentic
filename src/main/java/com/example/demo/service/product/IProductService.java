package com.example.demo.service.product;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService extends IService<Product> {


    Page<Product> findByCategory(Category category,Pageable pageable);
    Page<Product> findByName(String name,Pageable pageable);

}
