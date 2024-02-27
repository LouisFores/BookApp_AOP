package com.example.be2_lesson1_aopexceptionhandling_bookborrow.repository;

import com.example.be2_lesson1_aopexceptionhandling_bookborrow.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book, Long> {
    Optional<Book> findBookByNumberCode(int code);
}
