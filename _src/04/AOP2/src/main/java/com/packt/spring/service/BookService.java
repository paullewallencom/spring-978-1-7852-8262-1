package com.packt.spring.service;
import com.packt.spring.model.Book;
public class BookService {
    private Book Book;
    public Book getBook(){
        return this.Book;
    }
    public void setBook(Book e){
        this.Book=e;
    }
}
