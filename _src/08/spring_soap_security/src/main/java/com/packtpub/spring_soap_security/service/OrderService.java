
package com.packtpub.liverestaurant.service;





public interface OrderService {

 
	 String placeOrder( String fName,String lName,String refNumber);
    boolean cancelOrder( String refNumber );
}
