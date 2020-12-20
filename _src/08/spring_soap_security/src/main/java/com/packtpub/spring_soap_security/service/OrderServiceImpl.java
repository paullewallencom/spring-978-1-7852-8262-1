
package com.packtpub.spring_soap_security.service;

import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

	public String getBook( String fName,String lName,String refNumber){
    	return "Book-"+fName+"_"+lName+"_"+refNumber;
    }
    
    public boolean cancelBook( String refNumber ){
    	return true;
    }
}
