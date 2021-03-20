package com.example.demo.repository;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {

    Page<Product> findAllByCategory(Category category,Pageable pageable);

    @Query(value = "select * from product where Product.name like ?", nativeQuery = true)
    Page<Product> findAllByName(String name,Pageable pageable);

}
