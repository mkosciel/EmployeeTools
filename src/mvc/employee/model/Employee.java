package mvc.employee.model;

import java.time.LocalDate;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import mvc.employee.model.dal.DepartmentsDAL;
import mvc.employee.model.dal.EmployeesDAL;
import mvc.employee.model.dal.JobsDAL;

public class Employee {
	private IntegerProperty employeeId;
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty email;
	private StringProperty phoneNumber;
	private ObjectProperty<LocalDate> hireDate;
	private StringProperty jobId;
	private DoubleProperty salary;
	private IntegerProperty managerId;
	private IntegerProperty departmentId;
	private StringProperty departmentName;
	private StringProperty managerName;
	private StringProperty jobTitle;

	public Employee() {
		employeeId = new SimpleIntegerProperty(0);
		firstName = new SimpleStringProperty("");
		lastName = new SimpleStringProperty("");
		email = new SimpleStringProperty("");
		phoneNumber = new SimpleStringProperty("");
		hireDate = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		jobId = new SimpleStringProperty("");
		salary = new SimpleDoubleProperty(0.0);
		managerId = new SimpleIntegerProperty(0);
		departmentId = new SimpleIntegerProperty(0);
		departmentName = new SimpleStringProperty("");
		managerName = new SimpleStringProperty("");
		jobTitle = new SimpleStringProperty("");

	}

	public Employee(int employeeId) {
		this();
		this.employeeId.set(employeeId);
	}

	public int getEmployeeId() {
		return this.employeeId.get();
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId.set(employeeId);
	}

	public IntegerProperty employeeIdProperty() {
		return employeeId;
	}

	public String getFirstName() {
		return this.firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public StringProperty firstNameProperty() {
		return this.firstName;
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public StringProperty lastNameProperty() {
		return this.lastName;
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	public StringProperty emailProperty() {
		return this.email;
	}

	public String getPhoneNumber() {
		return phoneNumber.get();
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}

	public StringProperty phoneNumberProperty() {
		return this.phoneNumber;
	}

	public LocalDate getHireDate() {
		return hireDate.get();
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate.set(hireDate);
	}

	public ObjectProperty<LocalDate> hireDateProperty() {
		return this.hireDate;
	}

	public String getJobId() {
		return jobId.get();
	}

	public void setJobId(String jobId) {
		this.jobId.set(jobId);
	}

	public StringProperty jobIdProperty() {
		return this.jobId;
	}

	public double getSalary() {
		return salary.get();
	}

	public void setSalary(double salary) {
		this.salary.set(salary);
	}

	public DoubleProperty salaryProperty() {
		return this.salary;
	}

	public int getManagerId() {
		return managerId.get();
	}

	public void setManagerId(int managerId) {
		this.managerId.set(managerId);
	}

	public IntegerProperty managerIdProperty() {
		return this.managerId;
	}

	public int getDepartmentId() {
		return departmentId.get();
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId.set(departmentId);
	}

	public IntegerProperty departmentIdProperty() {
		return this.departmentId;
	}

	public String getDepartmentName() {
		DepartmentsDAL dept = new DepartmentsDAL();
		ObservableList<Department> depList = dept.getDepartmentsByDepartmentId(this.getDepartmentId());
		if (depList.size() == 1)
			setDepartmentName(depList.get(0).getDepartmentName());
		else
			setDepartmentName(" ");
		return departmentName.get();
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName.set(departmentName);
	}

	public StringProperty departmentNameProperty() {
		DepartmentsDAL dept = new DepartmentsDAL();
		ObservableList<Department> depList = dept.getDepartmentsByDepartmentId(this.getDepartmentId());
		if (depList.size() == 1)
			setDepartmentName(depList.get(0).getDepartmentName());
		else
			setDepartmentName(" ");
		return this.departmentName;
	}

	public String getManagerName() {
		EmployeesDAL emp = new EmployeesDAL();
		ObservableList<Employee> empList = emp.getEmployeesByEmployeeId(managerId.get());
		if (empList.size() == 1)
			setManagerName(empList.get(0).getLastName() + " " + empList.get(0).getFirstName());
		else
			setManagerName(" ");
		return managerName.get();
	}

	public void setManagerName(String managerName) {
		this.managerName.set(managerName);
	}

	public StringProperty managerNameProperty() {
		EmployeesDAL emp = new EmployeesDAL();
		ObservableList<Employee> empList = emp.getEmployeesByEmployeeId(managerId.get());
		if (empList.size() == 1)
			setManagerName(empList.get(0).getLastName() + " " + empList.get(0).getFirstName());
		else
			setManagerName(" ");
		return this.managerName;
	}

	public String getJobTitle() {
		JobsDAL jobs = new JobsDAL();
		ObservableList<Job> jobsList = jobs.getJobsByJobId(jobId.get());
		if (jobsList.size() == 1)
			setJobTitle(jobsList.get(0).getJobTitle());
		else
			setJobTitle(" ");
		return jobTitle.get();
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle.set(jobTitle);
	}

	public StringProperty jobTitleProperty() {
		JobsDAL jobs = new JobsDAL();
		ObservableList<Job> jobsList = jobs.getJobsByJobId(jobId.get());
		if (jobsList.size() == 1)
			setJobTitle(jobsList.get(0).getJobTitle());
		else
			setJobTitle(" ");
		return this.jobTitle;
	}

	@Override
	public String toString() {
		return lastName.getValue() + " " + firstName.getValue();
	}

}
