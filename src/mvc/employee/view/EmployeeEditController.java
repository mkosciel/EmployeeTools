package mvc.employee.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mvc.employee.model.Department;
import mvc.employee.model.Employee;
import mvc.employee.model.Job;
import mvc.employee.model.dal.DepartmentsDAL;
import mvc.employee.model.dal.EmployeesDAL;
import mvc.employee.model.dal.JobsDAL;

public class EmployeeEditController {

	// Label
	@FXML
	private TextField employeeIdTextField;
	@FXML
	private TextField firstNameTextField;
	@FXML
	private TextField lastNameTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField phoneNumberTextField;
	@FXML
	private DatePicker hireDateDatePicker;
	@FXML
	private TextField salaryTextField;
	@FXML
	private ComboBox<Job> jobTitleComboBox;
	@FXML
	private ComboBox<Employee> managerComboBox;
	@FXML
	private ComboBox<Department> departmentComboBox;

	@FXML
	private Button closeButton;

	@FXML
	private Button save;

	@FXML
	private void initialize() {
		setEmployee(null);
	}

	public void setEmployee(Employee emp) {
		if (emp != null) {
			employeeIdTextField.setText(Integer.toString(emp.getEmployeeId()));
			firstNameTextField.setText(emp.getFirstName());
			lastNameTextField.setText(emp.getLastName());
			emailTextField.setText(emp.getEmail());
			phoneNumberTextField.setText(emp.getPhoneNumber());
			hireDateDatePicker.setValue(emp.getHireDate());
			jobTitleComboBox.setItems(new JobsDAL().getJobs());
			jobTitleComboBox.setValue((Job) new JobsDAL().getJobsByJobId(emp.getJobId()).get(0));
			salaryTextField.setText(Double.toString(emp.getSalary()));
			departmentComboBox.setItems(new DepartmentsDAL().getDepartments());
			departmentComboBox.setValue(
					(Department) new DepartmentsDAL().getDepartmentsByDepartmentId(emp.getDepartmentId()).get(0));
			managerComboBox.setItems(new EmployeesDAL().getEmployees());
			if (new EmployeesDAL().getEmployeesByEmployeeId(emp.getManagerId()).size() > 0)
				managerComboBox
						.setValue((Employee) new EmployeesDAL().getEmployeesByEmployeeId(emp.getManagerId()).get(0));

		} else {
			employeeIdTextField.setText("");
			firstNameTextField.setText("");
			lastNameTextField.setText("");
			emailTextField.setText("");
			phoneNumberTextField.setText("");
			hireDateDatePicker.setValue(null);
			// jobTitleLabel.setText("");
			salaryTextField.setText("");
			// departmentNameLabel.setText("");
			// managerNameLabel.setText("");
		}
		employeeIdTextField.setDisable(true);
	}

	@FXML
	private void closeButtonAction() {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void saveButtonAction() {
		Employee emp = new Employee();
		emp.setEmployeeId(Integer.parseInt(employeeIdTextField.getText()));
		emp.setFirstName(firstNameTextField.getText());
		emp.setLastName(lastNameTextField.getText());
		emp.setEmail(emailTextField.getText());
		emp.setPhoneNumber(phoneNumberTextField.getText());
		emp.setHireDate(hireDateDatePicker.getValue());
		emp.setJobId(jobTitleComboBox.getSelectionModel().getSelectedItem().getJobId());
		emp.setSalary(Double.valueOf(salaryTextField.getText()));
		emp.setDepartmentId(departmentComboBox.getSelectionModel().getSelectedItem().getDepartmentId());
		if (managerComboBox.getSelectionModel().isEmpty())
			emp.setManagerId(-1);
		else
			emp.setManagerId(managerComboBox.getSelectionModel().getSelectedItem().getEmployeeId());

		new EmployeesDAL().updateEmployee(emp);
		closeButtonAction();
	}
}
