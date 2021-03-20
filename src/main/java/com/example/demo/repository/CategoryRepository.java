package com.example.demo.repository;

import com.example.demo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


// add Generic target class and id dataType
@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
}