package za.co.xigler.business.bookstore.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="BOOK_STORE")
public class BookStoreEntity implements Serializable {

	private static final long serialVersionUID = 315071588024972618L;

	private long id;
	private List<Book> books = new ArrayList<Book>();
	
	@Id
	@GeneratedValue(generator = "ID_SEQUENCE", strategy= GenerationType.SEQUENCE)
	@SequenceGenerator(name = "ID_SEQUENCE" , allocationSize= 25, initialValue = 0, sequenceName = "ID_SEQUENCE")
	@Column(name ="ID")
	public Long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@JoinTable(name= "BOOK_STORE_BOOK",
			joinColumns=@JoinColumn(name ="BOOK_STORE_ID"),
			inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
