package za.co.xigler.business.bookstore.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="BOOK")
public class Book  implements Serializable {

	private static final long serialVersionUID = -4953890441470848217L;

	private long id;
	private Title title;
	private int year;
	private double price;
	private List<Author> authors = new ArrayList<Author>();
	private String category;
	private BookStoreEntity bookStore;

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


	@OneToOne(optional=false,fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="TITLE_ID" )
	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	@Column(name ="YEAR")
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Column(name ="PRICE")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@JoinTable(name= "BOOK_AUTHOR",
			joinColumns=@JoinColumn(name ="BOOK_ID"),
			inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	@Column(name ="CATEGORY")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "BOOK_STORE_ID", foreignKey = @ForeignKey(name = "BOOK_STORE_FK01"))
	public BookStoreEntity getBookStore() {
		return bookStore;
	}

	public void setBookStore(BookStoreEntity bookStore) {
		this.bookStore = bookStore;
	}

}
