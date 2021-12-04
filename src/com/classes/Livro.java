package com.classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bookID;
	
    @ManyToOne(fetch = FetchType.LAZY)
 
    private Estante estante;
    
    @Column(nullable = false)
    private int pagesNumber;
    
    @Column(nullable= false)
    private String bookListName;
    public Livro() {}
    
    
    
    public Livro(Estante estante, int pagesNumber, String bookListName) {

		this.estante = estante;
		this.pagesNumber = pagesNumber;
		this.bookListName = bookListName;
	}

    
    public Livro( int pagesNumber, String bookListName) {

	
		this.pagesNumber = pagesNumber;
		this.bookListName = bookListName;
	}

	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
	}



	public int getPagesNumber() {
		return pagesNumber;
	}

	public void setPagesNumber(int pagesNumber) {
		this.pagesNumber = pagesNumber;
	}

	public Estante getEstante() {
		return estante;
	}

	public void setEstante(Estante estante) {
		this.estante = estante;
	}



	public String getBookListName() {
		return bookListName;
	}



	public void setBookListName(String bookListName) {
		this.bookListName = bookListName;
	}



	@Override
	public String toString() {
		return "Livro [bookID=" + bookID + ", estante=" + estante + ", pagesNumber=" + pagesNumber + ", bookListName="
				+ bookListName + "]";
	}

	
    
    
}
