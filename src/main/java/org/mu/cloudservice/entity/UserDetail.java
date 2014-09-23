package org.mu.cloudservice.entity;


public class UserDetail {

	private boolean gender;
	
	private String company;
	
	private boolean companyVerified;
	
	private String role;
	
	private String country;
	
	private String city;
	
	private String tag;

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public boolean isCompanyVerified() {
		return companyVerified;
	}

	public void setCompanyVerified(boolean companyVerified) {
		this.companyVerified = companyVerified;
	}
	
}
