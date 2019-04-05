package mvc.employee.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mvc.employee.ViewLoader;

public class MainController {
	private Stage primaryStage;
	ViewLoader<AnchorPane, EmployeeController> viewLoaderEmp;

	public void setStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void setEmployeeFXML(ViewLoader<AnchorPane, EmployeeController> viewLoaderEmp) {
		this.viewLoaderEmp = viewLoaderEmp;
	}

	@FXML
	private void menuItem_About() {
		AlertBox.showAndWait(AlertType.INFORMATION, "O programie", "Employee Tools 2018 for Oracle Database\nVersion 1.0");
	}

	@FXML
	private void menuItem_Exit() {
		primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
	}

}
