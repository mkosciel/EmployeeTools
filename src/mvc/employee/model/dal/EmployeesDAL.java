package mvc.employee.model.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mvc.employee.model.Employee;

public class EmployeesDAL {
	private SQLException ex;

	public SQLException getSQLException() {
		return ex;
	}

	public EmployeesDAL() {
	}

	public ObservableList<Employee> getEmployees() {

		ObservableList<Employee> employees = FXCollections.observableArrayList();
		try (Statement statement = OraConn.getConnection().createStatement();) {
			String query = "SELECT * FROM EMPLOYEES";
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				employees.add(rs2Employee(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	public ObservableList<Employee> getEmployeesByEmployeeId(int employeeId) {

		ObservableList<Employee> employees = FXCollections.observableArrayList();
		try (Statement statement = OraConn.getConnection().createStatement();) {
			String query = "SELECT * FROM EMPLOYEES WHERE EMPLOYEE_ID = " + employeeId;
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				employees.add(rs2Employee(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	private Employee rs2Employee(ResultSet resultSet) {
		Employee emp = null;
		try {
			int col = 1;
			emp = new Employee(resultSet.getInt(col++));
			emp.setFirstName(resultSet.getNString(col++));
			emp.setLastName(resultSet.getNString(col++));
			emp.setEmail(resultSet.getNString(col++));
			emp.setPhoneNumber(resultSet.getNString(col++));
			emp.setHireDate(resultSet.getDate(col++).toLocalDate());
			emp.setJobId(resultSet.getNString(col++));
			emp.setSalary(resultSet.getDouble(col++));
			col++;
			emp.setManagerId(resultSet.getInt(col++));
			emp.setDepartmentId(resultSet.getInt(col++));

		} catch (SQLException ex) {
			this.ex = ex;
		}
		return emp;
	}

	public void updateEmployee(Employee emp) {
		try (Statement statement = OraConn.getConnection().createStatement();) {
			String query = "UPDATE EMPLOYEES SET " + "FIRST_NAME = '" + emp.getFirstName() + "', LAST_NAME = '"
					+ emp.getLastName() + "', EMAIL = '" + emp.getEmail() + "', PHONE_NUMBER = '" + emp.getPhoneNumber()
					+ "', HIRE_DATE = '" + emp.getHireDate() + "', JOB_ID = '" + emp.getJobId() + "', SALARY = "
					+ emp.getSalary() + ", MANAGER_ID = " + ((emp.getManagerId() != -1) ? emp.getManagerId() : "NULL")
					+ ", DEPARTMENT_ID = '" + emp.getDepartmentId() + "' WHERE EMPLOYEE_ID = " + emp.getEmployeeId();
			statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertEmployee(Employee emp) {
		try (Statement statement = OraConn.getConnection().createStatement();) {
			String query = "INSERT INTO EMPLOYEES (EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, HIRE_DATE, JOB_ID, SALARY, MANAGER_ID, DEPARTMENT_ID) "
					+ "VALUES ((SELECT MAX(EMPLOYEE_ID) +1 FROM EMPLOYEES), '" + emp.getFirstName() + "', '" + emp.getLastName() + "', '" + emp.getEmail() + "', '"
					+ emp.getPhoneNumber() + "', '" + emp.getHireDate() + "', '" + emp.getJobId() + "', "
					+ emp.getSalary() + ", " + ((emp.getManagerId() != -1) ? emp.getManagerId() : "NULL") + ", "
					+ emp.getDepartmentId() + ")";
			System.err.println(query);
			statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
