package application;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.classes.Aluno;
import com.classes.Estado;

public class Mini_CRUD {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		// 2.1 - Criar instancias para serem adicionadas no banco de dados


		//CREATE
		Estado estadoParaAdicionar = new Estado("Rio de Janeiro", "RJ");
		Aluno alunoParaAdicionar = new Aluno("Daniel", 29, estadoParaAdicionar);

		// 2.2 - Iniciar uma trasacao para adiconar as instancias no banco de dados
		entityManager.getTransaction().begin();

		entityManager.persist(estadoParaAdicionar);
		entityManager.persist(alunoParaAdicionar);

		entityManager.getTransaction().commit();


		// 3 - Encerrar o gerenciador de entidades e encerrar a fabrica de gerenciadores de entidade.

		//SEARCH

		Estado estadoEncontrado = entityManager.find(Estado.class, 1);
		Aluno alunoEncontrado = entityManager.find(Aluno.class, 1);

		  System.out.println(estadoEncontrado);
		 System.out.println(alunoEncontrado);

		//UPDATE
		    entityManager.getTransaction().begin();
	alunoEncontrado.setNome("Karam");
	 alunoEncontrado.setIdade(20);

	System.out.println(alunoEncontrado);

	entityManager.getTransaction().commit();
		// 5 - Remover uma entidade
		//	        entityManager.getTransaction().begin();
		//
		//	        entityManager.remove(alunoEncontrado);
		//
		//	        entityManager.getTransaction().commit();

		 entityManager.close();
	        entityManagerFactory.close();

	}
}
