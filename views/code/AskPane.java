package views.code;

import java.io.IOException;

import controllers.AskController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class AskPane {
	private Main main;
	private Parent parent;
	public AskPane(Main main){
		super();
		this.main = main;
		
		initView();
	}
	
	private void initView(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(WelcomePane.class.getResource("../fxml/AskPane.fxml"));
		
		try {
			parent = (Parent) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		AskController controller = loader.getController();
		controller.setMain(main);
	}
	
	public Parent getPane(){
		return parent;
	}
}
