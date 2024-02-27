package com.example.be2_lesson1_aopexceptionhandling_bookborrow.model;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int number;
    private int numberBookRemain;
    private int numberCode;

    public Book() {}

    public Book(Long id, String name, int number, int numberCode) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.numberCode = numberCode;
    }

    public Book(Long id, String name, int number, int numberBookRemain, int numberCode) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.numberBookRemain = numberBookRemain;
        this.numberCode = numberCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberCode() {
        return numberCode;
    }

    public void setNumberCode(int numberCode) {
        this.numberCode = numberCode;
    }

    public int getNumberBookRemain() {
        return numberBookRemain;
    }

    public void setNumberBookRemain(int numberBookRemain) {
        this.numberBookRemain = numberBookRemain;
    }
}
