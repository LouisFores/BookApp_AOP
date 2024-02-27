package com.example.be2_lesson1_aopexceptionhandling_bookborrow.service;

import com.example.be2_lesson1_aopexceptionhandling_bookborrow.model.Book;
import com.example.be2_lesson1_aopexceptionhandling_bookborrow.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService implements IBookService{
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByCode(int code) {
        return bookRepository.findBookByNumberCode(code);
    }

    @Override
    public void remove(Long id) {
        bookRepository.deleteById(id);
    }
}
