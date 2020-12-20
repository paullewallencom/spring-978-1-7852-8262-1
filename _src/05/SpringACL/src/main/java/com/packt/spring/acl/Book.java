package com.packt.spring.acl;

public class Book {

    private Long id;

    private String text;

    @Override
    public String toString() {
        return id + " " + text;
    }

    public Book(long id, String text) {
        this.id = id;
        this.text = text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {        return id;    }

    public String getText() {        return text;    }
}
