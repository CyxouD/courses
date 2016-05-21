package com.courses.spalah.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Ievgen Tararaka
 */
@Entity
@Table(name = "book_shelves")
public class BookShelf {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shelf_name", nullable = false)
    private String shelfName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookShelf", orphanRemoval = true)
    private Collection<Book> books = new ArrayList<Book>();

    public BookShelf() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShelfName() {
        return shelfName;
    }

    public void setShelfName(String shelfName) {
        this.shelfName = shelfName;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "BookShelf{" +
                "id=" + id +
                ", shelfName='" + shelfName + '\'' +
                ", books=" + books +
                '}';
    }
}
