package com.packt.spring.main;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.packt.spring.service.BookService;
public class SpringMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        BookService BookService = ctx.getBean("BookService", BookService.class);
        System.out.println(BookService.getBook().getName());
        BookService.getBook().setName("Packt");
        BookService.getBook().throwException();
        ctx.close();
    }
}
