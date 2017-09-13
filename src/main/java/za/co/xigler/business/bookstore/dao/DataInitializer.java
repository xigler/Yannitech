package za.co.xigler.business.bookstore.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import za.co.xigler.business.bookstore.dto.Bookstore;
import za.co.xigler.business.bookstore.dto.Bookstore.Book.Authors;
import za.co.xigler.business.bookstore.entity.Author;
import za.co.xigler.business.bookstore.entity.Book;
import za.co.xigler.business.bookstore.entity.BookStoreEntity;
import za.co.xigler.business.bookstore.entity.Title;
import za.co.xigler.common.jpa.crud.JPACrudService;

@Startup
@Singleton
public class DataInitializer {

	@Inject
	private JPACrudService jpaCrudService;

	public DataInitializer(){}

	

	@PostConstruct
	public void init() {

		// build domain entity object from xml
		Bookstore jaxbBookStore = buildDomainObject();

		BookStoreEntity bookStore = convertJAXBObjectToDomainObject(jaxbBookStore);

		// persist to db
		BookStoreEntity createdBookStore = jpaCrudService.create(bookStore);
		
		System.out.println("@@@ Bookstore " + createdBookStore);
	}

	private BookStoreEntity convertJAXBObjectToDomainObject(Bookstore jaxbBookStore) {
		// convert jaxb object to entity object
		BookStoreEntity bookStore = new BookStoreEntity();

		List<Book> books = new ArrayList<Book>();
		List<Bookstore.Book> jaxbBooks = jaxbBookStore.getBook();		

		for (Bookstore.Book jaxbBook : jaxbBooks) {
			Book book = new Book();
			List<Author> authors = new ArrayList<Author>();
			// loop thru jaxb book authors
			Authors jaxbBookAuthors = jaxbBook.getAuthors();		
			for (String authorName: jaxbBookAuthors.getAuthor()) {
				// create domain entity author
				Author author = new Author();
				author.setBook(book);
				author.setName(authorName);
				// add to list
				authors.add(author);
			}
			book.setAuthors(authors);
			book.setBookStore(bookStore);
			book.setCategory(jaxbBook.getCategory());
			book.setPrice(jaxbBook.getPrice());
			book.setYear(jaxbBook.getYear());
			
			
			Title title = new Title();
			title.setBook(book);
			title.setLang(jaxbBook.getTitle().getLang());
			title.setValue(jaxbBook.getTitle().getValue());
			book.setTitle(title);
			books.add(book);
			
			
			// add book to list
			bookStore.setBooks(books);
		}
		return bookStore;
	}

	private Bookstore buildDomainObject() {
		Bookstore bookstore = null;
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("bookstore.xml").getFile());
			JAXBContext jaxbContext = JAXBContext.newInstance(Bookstore.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			bookstore = (Bookstore) jaxbUnmarshaller.unmarshal(file);
			System.out.println("@@@ book store " + bookstore);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return bookstore;
	}

}
