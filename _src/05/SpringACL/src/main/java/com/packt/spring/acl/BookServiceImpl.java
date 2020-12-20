package com.packt.spring.acl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BookServiceImpl implements BookService {

    private Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    private Map<Long, Book> Books = new HashMap<>();

    @Resource
    private MutableAclService aclService;

    @Override
    @Transactional
    public void grantPermission(String principal, Book Book, 
		Permission[] permissions) {
        LOGGER.error("Grant {} permission to principal {} on Book {}", 
			permissions, principal, Book);
        ObjectIdentity oi = new ObjectIdentityImpl(Book.class, 
			Book.getId());
        Sid sid = new PrincipalSid(principal);

        MutableAcl acl;
        try {
            acl = (MutableAcl) aclService.readAclById(oi);
        } catch (NotFoundException nfe) {
            acl = aclService.createAcl(oi);
        }

        for (Permission permission : permissions) {
            switch (permission) {
                case READ:
                    acl.insertAce(acl.getEntries().size(), BasePermission.READ, 
					sid, true);
                    break;
                case WRITE:
                    acl.insertAce(acl.getEntries().size(), BasePermission.WRITE, 
					sid, true);
                    break;
            }
        }
        aclService.updateAcl(acl);
    }

    @Override
    public void createBook(Book Book) {
        LOGGER.info("Create Book: {}", Book);
        Books.put(Book.getId(), Book);
    }

    @Override
    @PreAuthorize("hasPermission(#id, 'com.packt.spring.acl.Book', 'read')	or hasRole('ADMIN')")
    public Book findBookById(long id) {
        return Books.get(id);
    }

    @Override
    @PreAuthorize("hasPermission(#Book, 'write') or hasRole('ADMIN')")
    public void updateBook(Book Book) {
        Books.put(Book.getId(), Book);
    }

    @Override
    @PostFilter("hasPermission(filterObject, 'read') or hasRole('ADMIN')")
    public List<Book> findAllBooks() {
        return new ArrayList<>(Books.values());
    }
}
