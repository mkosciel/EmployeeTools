package mvc.employee.model.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mvc.employee.model.Department;

public class DepartmentsDAL {

	SQLException ex;

	public SQLException getSQLException() {
		return ex;
	}

	public DepartmentsDAL() {
	}

	public ObservableList<Department> getDepartments() {

		ObservableList<Department> depts = FXCollections.observableArrayList();
		try (Statement statement = OraConn.getConnection().createStatement();) {

			String query = "SELECT * FROM DEPARTMENTS";
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				depts.add(rs2Department(resultSet));
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return depts;
	}

	public ObservableList<Department> getDepartmentsByDepartmentId(int DepartmentId) {

		ObservableList<Department> depts = FXCollections.observableArrayList();
		try (Statement statement = OraConn.getConnection().createStatement();) {

			String query = "SELECT * FROM DEPARTMENTS WHERE DEPARTMENT_ID = " + DepartmentId;
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				depts.add(rs2Department(resultSet));
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return depts;
	}

	private Department rs2Department(ResultSet resultSet) {
		Department dep = new Department();
		try {
			int col = 1;
			dep.setDepartmentId(resultSet.getInt(col++));
			dep.setDepartmentName(resultSet.getNString(col++));
			dep.setManagerId(resultSet.getInt(col++));
			dep.setLocationId(resultSet.getInt(col++));
		} catch (SQLException ex) {
			this.ex = ex;
		}
		return dep;
	}
}
