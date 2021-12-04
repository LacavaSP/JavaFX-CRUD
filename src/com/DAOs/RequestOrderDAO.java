package com.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.classes.Product;
import com.classes.RequestOrder;
import com.connection.Connect;

public class RequestOrderDAO {





	public static void insertRequestID(int id) throws Exception {
		String sql = "SELECT * FROM `exerciciocap4.1`.ordempedido;";
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
					if(rs.getInt("idPedido")== 0 && rs.getInt("idProduto")> 0) {
						sql = "UPDATE `exerciciocap4.1`.`ordempedido` SET `idPedido` = '"+id+"' WHERE (`idOrdemPedido` = '"+rs.getInt("idPedido")+"');";


			
						st1.executeUpdate(sql);
			

						System.out.println("SUCCESS 1!");
					}else if (rs.getInt("idProduto") > 0 && rs.getInt("idPedido")> 0) {
						sql = "INSERT INTO `exerciciocap4.1`.`ordempedido` (`idPedido`) VALUES ('"+id+"')";
						PreparedStatement st = conn.prepareStatement(sql);
			
						st.executeUpdate(sql);
				
						System.out.println("SUCCESS 3.0");
					} else  {
						sql = "INSERT INTO `exerciciocap4.1`.`ordempedido` (`idPedido`) VALUES ('"+id+"')";
						PreparedStatement st = conn.prepareStatement(sql);
						
						st.executeUpdate(sql);

						System.out.println("SUCCESS 3.2");
					}

				}else if(!rs.isFirst()) {

					System.out.println("pussy");

					if(rs.getInt("idProduto")>0 && rs.getInt("idPedido") == 0 && previous.getInt("idPedido") > 0){
						sql = "UPDATE `exerciciocap4.1`.`ordempedido` SET `idPedido` = '"+id+"' WHERE (`idOrdemPedido` = '"+rs.getInt("idOrdemPedido")+"');";
	
						st1.executeUpdate(sql);
					
						System.out.println("SUCCESS 1.2!");
					}else if(rs.getInt("idPedido")>0 && rs.getInt("idProduto") > 0 && previous.getInt("idPedido") > 0){
						sql = "INSERT INTO `exerciciocap4.1`.`ordempedido` (`idPedido`) VALUES ('"+id+"')";
						PreparedStatement st = conn.prepareStatement(sql);
		
						st.executeUpdate(sql);
				
						System.out.println("SUCCESS 3.2");
					}else if(rs.getInt("idPedido") > 0 &&  rs.getInt("idProduto")==0 && previous.getInt("idPedido") > 0 ) {
						sql = "INSERT INTO `exerciciocap4.1`.`ordempedido` (`idPedido`) VALUES ('"+id+"')";
						PreparedStatement st = conn.prepareStatement(sql);

						st.executeUpdate(sql);
	
						System.out.println("SUCCESS 3.3");

					}else if(rs.getInt("idPedido")==0 && rs.getInt("idProduto") > 0 && previous.getInt("idPedido") == 0){

						while(previous.previous()) {

							if(previous.getInt("idPedido")!=0) {
								previous.next();
								sql = "UPDATE `exerciciocap4.1`.`ordempedido` SET `idPedido` = '"+id+"' WHERE (`idOrdemPedido` = '"+previous.getInt("idOrdemPedido")+"');";

	
								st1.executeUpdate(sql);
			
								System.out.println("SUCCESS 1.3!");

								break;
							}else if(previous.isFirst()) {

								sql = "UPDATE `exerciciocap4.1`.`ordempedido` SET `idPedido` = '"+id+"' WHERE (`idOrdemPedido` = '"+previous.getInt("idOrdemPedido")+"');";

								st1.executeUpdate(sql);

								System.out.println("SUCCESS 1.4!");
								break;
							}
						}


					}

				}
			}else {

				sql = "INSERT INTO `exerciciocap4.1`.`ordempedido` (`idPedido`) VALUES ('"+id+"')";
				PreparedStatement stx = conn.prepareStatement(sql);

				stx.executeUpdate(sql);

				System.out.println("SUCCESS 3!");



			}


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void insertProductID(int id) throws Exception {
		String sql = "SELECT * FROM `exerciciocap4.1`.ordempedido;";

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
					if(rs.getInt("idProduto")== 0 && rs.getInt("idPedido")> 0) {
						sql = "UPDATE `exerciciocap4.1`.`ordempedido` SET `idProduto` = '"+id+"' WHERE (`idOrdemPedido` = '"+rs.getInt("idPedido")+"');";



						st1.executeUpdate(sql);


						System.out.println("SUCCESS 1!");
					}else if (rs.getInt("idProduto") > 0 && rs.getInt("idPedido")> 0) {
						sql = "INSERT INTO `exerciciocap4.1`.`ordempedido` (`idProduto`) VALUES ('"+id+"')";
						PreparedStatement st = conn.prepareStatement(sql);

						st.executeUpdate(sql);

						System.out.println("SUCCESS 3.0");
					} else  {
						sql = "INSERT INTO `exerciciocap4.1`.`ordempedido` (`idProduto`) VALUES ('"+id+"')";
						PreparedStatement st = conn.prepareStatement(sql);

						st.executeUpdate(sql);

						System.out.println("SUCCESS 3.2");
					}

				}else if(!rs.isFirst()) {


					if(rs.getInt("idPedido")>0 && rs.getInt("idProduto") == 0 && previous.getInt("idProduto") > 0){
						sql = "UPDATE `exerciciocap4.1`.`ordempedido` SET `idProduto` = '"+id+"' WHERE (`idOrdemPedido` = '"+rs.getInt("idOrdemPedido")+"');";

						st1.executeUpdate(sql);

						System.out.println("SUCCESS 1.2!");
					}else if(rs.getInt("idPedido")>0 && rs.getInt("idProduto") > 0 && previous.getInt("idProduto") > 0){
						sql = "INSERT INTO `exerciciocap4.1`.`ordempedido` (`idProduto`) VALUES ('"+id+"')";
						PreparedStatement st = conn.prepareStatement(sql);

						st.executeUpdate(sql);

						System.out.println("SUCCESS 3.2");
					}else if(rs.getInt("idProduto") > 0 &&  rs.getInt("idPedido")==0 && previous.getInt("idProduto") > 0 ) {
						sql = "INSERT INTO `exerciciocap4.1`.`ordempedido` (`idPedido`) VALUES ('"+id+"')";
						PreparedStatement st = conn.prepareStatement(sql);

						st.executeUpdate(sql);

						System.out.println("SUCCESS 3.3");

					}else if(rs.getInt("idProduto")==0 && rs.getInt("idPedido") > 0 && previous.getInt("idProduto") == 0){

						while(previous.previous()) {

							if(previous.getInt("idProduto")!=0) {
								previous.next();
								sql = "UPDATE `exerciciocap4.1`.`ordempedido` SET `idProduto` = '"+id+"' WHERE (`idOrdemPedido` = '"+previous.getInt("idOrdemPedido")+"');";


								st1.executeUpdate(sql);

								System.out.println("SUCCESS 1.3!");

								break;
							}else if(previous.isFirst()) {

								sql = "UPDATE `exerciciocap4.1`.`ordempedido` SET `idProduto` = '"+id+"' WHERE (`idOrdemPedido` = '"+previous.getInt("idOrdemPedido")+"');";


								st1.executeUpdate(sql);

								System.out.println("SUCCESS 1.4!");
								break;
							}
						}


					}

				}
			}else {

				sql = "INSERT INTO `exerciciocap4.1`.`ordempedido` (`idProduto`) VALUES ('"+id+"')";
				PreparedStatement stx = conn.prepareStatement(sql);

				stx.executeUpdate(sql);

				System.out.println("SUCCESS 3!");



			}


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



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
		String sel = "SELECT * FROM `exerciciocap4.1`.ordempedido;";


		try (Connection conn = Connect.connectione();

				PreparedStatement stmt = conn.prepareStatement(sel);){

			String sql = "UPDATE `exerciciocap4.1`.`ordempedido` SET `quantidade` =? WHERE (`idOrdemPedido` = ?);";
			PreparedStatement st = conn.prepareStatement(sql);
			System.out.println("Write the  order request ID");
			st.setInt(2,entrada.nextInt());
			System.out.println("Write the new order request quantity");
			st.setInt(1,entrada.nextInt());

			st.execute();
			System.out.println("The order request has been updated");


		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<RequestOrder> read() throws Exception {

		try {
			tableTest();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String sql = "SELECT * FROM `exerciciocap4.1`.ordempedido;";
		List<RequestOrder> retorno = new ArrayList<>();

		try (Connection conn = Connect.connectione();
				PreparedStatement stmt = conn.prepareStatement(sql);){
			ResultSet sp = stmt.executeQuery();

			while (sp.next()) {
				RequestOrder sc = new RequestOrder(sp.getInt("idOrdemPedido"),sp.getInt("idProduto"),sp.getInt("idPedido"),sp.getInt("quantidade"));
				retorno.add(sc);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(retorno);
		return retorno;

	}

	public static List<RequestOrder> readByParameter() throws Exception {

		tableTest();
		Scanner entrada = new Scanner(System.in);	
		String sql = "";
		List<RequestOrder> retorno = new ArrayList<>();

		try (Connection conn = Connect.connectione()){

			int nm = 0;
			int nn = 0;

			Thread.sleep(1);
			System.out.println("");
			System.out.print("Search the OrderRequest by ID ");
			Thread.sleep(1);
			nn = entrada.nextInt();
			sql = "SELECT * FROM `exerciciocap4.1`.ordempedido WHERE idOrdemPedido=?;";
			PreparedStatement st = conn.prepareStatement(sql);
			nm = entrada.nextInt();
			st.setInt(1, nm);
			//OBS: SEMPRE QUE UTILIZAR O RESULTSET, UTILIZAR O RS.NEXT() PARA O PONTEIRO APONTAR PARA AS TUPLAS
			ResultSet sp = st.executeQuery();
			if(sp.next()) {
				RequestOrder sc = new RequestOrder(sp.getInt("idOrdemPedido"),sp.getInt("idProduto"),sp.getInt("idPedido"),sp.getInt("quantidade"));
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
		String sql = "SELECT * FROM `exerciciocap4.1`.ordempedido;";
		String trunc = "SET FOREIGN_KEY_CHECKS = 0;";
		String trunc1 = "SET FOREIGN_KEY_CHECKS = 1;";
		try (Connection conn = Connect.connectione();
				PreparedStatement stmt = conn.prepareStatement(sql);){

			tableTest();

			ResultSet rs= stmt.executeQuery();
			stmt.executeQuery(trunc);
			sql = "truncate TABLE ordempedido;";
			stmt.executeUpdate(sql);
			stmt.executeQuery(trunc1);
			System.out.println("The table 'ordempedido' has been reseted");


		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public static void delete() {

		Scanner entrada = new Scanner(System.in);	
		String sel = "SELECT * FROM `exerciciocap4.1`.ordempedido;";
		String trunc = "SET SQL_SAFE_UPDATES = 0";

		int r = 0;

		try (Connection conn = Connect.connectione();
				PreparedStatement stmt = conn.prepareStatement(sel);){
			ResultSet rs= stmt.executeQuery();
			if(rs.next()) {

				Thread.sleep(1);
				System.out.println("");
				System.out.print("Delete request order by id" + "\n");
				Thread.sleep(1);
				String sql = "DELETE FROM transportadora WHERE nomeTransportadora=?; ";
				PreparedStatement st = conn.prepareStatement(sql);
				System.out.println("Write the shipping company name");
				r = entrada.nextInt();
				st.executeQuery(trunc);
				st.setInt(1,r);
				st.execute();
				trunc = "SET SQL_SAFE_UPDATES = 1;";
				st.executeQuery(trunc);
				System.out.println("The shipping company has been deleted");



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

	public static void create() throws Exception {
	
		String sql = "INSERT INTO `exerciciocap4.1`.`ordempedido` ( `idProduto`, `idPedido`, `quantidade`) VALUES ( ?, ?, ?);";
		Scanner entrada = new Scanner(System.in);	

		try(Connection conn = Connect.connectione();
				PreparedStatement st = conn.prepareStatement(sql);) {
			
			ProductDAO.read();
			RequestDAO.read();
			System.out.println(""); System.out.println(""); 
			System.out.println("Insert the product ID!");
			st.setInt(1, entrada.nextInt());
			System.out.println("Insert the request ID!");
			st.setInt(2, entrada.nextInt());
			System.out.println("Insert the quantity!");
			st.setInt(3, entrada.nextInt());
			st.execute();
			System.out.println("Register done!");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private static void tableTest() throws Exception {
		String sql = "SELECT * FROM `exerciciocap4.1`.ordempedido";

		try (Connection connTeste = Connect.connectione();) {

			PreparedStatement testeST = connTeste.prepareStatement(sql);
			ResultSet testeRS = testeST.executeQuery();
			if(!testeRS.next()) {throw new Exception("An error occurred:'THE TABLE >>ordempedido<<"  + "\n" +" DON'T HAVE DATA");}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


	}

	private static boolean isEmpty() throws Exception {
		String sql = "SELECT * FROM `exerciciocap4.1`.ordempedido";

		try (Connection connTeste = Connect.connectione();) {

			PreparedStatement testeST = connTeste.prepareStatement(sql);
			ResultSet testeRS = testeST.executeQuery();
			if(!testeRS.next()) {return true;}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;

	}

}

