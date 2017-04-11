package views.code;

import java.io.IOException;

import controllers.WelcomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class WelcomePane{
	private Main main;
	private Parent parent;
	public WelcomePane(Main main){
		super();
		this.main = main;
		
		initView();
	}
	
	private void initView(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(WelcomePane.class.getResource("../fxml/Welcome.fxml"));
		
		try {
			parent = (AnchorPane) loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		WelcomeController controller = loader.getController();
		controller.setMain(main);
	}
	
	public Parent getPane(){
		return parent;
	}
	
	

}
