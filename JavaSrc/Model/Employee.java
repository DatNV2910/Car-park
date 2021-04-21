package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String account;
	private String departmentName;
	private String employeeAddress;
	private LocalDate employeeBirthdate;
	private String employeeName;
	private String employeePhone;
	private String employeeEmail;
	private String password;
	private boolean sex;
	
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}




	public Employee(long id, String account, String departmentName, String employeeAddress, LocalDate employeeBirthdate,
			String employeeName, String employeePhone, String employeeEmail, String password, boolean sex) {
		super();
		this.id = id;
		this.account = account;
		this.departmentName = departmentName;
		this.employeeAddress = employeeAddress;
		this.employeeBirthdate = employeeBirthdate;
		this.employeeName = employeeName;
		this.employeePhone = employeePhone;
		this.employeeEmail = employeeEmail;
		this.password = password;
		this.sex = sex;
	}
	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getAccount() {
		return account;
	}



	public void setAccount(String account) {
		this.account = account;
	}



	public String getDepartmentName() {
		return departmentName;
	}



	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}



	public String getEmployeeAddress() {
		return employeeAddress;
	}



	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}


	/**
	 * @return the employeeBirthdate
	 */
	public LocalDate getEmployeeBirthdate() {
		return employeeBirthdate;
	}
	/**
	 * @param employeeBirthdate the employeeBirthdate to set
	 */
	public void setEmployeeBirthdate(LocalDate employeeBirthdate) {
		this.employeeBirthdate = employeeBirthdate;
	}
	public String getEmployeeName() {
		return employeeName;
	}



	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}



	public String getEmployeePhone() {
		return employeePhone;
	}



	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	/**
	 * @return the sex
	 */
	public boolean isSex() {
		return sex;
	}



	/**
	 * @param sex the sex to set
	 */
	public void setSex(boolean sex) {
		this.sex = sex;
	}




	/**
	 * @return the employeeEmail
	 */
	public String getEmployeeEmail() {
		return employeeEmail;
	}




	/**
	 * @param employeeEmail the employeeEmail to set
	 */
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}




	
	
	
	

}
