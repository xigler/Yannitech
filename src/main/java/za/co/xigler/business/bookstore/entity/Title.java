package za.co.xigler.business.bookstore.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name ="TITLE")
public class Title implements Serializable {

	private static final long serialVersionUID = 266059239645871146L;

	private long id;
	private String value;
	private String lang;
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

	@Column(name ="VALUE")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	@Column(name ="LANGUAGE")
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}

	@OneToOne(optional=false, mappedBy="title",fetch = FetchType.EAGER,cascade=CascadeType.ALL)
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
