package com.packt.spring;

public class BookService {
	private String name;
	private String url;

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void printName() {
		System.out.println("Book name : " + this.name);
	}

	public void printURL() {
		System.out.println("Book website : " + this.url);
	}

	public void printThrowException() {
		throw new IllegalArgumentException();
	}

}
