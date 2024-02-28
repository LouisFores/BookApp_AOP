package com.example.be2_lesson1_aopexceptionhandling_bookborrow.repository;

import com.example.be2_lesson1_aopexceptionhandling_bookborrow.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
}

