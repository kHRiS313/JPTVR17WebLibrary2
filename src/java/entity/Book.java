/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Melnikov
 */
@Entity
public class Book implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    private String isbn;
    private int publishedYear;
    private int quantity;
    private int countInLibrary;

    public Book() {
    }

    public Book(String name, String author, String isbn, int publishedYear, int quantity) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.quantity = quantity;
        this.countInLibrary = quantity;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.setCountInLibrary(quantity - this.quantity + this.countInLibrary);
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name=" + name + ", author=" + author + ", isbn=" + isbn + ", publishedYear=" + publishedYear + ", quantity=" + quantity+ ", countInLibrary=" + countInLibrary + '}';
    }

    public int getCountInLibrary() {
        return countInLibrary;
    }

    public void setCountInLibrary(int countInLibrary) {
        this.countInLibrary = countInLibrary;
    }

    
    
    
}
