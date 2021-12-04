package application;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.classes.Estante;
import com.classes.Livro;

public class BookShelfTest {

	public static void main(String[] args) {
		Livro livro1 = new Livro(120,"Terror");
		Livro livro2 = new Livro(150,"Terror");
		Livro livro3 = new Livro(170,"Terror");
		List<Livro> livros = new ArrayList<>();
		livros.add(livro3);
		livros.add(livro2);
		livros.add(livro1);
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		


		Estante estante = new Estante(livros,1,"a");
		entityManager.getTransaction().begin();
		entityManager.persist(estante);
		entityManager.getTransaction().commit();
		
		
		

		entityManager.getTransaction().begin();
		entityManager.persist(livro3);
		entityManager.persist(livro2);
		entityManager.persist(livro1);
		entityManager.getTransaction().commit();
		
	
		Livro livroFounded = entityManager.find(Livro.class, 1);
		Estante estanteFounded = entityManager.find(Estante.class, 1);
		
		entityManager.getTransaction().begin();
		estanteFounded.setBookListName("Terror");
	
		entityManager.getTransaction().commit();
		

		Estante estanteFounded2 = entityManager.find(Estante.class, 1);
		
		List<Livro> livros2 = estanteFounded2.getBooks();
		
		entityManager.close();
		System.out.println(livroFounded);
		System.out.println(estanteFounded2);

	}

}
