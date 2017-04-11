package views.code;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage stage;
	private BorderPane mainPane;
	private WelcomePane welcomePane;
	private AskPane askPane;
	private CalendarPane calendarPane;
	private int classe;

	@Override
	public void start(Stage primaryStage) {
		this.stage = primaryStage;
		this.stage.setTitle("ISBS Calendar");
		this.classe = 0;

		initLayouts();
		showMain();
		showWelcome();
	}

	private void initLayouts() {
		// Loaders
		FXMLLoader loaderMain = new FXMLLoader();
		loaderMain.setLocation(Main.class.getResource("../fxml/MainPane.fxml"));

		try {
			mainPane = (BorderPane) loaderMain.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		welcomePane = new WelcomePane(this);
		askPane = new AskPane(this);
		calendarPane = new CalendarPane(this);
	}

	private void showMain() {
		Scene scene = new Scene(mainPane);
		stage.setScene(scene);
		stage.show();
	}

	public void showWelcome() {
		mainPane.setCenter(welcomePane.getPane());
	}

	public void showAsk() {
		mainPane.setCenter(askPane.getPane());
	}

	public void showAgenda() {
		mainPane.setCenter(calendarPane.getPane());
	}

	public static void main(String[] args) {
		launch(args);
	}

	public BorderPane getMainPane() {
		return mainPane;
	}

	public void setMainPane(BorderPane mainPane) {
		this.mainPane = mainPane;
	}

	public WelcomePane getWelcomePane() {
		return welcomePane;
	}

	public AskPane getAskPane() {
		return askPane;
	}

	public CalendarPane getCalendarPane() {
		return calendarPane;
	}

	public int getClasse() {
		return classe;
	}
	
	public void setClasse(int classe){
		this.classe = classe;
	}
	
	public Stage getStage(){
		return this.stage;
	}
	
}
