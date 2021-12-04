package com.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity

public class Estante {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookShelfID;
	
    @OneToMany(
            mappedBy = "estante",
            cascade = CascadeType.REFRESH,
            orphanRemoval = true
    )
	private List<Livro> books = new ArrayList<>();;

	@Column(nullable = false)
	private int cubicle;
	
	@Column(nullable = false)
	private String bookListName;
	
	public int getBookShelfID() {
		return bookShelfID;
	}

	
	public Estante( List<Livro> books, int cubicle, String bookListName) {

		this.bookShelfID = bookShelfID;
		this.books = books;
		this.cubicle = cubicle;
		this.bookListName = bookListName;
	}



	
	public void setBookShelfID(int bookShelfID) {
		this.bookShelfID = bookShelfID;
	}

	public List<Livro> getBooks() {
		return books;
	}

	public void setBooks(List<Livro> books) {
		this.books = books;
	}

	public int getCubicle() {
		return cubicle;
	}

	public void setCubicle(int cubicle) {
		this.cubicle = cubicle;
	}

	public Estante( List<Livro> books, int cubicle) {

		this.books = books;
		this.cubicle = cubicle;
	}

	public String getBookListName() {
		return bookListName;
	}

	public void setBookListName(String bookListName) {
		this.bookListName = bookListName;
	}

	@Override
	public String toString() {
		return "Estante [bookShelfID=" + bookShelfID + ", books=" + books + ", cubicle=" + cubicle + ", bookListName="
				+ bookListName + "]";
	}

 
	
	
	
}
