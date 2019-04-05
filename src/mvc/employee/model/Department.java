package mvc.employee.model;

public class Department {
	private int departmentId;
	private String departmentName;
	private int ManagerId;
	private int LocationId;

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public int getManagerId() {
		return ManagerId;
	}

	public void setManagerId(int managerId) {
		ManagerId = managerId;
	}

	public int getLocationId() {
		return LocationId;
	}

	public void setLocationId(int locationId) {
		LocationId = locationId;
	}

	public Department(int departmentId) {
		this.departmentId = departmentId;
	}

	public Department() {

	}

	@Override
	public String toString() {
		return String.valueOf(departmentId);
	}

}
