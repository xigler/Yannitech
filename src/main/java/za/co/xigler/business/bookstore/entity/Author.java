package za.co.xigler.business.bookstore.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="AUTHOR")
public class Author implements Serializable {

	private static final long serialVersionUID = 3521255728843179237L;
	
	private long id;
	private String name;
	private Book book;
	
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

	@Column(name ="NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "BOOK_ID", foreignKey = @ForeignKey(name = "BOOK_FK01"))
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
}
