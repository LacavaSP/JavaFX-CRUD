package com.classes;

public class RequestOrder {


int requestID;
int requestOrderID;
int productID;
int quantity;
public int getRequestID() {
	return requestID;
}
public void setRequestID(int requestID) {
	this.requestID = requestID;
}
public int getRequestOrderID() {
	return requestOrderID;
}
public void setRequestOrderID(int requestOrderID) {
	this.requestOrderID = requestOrderID;
}
public int getProductID() {
	return productID;
}
public void setProductID(int productID) {
	this.productID = productID;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
@Override
public String toString() {
	return "RequestOrder [requestID=" + requestID + ", requestOrderID=" + requestOrderID + ", productID=" + productID
			+ ", quantity=" + quantity + "]";
}
public RequestOrder(int requestID, int requestOrderID, int productID, int quantity) {
	
	this.requestID = requestID;
	this.requestOrderID = requestOrderID;
	this.productID = productID;
	this.quantity = quantity;
}
public RequestOrder() {
}




	
	
	
	
}
