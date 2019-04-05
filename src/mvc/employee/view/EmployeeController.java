package mvc.employee.view;

import java.io.IOException;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mvc.employee.Main;
import mvc.employee.model.Employee;
import mvc.employee.model.dal.EmployeesDAL;

public class EmployeeController {

	// TableView, TableColumn
	@FXML
	private TableView<Employee> employeeTable;
	@FXML
	private TableColumn<Employee, Integer> employeeIdColumn;
	@FXML
	private TableColumn<Employee, String> firstNameColumn;
	@FXML
	private TableColumn<Employee, String> lastNameColumn;
	@FXML
	private TableColumn<Employee, String> emailColumn;
	@FXML
	private TableColumn<Employee, String> phoneNameColumn;
	@FXML
	private TableColumn<Employee, LocalDate> hireDateColumn;
	@FXML
	private TableColumn<Employee, String> hireDateAsStrColumn;
	@FXML
	private TableColumn<Employee, Double> salaryColumn;
	@FXML
	private TableColumn<Employee, String> departmentNameColumn;
	@FXML
	private TableColumn<Employee, String> managerNameColumn;
	@FXML
	private TableColumn<Employee, String> jobTitleColumn;

	// Label
	@FXML
	private Label employeeIdLabel;
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label emailLabel;
	@FXML
	private Label phoneNumberLabel;
	@FXML
	private Label hireDateLabel;
	@FXML
	private Label salaryLabel;
	@FXML
	private Label departmentNameLabel;
	@FXML
	private Label managerNameLabel;
	@FXML
	private Label jobTitleLabel;

	@FXML
	private void initialize() {
		employeeTable.setTableMenuButtonVisible(true);
		employeeIdColumn.setCellValueFactory(cellData -> cellData.getValue().employeeIdProperty().asObject());
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		phoneNameColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
		hireDateColumn.setCellValueFactory(cellData -> cellData.getValue().hireDateProperty());
		jobTitleColumn.setCellValueFactory(cellData -> cellData.getValue().jobTitleProperty());
		salaryColumn.setCellValueFactory(cellData -> cellData.getValue().salaryProperty().asObject());

		departmentNameColumn.setCellValueFactory(cellData -> cellData.getValue().departmentNameProperty());
		managerNameColumn.setCellValueFactory(cellData -> cellData.getValue().managerNameProperty());

		// ustaw wartości pól
		refreshEmployee(null);
		// słuchaj zmiany zaznaczonego wiersza
		employeeTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> refreshEmployee(newValue));
	}

	public void setEmployees(ObservableList<Employee> olEmployees) {
		employeeTable.getItems().clear();
		employeeTable.setItems(olEmployees);
		// zaznacz pierwszy wiersz w widoku tabeli (o ile nie jest pusta)
		if (!employeeTable.getItems().isEmpty())
			employeeTable.getSelectionModel().select(0);
	}

	private void refreshEmployee(Employee emp) {
		if (emp != null) {
			employeeIdLabel.setText(Integer.toString(emp.getEmployeeId()));
			firstNameLabel.setText(emp.getFirstName());
			lastNameLabel.setText(emp.getLastName());
			emailLabel.setText(emp.getEmail());
			phoneNumberLabel.setText(emp.getPhoneNumber());
			hireDateLabel.setText(emp.getHireDate().toString());
			jobTitleLabel.setText(emp.getJobTitle());
			salaryLabel.setText(Double.toString(emp.getSalary()));
			departmentNameLabel.setText(emp.getDepartmentName());
			managerNameLabel.setText(emp.getManagerName());
		} else {
			employeeIdLabel.setText("");
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			emailLabel.setText("");
			phoneNumberLabel.setText("");
			hireDateLabel.setText("");
			jobTitleLabel.setText("");
			salaryLabel.setText("");
			departmentNameLabel.setText("");
			managerNameLabel.setText("");
		}
	}

	@FXML
	private void deleteEmployee() {
		int selIdx = employeeTable.getSelectionModel().getSelectedIndex();
		if (selIdx >= 0)
			employeeTable.getItems().remove(selIdx);
	}

	@FXML
	private void editEmployee() throws IOException {
		Employee selEmp = employeeTable.getSelectionModel().getSelectedItem();
		if (selEmp != null) {
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Edit");
			stage.setResizable(false);
			FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/EmployeeEdit.fxml"));
			AnchorPane fxmlLayout = fxmlLoader.load();
			EmployeeEditController fxmlController = fxmlLoader.getController();

			fxmlController.setEmployee(selEmp);
			stage.setScene(new Scene(fxmlLayout));
			stage.showAndWait();
			setEmployees(new EmployeesDAL().getEmployees());
			if (!employeeTable.getItems().isEmpty())
				employeeTable.getSelectionModel().select(0);
		}
	}

	@FXML
	private void insertEmployee() throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Insert");
		stage.setResizable(false);
		FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("view/EmployeeInsert.fxml"));
		AnchorPane fxmlLayout = fxmlLoader.load();
		EmployeeInsertController fxmlController = fxmlLoader.getController();

		stage.setScene(new Scene(fxmlLayout));
		stage.showAndWait();
		setEmployees(new EmployeesDAL().getEmployees());
		if (!employeeTable.getItems().isEmpty())
			employeeTable.getSelectionModel().select(0);
	}
}
