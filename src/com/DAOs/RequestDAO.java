package com.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.classes.Request;
import com.connection.Connect;
import com.interfaces.crud.BooleanInterfaces;

public class RequestDAO implements BooleanInterfaces {






	public static void choose() throws Exception {
		Scanner entrada = new Scanner(System.in);

		int r = 0;
		System.out.println("");
		System.out.println(">>============================<<");
		System.out.println("||          Request TABLE     ||");
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
		String sel = "SELECT * FROM `exerciciocap4.1`.pedido;";


		try (Connection conn = Connect.connectione();

				PreparedStatement stmt = conn.prepareStatement(sel);){

			String sql ="UPDATE `exerciciocap4.1`.`pedido` SET `datax` =?,`obs` =?,`idCliente=?`,`idTransportadora=?` WHERE (`idPedido` = ?);";
			PreparedStatement st = conn.prepareStatement(sql);

			System.out.println("write the request ID");
			st.setInt(5, entrada.nextInt());
			System.out.println("write the request data (YY-MM-DD)");
			st.setString(1, entrada.next());
			System.out.println("write an request observation");
			st.setString(2, entrada.next());
			System.out.println("write the request client ID");
			st.setString(3, entrada.next());
			System.out.println("write the request shipping company ID");
			st.setString(5, entrada.next());
			st.execute();
			System.out.println("Register done!");


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Request> read() throws Exception {

		try {
			tableTest();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "SELECT * FROM `exerciciocap4.1`.pedido;";
		List<Request> retorno = new ArrayList<>();

		try (Connection conn = Connect.connectione();
				PreparedStatement stmt = conn.prepareStatement(sql);){
			ResultSet sp = stmt.executeQuery();

			while (sp.next()) {
				Request sc = new Request(sp.getInt("idPedido"),sp.getInt("idCliente"),sp.getString("datax"),sp.getString("obs"));
				retorno.add(sc);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(retorno);
		return retorno;

	}

	public static List<Request> readByParameter() throws Exception {

		tableTest();
		Scanner entrada = new Scanner(System.in);	
		String sql = "";
		List<Request> retorno = new ArrayList<>();

		try (Connection conn = Connect.connectione()){

			String nm = "null";
			int nn = 0;

			Thread.sleep(1);
			System.out.println("");
			System.out.print("Search the Request by ID ");
			Thread.sleep(1);
			nn = entrada.nextInt();
			sql = "SELECT * FROM `exerciciocap4.1`.pedido WHERE idPedido=?;";
			PreparedStatement st = conn.prepareStatement(sql);

			nm = entrada.next();
			st.setString(1, nm);
			//OBS: SEMPRE QUE UTILIZAR O RESULTSET, UTILIZAR O RS.NEXT() PARA O PONTEIRO APONTAR PARA AS TUPLAS
			ResultSet sp = st.executeQuery();
			if(sp.next()) {
				Request sc = new Request(sp.getInt("idPedido"),sp.getInt("idCliente"),sp.getString("datax"),sp.getString("obs"));
				retorno.add(sc);
			}else {
				System.out.println("The request ID doesn't exist");
			}




		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return retorno;
	}

	public static void deleteAllItems() throws Exception {
		String sql = "SELECT * FROM `exerciciocap4.1`.pedido;";
		String trunc = "SET FOREIGN_KEY_CHECKS = 0;";
		String trunc1 = "SET FOREIGN_KEY_CHECKS = 1;";
		try (Connection conn = Connect.connectione();
				PreparedStatement stmt = conn.prepareStatement(sql);){

			tableTest();

			ResultSet rs= stmt.executeQuery();
			stmt.executeQuery(trunc);
			sql = "truncate TABLE pedido;";
			stmt.executeUpdate(sql);
			stmt.executeQuery(trunc1);
			System.out.println("The table 'pedido' has been reseted");


		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public static void delete() {

		Scanner entrada = new Scanner(System.in);	
		String sel = "SELECT * FROM `exerciciocap4.1`.pedido;";
		String trunc = "SET SQL_SAFE_UPDATES = 0";

		int r = 0;

		try (Connection conn = Connect.connectione();
				PreparedStatement stmt = conn.prepareStatement(sel);){
			ResultSet rs= stmt.executeQuery();
			if(rs.next()) {
				String name = rs.getString("idPedido");
				Thread.sleep(1);
				System.out.println("");
				System.out.print("Delete request by ID"+ "\n");
				Thread.sleep(1);
				System.out.println("Write the request ID");
				r = entrada.nextInt();
				String sql = "DELETE FROM pedido WHERE idPedido=?; ";
				PreparedStatement st = conn.prepareStatement(sql);
				st.executeQuery(trunc);
				st.setInt(1,r);
				st.execute();
				trunc = "SET SQL_SAFE_UPDATES = 1;";
				st.executeQuery(trunc);
				System.out.println("The request has been deleted");



			}else {
				throw new Exception("An error occurred:'THE TABLE >>pedido<<"  + "\n" +" DON'T HAVE DATA "+ "TO DELETE");
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
		String sql = "INSERT INTO `exerciciocap4.1`.`pedido` "
				+ "(`datax`, `obs`,`idCliente`,`idTransportadora`) "
				+ "VALUES (?, ?)";
		Scanner entrada = new Scanner(System.in);	

		try(Connection conn = Connect.connectione();
				PreparedStatement st = conn.prepareStatement(sql);) {
			System.out.println("write the request data (YY-MM-DD)");
			st.setString(1, entrada.next());
			System.out.println("write an request observation");
			st.setString(2, entrada.next());
			System.out.println("write an request client ID");
			st.setInt(3, entrada.nextInt());
			System.out.println("write an request shipping company ID");
			st.setInt(4, entrada.nextInt());
			st.execute();
			System.out.println("Register done!");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private static void tableTest() throws Exception {
		String sql = "SELECT * FROM `exerciciocap4.1`.pedido";

		try (Connection connTeste = Connect.connectione();) {

			PreparedStatement testeST = connTeste.prepareStatement(sql);
			ResultSet testeRS = testeST.executeQuery();
			if(!testeRS.next()) {throw new Exception("An error occurred:'THE TABLE >>pedido<<"  + "\n" +" DON'T HAVE DATA");}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


	}

	private static boolean isEmpty() throws Exception {
		String sql = "SELECT * FROM `exerciciocap4.1`.pedido";

		try (Connection connTeste = Connect.connectione();) {

			PreparedStatement testeST = connTeste.prepareStatement(sql);
			ResultSet testeRS = testeST.executeQuery();
			if(!testeRS.next()) {return true;}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;

	}

	public static void deleteByClientID(int i) {


		String sel = "SELECT * FROM `exerciciocap4.1`.pedido WHERE idCliente = "+i+";";
		String trunc = "SET FOREIGN_KEY_CHECKS=0;";


		try (Connection conn = Connect.connectione();
				PreparedStatement stmt = conn.prepareStatement(sel);){
			ResultSet rs= stmt.executeQuery();
			if(rs.next()) {

				String verifica = "DELETE FROM pedido WHERE idCliente = "+i+";";
				PreparedStatement st = conn.prepareStatement(verifica);


				ResultSet resultado = stmt.executeQuery();
				resultado.next();
				st.executeQuery(trunc);
				System.out.println(resultado.getInt("idCliente"));

				st.executeUpdate(verifica);	
				System.out.println("The clientID row has been deleted in RequestDAO");


				trunc = "SET FOREIGN_KEY_CHECKS=1;";
				st.executeQuery(trunc);




			}else {
				System.out.println("This client ID doesn't exist in table Request");			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} 

	public static void deleteByShippingCompanyID(int i) {


		String sel = "SELECT * FROM `exerciciocap4.1`.pedido WHERE idTransportadora = "+i+";";
		String trunc = "SET FOREIGN_KEY_CHECKS=0;";


		try (Connection conn = Connect.connectione();
				PreparedStatement stmt = conn.prepareStatement(sel);){
			ResultSet rs= stmt.executeQuery();
			if(rs.next()) {

				String verifica = "DELETE FROM pedido WHERE idTransportadora = "+i+";";
				PreparedStatement st = conn.prepareStatement(verifica);
				ResultSet resultado = stmt.executeQuery();
				resultado.next();
				st.executeQuery(trunc);
				System.out.println(resultado.getInt("idCliente"));
				st.executeUpdate(verifica);	
				System.out.println("The shippingcompanyID row has been deleted in RequestDAO");
				trunc = "SET FOREIGN_KEY_CHECKS=1;";
				st.executeQuery(trunc);

			}else {
				System.out.println("This Shipping Company ID doesn't exist in table Request");			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	} 


	public static void insertClientID(int id) throws Exception {
		String sql = "SELECT * FROM `exerciciocap4.1`.pedido;";
		String check = "SET FOREIGN_KEY_CHECKS=1";
		String downcheck = "SET FOREIGN_KEY_CHECKS=0";
		try(Connection conn = Connect.connectione();
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);
				Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);
				Statement stmt3 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);) {

			ResultSet previous = stmt.executeQuery(sql);
			ResultSet rs = stmt2.executeQuery(sql);

			if(!isEmpty()) {
				PreparedStatement st1 = conn.prepareStatement(sql);

				previous.last();
				previous.previous();
				rs.last();



				if(rs.isFirst()) {
					System.out.println("Eeseweeeeeeeeee");
					if(rs.getInt("idCliente")== 0 && rs.getInt("idTransportadora")> 0) {
						sql = "UPDATE `exerciciocap4.1`.`pedido` SET `idCliente` = '"+id+"' WHERE (`idPedido` = '"+rs.getInt("idPedido")+"');";


						st1.executeQuery(downcheck);
						st1.executeUpdate(sql);
						st1.executeQuery(check);

						System.out.println("SUCCESS 1!");
					}else if (rs.getInt("idTransportadora") > 0 && rs.getInt("idCliente")> 0) {
						sql = "INSERT INTO `exerciciocap4.1`.`pedido` (`idCliente`) VALUES ('"+id+"')";
						PreparedStatement st = conn.prepareStatement(sql);
						st.executeQuery(downcheck);
						st.executeUpdate(sql);
						st.executeQuery(check);
						System.out.println("SUCCESS 3.0");
					} else  {
						sql = "INSERT INTO `exerciciocap4.1`.`pedido` (`idCliente`) VALUES ('"+id+"')";
						PreparedStatement st = conn.prepareStatement(sql);
						st.executeQuery(downcheck);
						st.executeUpdate(sql);
						st.executeQuery(check);
						System.out.println("SUCCESS 3.2");
					}

				}else if(!rs.isFirst()) {

					System.out.println("pussy");

					if(rs.getInt("idTransportadora")>0 && rs.getInt("idCliente") == 0 && previous.getInt("idCliente") > 0){
						sql = "UPDATE `exerciciocap4.1`.`pedido` SET `idCliente` = '"+id+"' WHERE (`idPedido` = '"+rs.getInt("idPedido")+"');";
						st1.executeQuery(downcheck);
						st1.executeUpdate(sql);
						st1.executeQuery(check);
						System.out.println("SUCCESS 1.2!");
					}else if(rs.getInt("idCliente")>0 && rs.getInt("idTransportadora") > 0 && previous.getInt("idCliente") > 0){
						sql = "INSERT INTO `exerciciocap4.1`.`pedido` (`idCliente`) VALUES ('"+id+"')";
						PreparedStatement st = conn.prepareStatement(sql);
						st.executeQuery(downcheck);
						st.executeUpdate(sql);
						st.executeQuery(check);
						System.out.println("SUCCESS 3.2");
					}else if(rs.getInt("idCliente") > 0 &&  rs.getInt("idTransportadora")==0 && previous.getInt("idCliente") > 0 ) {
						sql = "INSERT INTO `exerciciocap4.1`.`pedido` (`idCliente`) VALUES ('"+id+"')";
						PreparedStatement st = conn.prepareStatement(sql);
						st.executeQuery(downcheck);
						st.executeUpdate(sql);
						st.executeQuery(check);
						System.out.println("SUCCESS 3.3");

					}else if(rs.getInt("idCliente")==0 && rs.getInt("idTransportadora") > 0 && previous.getInt("idCliente") == 0){

						while(previous.previous()) {

							if(previous.getInt("idCliente")!=0) {
								previous.next();
								sql = "UPDATE `exerciciocap4.1`.`pedido` SET `idCliente` = '"+id+"' WHERE (`idPedido` = '"+previous.getInt("idPedido")+"');";

								st1.executeQuery(downcheck);
								st1.executeUpdate(sql);
								st1.executeQuery(check);
								System.out.println("SUCCESS 1.3!");

								break;
							}else if(previous.isFirst()) {

								sql = "UPDATE `exerciciocap4.1`.`pedido` SET `idCliente` = '"+id+"' WHERE (`idPedido` = '"+previous.getInt("idPedido")+"');";

								st1.executeQuery(downcheck);
								st1.executeUpdate(sql);
								st1.executeQuery(check);
								System.out.println("SUCCESS 1.4!");
								break;
							}
						}


					}

				}
			}else {

				sql = "INSERT INTO `exerciciocap4.1`.`pedido` (`idCliente`) VALUES ('"+id+"')";
				PreparedStatement stx = conn.prepareStatement(sql);
				stx.executeQuery(downcheck);
				stx.executeUpdate(sql);
				stx.executeQuery(check);
				System.out.println("SUCCESS 3!");



			}


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void insertShippingCompanyID(int id) throws Exception {
		String sql = "SELECT * FROM `exerciciocap4.1`.pedido;";
		String check = "SET FOREIGN_KEY_CHECKS=1;";
		String downcheck = "SET FOREIGN_KEY_CHECKS=0;";
		try(Connection conn = Connect.connectione();
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);
				Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);
				Statement stmt3 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, 
						ResultSet.CONCUR_READ_ONLY);) {

			ResultSet previous = stmt.executeQuery(sql);
			ResultSet rs = stmt2.executeQuery(sql);

			if(!isEmpty()) {
				PreparedStatement st1 = conn.prepareStatement(sql);

				previous.last();
				previous.previous();
				rs.last();

				System.out.println(rs.isFirst());

				if(rs.isFirst()) {
					System.out.println("Eeseweeeeeeeeee");
					if(rs.getInt("idTransportadora")== 0 && rs.getInt("idCliente")> 0) {
						sql = "UPDATE `exerciciocap4.1`.`pedido` SET `idTransportadora` = '"+id+"' WHERE (`idPedido` = '"+rs.getInt("idPedido")+"');";


						st1.executeQuery(downcheck);
						st1.executeUpdate(sql);
						st1.executeQuery(check);

						System.out.println("SUCCESS 1!");
					}else if (rs.getInt("idTransportadora") > 0 && rs.getInt("idCliente")> 0) {
						sql = "INSERT INTO `exerciciocap4.1`.`pedido` (`idTransportadora`) VALUES ('"+id+"')";
						PreparedStatement st = conn.prepareStatement(sql);
						st.executeQuery(downcheck);
						st.executeUpdate(sql);
						st.executeQuery(check);
						System.out.println("SUCCESS 3.0");
					} else  {
						sql = "INSERT INTO `exerciciocap4.1`.`pedido` (`idTransportadora`) VALUES ('"+id+"')";
						PreparedStatement st = conn.prepareStatement(sql);
						st.executeQuery(downcheck);
						st.executeUpdate(sql);
						st.executeQuery(check);
						System.out.println("SUCCESS 3.2");
					}
				}else if(!rs.isFirst()) {

					System.out.println("pussy");

					if(rs.getInt("idCliente")>0 && rs.getInt("idTransportadora") == 0 && previous.getInt("idTransportadora") > 0 ){
						sql = "UPDATE `exerciciocap4.1`.`pedido` SET `idTransportadora` = '"+id+"' WHERE (`idPedido` = '"+rs.getInt("idPedido")+"');";
						st1.executeQuery(downcheck);
						st1.executeUpdate(sql);
						st1.executeQuery(check);
						System.out.println("SUCCESS 1.2!");
					}else if(rs.getInt("idCliente")>0 && rs.getInt("idTransportadora") > 0 && previous.getInt("idTransportadora") > 0){
						sql = "INSERT INTO `exerciciocap4.1`.`pedido` (`idTransportadora`) VALUES ('"+id+"')";
						PreparedStatement st = conn.prepareStatement(sql);
						st.executeQuery(downcheck);
						st.executeUpdate(sql);
						st.executeQuery(check);
						System.out.println("SUCCESS 3.2");
					}else if(rs.getInt("idTransportadora") > 0 &&  rs.getInt("idCliente")==0 && previous.getInt("idTransportadora") > 0 ) {
						sql = "INSERT INTO `exerciciocap4.1`.`pedido` (`idTransportadora`) VALUES ('"+id+"')";
						PreparedStatement st = conn.prepareStatement(sql);
						st.executeQuery(downcheck);
						st.executeUpdate(sql);
						st.executeQuery(check);
						System.out.println("SUCCESS 3.3");

					}else if(rs.getInt("idTransportadora")==0 && rs.getInt("idCliente") > 0 && previous.getInt("idTransportadora") == 0){

						while(previous.previous()) {

							if(previous.getInt("idTransportadora")!=0) {
								previous.next();
								sql = "UPDATE `exerciciocap4.1`.`pedido` SET `idTransportadora` = '"+id+"' WHERE (`idPedido` = '"+previous.getInt("idPedido")+"');";

								st1.executeQuery(downcheck);
								st1.executeUpdate(sql);
								st1.executeQuery(check);
								System.out.println("SUCCESS 1.3!");

								break;
							}else if(previous.isFirst()) {

								sql = "UPDATE `exerciciocap4.1`.`pedido` SET `idTransportadora` = '"+id+"' WHERE (`idPedido` = '"+previous.getInt("idPedido")+"');";

								st1.executeQuery(downcheck);
								st1.executeUpdate(sql);
								st1.executeQuery(check);
								System.out.println("SUCCESS 1.4!");
								break;
							}
						}
					}
				}
			}else {

				sql = "INSERT INTO `exerciciocap4.1`.`pedido` (`idTransportadora`) VALUES ('"+id+"')";
				PreparedStatement st1 = conn.prepareStatement(sql);
				st1.executeQuery(downcheck);
				st1.executeUpdate(sql);
				st1.executeQuery(check);
				System.out.println("SUCCESS 3.1!");



			}


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}

