package com.classes;

public class Request {



	int RequestID;
	int ClientID;
	int ProductID;
	String datax;
	String obs;
	public int getRequestID() {
		return RequestID;
	}
	public void setRequestID(int requestID) {
		RequestID = requestID;
	}
	public int getClientID() {
		return ClientID;
	}
	public void setClientID(int clientID) {
		ClientID = clientID;
	}
	public int getProductID() {
		return ProductID;
	}
	public void setProductID(int productID) {
		ProductID = productID;
	}
	public String getDatax() {
		return datax;
	}
	public void setDatax(String datax) {
		this.datax = datax;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public Request(int requestID, int clientID,  String datax, String obs) {
		
		RequestID = requestID;
		ClientID = clientID;

		this.datax = datax;
		this.obs = obs;
	}
	public Request() {
	}
	@Override
	public String toString() {
		return "Request [RequestID=" + RequestID + ", ClientID=" + ClientID + ", ProductID=" + ProductID + ", datax="
				+ datax + ", obs=" + obs + "]";
	}

	
	
	
	
}
