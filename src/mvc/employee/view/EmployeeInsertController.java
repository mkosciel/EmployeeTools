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

public class EmployeeInsertController {

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
		jobTitleComboBox.setItems(new JobsDAL().getJobs());
		departmentComboBox.setItems(new DepartmentsDAL().getDepartments());
		managerComboBox.setItems(new EmployeesDAL().getEmployees());
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
		new EmployeesDAL().insertEmployee(emp);
		closeButtonAction();
	}
}