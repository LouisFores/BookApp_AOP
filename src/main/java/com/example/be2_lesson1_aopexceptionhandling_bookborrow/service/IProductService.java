package com.example.be2_lesson1_aopexceptionhandling_bookborrow.service;


import com.example.be2_lesson1_aopexceptionhandling_bookborrow.model.Book;
import com.example.be2_lesson1_aopexceptionhandling_bookborrow.model.Product;

import java.util.Optional;


public interface IProductService {
    Iterable<Product> findAll();
    Optional<Product> findById(Long id);
    void save(Product product);

}
