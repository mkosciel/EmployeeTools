package mvc.employee;

import java.io.IOException;

import javafx.fxml.FXMLLoader;

public class ViewLoader<T, U> {
	private T fxmlLayout = null;
	private U fxmlController = null;

	public ViewLoader(String fxml) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
			fxmlLayout = fxmlLoader.load();
			fxmlController = fxmlLoader.getController();
		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	public T getLayout() {
		return fxmlLayout;
	}

	public U getController() {
		return fxmlController;
	}

}
