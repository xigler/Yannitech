package za.co.xigler.business.bookstore.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import za.co.xigler.business.bookstore.control.BookStoreManager;

@Stateless
@Path("bookstore")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
public class BookStoreResource {
	
	//private BookStoreManager bookStoreManager;

	public BookStoreResource(){}
	
/*	@Inject
	public BookStoreResource(BookStoreManager bookStoreManager) {
		this.bookStoreManager = bookStoreManager;
	}
	
	@GET
	@Path("/book")
	public Response retrieveAllBooks() {
		bookStoreManager.retrieveAllBooks();
		return null;
	}
	*/
	
	
}
