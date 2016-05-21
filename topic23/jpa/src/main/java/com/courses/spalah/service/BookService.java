package com.courses.spalah.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

import com.courses.spalah.domain.Book;
import com.courses.spalah.domain.BookShelf;

/**
 * @author Ievgen Tararaka
 */
public class BookService {
    private EntityManager entityManager;

    public BookService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Book findById(Long id) {
        entityManager.getTransaction().begin();

        Book foundBook = entityManager.find(Book.class, id);

        entityManager.getTransaction().commit();
        entityManager.close();

        return foundBook;
    }

    public void save(Book book) {
        entityManager.getTransaction().begin(); // вручную открываем транзакцию
        entityManager.persist(book); // сохранять сущности очень просто. Вызываем метод persist.
        entityManager.getTransaction().commit(); // закрываем транзакцию
    }

    public void save(BookShelf bookShelf) {
        entityManager.getTransaction().begin();
        entityManager.persist(bookShelf); // сохранять сущности очень просто. Вызываем метод persist.
        entityManager.getTransaction().commit();
    }

    public List<Book> getBooks() {
        entityManager.getTransaction().begin();
        // квери для выборки всех книг
        List<Book> books = entityManager.createQuery("from Book", Book.class).getResultList();
        entityManager.getTransaction().commit();
        return books;
    }

    public List<BookShelf> getBookShelves() {
        entityManager.getTransaction().begin();
        // квери для выборки всех полок
        List<BookShelf> bookShelves = entityManager.createQuery("from BookShelf", BookShelf.class).getResultList();
        entityManager.getTransaction().commit();
        return bookShelves;
    }

    public Long countBooks() {
        entityManager.getTransaction().begin();
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(b) FROM Book b", Long.class);
        entityManager.getTransaction().commit();
        return query.getSingleResult();
    }

    public void updateBook(Long bookId, Book book) {
        entityManager.getTransaction().begin();
        // Выполняем Update квери с дополнительными параметрами
        Query query = entityManager.createQuery("UPDATE Book Set bookName=:name, author=:author, " +
                "publishDate=:date, bookShelf=:shelf WHERE id=:id");
        query.setParameter("id", bookId);
        query.setParameter("name", book.getBookName());
        query.setParameter("author", book.getAuthor());
        query.setParameter("date", book.getPublishDate());
        query.setParameter("shelf", book.getBookShelf());
        query.executeUpdate();

        entityManager.getTransaction().commit();
    }
}
