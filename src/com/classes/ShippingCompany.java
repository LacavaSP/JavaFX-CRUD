package com.classes;

public class ShippingCompany {

	String shippingCompanyName = null;
	String address = null;
	String city = null;
	String state = null;
	String activity = null;
	int shippingCompanyID = 0;
	
	
	public ShippingCompany() {
	}
	
	public ShippingCompany(int shippingCompanyID, String shippingCompanyName, String address, String city, String state, String activity) {
		this.shippingCompanyID = shippingCompanyID;
		this.shippingCompanyName = shippingCompanyName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.activity = activity;
	}
	public String getShippingCompanyName() {
		return shippingCompanyName;
	}
	public void setShippingCompanyName(String shippingCompanyName) {
		this.shippingCompanyName = shippingCompanyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}

	public int getShippingCompanyID() {
		return shippingCompanyID;
	}

	public void setShippingCompanyID(int shippingCompanyID) {
		this.shippingCompanyID = shippingCompanyID;
	}

	@Override
	public String toString() {
		return "[[shippingCompanyName =" + shippingCompanyName + ", address=" + address + "\n" +", city=" + city
				+ ", state=" + state + ", activity=" + activity + "]"+ "\n"+"\n" ;
	}
	
	
	
}
