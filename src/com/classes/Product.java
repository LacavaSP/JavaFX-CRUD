package com.classes;

public class Product {

	String productName = null;
	String description = null;
	int price = 0;
	int quantity = 0;
	int idProduct = 0;
	
	
	public Product(int idProduct,String productName, String description, int price, int quantity) {
		this.idProduct = idProduct;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}
	
	
	public Product() {
	}


	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "Product [productName=" + productName + ", description=" + description + "\n" +
	", price=" + price 
				+ ", quantity=" + quantity + "]"+"\n";
	}
	
	
}
