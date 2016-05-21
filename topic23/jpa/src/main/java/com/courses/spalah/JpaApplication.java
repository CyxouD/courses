package com.courses.spalah;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
import java.util.List;

import com.courses.spalah.domain.Book;
import com.courses.spalah.domain.BookShelf;
import com.courses.spalah.service.BookService;

/**
 * @author Ievgen Tararaka
 */
public class JpaApplication {
    private static final String PERSISTENCE_UNIT = "com.courses.spalah.jpa";

    public static void main(String[] args) {
        EntityManager entityManager = initEntityManager();

        BookShelf bookShelf = createAndPrintShelf(entityManager);

        createAndPrintBooks(entityManager, bookShelf);

        updateBooks(entityManager, bookShelf);

        entityManager.close();
    }

    private static void test() {
        // Используем Persistence класс для получения EntityManagerFactory.
        // Используем EntityManagerFactory для получения EntityManager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // создаем нашу сущность
        Book book1 = new Book();
        book1.setBookName("The Seasons");
        book1.setAuthor("Edward Young");
        book1.setPublishDate(new Date(System.currentTimeMillis()));

        // создаем транзакцию
        entityManager.getTransaction().begin();

        // сохраняем сущность
        entityManager.persist(book1);


        //делаем коммит
        entityManager.getTransaction().commit();

        // закрываем ентити менеджер
        entityManager.close();
    }

    private static BookShelf createAndPrintShelf(EntityManager entityManager) {
        BookService bookService = new BookService(entityManager);

        BookShelf bookShelf = new BookShelf();
        bookShelf.setShelfName("English Literature");
        bookService.save(bookShelf);

        System.out.println("------");
        System.out.println("Shelves: \n" + bookService.getBookShelves());
        return bookShelf;
    }

    private static List<Book> createAndPrintBooks(EntityManager entityManager, BookShelf bookShelf) {
        BookService bookService = new BookService(entityManager);

        Book book1 = new Book();
        book1.setBookName("The Seasons");
        book1.setAuthor("Edward Young");
        book1.setPublishDate(new Date(System.currentTimeMillis()));
        book1.setBookShelf(bookShelf);

        Book book2 = new Book();
        book2.setBookName("Robinson Crusoe");
        book2.setAuthor("Daniel Defoe");
        book2.setPublishDate(new Date(System.currentTimeMillis()));
        book2.setBookShelf(bookShelf);

        bookService.save(book1);
        bookService.save(book2);

        List<Book> books = bookService.getBooks();
        System.out.println("------");
        System.out.println("Books: \n" + books);

        System.out.println("------");
        System.out.println("Shelves: \n" + bookService.getBookShelves());

        System.out.println("------");
        System.out.println("Book count: \n" + bookService.countBooks());

        return books;
    }

    private static void updateBooks(EntityManager entityManager, BookShelf bookShelf) {
        BookService bookService = new BookService(entityManager);

        Book book3 = new Book();
        book3.setBookName("Test");
        book3.setAuthor("Test");
        book3.setPublishDate(new Date(System.currentTimeMillis() - 10000000));
        book3.setBookShelf(bookShelf);

        List<Book> books = bookService.getBooks();

        bookService.updateBook(books.get(books.size() - 1).getId(), book3);

        books = bookService.getBooks();

        System.out.println("------");
        System.out.println("Books: \n" + books);
    }

    private static EntityManager initEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
        return entityManagerFactory.createEntityManager();
    }
}
