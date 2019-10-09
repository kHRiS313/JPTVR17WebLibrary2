/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + Objects.hashCode(this.author);
        hash = 17 * hash + Objects.hashCode(this.isbn);
        hash = 17 * hash + this.publishedYear;
        hash = 17 * hash + this.quantity;
        hash = 17 * hash + this.countInLibrary;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (this.publishedYear != other.publishedYear) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (this.countInLibrary != other.countInLibrary) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.isbn, other.isbn)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    
    
}
