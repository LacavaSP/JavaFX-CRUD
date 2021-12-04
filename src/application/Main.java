package application;

import java.util.Scanner;

import com.DAOs.ClientDAO;
import com.DAOs.ProductDAO;
import com.DAOs.RequestDAO;
import com.DAOs.RequestOrderDAO;
import com.DAOs.ShippingCompanyDAO;
import com.classes.ShippingCompany;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		//launch(args);

	menu();

		//System.out.println(RequestDAO.containsIDbyID(12));
		//ClientDAO.getLastRow();
		//	ClientDAO.deleteAllItems();
		//	RequestDAO.deleteAllItems();
		//RequestDAO.insertShippingCompanyID(2);
		//RequestDAO.insertClientID(6);
		//RequestDAO.insertSpc32(5);
		//RequestOrderDAO.insertRequestID(3);
		//RequestOrderDAO.insertProductID(2);
		//System.out.println(RequestDAO.containsIDbyID(2));
		//RequestDAO.deleteByClientID(11);
		//RequestDAO.deleteByShippingCompanyID(1);

		//RequestOrderDAO.insertRequestID(2);
		//RequestOrderDAO.insertProductID(23);
	}

	public static void menu () throws Exception {
		int resp = 0;
		Scanner data = new Scanner(System.in);

		System.out.println(">>============================<<");
		System.out.println("||  DATABASE CONSOLE MANAGER  ||");
		System.out.println(">>============================<<");	 
		System.out.println(">>============================<<");
		System.out.println("||      CHOOSE A OPTION!      ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||       1.Client CRUD        ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||       2.Product CRUD       ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||          3.SC CRUD         ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||      4.Request Order CRUD  ||");
		System.out.println(">>============================<<");	
		System.out.println(">>============================<<");
		System.out.println("||        5.Request CRUD      ||");
		System.out.println(">>============================<<");	

		resp = data.nextInt();


		switch (resp) {
		case 1:
			ClientDAO.choose();
			break;
		case 2:
			ProductDAO.choose();
			break;
		case 3:
			try {
				ShippingCompanyDAO.choose();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			RequestOrderDAO.choose();
			break;
		case 5:
			RequestDAO.choose();
			break;
		default:
			System.out.println("Número inválido");
		}
	}
}
