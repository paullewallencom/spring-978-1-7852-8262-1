package com.packt.spring.acl;

import java.util.List;

public interface BookService {

    public enum Permission {
        READ, WRITE
    }

    public void createBook(Book book);

    public Book findBookById(long id);

    public List<Book> findAllBooks();

    public void updateBook(Book book);

    public void grantPermission(String principal, Book book, 
		Permission[] permissions);
}
