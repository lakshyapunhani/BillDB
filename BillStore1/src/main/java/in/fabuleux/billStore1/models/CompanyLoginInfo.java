package in.fabuleux.billStore1.models;

public class CompanyLoginInfo 
{
	private String name;
	
	private String address;
	
	private String userName;
	
	private String password;

	public CompanyLoginInfo(String name, String address, String userName, String password) {
		super();
		this.name = name;
		this.address = address;
		this.userName = userName;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
