package com.example.be2_lesson1_aopexceptionhandling_bookborrow.service;

import com.example.be2_lesson1_aopexceptionhandling_bookborrow.model.Book;
import com.example.be2_lesson1_aopexceptionhandling_bookborrow.repository.BookRepository;

import java.util.Optional;

public interface IBookService {
    Iterable<Book> findAll();

    void save(Book book);

    Optional<Book> findById(Long id);

    Optional<Book> findByCode(int code);
    void remove(Long id);
}
