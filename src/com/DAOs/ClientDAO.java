package com.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.classes.Client;
import com.connection.Connect;
import com.interfaces.crud.BooleanInterfaces;
import com.interfaces.crud.VoidInterfaces;

public class ClientDAO implements BooleanInterfaces, VoidInterfaces {


	public static void choose() throws InterruptedException {
		Scanner entrada = new Scanner(System.in);

		int r = 0;
		
		System.out.println("");
		System.out.println(">>============================<<");
		System.out.println("||        CLIENT TABLE        ||");
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
		String sel = "SELECT * FROM `exerciciocap4.1`.cliente;";
		String trunc = "SET SQL_SAFE_UPDATES = 0";

		int r = 0;


		try (Connection conn = Connect.connectione();
				PreparedStatement stmt = conn.prepareStatement(sel);){
			ResultSet rs= stmt.executeQuery();
			if(rs.next()) {
				String name = rs.getString("nomeCliente");
				Thread.sleep(1);
				System.out.println("");
				System.out.print("Choose... 1.Update client DATA by ID || 2.Update client DATA by name" + "\n");
				Thread.sleep(1);
				r = entrada.nextInt();

				switch (r) {
				case 1:
					String sql = "UPDATE `exerciciocap4.1`.`cliente` SET `nomeCliente` =?, `endereco` =?, "
							+ "`cidade` =?, `estado` =?, `obs` =? WHERE (`idCliente` = ?);";
					PreparedStatement st = conn.prepareStatement(sql);
					//st.executeQuery(trunc);
					System.out.println("Write the client ID");
					st.setInt(6,entrada.nextInt());
					System.out.println("Write the client name or press Enter to leave equal");
					st.setString(1,entrada.next());
					System.out.println("Write the client address or press Enter to leave equal");
					st.setString(2,entrada.next());
					System.out.println("Write the client city or press Enter to leave equal");
					st.setString(3,entrada.next());
					System.out.println("Write the client state or press Enter to leave equal");
					st.setString(4,entrada.next());
					System.out.println("Write the client obs or press Enter to leave equal");
					st.setString(5,entrada.next());
					st.execute();

					//trunc = "SET SQL_SAFE_UPDATES = 1;";
					//st.executeQuery(trunc);
					System.out.println("The client has been updated");
					break;
				case 2:
					String sql2 = "UPDATE `exerciciocap4.1`.`cliente` SET `nomeCliente` =?, `endereco` =?, "
							+ "`cidade` =?, `estado` =?, `obs` =? WHERE nomeCliente = ?;";
					PreparedStatement st2 = conn.prepareStatement(sql2);
					System.out.println("Write the client name");
					st2.setString(6,entrada.next());
					System.out.println("Write the client name or press Enter to leave equal");
					st2.setString(1,entrada.next());
					System.out.println("Write the client address or press Enter to leave equal");
					st2.setString(2,entrada.next());
					System.out.println("Write the client city or press Enter to leave equal");
					st2.setString(3,entrada.next());
					System.out.println("Write the client state or press Enter to leave equal");
					st2.setString(4,entrada.next());
					System.out.println("Write the client obs or press Enter to leave equal");
					st2.setString(5,entrada.next());
					st2.execute();
					st2.executeQuery(trunc);
					
					System.out.println("The client has been updated");
					break;
				default:
					System.out.println("Invalid option!");
				}

				/*	stmt.executeQuery(trunc);
		stmt.executeUpdate(sql);
		stmt.executeQuery(trunc);
		System.out.println("The cliente has been deleted");
				 */

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

	
	public static List<Client> getLastRow() throws SQLException, ClassNotFoundException {

		try {
			tableTest();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "SELECT * FROM `exerciciocap4.1`.cliente;";
		List<Client> retorno = new ArrayList<>();

		try (Connection conn = Connect.connectione();
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);){
			ResultSet resultado = stmt.executeQuery(sql);
			Client cliente = new Client();
			
			resultado.last();
		
			
			retorno.add(cliente);
		System.out.println(retorno);
		return retorno;
		}
	}
	public static List<Client> read() throws SQLException {

		try {
			tableTest();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "SELECT * FROM `exerciciocap4.1`.cliente;";
		List<Client> retorno = new ArrayList<>();

		try (Connection conn = Connect.connectione();
				PreparedStatement stmt = conn.prepareStatement(sql);){
			ResultSet resultado = stmt.executeQuery();

			while (resultado.next()) {
				Client cliente = new Client();
				cliente.setClientID(resultado.getInt("idCliente"));
				cliente.setClientName(resultado.getString("nomeCliente"));
				cliente.setAddress(resultado.getString("endereco"));
				cliente.setCity(resultado.getString("cidade"));
				cliente.setState(resultado.getString("estado"));
				cliente.setObs(resultado.getString("obs"));
				retorno.add(cliente);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(retorno);
		return retorno;

	}

	public static List<Client> readByParameter() throws Exception {

		tableTest();

		Scanner entrada = new Scanner(System.in);	
		String sql = "";
		List<Client> retorno = new ArrayList<>();

		try (Connection conn = Connect.connectione()){

			String nm = "null";
			int nn = 0;

			Thread.sleep(1);
			System.out.println("");
			System.out.print("Choose... 1.Search the client by name || 2.Search the client by id" + "\n");
			Thread.sleep(1);
			nn = entrada.nextInt();

			if (nn == 1) {
				sql = "SELECT * FROM `exerciciocap4.1`.cliente WHERE nomeCliente=?;";
				PreparedStatement st = conn.prepareStatement(sql);
				System.out.println("Write the client name");
				nm = entrada.next();
				st.setString(1, nm);
				//OBS: SEMPRE QUE UTILIZAR O RESULTSET, UTILIZAR O RS.NEXT() PARA O PONTEIRO APONTAR PARA AS TUPLAS
				ResultSet r = st.executeQuery();
				if(r.next()) {
					Client c = new Client(r.getString("nomeCliente"),r.getString("endereco")
							,r.getString("cidade"),r.getString("estado"),r.getString("obs"));
					retorno.add(c);
				}else {
					System.out.println("The client name doesn't exist");
				}
			}else if(nn == 2) {
				sql = "SELECT * FROM `exerciciocap4.1`.cliente WHERE idCliente=?;";
				PreparedStatement st = conn.prepareStatement(sql);
				System.out.println("Write the client ID");
				nm = entrada.next();
				Integer.valueOf(nm);
				st.setString(1, nm);
				//OBS: SEMPRE QUE UTILIZAR O RESULTSET, UTILIZAR O RS.NEXT() PARA O PONTEIRO APONTAR PARA AS TUPLAS
				ResultSet r = st.executeQuery();
				if(r.next()) {
					Client c = new Client(r.getString("nomeCliente"),r.getString("endereco")
							,r.getString("cidade"),r.getString("estado"),r.getString("obs"));
					retorno.add(c);
				}else {
					System.out.println("The client ID doesn't exist");
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
		String sql = "SELECT * FROM `exerciciocap4.1`.cliente;";
		String trunc = "SET FOREIGN_KEY_CHECKS = 0;";

		try (Connection conn = Connect.connectione();
				PreparedStatement stmt = conn.prepareStatement(sql);){

			tableTest();

			ResultSet rs= stmt.executeQuery();
			stmt.executeQuery(trunc);
			sql = "truncate TABLE cliente;";
			read()
			.stream().
			forEach(returned -> RequestDAO.deleteByClientID(returned.getClientID()));
			stmt.executeUpdate(sql);
			stmt.executeQuery(trunc);
			System.out.println("The table 'cliente' has been reseted");
			
			

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public static void delete() {

		Scanner entrada = new Scanner(System.in);	
		String sel = "SELECT * FROM `exerciciocap4.1`.cliente;";
		String trunc = "SET FOREIGN_KEY_CHECKS=0;";
		int idClient = 0;
		int r = 0;

		try (Connection conn = Connect.connectione();
				PreparedStatement stmt = conn.prepareStatement(sel);){
			ResultSet rs= stmt.executeQuery();
			if(rs.next()) {
				String name = rs.getString("nomeCliente");
		
			
			
					String sql2 = "DELETE FROM cliente WHERE idCliente=?; ";
					int nm2 = 0;
					PreparedStatement st2 = conn.prepareStatement(sql2);
					System.out.println("Write the client ID");
					nm2 = entrada.nextInt();
					st2.executeQuery(trunc);
					st2.setInt(1,nm2);
					idClient = nm2;
				
					if(containsIDbyID(idClient)) {
						RequestDAO.deleteByClientID(idClient);
						st2.execute();
						System.out.println("The client has been deleted");
					
					trunc = "SET FOREIGN_KEY_CHECKS=1;";
					st2.executeQuery(trunc);
				
					
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

	public static void create() throws SQLException {
	
		String sql = "INSERT INTO `exerciciocap4.1`.`cliente` "
				+ "(`nomeCliente`, `endereco`, `cidade`, `estado`, `obs`) "
				+ "VALUES (?, ?, ?, ?, ?)";
		Scanner entrada = new Scanner(System.in);	

		try(Connection conn = Connect.connectione();
				PreparedStatement st = conn.prepareStatement(sql);) {
			System.out.println("write the client name");
			st.setString(1, entrada.next());
			System.out.println("write the client address");
			st.setString(2, entrada.next());
			System.out.println("write the client city");
			st.setString(3, entrada.next());
			System.out.println("write the client state");
			st.setString(4, entrada.next());
			System.out.println("write the client obs");
			st.setString(5, entrada.next());
			st.execute();
			System.out.println("The register has been created");
			insertID();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private static void tableTest() throws Exception {
		String sql = "SELECT * FROM `exerciciocap4.1`.cliente";

		try (Connection connTeste = Connect.connectione();) {

			PreparedStatement testeST = connTeste.prepareStatement(sql);
			ResultSet testeRS = testeST.executeQuery();
			if(!testeRS.next()) {throw new Exception("An error occurred:'THE TABLE >>CLIENTE<<"  + "\n" +" DON'T HAVE DATA");}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


	}
	
	private static void insertID() throws SQLException {
		
		 
		try(Connection conn = Connect.connectione();
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
			String sql = "SELECT * FROM `exerciciocap4.1`.cliente;";
			ResultSet resultado = stmt.executeQuery(sql);
			resultado.last();
			try {
				RequestDAO.insertClientID(resultado.getInt("idCliente"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static boolean containsIDbyID(int i) throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM `exerciciocap4.1`.cliente WHERE idCliente = "+i+" ;";
		try (Connection conn = Connect.connectione(); Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);){
	
			ResultSet resultado = stmt.executeQuery(sql);
			resultado.next();
			
			if(resultado.getInt("idCliente") > 0) {
				return true;
			}else {
				return false;
			}
		} catch (java.sql.SQLException e) {
			 
				System.out.println("The client ID doesn't exist");
		}

		return false;
	}


	public boolean containsIDbyName(String str) throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM `exerciciocap4.1`.cliente WHERE nomeCliente = "+str+" ;";
		try (Connection conn = Connect.connectione(); Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);){
			ResultSet resultado = stmt.executeQuery(sql);
			resultado.next();
			if(resultado.getInt("idCliente") > 0) {
				return true;
			}else {
				return false;
			}
		} catch (java.sql.SQLException e) {
			 
				System.out.println("The client ID doesn't existee");
		}
		return false;
	}

}

