package mvc.employee;

import java.sql.SQLException;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mvc.employee.model.dal.EmployeesDAL;
import mvc.employee.model.dal.OraConn;
import mvc.employee.view.AlertBox;
import mvc.employee.view.EmployeeController;
import mvc.employee.view.MainController;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws SQLException {
		try {

			ViewLoader<BorderPane, Object> viewLoader = new ViewLoader<BorderPane, Object>("view/Main.fxml");

			BorderPane borderPane = viewLoader.getLayout();
			Scene scene = new Scene(borderPane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Pracownicy");
			primaryStage.setOnHiding(e -> primaryStage_Hiding(e));
			primaryStage.setOnCloseRequest(e -> primaryStage_CloseRequest(e));
			primaryStage.show();


			ViewLoader<AnchorPane, EmployeeController> viewLoaderEmp = new ViewLoader<AnchorPane, EmployeeController>(
					"view/EmployeeData.fxml");
			AnchorPane anchorPaneEmp = viewLoaderEmp.getLayout();
			borderPane.setCenter(anchorPaneEmp);
			((MainController) viewLoader.getController()).setStage(primaryStage);
			EmployeeController empControler = viewLoaderEmp.getController();
			((MainController) viewLoader.getController()).setStage(primaryStage);
			((MainController) viewLoader.getController()).setEmployeeFXML(viewLoaderEmp);
			// źródło danych
			empControler.setEmployees(new EmployeesDAL().getEmployees());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void primaryStage_CloseRequest(WindowEvent e) {
		try {
			OraConn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	private void primaryStage_Hiding(WindowEvent e) {
		Optional<ButtonType> result = AlertBox.showAndWait(AlertType.CONFIRMATION, "Konczenie pracy",
				"Czy chcesz zamknac program?");
		if (result.orElse(ButtonType.CANCEL) != ButtonType.OK)
			e.consume();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
