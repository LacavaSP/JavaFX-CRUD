package com.classes;

public class Client {
	private int clientID;
	private String clientName = null;
	private String address = null;
	private String state = null;
	private String obs = null;
	private String city = null;
	
	public Client(String clientName, String address, String state, String obs, String city) {
		this.clientName = clientName;
		this.address = address;
		this.state = state;
		this.obs = obs;
		this.city = city;
	}
	
	public int getClientID() {
		return clientID;
	}

	public void setClientID(int clientID) {
		this.clientID = clientID;
	}

	public Client() {}

	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Client [clientID=" + clientID + ", clientName=" + clientName + ", address=" + address + ", state="
				+ state + ", obs=" + obs + ", city=" + city + "]";
	}

 
	
	
}
