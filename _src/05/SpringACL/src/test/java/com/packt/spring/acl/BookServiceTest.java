package com.packt.spring.acl;

import com.packt.spring.acl.Book;
import com.packt.spring.acl.Book;
import com.packt.spring.acl.BookService;
import com.packt.spring.acl.BookService;
import java.util.Collections;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml", "/applicationContext-test.xml"})
public class BookServiceTest {

    @Resource
    private BookService bookService;

    @Resource
    private MutableAclService aclService;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void init() {
        SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("admin1", "pass1", Collections.
                singletonList(new SimpleGrantedAuthority("ADMIN"))));

        aclService.deleteAcl(new ObjectIdentityImpl(Book.class, 1), true);
        aclService.deleteAcl(new ObjectIdentityImpl(Book.class, 2), true);

        Book Book1 = new Book(1, "test");
        bookService.createBook(Book1);

        bookService.grantPermission("user1", Book1, new BookService.Permission[]
			{BookService.Permission.READ, BookService.Permission.WRITE});
        bookService.grantPermission("user2", Book1, new BookService.Permission[]
			{BookService.Permission.READ});

        Book Book2 = new Book(2, "test");
        bookService.createBook(Book2);
        bookService.grantPermission("user1", Book2, new BookService.Permission[]
			{BookService.Permission.READ, BookService.Permission.WRITE});
    }




    @Test
    public void testPersistence() {
        assertEquals(5, jdbcTemplate.queryForObject("SELECT COUNT(*) FROM ACL_ENTRY", Integer.class).intValue());
    }

    @Test
    public void testUserWithReadAndWrite() {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("user1", "pass1", Collections.
                singletonList(new SimpleGrantedAuthority("USER"))));
        bookService.findBookById(1);
        bookService.updateBook(new Book(1, "test"));
    }

    @Test
    public void testUserWithRead() {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("user2", "pass2", Collections.
                singletonList(new SimpleGrantedAuthority("USER"))));
        bookService.findBookById(1);
        try {
            bookService.updateBook(new Book(1, "test"));
            fail("Access denied");
        } catch (AccessDeniedException ade) {
        }
    }

    @Test
    public void testUserWithNothing() {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("user3", "pass3", Collections.
                singletonList(new SimpleGrantedAuthority("USER"))));
        try {
            bookService.findBookById(1);
        } catch (AccessDeniedException ade) {
        }
        try {
            bookService.updateBook(new Book(1, "test"));
            fail("Access denied");
        } catch (AccessDeniedException ade) {
        }
    }

    @Test
    public void testFilterAdmin() {
        List<Book> Books = bookService.findAllBooks();
        assertEquals(2, Books.size());
    }

    @Test
    public void testFilterUser1() {
        SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("user1", "pass1", Collections.
                singletonList(new SimpleGrantedAuthority("USER"))));
        List<Book> Books = bookService.findAllBooks();
        assertEquals(2, Books.size());
    }

    @Test
    public void testFilterUser2() {
        SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("user2", "pass2", Collections.
                singletonList(new SimpleGrantedAuthority("USER"))));
        List<Book> Books = bookService.findAllBooks();
        assertEquals(1, Books.size());
    }

    @Test
    public void testFilterUser3() {
        SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("user3", "pass3", Collections.
                singletonList(new SimpleGrantedAuthority("USER"))));
        List<Book> Books = bookService.findAllBooks();
        assertEquals(0, Books.size());
    }

    @Resource
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


}
