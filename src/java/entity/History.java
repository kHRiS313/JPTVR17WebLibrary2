/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Melnikov
 */
@Entity
public class History implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Reader reader;
    @OneToOne
    private Book book;
    @Temporal(TemporalType.TIMESTAMP)
    private Date takeBook;
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnBook;

    public History() {
    }

    public History(Long id, Reader reader, Book book, Date takeBook, Date returnBook) {
        this.id = id;
        this.reader = reader;
        this.book = book;
        this.takeBook = takeBook;
        this.returnBook = returnBook;
    }

    public Date getReturnBook() {
        return returnBook;
    }

    public void setReturnBook(Date returnBook) {
        this.returnBook = returnBook;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getTakeBook() {
        return takeBook;
    }

    public void setTakeBook(Date takeBook) {
        this.takeBook = takeBook;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.reader);
        hash = 41 * hash + Objects.hashCode(this.book);
        hash = 41 * hash + Objects.hashCode(this.takeBook);
        hash = 41 * hash + Objects.hashCode(this.returnBook);
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
        final History other = (History) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.reader, other.reader)) {
            return false;
        }
        if (!Objects.equals(this.book, other.book)) {
            return false;
        }
        if (!Objects.equals(this.takeBook, other.takeBook)) {
            return false;
        }
        if (!Objects.equals(this.returnBook, other.returnBook)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "History{" 
                + "id=" + id 
                + ", reader=" + reader.getName()
                + " " + reader.getSurname()
                + ", book=" + book.getName()
                + ", takeBook=" + takeBook 
                + ", returnBook=" + returnBook 
                + '}';
    }
    
    
}
