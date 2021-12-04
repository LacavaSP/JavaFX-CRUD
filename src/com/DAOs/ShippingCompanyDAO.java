package com.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.classes.ShippingCompany;
import com.classes.ShippingCompany;
import com.connection.Connect;

public class ShippingCompanyDAO {


	public static void choose() throws Exception {
		Scanner entrada = new Scanner(System.in);

		int r = 0;
		System.out.println("");
		System.out.println(">>============================<<");
		System.out.println("||          SC TABLE          ||");
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

			Thread.sleep(1);
			System.out.println("");
			System.out.print("Choose... 1.Update shipping company DATA by ID || 2.Update shipping company DATA by name" + "\n");
			Thread.sleep(1);
			r = entrada.nextInt();

			switch (r) {
			case 1:
				String sql = "UPDATE `exerciciocap4.1`.`transportadora` SET `nomeTransportadora` =?, `endereco` =?, "
						+ "`cidade` =?, `atividade` =? WHERE (`idTransportadora` = ?);";
				PreparedStatement st = conn.prepareStatement(sql);
				//st.executeQuery(trunc);
				System.out.println("Write the shipping company ID");
				st.setInt(5,entrada.nextInt());
				System.out.println("Write the shipping company name or press Enter to leave equal");
				st.setString(1,entrada.next());
				System.out.println("Write the shipping company address or press Enter to leave equal");
				st.setString(2,entrada.next());
				System.out.println("Write the shipping company city or press Enter to leave equal");
				st.setInt(3,entrada.nextInt());
				System.out.println("Write the shipping company activity or press Enter to leave equal");
				st.setInt(4,entrada.nextInt());
				st.execute();

				//trunc = "SET SQL_SAFE_UPDATES = 1;";
				//st.executeQuery(trunc);
				System.out.println("The shipping company has been updated");
				break;
			case 2:
				String sql2 = "UPDATE `exerciciocap4.1`.`transportadora` SET `nomeTransportadora` =?, `endereco` =?, "
						+ "`cidade` =?, `atividade` =? WHERE (`nomeTransportadora` = ?);";
				PreparedStatement st2 = conn.prepareStatement(sql2);
				System.out.println("Write the shipping company name");
				st2.setString(5,entrada.next());
				System.out.println("Write the shipping company name or press Enter to leave equal");
				st2.setString(1,entrada.next());
				System.out.println("Write the shipping company address or press Enter to leave equal");
				st2.setString(2,entrada.next());
				System.out.println("Write the shipping company city or press Enter to leave equal");
				st2.setInt(3,entrada.nextInt());
				System.out.println("Write the shipping company activity or press Enter to leave equal");
				st2.setInt(4,entrada.nextInt());
				st2.execute();


				System.out.println("The shipping company has been updated");
				break;
			default:
				System.out.println("Invalid option!");
			}

			/*	stmt.executeQuery(trunc);
		stmt.executeUpdate(sql);
		stmt.executeQuery(trunc);
		System.out.println("The cliente has been deleted");
			 */


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<ShippingCompany> read() throws Exception {
		tableTest();
		try {
			tableTest();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "SELECT * FROM `exerciciocap4.1`.transportadora;";
		List<ShippingCompany> retorno = new ArrayList<>();

		try (Connection conn = Connect.connectione();
				PreparedStatement stmt = conn.prepareStatement(sql);){
			ResultSet sp = stmt.executeQuery();

			while (sp.next()) {
				ShippingCompany sc = new ShippingCompany(sp.getInt("idTransportadora"),sp.getString("nomeTransportadora"),sp.getString("endereco"),
						sp.getString("cidade"),sp.getString("estado"),sp.getString("atividade"));
				retorno.add(sc);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(retorno);
		return retorno;

	}

	public static List<ShippingCompany> readByParameter() throws Exception {

		tableTest();
		Scanner entrada = new Scanner(System.in);	
		String sql = "";
		List<ShippingCompany> retorno = new ArrayList<>();

		try (Connection conn = Connect.connectione()){

			String nm = "null";
			int nn = 0;

			Thread.sleep(1);
			System.out.println("");
			System.out.print("Choose... 1.Search the shipping company by name || 2.Search the shipping company by id" + "\n");
			Thread.sleep(1);
			nn = entrada.nextInt();

			if (nn == 1) {
				sql = "SELECT * FROM `exerciciocap4.1`.transportadora WHERE nomeTransportadora=?;";
				PreparedStatement st = conn.prepareStatement(sql);
				System.out.println("Write the shipping company name");
				nm = entrada.next();
				st.setString(1, nm);
				//OBS: SEMPRE QUE UTILIZAR O RESULTSET, UTILIZAR O RS.NEXT() PARA O PONTEIRO APONTAR PARA AS TUPLAS
				ResultSet r = st.executeQuery();
				if(r.next()) {
					ShippingCompany p = new ShippingCompany(r.getInt("idTransportadora"),r.getString("nomeTransportadora"),r.getString("endereco"),
							r.getString("cidade"),r.getString("estado"),r.getString("atividade"));
					retorno.add(p);
				}else {
					System.out.println("The shipping company name doesn't exist");
				}
			}else if(nn == 2) {
				sql = "SELECT * FROM `exerciciocap4.1`.transportadora WHERE idTransportadora=?;";
				PreparedStatement st = conn.prepareStatement(sql);
				System.out.println("Write the shipping company ID");
				nm = entrada.next();
				Integer.valueOf(nm);
				st.setString(1, nm);
				//OBS: SEMPRE QUE UTILIZAR O RESULTSET, UTILIZAR O RS.NEXT() PARA O PONTEIRO APONTAR PARA AS TUPLAS
				ResultSet r = st.executeQuery();
				if(r.next()) { 
					ShippingCompany p = new ShippingCompany(r.getInt("idTransportadora"),r.getString("nomeTransportadora"),r.getString("endereco"),
							r.getString("cidade"),r.getString("estado"),r.getString("atividade"));
					retorno.add(p);
				}else {
					System.out.println("The shipping company ID doesn't exist");
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
		String sql = "SELECT * FROM `exerciciocap4.1`.transportadora;";
		String trunc = "SET FOREIGN_KEY_CHECKS = 0;";

		try (Connection conn = Connect.connectione();
				PreparedStatement stmt = conn.prepareStatement(sql);){

			tableTest();

			ResultSet rs= stmt.executeQuery();
			stmt.executeQuery(trunc);
			sql = "truncate TABLE transportadora;";
			read()
			.stream().
			forEach(returned -> RequestDAO.deleteByShippingCompanyID(returned.getShippingCompanyID()));
			stmt.executeUpdate(sql);
			stmt.executeQuery(trunc);
			System.out.println("The table 'transportadora' has been reseted");


		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public static void delete() throws SQLException, ClassNotFoundException {

		Scanner entrada = new Scanner(System.in);	
		String sel = "SELECT * FROM `exerciciocap4.1`.transportadora;";
		String trunc = "SET FOREIGN_KEY_CHECKS = 0;";

		int r = 0;

		try (Connection conn = Connect.connectione();
				PreparedStatement stmt = conn.prepareStatement(sel);){
			ResultSet rs= stmt.executeQuery();
			if(rs.next()) {

				String sql2 = "DELETE FROM transportadora WHERE idTransportadora=?; ";
				int nm2 = 0;
				PreparedStatement st2 = conn.prepareStatement(sql2);
				System.out.println("Write the shipping company ID");
				nm2 = entrada.nextInt();
				st2.executeQuery(trunc);
				st2.setInt(1,nm2);

				RequestDAO.deleteByShippingCompanyID(nm2);
				if(containsIDbyID(nm2)) {
					RequestDAO.deleteByShippingCompanyID(nm2);
					st2.execute();
					System.out.println("The shipping company has been deleted");
				}


				trunc = "SET FOREIGN_KEY_CHECKS = 1;";
				st2.executeQuery(trunc);




			}else {
				throw new Exception("An error occurred:'THE TABLE >>CLIENTE<<"  + "\n" +" DON'T HAVE DATA "+ "TO DELETE");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

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
		String sql = "INSERT INTO `exerciciocap4.1`.`transportadora` "
				+ "(`nomeTransportadora`, `endereco`, `cidade`, `estado`,`atividade`) "
				+ "VALUES (?, ?, ?, ?, ?)";
		Scanner entrada = new Scanner(System.in);	

		try(Connection conn = Connect.connectione();
				PreparedStatement st = conn.prepareStatement(sql);) {
			System.out.println("write the shipping company name");
			st.setString(1, entrada.next());
			System.out.println("write the shipping company address");
			st.setString(2, entrada.next());
			System.out.println("write the shipping company city");
			st.setString(3, entrada.next());
			System.out.println("write the shipping company state");
			st.setString(4, entrada.next());
			System.out.println("write the shipping company activity");
			st.setString(5, entrada.next());
			st.execute();
			System.out.println("Register done!");
			insertID();
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
	private static void insertID() throws SQLException {


		try(Connection conn = Connect.connectione();
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
			String sql = "SELECT * FROM `exerciciocap4.1`.transportadora;";
			ResultSet resultado = stmt.executeQuery(sql);
			resultado.last();
			try {
				RequestDAO.insertShippingCompanyID(resultado.getInt("idTransportadora"));
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
		String sql = "SELECT * FROM `exerciciocap4.1`.transportadora WHERE idTransportadora = "+i+" ;";
		try (Connection conn = Connect.connectione(); Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);){

			ResultSet resultado = stmt.executeQuery(sql);
			resultado.next();

			if(resultado.getInt("idTransportadora") > 0) {
				return true;
			}else {
				return false;
			}
		} catch (java.sql.SQLException e) {

			System.out.println("The shipping company ID doesn't exist");
		}

		return false;
	}


}

