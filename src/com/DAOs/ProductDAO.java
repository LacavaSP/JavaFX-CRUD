package com.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.classes.Product;
import com.connection.Connect;

public class ProductDAO {


	public static void choose() throws InterruptedException {
		Scanner entrada = new Scanner(System.in);

		int r = 0;
		System.out.println("");
		System.out.println(">>============================<<");
		System.out.println("||       Product TABLE        ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||    1.Delete an register    ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||    2.Delete all registers  ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||      3.Search Register     ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||     4.List All Registers   ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||     5.Update an Register   ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||  6.Insert an new Register  ||");
		System.out.println(">>============================<<");
		System.out.println("");
		Thread.sleep(1);
		r = entrada.nextInt();

		switch (r) {
		case 1:
			delete();
			break;
		case 2:
			try {
				deleteAllItems();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			try {
				readByParameter();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			try {
				read();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 5:
			update();
			break;
		case 6:
			try {
				create();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("Invalid option!");
			break;
		}



	}

	public static void update() {
		try {
			tableTest();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Scanner entrada = new Scanner(System.in);	
		String sel = "SELECT * FROM `exerciciocap4.1`.produto;";
		String trunc = "SET SQL_SAFE_UPDATES = 0";

		int r = 0;


		try (Connection conn = Connect.connectione();
				PreparedStatement stmt = conn.prepareStatement(sel);){
			ResultSet rs= stmt.executeQuery();
			if(rs.next()) {
				String name = rs.getString("nomeProduto");
				Thread.sleep(1);
				System.out.println("");
				System.out.print("Choose... 1.Update produto DATA by ID || 2.Update produto DATA by name" + "\n");
				Thread.sleep(1);
				r = entrada.nextInt();

				switch (r) {
				case 1:
					String sql = "UPDATE `exerciciocap4.1`.`produto` SET `nomeProduto` =?, `descricao` =?, "
							+ "`preco` =?, `quantidade` =? WHERE (`idProduto` = ?);";
					PreparedStatement st = conn.prepareStatement(sql);

					System.out.println("Write the product ID");
					st.setInt(5,entrada.nextInt());
					System.out.println("Write the product name or press Enter to leave equal");
					st.setString(1,entrada.next());
					System.out.println("Write the product description or press Enter to leave equal");
					st.setString(2,entrada.next());
					System.out.println("Write the product price or press Enter to leave equal");
					st.setInt(3,entrada.nextInt());
					System.out.println("Write the product quantity or press Enter to leave equal");
					st.setInt(4,entrada.nextInt());
					st.execute();

					System.out.println("The client has been updated");
					break;
				case 2:
					String sql2 = "UPDATE `exerciciocap4.1`.`produto` SET `nomeProduto` =?, `descricao` =?, "
							+ "`preco` =?, `quantidade` =? WHERE (`nomeProduto` = ?);";
					PreparedStatement st2 = conn.prepareStatement(sql2);
					System.out.println("Write the product name");
					st2.setString(5,entrada.next());
					System.out.println("Write the product name or press Enter to leave equal");
					st2.setString(1,entrada.next());
					System.out.println("Write the product description or press Enter to leave equal");
					st2.setString(2,entrada.next());
					System.out.println("Write the product price or press Enter to leave equal");
					st2.setInt(3,entrada.nextInt());
					System.out.println("Write the product quantity or press Enter to leave equal");
					st2.setInt(4,entrada.nextInt());
					st2.execute();

					System.out.println("The client has been updated");
					break;
				default:
					System.out.println("Invalid option!");
				}


			}else {
				throw new Exception("An error occurred:'THE TABLE >>CLIENTE<<"  + "\n" +" DON'T HAVE DATA "+ "TO DELETE");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Product> read() throws SQLException {

		try {
			tableTest();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "SELECT * FROM `exerciciocap4.1`.produto;";
		List<Product> retorno = new ArrayList<>();

		try (Connection conn = Connect.connectione();
				PreparedStatement stmt = conn.prepareStatement(sql);){
			ResultSet resultado = stmt.executeQuery();

			while (resultado.next()) {
				Product produto = new Product(resultado.getInt("idProduto"),resultado.getString("nomeProduto"),resultado.getString("descricao"),
						resultado.getInt("preco"),resultado.getInt("quantidade"));
				retorno.add(produto);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(retorno);
		return retorno;

	}

	public static List<Product> readByParameter() throws Exception {

		tableTest();
		Scanner entrada = new Scanner(System.in);	
		String sql = "";
		List<Product> retorno = new ArrayList<>();

		try (Connection conn = Connect.connectione()){

			String nm = "null";
			int nn = 0;

			Thread.sleep(1);
			System.out.println("");
			System.out.print("Choose... 1.Search the product by name || 2.Search the product by id" + "\n");
			Thread.sleep(1);
			nn = entrada.nextInt();

			if (nn == 1) {
				sql = "SELECT * FROM `exerciciocap4.1`.produto WHERE nomeProduto=?;";
				PreparedStatement st = conn.prepareStatement(sql);
				System.out.println("Write the product name");
				nm = entrada.next();
				st.setString(1, nm);
				//OBS: SEMPRE QUE UTILIZAR O RESULTSET, UTILIZAR O RS.NEXT() PARA O PONTEIRO APONTAR PARA AS TUPLAS
				ResultSet r = st.executeQuery();
				if(r.next()) {
					Product p = new Product(r.getInt("idProduto"),r.getString("nomeProduto"),r.getString("descricao"),r.getInt("preco"),r.getInt("quantity"));
					retorno.add(p);
				}else {
					System.out.println("The product name doesn't exist");
				}
			}else if(nn == 2) {
				sql = "SELECT * FROM `exerciciocap4.1`.produto WHERE idProduto=?;";
				PreparedStatement st = conn.prepareStatement(sql);
				System.out.println("Write the product ID");
				nm = entrada.next();
				Integer.valueOf(nm);
				st.setString(1, nm);
				//OBS: SEMPRE QUE UTILIZAR O RESULTSET, UTILIZAR O RS.NEXT() PARA O PONTEIRO APONTAR PARA AS TUPLAS
				ResultSet r = st.executeQuery();
				if(r.next()) {
					Product p = new Product(r.getInt("idProduto"),r.getString("nomeProduto"),r.getString("descricao"),r.getInt("preco"),r.getInt("quantidade"));
					retorno.add(p);
				}else{
					System.out.println("The product ID doesn't exist");
				}

			}else {
				throw new Exception("Invalid option!");
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(retorno);
		return retorno;

	}

	public static void deleteAllItems() throws Exception {
		String sql = "SELECT * FROM `exerciciocap4.1`.produto;";
		String trunc = "SET FOREIGN_KEY_CHECKS = 0;";

		try (Connection conn = Connect.connectione();
				PreparedStatement stmt = conn.prepareStatement(sql);){

			tableTest();

			ResultSet rs= stmt.executeQuery();
			stmt.executeQuery(trunc);
			sql = "truncate TABLE produto;";
			stmt.executeUpdate(sql);
			stmt.executeQuery(trunc);
			System.out.println("The table 'produto' has been reseted");


		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public static void delete() {

		Scanner entrada = new Scanner(System.in);	
		String sel = "SELECT * FROM `exerciciocap4.1`.cliente;";
		String trunc = "SET SQL_SAFE_UPDATES = 0";
		int r = 0;

		try (Connection conn = Connect.connectione();
				PreparedStatement stmt = conn.prepareStatement(sel);){
			ResultSet rs= stmt.executeQuery();
			if(rs.next()) {
				String name = rs.getString("nomeProduto");
				

					String sql2 = "DELETE FROM cliente WHERE idProduto=?; ";
					int nm2 = 0;
					PreparedStatement st2 = conn.prepareStatement(sql2);
					System.out.println("Write the product ID");
					nm2 = entrada.nextInt();
					st2.executeQuery(trunc);
					st2.setInt(1,nm2);
					st2.execute();
					trunc = "SET SQL_SAFE_UPDATES = 1;";
					st2.executeQuery(trunc);
					System.out.println("The product has been deleted");
			
			}else {
				
			System.out.println("An error occurred:'THE TABLE >>PRODUTO<<"  + "\n" +" DON'T HAVE DATA "+ "TO DELETE");
			
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} 

	public static void create() throws SQLException {
		try {
			tableTest();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "INSERT INTO `exerciciocap4.1`.`produto` "
				+ "(`nomeProduto`, `descricao`, `preco`, `quantidade`) "
				+ "VALUES (?, ?, ?, ?)";
		Scanner entrada = new Scanner(System.in);	

		try(Connection conn = Connect.connectione();
				PreparedStatement st = conn.prepareStatement(sql);) {
			System.out.println("write the product name");
			st.setString(1, entrada.next());
			System.out.println("write the product description");
			st.setString(2, entrada.next());
			System.out.println("write the product price");
			st.setInt(3, entrada.nextInt());
			System.out.println("write the product quantity");
			st.setString(4, entrada.next());
			st.execute();
			System.out.println("Register done!");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private static void tableTest() throws Exception {
		String sql = "SELECT * FROM `exerciciocap4.1`.produto";

		try (Connection connTeste = Connect.connectione();) {

			PreparedStatement testeST = connTeste.prepareStatement(sql);
			ResultSet testeRS = testeST.executeQuery();
			if(!testeRS.next()) {throw new Exception("An error occurred:'THE TABLE >>produto<<"  + "\n" +" DON'T HAVE DATA");}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


	}

}

