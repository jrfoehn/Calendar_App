package controllers;

import javafx.fxml.FXML;
import views.code.Main;

public class WelcomeController {
	private Main main;
	
	public WelcomeController(){}
	
	@FXML
	private void next(){
		main.showAsk();
	}
	
	
	public void setMain(Main main){
		this.main=main;
	}

}
